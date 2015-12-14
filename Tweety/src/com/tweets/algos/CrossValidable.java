package com.tweets.algos;


public abstract class CrossValidable extends Classifier {


	protected TweetPool tweetPool;


	public CrossValidable ( TweetPool tweetPool ) {
		this.tweetPool = tweetPool;
	}


	public void setTweetPool ( TweetPool newTweetPool ) {
		this.tweetPool = newTweetPool;
	}
	
	public boolean isCrossValidable () {
		return true;
	}

}
