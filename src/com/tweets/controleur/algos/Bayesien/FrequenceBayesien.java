/**
 * 
 */
package com.tweets.controleur.algos.Bayesien;

import java.util.List;

import com.tweets.controleur.algos.TweetPool;
import com.tweets.controleur.clean.Feeling;



/**
 * @author malembeti
 *
 */
public class FrequenceBayesien extends Bayesien{

	public FrequenceBayesien(TweetPool tweetPool, Boolean simplified,
							 List<Integer> degrees) {
		super(tweetPool, simplified, degrees);
		
	}
	// Fournit le nombre d'occurences de n-grammes representant le nombre de mots de 0 à n-1 elements.
		private int nbOccurenceOfNGrammeInMsg (NGramme ng, String msg ) {
			int res = 0;

			for ( NGramme nGramme : NGramme.buildNGrammesFrom( msg, ng.getDegree() ) ) {
				if ( nGramme.equals( ng ) ) {
					res++;
				}
			}

			return res;
		}
	@Override
	protected double probaTweetHasFeeling(Feeling feeling, String msg) {
		double res = 1;
		List< NGramme > nGrammes = this.getNGrammesListFrom( msg );

		for ( NGramme nGramme : nGrammes ) {
			int nb = this.nbOccurenceOfNGrammeInMsg( nGramme, msg );
			res *= Math.pow( this.probaNGrammeForFeeling( nGramme, feeling ), nb );
		}

		return res;
	}
	
	@Override
	public String toString () {
		return "Algorithme Bayesien par fréquence";
	
	}
}
