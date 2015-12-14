/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;




/**
 * @author malem
 *
 */
public class TweetsPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Tweet tweet;
	protected static int NB_PANEL_TWEET = 0;
	
	public TweetsPanel(Controleur cont, Tweet tw, Color c){
		super();
		if ( TweetsPanel.NB_PANEL_TWEET++ % 2 == 0 ) {
			this.setBackground( Color.WHITE );
		}else{
			this.setBackground( c );
		}
this.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
		
		this.setLayout( new BorderLayout() );
				
		this.tweet = tw;
		JLabel idt = new JLabel("<html>"+ this.tweet.getId() + "</html>");
		idt.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		
		JLabel usernameComputed = new JLabel( "@" + this.tweet.getTweet() );
		usernameComputed.setFont( new Font( "Times new roman", Font.BOLD, 16 ) );
		
		JLabel messageComputed = new JLabel( "<html>" + this.tweet.getMsg() + "</html>" );
		messageComputed.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		
		JLabel dateCreation = new JLabel("<html>"+ this.tweet.getDate() + "</html>");
		dateCreation.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		
		JLabel requete = new JLabel("<html>"+ this.tweet.getQuery() + "</html>");
		requete.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		
		JLabel feelingComputed = new JLabel( this.tweet.getFeeling().toString() + "  " );
		feelingComputed.setFont( new Font( "Times new roman", Font.BOLD, 50 ) );
		
		this.add( usernameComputed, BorderLayout.NORTH );
		//this.add( usernameComputed, BorderLayout.LINE_START);
		this.add( messageComputed, BorderLayout.CENTER);
		this.add( dateCreation, BorderLayout.WEST );
		//this.add( requete, BorderLayout.EAST );
		this.add( feelingComputed, BorderLayout.EAST );
	}
	public Tweet getTweet () {
		return this.tweet;
	}
	public Dimension getPreferredSize () {
		return new Dimension( 900, 200 );
	}
	public Dimension getMaximumSize () {
		return new Dimension( 1400, 800 );
	}

}
