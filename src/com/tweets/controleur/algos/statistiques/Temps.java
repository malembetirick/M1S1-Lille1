package com.tweets.controleur.algos.statistiques;

/**
 * Created by malem on 06/12/2015.
 */
public class Temps {
        long tmp;
        long prev = tmp;
        long now = System.nanoTime();
    public String Temps(){
        tmp = now;
        return ((double) (now - prev)) / 1000000000.+"secondes";
    }


}
