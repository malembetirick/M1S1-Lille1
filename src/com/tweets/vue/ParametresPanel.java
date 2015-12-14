/**
 * 
 */
package com.tweets.vue;

import java.awt.Color;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.border.EmptyBorder;

import com.tweets.controleur.principal.Controleur;


/**
 * @author malem
 *
 */
public class ParametresPanel extends AbstraitPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String CARD_SETTINGS =  "Panel des paramètres";
    public static final Color SETTINGS_COLOR = new Color(193,174,129);

    private ConfigurationPanel a;
    private NBTweetsPanel b;
    private ProxyPanel c;

	public ParametresPanel(Controleur controle) {
		super(controle);
		this.setBorder( new EmptyBorder( 25, 25, 25, 25 ) );
		this.setBackground( ParametresPanel.SETTINGS_COLOR );
		this.setLayout( new BoxLayout( this, BoxLayout.PAGE_AXIS ) );
		
		this.a = new ConfigurationPanel( controle, ParametresPanel.SETTINGS_COLOR );
		this.a.setAlignmentX( LEFT_ALIGNMENT );
		this.b = new NBTweetsPanel( controle, ParametresPanel.SETTINGS_COLOR );
		this.b.setAlignmentX( LEFT_ALIGNMENT );
		this.c = new ProxyPanel( controle, ParametresPanel.SETTINGS_COLOR );
		this.c.setAlignmentX( LEFT_ALIGNMENT );
		
		this.add( this.a );
		this.add( this.b );
		this.add( this.c );
	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		
	}
	public String toString () {
		return ParametresPanel.CARD_SETTINGS;
	}

}
