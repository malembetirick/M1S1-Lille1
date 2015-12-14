package com.tweets.controleur.clean;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by malembeti on 28/10/15.
 */
public class MessageClean {
    public static final MessageClean INSTANCE = new MessageClean();

    /**
     * Liste des methodes à appliquer pour nettoyer les tweets
     */
    public List< Method > methods;

    public MessageClean() {
        this.methods = new ArrayList< Method >();

        for ( Method m : this.getClass().getMethods() ) {
            if ( m.getName().startsWith( "delete" ) ) {
                this.methods.add( m );
            }
        }
    }


    public String deleteDoubleQuotes ( String s ) {
        return s.replace( '"', ' ' );
    }


    public String deleteComa ( String s ) {
        return s.replace( ',', ' ' );
    }


    public String deleteNewLineChar ( String s ) {
        return s.replace( '\n', ' ' );
    }


    public String deleteUsername ( String s ) {
        return s.replaceAll( "@[A-Za-z0-9_-]+", "" );
    }


    public String deleteHashtag ( String s ) {
        return s.replaceAll( "#[A-Za-z0-9_-]+", "" );
    }

    public String deleteHttpUrl ( String s ) {
        return s.replaceAll( "http[s]?://[^\\s]+", "" );
    }

    public String deleteExtraSpaces ( String s ) {
        return s.replaceAll( "\\s+", " " );
    }

    public String deleteSpecialCharacter ( String s ) {
        return s.replaceAll( "[^a-zA-Z0-9 ]+", " " );
    }

    public String cleanText ( String text ) {
        String s = text;

        for ( Method m : this.methods ) {
            try {
                s = ( String ) m.invoke( this, s );
            } catch ( Exception e ) {
                e.printStackTrace();
            }
        }

        return s;
    }
    public void cleanTweet ( Tweet tweet ) {
		tweet.setMsg( this.cleanText( tweet.getMsg() ) );
	}

    public static MessageClean getInstance () {
        return INSTANCE;
    }
}
