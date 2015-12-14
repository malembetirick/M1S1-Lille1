/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.util.List;
import java.util.Observable;

import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class ApprentissageListeTweetPanel extends ListeTweetsPanel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public ApprentissageListeTweetPanel(Controleur cont, Color c) {
		super(cont, c);
		// TODO Auto-generated constructor stub
	}
	public void update ( Observable o, Object arg ) {
		@SuppressWarnings ( "unchecked" )
		List< Tweet > lt = ( List< Tweet > ) arg;

		for ( Tweet tweet : lt ) {
			this.cont.classificationRequest( tweet );
			this.add( new ApprentissageTweetPanel( this.cont, tweet, ApprentissagePanel.LEARNING_COLOR_BRIGHTER ) );
		}

		/* View have changed and need to be repaint */
		this.revalidate();
	}

	
}
