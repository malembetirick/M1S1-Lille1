/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class SidebarPanel extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LogoPanel logoPanel;
	private MenuPanel menuPanel;
	
	public SidebarPanel ( Controleur controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
				
		this.logoPanel = new LogoPanel( controller );
		this.menuPanel = new MenuPanel( controller );
		
		this.add( this.logoPanel, BorderLayout.NORTH );
		this.add( this.menuPanel, BorderLayout.CENTER );
	}

	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 200, 600 );
	}

}
