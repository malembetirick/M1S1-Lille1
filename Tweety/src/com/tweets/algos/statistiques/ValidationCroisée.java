package com.tweets.algos.statistiques;

import com.tweets.algos.CrossValidable;
import com.tweets.algos.TweetPool;
import com.tweets.clean.Feeling;
import com.tweets.clean.Tweet;

import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by malem on 22/11/2015.
 */
public class ValidationCroisée {

    private TweetPool tweetPool;

    /**
     * Classifieur de la validation croisée.
     */
    private CrossValidable classifier;


    private int nbFolds;



    /**
     * Constructeur
     *
     * @param tweetPool
     *            la classe tweetPool est utilisé pour implémenter la validation croisée
     * @param classifier
     *            Classifieur de la validation croisée
     * @param nbFolds
     *            Nombre de plis pour la validation croisée
     */
    public ValidationCroisée ( TweetPool tweetPool, CrossValidable classifier, int nbFolds ) {
        this.tweetPool = tweetPool;
        this.classifier = classifier;
        this.nbFolds = nbFolds;
    }

    // Genere les plis
    private TweetPool[] generatesFolds () throws IllegalArgumentException {
        Collection<Tweet> tweets = this.tweetPool.tweets();

        if ( tweets.size() <= this.nbFolds ) {
            throw new IllegalArgumentException(
                    "Pas assez de Tweets pour remplir les plis." );
        } else {
            TweetPool[] res = new TweetPool[ this.nbFolds ];
            ArrayList< Tweet > positif = new ArrayList< Tweet >();
            ArrayList< Tweet > negatif = new ArrayList< Tweet >();
            ArrayList< Tweet > neutre = new ArrayList< Tweet >();

            // Initialise la variable res
            for ( int i = 0; i < res.length; i++ ) {
                res[ i ] = new TweetPool();
            }

            // Remplir la liste des sentiments
            for ( Tweet tweet : tweets ) {
                Feeling feeling = tweet.getFeeling();

                if ( feeling == Feeling.Positif ) {
                    positif.add( tweet );
                } else if ( feeling == Feeling.Neutre ) {
                    negatif.add( tweet );
                } else if ( feeling == Feeling.Negatif ) {
                    neutre.add( tweet );
                }
            }

            // Construit les listes de tweets positifs, negatifs et neutres
            ArrayList< ArrayList< Tweet >> lists = new ArrayList< ArrayList< Tweet > >();
            lists.add( positif );
            lists.add( negatif );
            lists.add( neutre );

            // remplit les plis equitablement
            for ( ArrayList< Tweet > list : lists ) {
                int i = 0;
                for ( Tweet tweet : list ) {
                    res[ i ].add( tweet );
                    i = ( i + 1 ) % nbFolds;
                }
            }

            return res;
        }
    }

    // Evaluer le classifieur avec le nombre de plis de la base d'apprentissage
    private double evaluatesWithFoldNb ( int nbFold, TweetPool[] folds )
            throws IllegalArgumentException {
        if ( nbFold >= folds.length ) {
            throw new IllegalArgumentException( "Numero de plis inconnu." );
        } else {
            int res = 0;
            TweetPool learningBase = new TweetPool();
            TweetPool toClassify = folds[ nbFold ];

            // Remplir la base d'apprentissage
            for ( int i = 0; i < folds.length; i++ ) {
                if ( i != nbFold ) {
                    for ( Tweet tweet : folds[ i ].tweets() ) {
                        learningBase.add( tweet );
                    }
                }
            }

            // Fournir le classifieur de la base d'apprentissage
            this.classifier.setTweetPool( learningBase );

            // Calculer le taux d'erreurs
            for ( Tweet tweet : toClassify.tweets() ) {
                Feeling feeling = this.classifier.classifies( tweet.getMsg() );
                if ( feeling != tweet.getFeeling() ) {
                    res++;
                }
            }

            return ( ( double ) res / ( double ) toClassify.tweets().size() ) * 100;
        }
    }

    public double evaluer () {
        double res = 0;
        TweetPool[] folds = this.generatesFolds();

        for ( int fold = 0; fold < this.nbFolds; fold++ ) {
            res += this.evaluatesWithFoldNb( fold, folds );
        }

        // Fournir les tweets au classifieur
        this.classifier.setTweetPool( this.tweetPool );

        return res / this.nbFolds;
    }
}
