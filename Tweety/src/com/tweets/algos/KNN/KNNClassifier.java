package com.tweets.algos.KNN;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tweets.algos.CrossValidable;
import com.tweets.algos.TweetPool;
import com.tweets.clean.Feeling;
import com.tweets.clean.Tweet;


public class KNNClassifier extends CrossValidable {

	private int nbNeighbors;

	 
	public KNNClassifier (TweetPool tweetPool, int nbNeighbors ) {
		super( tweetPool );
		this.nbNeighbors = nbNeighbors;
	}

	/**
	 * donne la distance entre deux tweets.
	 * 
	 * @return la distance entre tweet1 et tweet2
	 */
	public int distance ( String tweet1, String tweet2 ) {
		List< String > msg1 = Arrays.asList( tweet1.split( " " ) );
		List< String > msg2 = Arrays.asList( tweet2.split( " " ) );
		int totalNbOfWords = msg1.size() + msg2.size();
		int commonNbOfWords = 0;

		for ( String word : msg1 ) {
			if ( msg2.contains( word ) ) {
				commonNbOfWords++;
			}
		}

		return totalNbOfWords - ( 2 * commonNbOfWords )/totalNbOfWords;
	}

	public Feeling classifies ( String msg ) {
		int nb = this.nbNeighbors;
		List< Tweet > tweets = new ArrayList< Tweet >( this.tweetPool.tweets() );
		DTCouple[] neighbors = new DTCouple[ nb ];
		int maxIndex = 0;

		for ( int i = 0; i < nb; i++ ) {
			Tweet tweet = tweets.get( i );
			neighbors[ i ] = new DTCouple( this.distance( msg, tweet.getMsg() ), tweet );
			if ( neighbors[ i ].getDistance() > neighbors[ maxIndex ].getDistance() ) {
				maxIndex = i;
			}
		}

		for ( int i = nb; i < tweets.size(); i++ ) {
			Tweet tweet = tweets.get( i );
			int distance = this.distance( msg, tweet.getMsg() );

			if ( distance < neighbors[ maxIndex ].getDistance() ) {
				neighbors[ maxIndex ] = new DTCouple( distance, tweet );

				// cherche l'index max
				for ( int k = 0; k < neighbors.length; k++ ) {
					if ( neighbors[ k ].getDistance() > neighbors[ maxIndex ].getDistance() ) {
						maxIndex = k;
					}
				}
			}
		}

		int cptPositif = 0;
		int cptNegatif = 0;
		int cptNeutre = 0;

		for ( int i = 0; i < nb; i++ ) {
			Feeling feeling = neighbors[ i ].getTweet().getFeeling();

			if ( feeling == Feeling.Positif ) {
				cptPositif++;
			} else if ( feeling == Feeling.Negatif ) {
				cptNegatif++;
			} else {
				cptNeutre++;
			}
		}

		if ( ( cptNeutre >= cptPositif ) && ( cptNeutre >= cptNegatif ) ) {
			return Feeling.Neutre;
		} else if ( cptPositif > cptNegatif ) {
			return Feeling.Positif;
		} else {
			return Feeling.Negatif;
		}
	}
	
	@Override
	public String toString () {
		return "KNN";
	}


	// Class representant le couple : ( Distance, Tweet )
	private class DTCouple {

		private int distance;

		private Tweet tweet;

		public DTCouple ( int distance, Tweet tweet ) {
			this.distance = distance;
			this.tweet = tweet;
		}

		public double getDistance () {
			return this.distance;
		}

		public Tweet getTweet () {
			return this.tweet;
		}

		public String toString () {
			return "(" + this.distance + ", " + this.tweet + ")";
		}

	}

}
