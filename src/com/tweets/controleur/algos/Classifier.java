package com.tweets.controleur.algos;

import com.tweets.controleur.clean.Feeling;

public abstract class Classifier {

	/**
	 * Classifier un tweet.
	 * 
	 * @param msg
	 *            analyse les messages, et donne la polarité
	 * @return sentiments des tweets
	 */
	public abstract Feeling classifies ( String msg );

	
	public boolean isCrossValidable () {
		return false;
	}

}
