/**
 * 
 */
package com.tweets.vue;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.Observable;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.algos.Classifier;
import com.tweets.controleur.principal.Controleur;

/**
 * @author malem
 *
 */
public class EvaluerPanel extends AbstraitPanel{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String CARD_EVALUATION = "Panel d'evaluation des algos";

	public static final Color EVALUATION_COLOR = new Color(223,227,238);

	public static final Color EVALUATION_COLOR_BRIGHTER = new Color( 0xF06060 );

	private Controleur controller;

	private Classifier classifier;

	private EvaluationTitrePanel evaluationTitlePanel;

	private JLabel evaluationValueLabel;

	private long  start = new Date().getTime();
	private long end = new Date().getTime();
	private String difference = (start - end)/1000000+""+"secondes";
	private JLabel tmp2;
	
	private JButton evaluationButton;
	
	public EvaluerPanel(Controleur controller){
		super(controller) ;
		this.setBackground( EvaluerPanel.EVALUATION_COLOR );
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );

		this.setLayout( new BorderLayout() );

		this.controller = controller;
		this.classifier = this.controller.getRecentClassifier();

		this.evaluationTitlePanel =
		        new EvaluationTitrePanel( controller, EvaluerPanel.EVALUATION_COLOR );

		this.evaluationValueLabel = new JLabel( "", SwingConstants.CENTER );
		this.evaluationValueLabel.setBorder( new EmptyBorder( 0, 0, 0, 0 ) );
		this.evaluationValueLabel.setFont( new Font( "Times new roman", Font.PLAIN, 100 ) );
		
		this.evaluationButton = new JButton( "Evaluer" );
		if ( ! ( this.controller.getRecentClassifier().isCrossValidable() ) ) {
			this.evaluationButton.setText( "Evaluation impossible sur ce classifieur" );
			this.evaluationButton.setEnabled( false );
		}
		this.evaluationButton.setForeground( Color.WHITE );
		this.evaluationButton.setBackground( new Color( 0x2F3238 ) );
		this.evaluationButton.setBorder( BorderFactory.createMatteBorder( 15, 150, 15, 150,
		        EvaluerPanel.EVALUATION_COLOR ) );
		this.evaluationButton.setFont( new Font( "Times new roman", Font.PLAIN, 20 ) );
		this.evaluationButton.setPreferredSize( new Dimension( 150, 100 ) );
		this.evaluationButton.setOpaque( true );

		this.add( this.evaluationTitlePanel, BorderLayout.NORTH );
		this.add( this.evaluationValueLabel, BorderLayout.CENTER );
		this.add( this.evaluationButton, BorderLayout.SOUTH );

		this.initListeners();
	}

	public void update ( Observable o, Object arg ) {
		DecimalFormat df = new DecimalFormat( "###.##" );
		Double value = ( Double ) arg;
		this.evaluationValueLabel.setText( df.format( value ) + " %" );
		this.tmp2.setText("Temps d'execution:"+this.difference.toString());
	}

	public void clear () {
		this.evaluationValueLabel.setText( "" );
	}

	public String toString () {
		return EvaluerPanel.CARD_EVALUATION;
	}
	public void resume () {
		this.classifier = this.controller.getRecentClassifier();
		this.evaluationTitlePanel.resume();
		if ( ! ( this.classifier.isCrossValidable() ) ) {
			this.evaluationButton.setText( "Evaluation impossible sur ce classifieur" );
			this.evaluationButton.setEnabled( false );
		} else {
			this.evaluationButton.setText( "Evaluer" );
			this.evaluationButton.setEnabled( true );
		}
	}

	private void initListeners () {
		EvaluationButtonListener EvaluationButtonListener = new EvaluationButtonListener();
		this.evaluationButton.addActionListener( EvaluationButtonListener );
	}

	class EvaluationButtonListener implements ActionListener {

		public void actionPerformed ( ActionEvent e ) {
			EvaluerPanel.this.controller.evaluerRecentClassifier2();
		}
	}

}
