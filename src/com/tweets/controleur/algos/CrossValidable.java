package com.tweets.controleur.algos;

public abstract class CrossValidable extends Classifier {


	protected TweetPool tweetPool;
	
	protected String positivePath, negativePath;

	public CrossValidable ( TweetPool tweetPool ) {
		this.tweetPool = tweetPool;
	}
	
	public CrossValidable ( String positivePath, String negativePath ) {
		this.positivePath = positivePath;
		this.negativePath = negativePath;
	}
	
	public void setTweetPool ( TweetPool newTweetPool ) {
		this.tweetPool = newTweetPool;
	}
	
	public boolean isCrossValidable () {
		return true;
	}

}
