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

import twitter4j.TwitterException;



/**
 * @author malem
 *
 */
public class ProxyPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controleur cont;
	private JLabel lab;
	private JComboBox<String> s;
	private final static String[] PROXY = { "Pas activé", "Activé" };
	
	public ProxyPanel(Controleur cont, Color c){
		super();
		this.setBackground( c );
		
		this.setLayout( new BorderLayout() );
		
		this.cont = cont;
		this.lab = new JLabel( "Utilisation du proxy de Lille 1 :" );
		this.s = new JComboBox<String>( ProxyPanel.PROXY );
		this.s.setSelectedIndex( 0 );
		
		this.lab.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );
		this.s.setFont( new Font( "Times new roman", Font.PLAIN, 16 ) );

		this.add( this.lab, BorderLayout.WEST );
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
		return new Dimension( 400, 50 );
	}
	class SListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			@SuppressWarnings ( "unchecked" )
            JComboBox<String> selectField = ( JComboBox<String> ) e.getSource();
			String proxyUse = ( String ) selectField.getSelectedItem();
			
			if ( proxyUse.equals( ProxyPanel.PROXY[ 1 ] ) ) {
				try {
					ProxyPanel.this.cont.setProxyTwitter();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} else {
				try {
					ProxyPanel.this.cont.SansProxyTwitter();
				} catch (TwitterException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
		}
	}
	

}
