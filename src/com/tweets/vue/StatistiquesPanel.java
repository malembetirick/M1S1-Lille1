/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.io.IOException;
import java.util.List;
import java.util.Observable;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;

import twitter4j.TwitterException;



/**
 * @author malem
 *
 */
public class StatistiquesPanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Controleur cont;
	private Color c;
	
	public StatistiquesPanel(Controleur cont, Color c){
		super();
		this.setBackground( c );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );

		this.cont = cont;
		this.c = c;
	}
	public void drawPieChart ( List< Tweet > list ) throws IllegalArgumentException, TwitterException {
		this.removeAll();
		
		try {
			this.add( new CamembertPanel( this.cont.pieChartImageRequest( list ), this.c ) );
	        
	        
			this.revalidate();
        } catch ( IOException e ) {
	        e.printStackTrace();
        }
	}
	
	public void update ( Observable o, Object arg ) throws IllegalArgumentException, TwitterException {
		@SuppressWarnings ( "unchecked" )
        List< Tweet > list = ( List< Tweet > ) arg;
		this.drawPieChart( list );
	}
	
	public void clear () {
		this.removeAll();
	}

}
