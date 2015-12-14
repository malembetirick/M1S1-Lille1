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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class RecherchePanel extends JPanel{
	
	private Controleur cont;
	private ChampRecherche cr;
	private JLabel cl;
	private BoutonRecherche br;
	
	public RecherchePanel ( Controleur cont, Color color ) {
		super();	
		this.setBackground( color );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		
	    this.setLayout( new BorderLayout() );

		this.cont = cont;
	    
		this.cr = new ChampRecherche();
		this.br = new BoutonRecherche();
		
		this.cl = new JLabel( "avec " + this.cont.getRecentClassifier().toString(), SwingConstants.RIGHT );
		this.cl.setBackground( Color.WHITE );
		this.cl.setForeground( new Color( 0x2F3238 ) );
		this.cl.setFont( new Font( "Times new roman", Font.BOLD, 18 ) );
		this.cl.setBorder( new CompoundBorder(
			BorderFactory.createMatteBorder( 0, 0, 0, 20, color ),
			BorderFactory.createMatteBorder( 0, 0, 0, 20, Color.green )
		) );
		this.cl.setOpaque( true );
		
		this.add( this.cr, BorderLayout.WEST );
		this.add( this.cl, BorderLayout.CENTER );
		this.add( this.br, BorderLayout.EAST );
		
		this.initListeners();
	}
	
	private void initListeners () {
		BoutonRechercheListener searchButtonListener = new BoutonRechercheListener();
		this.br.addActionListener( searchButtonListener );
	}
	
	class BoutonRechercheListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {
			RecherchePanel.this.cont.setQuery( RecherchePanel.this.br.getText() );
		}
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 800, 100 );
	}
	
	public void clear () {
		this.br.setText( "" );
	}
	
	public void resume () {
		this.cl.setText( "avec " + this.cont.getRecentClassifier().toString() );
	}
}
