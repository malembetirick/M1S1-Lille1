/**
 * 
 */
package com.tweets.main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Observer;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import com.tweets.controleur.algos.statistiques.Temps;
import com.tweets.controleur.principal.Controleur;
import com.tweets.modele.Modele;
import com.tweets.vue.AppliPanel;
import com.tweets.vue.ParametresPanel;






/**
 * @author malem
 *
 */
public class TweetyMain extends JFrame{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private		JTabbedPane tabbedPane;
	private		JPanel		panel1;
	private		JPanel		panel2;
	private		JPanel		panel3;
	
	private Image img;
	
	static Modele search = new Modele();
	static Controleur controller = new Controleur( search );
	Temps op;


	public TweetyMain()
	{
		// NOTE: to reduce the amount of code in this example, it uses
		// panels with a NULL layout.  This is NOT suitable for
		// production code since it may not display correctly for
		// a look-and-feel.
		
		setTitle( "Tweety, l'appli revolutionnaire" );
		setSize( 1400, 800 );
		setBackground( new Color(0,150,136) );

		JPanel topPanel = new JPanel();
		topPanel.setLayout( new BorderLayout() );
		getContentPane().add( topPanel );
		

		// Create the tab pages
		createPage1();
		createPage2();
		
		
		

		// Create a tabbed pane
		tabbedPane = new JTabbedPane();
		tabbedPane.addTab( "Parametres", panel1 );
		tabbedPane.addTab( "Application", panel2 );
		
		
		topPanel.add( tabbedPane, BorderLayout.CENTER );
		
		
		
	}

	public void createPage1()
	{
		 panel1 = new ParametresPanel(controller);
		 panel1.setVisible(true);
	}

	public void createPage2()
	{
		panel2 = new AppliPanel(controller);
		panel2.setVisible(true);
		search.addObserver((Observer) panel2);
		
		
	}

    // Main method to get things started
	public static void main( String args[] )
	{
		// Create an instance of the test application
		TweetyMain mainFrame	= new TweetyMain();
		mainFrame.setVisible( true );
        mainFrame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		}
		
	}


