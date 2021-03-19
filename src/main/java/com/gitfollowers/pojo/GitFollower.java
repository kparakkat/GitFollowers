package com.gitfollowers.pojo;

import java.util.ArrayList;
import java.util.List;

public class GitFollower {
	private String login;
	private String followers_url;
	
	private List<GitFollower> followers;

	public String getLogin() {
		return login;
	}

	public List<GitFollower> getFollowers() {
		if (followers == null) {
			followers = new ArrayList<>();
		}
		return followers;
	}

	public void setFollowers(List<GitFollower> followers) {
		this.followers = followers;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getFollowers_url() {
		return followers_url;
	}

	public void setFollowers_url(String followers_url) {
		this.followers_url = followers_url;
	}
}
