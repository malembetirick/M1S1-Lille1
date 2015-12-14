/**
 * 
 */
package com.tweets.vue;

import java.awt.CardLayout;
import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;

/**
 * @author malem
 *
 */
public class ContenuPanel extends JPanel implements Observer{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private FeelingsPanel feelingsPanel;
	private TendancesPanel tendenciesPanel;
	private ApprentissagePanel learningPanel;
	private EvaluerPanel evaluationPanel;
	private ParametresPanel settingsPanel;
	private AbstraitPanel currentPanel;
	
	public ContenuPanel ( Controleur controller ) {
		super();		
		this.setBackground( Color.WHITE );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new CardLayout() );
				
		this.feelingsPanel = new FeelingsPanel( controller );
		this.tendenciesPanel = new TendancesPanel( controller );
		this.learningPanel = new ApprentissagePanel( controller );
		this.evaluationPanel = new EvaluerPanel( controller );
		this.settingsPanel = new ParametresPanel( controller );
		this.currentPanel = this.feelingsPanel;
		
		this.add( this.feelingsPanel, FeelingsPanel.CARD_FEELINGS );
		this.add( this.tendenciesPanel, TendancesPanel.CARD_TENDENCIES );
		this.add( this.learningPanel, ApprentissagePanel.CARD_LEARNING );
		this.add( this.evaluationPanel, EvaluerPanel.CARD_EVALUATION );
		this.add( this.settingsPanel, ParametresPanel.CARD_SETTINGS );
	}

	public AbstraitPanel getRecentPanel () {
		return this.currentPanel;
	}
	
	public void setRecentPanel ( AbstraitPanel newPanel ) {
		System.out.println( "Panel de contenu: " + newPanel.toString() );
		this.getRecentPanel().clear();
		this.currentPanel = newPanel;
	}
	
	@Override
	public void update ( Observable o, Object arg ) {
		this.getRecentPanel().update( o, arg );
	}
	
	public AbstraitPanel getPanelByName ( String cardName ) {	
		if ( cardName.equals( FeelingsPanel.CARD_FEELINGS ) ) {
			return this.feelingsPanel;
		} else if ( cardName.equals( TendancesPanel.CARD_TENDENCIES ) ) {
			return this.tendenciesPanel;
		} else if ( cardName.equals( ApprentissagePanel.CARD_LEARNING ) ) {
			return this.learningPanel;
		} else if ( cardName.equals( EvaluerPanel.CARD_EVALUATION ) ) {
			return this.evaluationPanel;
		} else {
			return this.settingsPanel;
		}
	}
}
