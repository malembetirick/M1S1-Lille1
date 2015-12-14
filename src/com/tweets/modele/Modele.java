/**
 * 
 */
package com.tweets.modele;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;


import com.tweets.controleur.algos.Classifier;
import com.tweets.controleur.algos.CrossValidable;
import com.tweets.controleur.algos.TweetPool;
import com.tweets.controleur.algos.Bayesien.FrequenceBayesien;
import com.tweets.controleur.algos.Bayesien.PresenceBayesien;
import com.tweets.controleur.algos.KNN.KNNClassifier;
import com.tweets.controleur.algos.dictionnaire.DictionaryClassifier;
import com.tweets.controleur.algos.statistiques.PieChart;
import com.tweets.controleur.algos.statistiques.ValidationCroisee;
import com.tweets.controleur.clean.Feeling;
import com.tweets.controleur.clean.MessageClean;
import com.tweets.controleur.clean.Tweet;


import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;






/**
 * @author malem
 *
 */
public class Modele extends Observable{
	
	private Twitter twitter;
	private TweetPool tweetPool;
	private MessageClean clean;
	private Classifier[] classify;
	private int nbTweets;
	
	//Proxy Lille1
		public void AvecProxyTwitter () throws TwitterException {
			
			ConfigurationBuilder conf = new ConfigurationBuilder();
			conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
			conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
			conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
			conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
			conf.setHttpProxyHost( "cache-etu.univ-lille1.fr" );
			conf.setHttpProxyPort( 3128 );

			this.twitter = new TwitterFactory( conf.build() ).getInstance();
			
			
		}
		
		public void SansProxyTwitter () throws TwitterException {
			ConfigurationBuilder conf = new ConfigurationBuilder();
			conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
			conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
			conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
			conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
			

			this.twitter = new TwitterFactory( conf.build() ).getInstance();
		}
	
		public Modele(){
			
			ConfigurationBuilder conf = new ConfigurationBuilder();
			conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
			conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
			conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
			conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
			conf.setHttpProxyHost( "cache-etu.univ-lille1.fr" );
			conf.setHttpProxyPort( 3128 );

			this.twitter = new TwitterFactory( conf.build() ).getInstance();
		
			//Twitter twitter = TwitterFactory.getSingleton();
			
		this.tweetPool = new TweetPool( "ressources/tweets2.csv" );
		this.clean = MessageClean.getInstance();
		this.classify = new Classifier[ 15 ];
		
		List< Integer > uni = new ArrayList< Integer >();
		uni.add( 1 );
		List< Integer > bi = new ArrayList< Integer >();
		bi.add( 2 );
		List< Integer > uniEtBi = new ArrayList< Integer >();
		uniEtBi.add( 1 );
		uniEtBi.add( 2 );
		
		//initialisation classifieur
		
		//Dictionnaire mots-cles
		
		this.classify[ 0 ] =
		        new DictionaryClassifier( "ressources/positive.txt", "ressources/negative.txt" );
		
		//KNN à 5 voisins
		this.classify[ 1 ] = new KNNClassifier( this.tweetPool, 5 );
		
		//KNN à 10 voisins
				this.classify[ 2 ] = new KNNClassifier( this.tweetPool, 10 );
				
				//Bayesien presence
				this.classify[ 3 ] = new PresenceBayesien( this.tweetPool, false, uni );
				this.classify[ 4 ] = new PresenceBayesien( this.tweetPool, true, uni );
				this.classify[ 5 ] = new PresenceBayesien( this.tweetPool, false, bi );
				this.classify[ 6 ] = new PresenceBayesien( this.tweetPool, true, bi );
				this.classify[ 7 ] = new PresenceBayesien( this.tweetPool, false, uniEtBi );
				this.classify[ 8 ] = new PresenceBayesien( this.tweetPool, true, uniEtBi );
				
				//Bayesien frequence
				this.classify[ 9 ] = new FrequenceBayesien( this.tweetPool, false, uni );
				this.classify[ 10 ] = new FrequenceBayesien( this.tweetPool, true, uni );
				this.classify[ 11 ] = new FrequenceBayesien( this.tweetPool, false, bi );
				this.classify[ 12 ] = new FrequenceBayesien( this.tweetPool, true, bi );
				this.classify[ 13 ] = new FrequenceBayesien( this.tweetPool, false, uniEtBi );
				this.classify[ 14 ] = new FrequenceBayesien( this.tweetPool, true, uniEtBi );
				
				//Nbre de Tweets par defaut
				this.nbTweets = 25;
		
	}
	
	
			
	//Recuperer nbre de Tweets Total
	public int getTweetsNb () {
		return this.nbTweets;
	}
	//Saisir le nombre de Tweets à recuperer
	public void setTweetsNb ( int newValue ) {
		this.nbTweets = newValue;
	}
	//Recuperer le classifieur par ID
	public Classifier getClassifierById ( int id ) {
		return this.classify[ id ];
	}
	//methode pour rechercher les Tweets
	public void recherche ( String r ) {
		Query q = new Query( r );
		QueryResult result = null;
		List< Tweet > res = new ArrayList< Tweet >();

		q.setLang( "fr" );
		q.setLocale("fr");
		
		q.setCount( this.nbTweets );

		try {
			result = this.twitter.search( q );

			
			while ( result.hasNext() && ( res.size() < this.nbTweets ) ) {
				List< Status > list = result.getTweets();
				int i = 0;

				while ( ( i < list.size() ) && ( res.size() < this.nbTweets ) ) {
					Status status = list.get( i );
					if ( !status.isRetweet() ) {
						res.add( new Tweet( status, r, Feeling.NonPolarise ) );
					}
					i++;
				}

				q = result.nextQuery();
				result = this.twitter.search( q );
			}

			this.setChanged();
			this.notifyObservers( res );
		} catch ( TwitterException e ) {
			System.out.println( "Erreur dans la recuperation des Tweets" );
			e.printStackTrace();
			System.exit( 0 );
		}
	}
	//Classifier les tweets en fonction du classifieur choisi
	public void classifier ( Tweet tweet, Classifier classify ) {
		tweet.setFeeling( classify.classifies( tweet.getMsg() ) );
	}
	public void saves ( Tweet tweet, Feeling feeling ) {
		this.clean.cleanTweet( tweet );
		String content = tweet.getMsg();

		
		if ( !content.trim().isEmpty() ) {
			tweet.setFeeling( feeling );
			this.tweetPool.add( tweet );
		}
	}
	//Generer le Camembert
	public Image genererCamembert ( Classifier classifier, List< Tweet > tweets )
	        throws MalformedURLException, IOException, TwitterException {
		
		return new PieChart( classifier, tweets.get( 0 ).getQuery(), tweets )
		        .getPieChartImage();
	}
	public void SauvegarderCSV1 () {
		this.tweetPool.writeCSV( "ressources/tweets.csv" );
	}
	public void SauvegarderCSV2 () {
		this.tweetPool.writeCSV( "ressources/tweets2.csv" );
	}
	public void SauvegarderCSV3 () {
		this.tweetPool.writeCSV( "ressources/tweets3.csv" );
	}
	public void SauvegarderDicoPositif () {
		this.tweetPool.writeCSV( "ressources/positive.txt" );
	}
	public void SauvegarderDicoNegatif () {
		this.tweetPool.writeCSV( "ressources/negative.txt" );
	}
	public void evaluerBase1 ( Classifier classify ) {
		if ( classify.isCrossValidable() ) {
			ValidationCroisee cv =
			        new ValidationCroisee( this.tweetPool, (CrossValidable) classify, 121 );
			Double res = cv.evaluer();
			this.setChanged();
			this.notifyObservers( res );
		}
	}
	public void evaluerBase2 ( Classifier classify ) {
		if ( classify.isCrossValidable() ) {
			ValidationCroisee cv =
			        new ValidationCroisee( this.tweetPool, (CrossValidable) classify, 17 );
			Double res = cv.evaluer();
			this.setChanged();
			this.notifyObservers( res );
		}
	}
	public void evaluerBase3 ( Classifier classify ) {
		if ( classify.isCrossValidable() ) {
			ValidationCroisee cv =
			        new ValidationCroisee( this.tweetPool, (CrossValidable) classify, 32 );
			Double res = cv.evaluer();
			this.setChanged();
			this.notifyObservers( res );
		}
	}


}
