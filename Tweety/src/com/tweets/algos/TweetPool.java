package com.tweets.algos;



import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.tweets.clean.Tweet;
import com.tweets.clean.Feeling;



public class TweetPool {

	
	private Map< String, Tweet > tweetPool;

	
	public TweetPool ( String path ) {
		this.tweetPool = new HashMap< String, Tweet >();
		this.readCSV( path );
	}

	
	public TweetPool () {
		this.tweetPool = new HashMap< String, Tweet >();
	}

	
	public Collection< Tweet > tweets () {
		return this.tweetPool.values();
	}

	
	public void readCSV ( String path ) {
		BufferedReader br = null;
		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				String[] elem = sCurrentLine.split(",");
				for (int i = 0; i < elem.length-1; i++) {
					if(elem != null) {
						SimpleDateFormat fg = new SimpleDateFormat("EEE MMM dd HH:mm:ss ZZZZZ yyyy", Locale.ENGLISH);
						try {
							fg.setLenient(true);
							Date date = fg.parse(elem[3]);
							Tweet tweet =
									new Tweet(elem[0], elem[1], elem[2], date,
											elem[4],
											Feeling.creer(Integer.parseInt(elem[5])));

							this.add(tweet);
						} catch (ParseException e) {
							e.printStackTrace();
						}
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}


	public void writeCSV ( String path ) {
		try {
			BufferedWriter out = new BufferedWriter( new FileWriter( path, false ) );

			for ( Tweet tweet : this.tweets() ) {
				String msg = tweet.getMsg();
				StringBuffer tweetText =
				        new StringBuffer( tweet.getId() + "," + tweet.getTweet() + "," );

				if ( msg.charAt( 0 ) == '"' && msg.charAt( msg.length() - 1 ) == '"' ) {
					tweetText.append( tweet.getMsg() );
				} else {
					tweetText.append( "\"" + tweet.getMsg() + "\"" );
				}

				tweetText.append( "," + tweet.getDate().getTime() + "," + tweet.getQuery() + ","
				        + tweet.getFeeling().toString() );

				out.write( tweetText.toString() );
				out.newLine();
			}

			out.close();
		} catch ( IOException e1 ) {
			e1.printStackTrace();
		}
	}

	
	public void add ( Tweet tweet ) {
		this.tweetPool.put(String.valueOf(tweet.getId()), tweet );
	}

	
	public void remove ( Tweet tweet ) {
		this.tweetPool.remove( tweet );
	}

	public void clear () {
		this.tweetPool.clear();
	}

}
