package com.tweets.controleur.algos.Bayesien;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.tweets.controleur.algos.CrossValidable;
import com.tweets.controleur.algos.TweetPool;
import com.tweets.controleur.clean.Feeling;
import com.tweets.controleur.clean.Tweet;



/**
 * 
 */

/**
 * @author malembeti
 *
 */
public abstract class Bayesien extends CrossValidable {
	
	private final int WORD_LENGTH_MIN = 3;
	
	protected Boolean simplified;

	/**
	 * Liste les degres de ngrammes (le nombre de mots) consideres.
	 */
	protected List< Integer > degrees;

	
	public Bayesien (TweetPool tweetPool, Boolean simplified, List< Integer > degrees ) {
		super( tweetPool );
		this.simplified = simplified;
		this.degrees = degrees;
	}

	/**
	 * Fournit la probabilité d'un tweet.
	 * 
	 */
	public double probaFeeling ( Feeling feeling ) {
		double res = 0;
		double cpt = 0;

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			if ( tweet.getFeeling() == feeling ) {
				res++;
			}
			cpt++;
		}

		return res / cpt;
	}

	/**
	 * Donne la liste des n-grammes à considerer pour la classification.
	 * 
	 */
	protected List<NGramme> getNGrammesListFrom (String msg ) {
		List< NGramme > res = new ArrayList< NGramme >();

		for ( int degree : this.degrees ) {
			res.addAll( NGramme.buildNGrammesFrom( msg, degree ) );
		}

		return res;
	}

	
	private int nbOfNGrammeOfDegree ( int degree ) {
		Set< NGramme > set = new HashSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), degree ) ) {
				set.add( nGramme );
			}
		}

		return set.size();
	}

	private int nbOfNGrammesForFeeling ( Feeling feeling, int degree ) {
		Set< NGramme > set = new HashSet< NGramme >();

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), degree ) ) {
					set.add( nGramme );
				}
			}
		}

		return set.size();
	}

	
	private int nbOccurenceOfNGrammeForTheFeeling ( NGramme ng, Feeling feeling ) {
		int res = 0;

		for ( Tweet tweet : this.tweetPool.tweets() ) {
			if ( tweet.getFeeling() == feeling ) {
				for ( NGramme nGramme : NGramme.buildNGrammesFrom( tweet.getMsg(), ng.getDegree() ) ) {
					if ( nGramme.equals( ng ) ) {
						res++;
					}
				}
			}
		}

		return res;
	}

	/**
	 * Determine la classe la plus probable d'un nouveau tweet
	 * 
	 */
	protected double probaNGrammeForFeeling ( NGramme nGramme, Feeling feeling ) {
		int degree = nGramme.getDegree();

		return ( ( double ) ( this.nbOccurenceOfNGrammeForTheFeeling( nGramme, feeling ) + 1 ) )
		        / ( ( double ) ( this.nbOfNGrammesForFeeling( feeling, degree ) + this
		                .nbOfNGrammeOfDegree( degree ) ) );
	}

	
	protected String getAcceptedWords ( String msg ) {
		if ( this.simplified ) {
			StringBuffer bs = new StringBuffer();
			for ( String word : msg.split( " " ) ) {
				if ( word.length() > this.WORD_LENGTH_MIN ) {
					bs.append( word + " " );
				}
			}
			return bs.toString();
		} else {
			return msg;
		}
	}

	
	protected abstract double probaTweetHasFeeling ( Feeling feeling, String msg );

	@Override
	public Feeling classifies ( String msg ) {
		double pNegative =
		        this.probaTweetHasFeeling( Feeling.Negatif, this.getAcceptedWords( msg ) );
		double pPositive =
		        this.probaTweetHasFeeling( Feeling.Positif, this.getAcceptedWords( msg ) );
		double pNeutral = this.probaTweetHasFeeling( Feeling.Neutre, this.getAcceptedWords( msg ) );

		if ( ( pNeutral >= pPositive ) && ( pNeutral >= pNegative ) ) {
			return Feeling.Neutre;
		} else if ( pPositive > pNegative ) {
			return Feeling.Positif;
		} else {
			return Feeling.Positif;
		}
	}

	public String toString () {
		return "Bayes";
	}


}
