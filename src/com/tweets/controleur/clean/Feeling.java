package com.tweets.controleur.clean;

/**
 * Created by malembeti on 28/10/15.
 */



public enum Feeling {

    NonPolarise {

        @Override
        public int getValue () {
            return -1;
        }

    },

    Negatif {

        @Override
        public int getValue () {
            return 0;
        }

    },

    Neutre {

        @Override
        public int getValue () {
            return 2;
        }

    },

    Positif {

        @Override
        public int getValue () {
            return 4;
        }

    };

    public abstract int getValue ();


    public static Feeling creer ( int value ) throws IllegalArgumentException {
        switch ( value ) {
            case -1:
                return NonPolarise;

            case 0:
                return Negatif;

            case 2:
                return Neutre;

            case 4:
                return Positif;

            default:
                throw new IllegalArgumentException( "Les valeurs doivent etre -1, 0, 2 or 4" );
        }
    }

    @Override
    public String toString () {
        if ( this == NonPolarise ) {
            return "Non noté";
        } else if ( this == Negatif ) {
            return "-";
        } else if ( this == Neutre ) {
            return "=";
        } else {
            return "+";
        }
    }

}

