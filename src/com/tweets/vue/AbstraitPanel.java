/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;

/**
 * @author malem
 *
 */
@SuppressWarnings("serial")
public abstract class AbstraitPanel extends JPanel implements Observer{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected Controleur controle;
	
	public AbstraitPanel(Controleur controle){
		super();
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.controle = controle;
	}
	public Dimension getPreferredSize () {
		return new Dimension( 800, 600 );
	}
		public abstract void update ( Observable o, Object arg );
			
			public abstract void resume ();
			
			public abstract void clear ();

}
