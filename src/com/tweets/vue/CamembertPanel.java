/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

/**
 * @author malem
 *
 */
public class CamembertPanel extends JPanel{
	
	private Image img;
	
	public CamembertPanel(Image img, Color c){
		this.img = img;
		this.setBackground(c);
		
	}
	protected void paintComponent(Graphics g) {
        super.paintComponent( g );
        g.drawImage( this.img, 0, 0, null );         
    }
	public Dimension getPreferredSize () {
		return new Dimension( 900, 600 );
	}
	

}
