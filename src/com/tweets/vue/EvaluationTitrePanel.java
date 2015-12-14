/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.util.Date;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.algos.Classifier;
import com.tweets.controleur.algos.statistiques.Temps;
import com.tweets.controleur.principal.Controleur;



/**
 * @author malem
 *
 */
public class EvaluationTitrePanel extends JPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8220852240036011057L;
	
	private Controleur cont;
	private Classifier classifier;
	private JLabel evaluationLabel1;
	private JLabel classifierLabel;
	
	
	public EvaluationTitrePanel(Controleur cont, Color c){
		super();
		this.setBackground( c );
		this.setBorder( new EmptyBorder( 50, 0, 0, 0 ) );
		
		this.setLayout( new BorderLayout() );
		
		this.cont = cont;
		this.classifier = this.cont.getRecentClassifier();
		
		this.evaluationLabel1 = new JLabel( "Evaluation du taux d'erreur du classifieur:", SwingConstants.CENTER );
		this.evaluationLabel1.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationLabel1.setBackground( c );
		this.evaluationLabel1.setForeground( new Color( 0x2F3238 ) );
		this.evaluationLabel1.setFont( new Font( "Times new roman", Font.PLAIN, 20 ) );
		this.evaluationLabel1.setOpaque( true );
		
		
		
		
		this.classifierLabel = new JLabel( this.classifier.toString(), SwingConstants.CENTER );
		this.classifierLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.classifierLabel.setBackground( c );
		this.classifierLabel.setForeground( new Color( 0x2F3238 ) );
		this.classifierLabel.setFont( new Font( "Times new roman", Font.BOLD, 20 ) );
		this.classifierLabel.setOpaque( false );	
		
		this.add( this.evaluationLabel1, BorderLayout.NORTH );
		this.add( this.classifierLabel, BorderLayout.SOUTH );
		
	
		
	}
	public void resume () {
		this.classifier = this.cont.getRecentClassifier();
		this.classifierLabel.setText( this.classifier.toString() );
		
	}

}
