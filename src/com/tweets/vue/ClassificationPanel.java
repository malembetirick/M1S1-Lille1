/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;




/**
 * @author malem
 *
 */
public class ClassificationPanel extends JPanel{
	
	private ClassificationBouton NonAnnoteButton;
	private ClassificationBouton negatifButton;
	private ClassificationBouton neutreButton;
	private ClassificationBouton positifButton;
	
	public ClassificationPanel ( Controleur cont ) {
		super();
		
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.NonAnnoteButton = new ClassificationBouton( cont, "Non Annote", com.tweets.controleur.clean.Feeling.NonPolarise );
		this.negatifButton = new ClassificationBouton( cont, "Negatif", com.tweets.controleur.clean.Feeling.Negatif );
		this.neutreButton = new ClassificationBouton( cont, "Neutre", com.tweets.controleur.clean.Feeling.Neutre );
		this.positifButton = new ClassificationBouton( cont, "Positif", com.tweets.controleur.clean.Feeling.Positif );
		
		this.add( this.NonAnnoteButton );
		this.add( this.negatifButton );
		this.add( this.neutreButton );
		this.add( this.positifButton );
	}
	public void unhighlightButtons () {
		this.NonAnnoteButton.unhighlight();
		this.negatifButton.unhighlight();
		this.neutreButton.unhighlight();
		this.positifButton.unhighlight();
	}
	public Dimension getPreferredSize () {
		return new Dimension( 500, 45 );
	}

}
