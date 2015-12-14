/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.clean.Feeling;
import com.tweets.controleur.clean.Tweet;
import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class ClassificationBouton extends JButton{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Controleur cont;
	private Feeling feel;
	
	public ClassificationBouton(Controleur cont, String text, Feeling feel){
		super( text );
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 59,89,152 ) );
		this.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.setFont( new Font( "Times new roman", Font.PLAIN, 15 ) );
		
		this.cont = cont;
		this.feel = feel;
		
		this.setOpaque( true );
				
		this.initListener();
		
	}
	public Dimension getPreferredSize () {
		return new Dimension( 125, 35 );
	}
	
	public Feeling getFeeling () {
		return this.feel;
	}
	public void highlight () {
		this.setForeground(  Color.WHITE  );
		this.setBackground( new Color( 15, 157, 88 ) );
		this.repaint();
	}
	public void unhighlight () {
		this.setForeground( Color.WHITE );
		this.setBackground( new Color( 66, 133, 244 ) );
		this.repaint();
	}
	
	private void initListener () {
		ClassificationBoutonListener cbl = new ClassificationBoutonListener();
		this.addActionListener( cbl );
	}
	class ClassificationBoutonListener implements ActionListener {
		public void actionPerformed ( ActionEvent e ) {	
			ClassificationBouton buttonPressed = ( ClassificationBouton ) e.getSource();
			ClassificationPanel clp = ( ClassificationPanel ) buttonPressed.getParent();
			ApprentissageTweetPanel lea = ( ApprentissageTweetPanel ) clp.getParent();
			Tweet recentTweet = lea.getTweet();
			ClassificationBouton.this.cont.saveRequest( recentTweet, buttonPressed.getFeeling() );
			clp.unhighlightButtons();
			buttonPressed.highlight();
			System.out.println( "Action de classification executé: " + buttonPressed.getFeeling() );
		}
	}

}
