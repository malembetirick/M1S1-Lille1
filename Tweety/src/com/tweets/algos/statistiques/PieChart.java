package com.tweets.algos.statistiques;

import com.googlecode.charts4j.*;
import com.tweets.algos.Classifier;
import com.tweets.clean.Feeling;
import com.tweets.clean.Tweet;

import java.util.List;

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
                Slice.newSlice( ( positif * 100 ) / nb, Color.newColor( "0E83CD" ), "Positif", "Positif" );
        Slice negatifDecoup =
                Slice.newSlice( ( negatif * 100 ) / nb, Color.newColor( "CC6055" ), "Négatif", "Négatif" );
        Slice neutreDecoup =
                Slice.newSlice( ( neutre * 100 ) / nb, Color.newColor( "FCD04B" ), "Neutre", "Neutre" );

        // créer le camembert
        com.googlecode.charts4j.PieChart chart = GCharts.newPieChart( positifDecoup, negatifDecoup, neutreDecoup );

        // paramètres du camembert
        chart.setTitle( "Estimation du sentiment pour la recherche \"" + this.request + "\"", Color.BLACK, 16 );
        chart.setSize( 400, 200 );
        chart.setThreeD( false );

        // arriere plan transparent
        chart.setBackgroundFill( Fills.newSolidFill( Color.newColor( "000000", 0 ) ) );



        return chart;
    }
}
