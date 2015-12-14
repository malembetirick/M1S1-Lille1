package com.tweets.algos.dictionnaire;



import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tweets.algos.Classifier;
import com.tweets.clean.Feeling;


public class DictionaryClassifier extends Classifier {

	
	private String positivePath;

	private String negativePath;

	public DictionaryClassifier ( String positivePath, String negativePath ) {
		this.positivePath = positivePath;
		this.negativePath = negativePath;
	}

	// lire le contenu d'un fichier
	private String fileToString ( String path ) {
		StringBuffer res = new StringBuffer();
		File file = new File( path );

		if ( file.exists() && !file.isDirectory() ) {
			try {
				BufferedReader br = new BufferedReader( new FileReader( path ) );
				String line = "";

				while ( ( line = br.readLine() ) != null ) {
					res.append( line );
				}

				br.close();
			} catch ( FileNotFoundException e ) {
				System.out.println( "Erreur: fichier " + path + " introuvable" );
				e.printStackTrace();
			} catch ( IOException e ) {
				e.printStackTrace();
			}
		}

		return res.toString();
	}

	
	private String addBackSlash ( String s ) {
		String metachars = "()[]";
		StringBuffer buf = new StringBuffer();

		for ( int i = 0; i < s.length(); i++ ) {
			String charRead = ( ( Character ) s.charAt( i ) ).toString();

			if ( metachars.contains( charRead ) ) {
				buf.append( "\\" + charRead );
			} else {
				buf.append( charRead );
			}
		}

		return buf.toString();
	}

	//Donner les sentiments d'un tweets par le biais du dictionnaire de mot-cles
	private Feeling msgDictionaryPolarize ( String msg, List< String > positiveWords,
	        List< String > negativeWords ) {
		int cpt = 0;

		for ( String positiveWord : positiveWords ) {
			if ( msg.matches( ".*\\b" + this.addBackSlash( positiveWord ) + "\\b.*" ) ) {
				cpt++;
			}
		}

		for ( String negativeWord : negativeWords ) {
			if ( msg.matches( ".*\\b" + this.addBackSlash( negativeWord ) + "\\b.*" ) ) {
				cpt--;
			}
		}

		if ( cpt < 0 ) {
			return Feeling.Negatif;
		} else if ( cpt > 0 ) {
			return Feeling.Positif;
		} else {
			return Feeling.Neutre;
		}
	}

	// Removes empty strings from a list of string
	private List< String > removeEmptyString ( List< String > ls ) {
		List< String > res = new ArrayList< String >();

		for ( String s : ls ) {
			String st = s.trim();

			if ( !st.isEmpty() ) {
				res.add( st );
			}
		}

		return res;
	}

	@Override
	public Feeling classifies ( String msg ) {
		
		List< String > positiveWords =
		        this.removeEmptyString( Arrays.asList( this.fileToString( this.positivePath )
		                .split( "," )) );
		List< String > negativeWords =
		        this.removeEmptyString( Arrays.asList( this.fileToString( this.negativePath )
		                .split( "," ) ) );

		return msgDictionaryPolarize( msg, positiveWords, negativeWords );
	}
	
	public String toString () {
		return "Dictionnaire";
	}
}
