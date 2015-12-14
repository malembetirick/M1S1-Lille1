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
public class ApprentissagePanel extends AbstraitPanel {
	
	public static final String CARD_LEARNING =  "Panel d'apprentissage";
    public static final Color LEARNING_COLOR = new Color(179,205,224);
    public static final Color LEARNING_COLOR_BRIGHTER = new Color(235,218,218);
    
    private RecherchePanel b;
	private ApprentissageListeTweetPanel c;

	public ApprentissagePanel(Controleur controle) {
		super(controle);
		this.setBackground( ApprentissagePanel.LEARNING_COLOR );
		this.b = new RecherchePanel( controle, ApprentissagePanel.LEARNING_COLOR );
		this.c = new ApprentissageListeTweetPanel( controle, ApprentissagePanel.LEARNING_COLOR );
		JScrollPane scrollContainer = new JScrollPane( this.c );
		scrollContainer.getVerticalScrollBar().setUnitIncrement(10);
		scrollContainer.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_NEVER );
		scrollContainer.setBackground( ApprentissagePanel.LEARNING_COLOR );
		scrollContainer.setBorder( new EmptyBorder( 0, 25, 25, 25 ) );
		
		this.add( this.b, BorderLayout.NORTH );
		this.add( scrollContainer, BorderLayout.CENTER );
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public void update(Observable o, Object arg) {
		this.c.update( o, arg );
		
	}

	@Override
	public void resume() {
		this.b.resume();
		
	}

	@Override
	public void clear() {
		this.b.clear();
		this.c.clear();
		
	}
	public String toString () {
		return ApprentissagePanel.CARD_LEARNING;
	}

}
