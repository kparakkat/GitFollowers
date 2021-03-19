package com.gitfollowers.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gitfollowers.pojo.GitFollower;
import com.gitfollowers.service.GitFollowerService;

@RestController
@RequestMapping("gitfollowers")
public class GitFollowersController {

	@Autowired
	GitFollowerService gitFollowerService;

	@Value("${operations.gitapiURL}")
	String serviceURL;

	@GetMapping(path = "/{githubId}", produces = { MediaType.APPLICATION_JSON_VALUE })
	public List<GitFollower> getGitFollowers(@PathVariable String githubId) throws Throwable {

		List<GitFollower> gitFollowers = null;
		String followerURL = serviceURL + githubId + "/followers";
		CompletableFuture<List<GitFollower>> gitFollowersFuture = gitFollowerService.getFollowers(followerURL);
		try {
			gitFollowers = new ArrayList<>(gitFollowersFuture.get().stream().collect(Collectors.toList()));
			if (gitFollowers != null) {
				List<CompletableFuture<List<GitFollower>>> parallelFutures = new ArrayList<>();
				for (GitFollower follower : gitFollowers) {
					parallelFutures.add(gitFollowerService.getFollowers(follower.getFollowers_url()));
				}

				int i = 0;
				for (GitFollower follower : gitFollowers) {
					follower.getFollowers().addAll(parallelFutures.get(i).get().stream().collect(Collectors.toList()));
					i++;
				}
			}
		} catch (Throwable e) {
			throw e.getCause();
		}

		return gitFollowers;
	}
}
