/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;




/**
 * @author malem
 *
 */
public class MenuPanel extends JPanel{
	
	private MenuButton feelingsButton;
	private MenuButton tendenciesButton;
	private MenuButton learningButton;
	private MenuButton evaluationButton;
	private MenuButton settingsButton;
	private MenuButton currentButton;
	
	public MenuPanel ( Controleur controller ) {
		super();
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( -1, 0, -1, 0 ) );
		
		this.setLayout( new GridLayout( 0, 1 ) );

		this.feelingsButton = new MenuButton( "Sentiments", FeelingsPanel.CARD_FEELINGS, FeelingsPanel.FEELINGS_COLOR );
		this.tendenciesButton = new MenuButton( "Camembert", TendancesPanel.CARD_TENDENCIES, TendancesPanel.TENDENCIES_COLOR );
		this.learningButton = new MenuButton( "Apprentissage", ApprentissagePanel.CARD_LEARNING, ApprentissagePanel.LEARNING_COLOR );
		this.evaluationButton = new MenuButton( "Evaluer", EvaluerPanel.CARD_EVALUATION, EvaluerPanel.EVALUATION_COLOR );
		this.settingsButton = new MenuButton( "Parametres", ParametresPanel.CARD_SETTINGS, ParametresPanel.SETTINGS_COLOR );
		this.setRecentButton( this.feelingsButton );
		
		this.add( this.feelingsButton );
		this.add( this.tendenciesButton );
		this.add( this.learningButton );
		this.add( this.evaluationButton );
		this.add( this.settingsButton );
	}
	
	public MenuButton getRecentButton () {
		return this.currentButton;
	}
	
	public void setRecentButton ( MenuButton button ) {
		this.currentButton = button;
		this.unhighlightButtons();
		this.highlightCurrentButton();
	}
	
	public void unhighlightButtons () {
		this.feelingsButton.unhighlight();
		this.tendenciesButton.unhighlight();
		this.learningButton.unhighlight();
		this.evaluationButton.unhighlight();
		this.settingsButton.unhighlight();
	}
	
	public void highlightCurrentButton () {
		this.getRecentButton().highlight();
	}
	
	@Override
	public Dimension getPreferredSize () {
		return new Dimension( 200, 200 );
	}

}
