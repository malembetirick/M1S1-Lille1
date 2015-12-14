/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class ApprentissageTweetPanel extends TweetsPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ClassificationPanel a;

	public ApprentissageTweetPanel(Controleur cont, Tweet tw, Color c) {
		super(cont, tw, c);
		this.a = new ClassificationPanel( cont );
		if ( TweetsPanel.NB_PANEL_TWEET % 2 == 0 ) {
			this.a.setBackground( c );
		} else {
			this.a.setBackground( Color.WHITE );
		}
		
		this.add( this.a, BorderLayout.SOUTH );
	}
	public Dimension getPreferredSize () {
		return new Dimension( 900, 145 );
	}
	public Dimension getMaximumSize () {
		return new Dimension( 1400, 800 );
	}

	

}
