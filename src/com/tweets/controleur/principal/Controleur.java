/**
 * 
 */
package com.tweets.controleur.principal;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import com.tweets.controleur.algos.Classifier;
import com.tweets.controleur.clean.Feeling;
import com.tweets.controleur.clean.Tweet;
import com.tweets.modele.Modele;

import twitter4j.TwitterException;



/**
 * @author malem
 *
 */
public class Controleur {
	
	protected Modele mod;
	
	private Preferences pref;
	
	private Classifier classify;
	
	public Controleur(Modele mod){
		
		this.mod = mod;
		this.pref = Preferences.userNodeForPackage(this.getClass());
		this.classify = this.mod.getClassifierById(pref.getInt("Dernier champ ouvert", 0));
	}
	
	public Classifier getRecentClassifier () {
		return this.classify;
	}
	
	public int getRecentClassifierId () {
		return this.pref.getInt( "Dernier champ ouvert", 0 );
	}
	public void setRecentClassifierId ( int id ) {
		this.classify = this.mod.getClassifierById( id );
		this.pref.putInt( "Dernier champ ouvert", id );
		try {
			this.pref.flush();
		} catch ( BackingStoreException e ) {
			System.out.println( "Erreur: utilisation des preferences" );
			e.printStackTrace();
		}
		
	}
	public int getNbTweets () {
		return this.mod.getTweetsNb();
	}
	public void setNbTweets (int val) {
		this.mod.setTweetsNb( val );
	}
	public void setProxyTwitter () throws TwitterException {
		this.mod.AvecProxyTwitter();
	}
	public void SansProxyTwitter () throws TwitterException {
		this.mod.SansProxyTwitter();
	}
	private String control ( String query ) {
		// les virgules ne sont pas permises dans la requete
		return query.replace( ',', ' ' );
	}
	public void setQuery ( String q ) {
		String cq = this.control( q );
		this.mod.recherche( cq );
	}
	
	public void classificationRequest ( Tweet tweet ) {
		this.mod.classifier( tweet, this.getRecentClassifier() );
	}
	
	public void saveRequest ( Tweet tweet, Feeling feeling ) {
		this.mod.saves( tweet, feeling );
	}
	public Image pieChartImageRequest ( List< Tweet > tweets ) throws MalformedURLException,
    IOException, IllegalArgumentException, TwitterException {
	if ( tweets.isEmpty() ) {
		throw new IllegalArgumentException( "Liste de tweets vide." );
	}
	
	return this.mod.genererCamembert( this.classify, tweets );
	
	
}
	
	public void SauvegarderRequete1 () {
		this.mod.SauvegarderCSV1();
	}
	public void SauvegarderRequete2 () {
		this.mod.SauvegarderCSV2();
	}
	public void SauvegarderRequete3 () {
		this.mod.SauvegarderCSV3();
	}
	public void SauvegarderDicoPositif () {
		this.mod.SauvegarderDicoPositif();
	}
	public void SauvegarderDicoNegatif () {
		this.mod.SauvegarderDicoNegatif();
	}
	public void evaluerRecentClassifier1 () {
		this.mod.evaluerBase1(this.getRecentClassifier());
	}
	public void evaluerRecentClassifier2 () {
		this.mod.evaluerBase2(this.getRecentClassifier());
	}
	public void evaluerRecentClassifier3 () {
		this.mod.evaluerBase3(this.getRecentClassifier());
	}
	

}
