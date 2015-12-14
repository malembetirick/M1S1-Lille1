/**
 * 
 */
package com.tweets.vue;

import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JPanel;

import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class TweetyVue extends JPanel implements Observer{

	private AppliPanel app;
	private Controleur cont;
	
	public TweetyVue(Controleur cont){
		
		this.app = new AppliPanel(cont);
		this.cont = cont;
		
		WindowListener exitListener = new WindowAdapter () {
			@Override
			public void windowClosing ( WindowEvent e ) {
				TweetyVue.this.cont.SauvegarderRequete2();
				System.exit(0);
			}
		};
        
		
		
	}
	public Dimension getPreferredSize () {
		return new Dimension( 1400, 800 );
	}
	public Dimension getMaximumSize () {
		return new Dimension( 1400, 800 );
	}

	
	@Override
	public void update(Observable arg0, Object arg1) {
		this.app.update( arg0, arg1 );
		
	}

}
