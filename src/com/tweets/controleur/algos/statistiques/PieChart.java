package com.tweets.controleur.algos.statistiques;

import com.googlecode.charts4j.*;
import com.tweets.controleur.algos.Classifier;
import com.tweets.controleur.clean.Feeling;
import com.tweets.controleur.clean.Tweet;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

import javax.imageio.ImageIO;

/**
 * Created by malem on 28/11/2015.
 */
public class PieChart extends PieChartBuilder{

    public PieChart (Classifier classifier, String request, List<Tweet> tweets ) {
        super( classifier, request, tweets );
    }
    @Override
    public AbstractGraphChart generatesChart() {
        int positif = 0;
        int negatif = 0;
        int neutre = 0;
        int nb = this.tweets.size();

        // Tweets classification
        for ( Tweet tweet : this.tweets ) {
            Feeling feeling = this.classifier.classifies( tweet.getMsg() );

            if ( feeling == Feeling.Positif ) {
                positif++;
            } else if ( feeling == Feeling.Neutre ) {
                neutre++;
            } else if ( feeling == Feeling.Negatif ) {
                negatif++;
            }
        }

        // decouper le camembert
        Slice positifDecoup =
                Slice.newSlice( ( positif * 100 ) / nb, Color.newColor( "0E83CD" ), "Positif avec"+""+( positif * 100 ) / nb+"%" , "Positif" );
        Slice negatifDecoup =
                Slice.newSlice( ( negatif * 100 ) / nb, Color.newColor( "CC6055" ), "Negatif avec"+""+ ( negatif * 100 ) / nb+"%", "Negatif" );
        Slice neutreDecoup =
                Slice.newSlice( ( neutre * 100 ) / nb, Color.newColor( "FCD04B" ), "Neutre avec"+""+( neutre * 100 ) / nb+"%", "Neutre" );

        // créer le camembert
        com.googlecode.charts4j.PieChart chart = GCharts.newPieChart( positifDecoup, negatifDecoup, neutreDecoup );

        // paramètres du camembert
        chart.setTitle( "Estimation du sentiment pour la recherche \"" + this.request + "\"", Color.BLACK, 16 );
        
        chart.setSize( 750, 400 );
        chart.setThreeD( false );

        // arriere plan transparent
        chart.setBackgroundFill( Fills.newSolidFill( Color.newColor( "000000", 0 ) ) );



        return chart;
    }
    public Image getPieChartImage () throws MalformedURLException, IOException {
		return ImageIO.read( new URL( this.generatesChart().toURLString() ) );
	}
}
