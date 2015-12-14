package com.tweets.clean;

import java.util.Date;

import twitter4j.Status;

/**
 * Created by malembeti on 28/10/15.
 */
public class Tweet {
    private String id;

    private String tweets;

    private String msg;

    private Date date;

    private String query;

    private Feeling polarité;
    
    public Tweet(String id, String tweets, String msg, Date date, String query, Feeling polarité){
    	this.id = id;
    	this.tweets = tweets;
    	this.msg = msg;
    	this.date = date;
    	this.query = query;
    	this.polarité = polarité;
    	
    }
    public Tweet ( Status status, String query, Feeling polarité ) {
		this.id = String.valueOf(status.getId());
		this.tweets = status.getUser().getName();
		this.msg = status.getText();
		this.date = status.getCreatedAt();
		this.query = query;
		this.polarité = polarité;
	}
    public String getId () {
		return this.id;
	}
    public String getTweet () {
		return this.tweets;
	}
    public String getMsg () {
		return this.msg;
	}
    public Date getDate () {
		return this.date;
	}
    public String getQuery () {
		return this.query;
	}
    public Feeling getFeeling () {
		return this.polarité;
	}
    public void setMsg ( String newMsg ) {
		this.msg = newMsg;
	}
    public void setFeeling ( Feeling newPol ) {
		this.polarité = newPol;
	}
    public String toString () {
		return "----------\n" + "id : " + this.getId() + "\n" + "tweets : " + this.getTweet()
		        + "\n" + "message : " + this.getMsg() + "\n" + "date : " + this.getDate() + "\n"
		        + "query : " + this.getQuery() + "\n" + "Polarité : " + this.getFeeling() + "\n"
		        + "----------\n";
	}
    public boolean equals ( Object obj ) {
	    if ( this == obj ) {
		    return true;
	    }
	    if ( obj == null ) {
		    return false;
	    }
	    if ( ! ( obj instanceof Tweet ) ) {
		    return false;
	    }
	    Tweet other = ( Tweet ) obj;
	    if ( id == null ) {
		    if ( other.id != null ) {
			    return false;
		    }
	    } else if ( !id.equals( other.id ) ) {
		    return false;
	    }
	    return true;
    }
    public int hashCode () {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ( ( id == null ) ? 0 : id.hashCode() );
	    return result;
    }
    
    
}
