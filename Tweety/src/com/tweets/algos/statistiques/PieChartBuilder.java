package com.tweets.algos.statistiques;

import com.googlecode.charts4j.AbstractGraphChart;
import com.tweets.algos.Classifier;
import com.tweets.clean.Tweet;

import java.util.List;

/**
 * Created by malem on 28/11/2015.
 */
public abstract class PieChartBuilder {

    protected Classifier classifier;

    protected String request;

    protected List<Tweet> tweets;

    public PieChartBuilder ( Classifier classifier, String request, List< Tweet > tweets ) {
        this.classifier = classifier;
        this.request = request;
        this.tweets = tweets;
    }
    public abstract AbstractGraphChart generatesChart ();
}
