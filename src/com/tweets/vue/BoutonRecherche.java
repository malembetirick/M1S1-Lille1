/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

/**
 * @author malem
 *
 */
public class BoutonRecherche extends JButton{

	public BoutonRecherche(){
		super( "Rechercher" );		
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		
		this.setOpaque( true );
	}
	public Dimension getPreferredSize () {
		return new Dimension( 150, 100 );
	}
}
