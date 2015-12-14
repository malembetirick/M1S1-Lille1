/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.tweets.controleur.principal.Controleur;




/**
 * @author malem
 *
 */
public class NBTweetsPanel extends JPanel{
	
	private Controleur cont;
	private JLabel j;
	private JComboBox<Integer> s;
	private final static Integer[] NB_DISPO = { 10, 25, 50, 100 };
	
	public NBTweetsPanel(Controleur cont, Color c){
		super();
		this.setBackground( c );
		
		this.setLayout( new BorderLayout() );
		
		this.cont = cont;
		this.j = new JLabel( "Nombre de tweets par recherche :" );
		this.s = new JComboBox<Integer>( NBTweetsPanel.NB_DISPO );
		this.s.setSelectedIndex( 1 );
		
		this.j.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		this.s.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );

		this.add( this.j, BorderLayout.WEST );
		this.add( this.s, BorderLayout.EAST );
		
		this.initListeners();
	}
	private void initListeners () {
		this.s.addActionListener( new SListener() );
	}
	public Dimension getPreferredSize () {
		return new Dimension( 400, 50 );
	}
	public Dimension getMaximumSize () {
		return new Dimension( 400, 80 );
	}
	class SListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			@SuppressWarnings ( "unchecked" )
            JComboBox<Integer> s = ( JComboBox<Integer> ) e.getSource();
			Integer NBTweet = ( Integer ) s.getSelectedItem();
			NBTweetsPanel.this.cont.setNbTweets(NBTweet );
		}
	}

}
