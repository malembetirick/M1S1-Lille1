/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 * @author malem
 *
 */
public class ChampRecherche extends JTextField{
	
	public ChampRecherche(){
		super();
		this.setBackground( Color.WHITE );
		this.setForeground( new Color( 0x2F3238 ) );
		this.setBorder( new EmptyBorder( 0, 20, 0, 20 ) );
		this.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
	}
	public Dimension getPreferredSize () {
		return new Dimension( 280, 100 );
	}

}
