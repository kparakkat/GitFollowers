package com.gitfollowers.service;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.gitfollowers.pojo.GitFollower;

@Service
public class GitFollowerService {

	@Autowired
	RestTemplate restTemplate;

	@Async
	public CompletableFuture<List<GitFollower>> getFollowers(String followerURL) {
		List<GitFollower> actualResponse = null;
		GitFollower[] response = restTemplate.getForObject(followerURL, GitFollower[].class);
		if (response != null && response.length >= 0) {
			actualResponse = Arrays.asList(response).stream().limit(5).collect(Collectors.toList());
		}
		return CompletableFuture.completedFuture(actualResponse);
	}

}
