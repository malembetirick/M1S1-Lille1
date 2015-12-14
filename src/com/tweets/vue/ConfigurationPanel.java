/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class ConfigurationPanel extends JPanel{
	
	private Controleur cont;
	
	private JLabel lab;
	
	private JRadioButton[] rd;
	
	private ButtonGroup bg;
	
	public ConfigurationPanel(Controleur cont, Color col){
		super();
		this.setBackground(col);
		this.setLayout(new GridLayout(0,1));
		this.cont = cont;
		this.lab = new JLabel("Les Algorithmes de classification implementees:");
		this.rd = new JRadioButton[15];
		this.rd[0] = new JRadioButton("Dictionnaire de mots-cles");
		this.rd[1] = new JRadioButton("KNN a 5 voisins");
		this.rd[2] = new JRadioButton("KNN a 10 voisins");
		this.rd[3] = new JRadioButton("Bayesien Presence unigramme non-simplifie");
		this.rd[4] = new JRadioButton("Bayesien Presence unigramme simplifie");
		this.rd[5] = new JRadioButton("Bayesien Presence bigramme non-simplifie");
		this.rd[6] = new JRadioButton("Bayesien Presence bigramme simplifie");
		this.rd[7] = new JRadioButton("Bayesien Presence uniEtBigramme non-simplifie");
		this.rd[8] = new JRadioButton("Bayesien Presence uniEtBigramme simplifie");
		this.rd[9] = new JRadioButton("Bayesien Frequence unigramme non-simplifie");
		this.rd[10] = new JRadioButton("Bayesien Frequence unigramme simplifie");
		this.rd[11] = new JRadioButton("Bayesien Frequence bigramme non-simplifie");
		this.rd[12] = new JRadioButton("Bayesien Frequence bigramme simplifie");
		this.rd[13] = new JRadioButton("Bayesien Frequence uniEtBigramme non-simplifie");
		this.rd[14] = new JRadioButton("Bayesien Frequence uniEtBigramme simplifie");
		
		this.bg = new ButtonGroup();
		this.rd[this.cont.getRecentClassifierId()].setSelected(true);
		this.lab.setFont(new Font("Times new roman",Font.BOLD,16));
		this.add(this.lab);
		
		for ( int i = 0; i < this.rd.length; i++ ) {
			this.rd[ i ].setFont( new Font( "Times new roman", Font.BOLD, 16 ) );
			this.bg.add( this.rd[ i ] );
			this.add( this.rd[ i ] );
		}

		this.initListeners();
	}

	private void initListeners () {
		for ( int i = 0; i < this.rd.length; i++ ) {
			this.rd[ i ].addActionListener( new RadioButtonListener( i ) );
		}
	}
	public Dimension getPreferredSize () {
		return new Dimension( 900, 400 );
	}
	public Dimension getMaximumSize () {
		return new Dimension( 1400, 800 );
	}
	class RadioButtonListener implements ActionListener {

		private int au;

		public RadioButtonListener ( int au ) {
			this.au = au;
		}

		public void actionPerformed ( ActionEvent e ) {
			ConfigurationPanel.this.cont.setRecentClassifierId( this.au );
		}
	}
	}

