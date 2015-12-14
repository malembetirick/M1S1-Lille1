/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.util.Observable;

import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;







/**
 * @author malem
 *
 */
public class FeelingsPanel extends AbstraitPanel{
	
	public static final String CARD_FEELINGS =  "Panneau d'analyse de sentiments";
    public static final Color FEELINGS_COLOR = new Color( 0x0E83CD );
    public static final Color FEELINGS_COLOR_BRIGHTER = new Color( 0xC3E0F2 );
    
    private RecherchePanel rp;
	private ListeTweetsPanel ltp;

	public FeelingsPanel(Controleur controle) {
		super(controle);
		this.setBackground( FeelingsPanel.FEELINGS_COLOR );
		this.rp = new RecherchePanel( controle, FeelingsPanel.FEELINGS_COLOR );
		this.ltp = new ListeTweetsPanel( controle, FeelingsPanel.FEELINGS_COLOR );		
		
		JScrollPane scrollContainer = new JScrollPane( this.ltp );
		scrollContainer.getVerticalScrollBar().setUnitIncrement(10);
		scrollContainer.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollContainer.setBackground( FeelingsPanel.FEELINGS_COLOR );
		scrollContainer.setBorder( new EmptyBorder( 0, 25, 25, 25 ) );
		
		this.add( this.rp, BorderLayout.NORTH );
		this.add( scrollContainer, BorderLayout.CENTER );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
		this.ltp.update( o, arg );
		
	}

	@Override
	public void resume() {
		this.rp.resume();
		
	}

	@Override
	public void clear() {
		this.rp.clear();
		this.ltp.clear();
		
	}
	public String toString () {
		return FeelingsPanel.CARD_FEELINGS;
	}

}
