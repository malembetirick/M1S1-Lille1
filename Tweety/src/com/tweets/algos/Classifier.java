package com.tweets.algos;

import com.tweets.clean.Feeling;

public abstract class Classifier {

	/**
	 * Classifier un tweet.
	 * 
	 * @param msg
	 *            analyse les messages, et donne la polarité
	 * @return sentiments des tweets
	 */
	public abstract Feeling classifies ( String msg );

	
	private boolean isCrossValidable () {
		return false;
	}

}
