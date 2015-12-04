package com.tweets.algos.Bayesien;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 
 */

/**
 * @author malembeti
 *
 */
public class NGramme {

	private int n;
	
	private String[] words;

	public NGramme ( int n, String[] words ) throws IllegalArgumentException {
		if ( words.length != n ) {
			throw new IllegalArgumentException( "This n-gramme has to contain " + n + " words" );
		} else {
			this.n = n;
			this.words = words;
		}
	}
	
	public String[] getWords () {
		return this.words;
	}
	
	public int getDegree () {
		return this.n;
	}

	@Override
	public int hashCode () {
		final int prime = 31;
		int result = 1;
		result = prime * result + n;
		result = prime * result + Arrays.hashCode( words );
		return result;
	}

	@Override
	public boolean equals ( Object obj ) {
		if ( this == obj ) return true;
		if ( obj == null ) return false;
		if ( getClass() != obj.getClass() ) return false;
		NGramme other = ( NGramme ) obj;
		if ( n != other.n ) return false;
		if ( !Arrays.equals( words, other.words ) ) return false;
		return true;
	}

	@Override
	public String toString () {
		StringBuffer sb = new StringBuffer();
		for ( String word : this.words ) {
			sb.append( word + "    " );
		}
		return sb.toString();
	}
	
	public static List< NGramme > buildNGrammesFrom ( String msg, int n )
	        throws IllegalArgumentException {
		String[] words = msg.split( " " );

		if ( words.length < n ) {
			return buildNGrammesFrom( msg, n - 1 );
		} else {
			List< NGramme > res = new ArrayList< NGramme >();

			for ( int i = 0; i <= words.length - n; i++ ) {
				String[] w = new String[ n ];
				
				for ( int j = i; j < i + n; j++ ) {
					w[ j - i ] = words[ j ];
				}
				
				NGramme nGramme = new NGramme( n, w );
				res.add( nGramme );
			}

			return res;
		}
	}


}
