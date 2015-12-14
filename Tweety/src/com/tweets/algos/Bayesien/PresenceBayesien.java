/**
 * 
 */
package com.tweets.algos.Bayesien;

import java.util.List;

import com.tweets.algos.TweetPool;
import com.tweets.clean.Feeling;

/**
 * @author malembeti
 *
 */
public class PresenceBayesien extends Bayesien{

	public PresenceBayesien(TweetPool tweetPool, Boolean simplified,
							List<Integer> degrees) {
		super(tweetPool, simplified, degrees);
		
	}
	//Fournit la probabilité du sentiment d'un ensemble de tweets
	@Override
	protected double probaTweetHasFeeling(Feeling feeling, String msg) {
		double res = 1;
		List<NGramme> nGrammes = this.getNGrammesListFrom( msg );

		for ( NGramme nGramme : nGrammes ) {
			res *= this.probaNGrammeForFeeling( nGramme, feeling );
		}

		return res * this.probaFeeling( feeling );
	}

	public String toString () {
		return "Algorithme Bayesien par présence";
	}
}
