/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class ListeTweetsPanel extends JPanel implements Observer{
	
	protected Controleur cont;
	
	public ListeTweetsPanel(Controleur cont, Color c){
		super();
		this.setBackground( c );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );

		this.setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );

		this.cont = cont;
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		List< Tweet > lt = ( List< Tweet > ) arg1;

		for ( Tweet tweet : lt ) {
			this.cont.classificationRequest( tweet );
			this.add( new TweetsPanel( this.cont, tweet, FeelingsPanel.FEELINGS_COLOR_BRIGHTER ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}

	public void clear () {
		this.removeAll();
	}
		
	}

