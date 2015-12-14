/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import com.tweets.controleur.principal.Controleur;

import twitter4j.TwitterException;


/**
 * @author malem
 *
 */
public class TendancesPanel extends AbstraitPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CARD_TENDENCIES =  "Panel des Tendances";
    public static final Color TENDENCIES_COLOR = new Color(123,192,67);
    
    private RecherchePanel d;
    private StatistiquesPanel e;

	public TendancesPanel(Controleur controle) {
		super(controle);
		this.setBackground( TendancesPanel.TENDENCIES_COLOR );
		this.d = new RecherchePanel( controle, TendancesPanel.TENDENCIES_COLOR );
		this.e = new StatistiquesPanel( controle, TendancesPanel.TENDENCIES_COLOR );
		
		this.add( this.d, BorderLayout.NORTH );
		this.add( this.e, BorderLayout.CENTER );
	}

	

	@Override
	public void update(Observable o, Object arg) {
		try {
			this.e.update( o, arg );
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public void resume() {
		this.d.resume();
		
	}
	public String toString () {
		return TendancesPanel.CARD_TENDENCIES;
	}

	@Override
	public void clear() {
		this.d.clear();
		this.e.clear();
		
	}

}
