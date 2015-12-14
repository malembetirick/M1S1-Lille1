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
public class LogoPanel extends JPanel{
	
	public LogoPanel ( Controleur controller ) {
		super();
		this.setBackground( new Color(179,205,224) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );		
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 200, 200 );
	}

}
