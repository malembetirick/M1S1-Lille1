/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class AppliPanel extends JPanel implements Observer{
	
	private SidebarPanel sidebarPanel;
	private ContenuPanel contenuPanel;
	
	public AppliPanel ( Controleur controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new BorderLayout() );
				
		this.sidebarPanel = new SidebarPanel( controller );
		this.contenuPanel = new ContenuPanel( controller );
		
		this.add( this.sidebarPanel, BorderLayout.WEST );
		this.add( this.contenuPanel, BorderLayout.CENTER );
	}
	
	public SidebarPanel getSidebarPanel () {
		return this.sidebarPanel;
	}
	
	public ContenuPanel getContentPanel () {
		return this.contenuPanel;
	}

	@Override
	public void update(Observable o, Object arg) {
		this.contenuPanel.update( o, arg );
		
	}

}
