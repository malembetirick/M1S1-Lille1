package com.tweets.test;
import twitter4j.RateLimitStatus;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Map;

/**
 * Gets rate limit status.
 *
 * @author Yusuke Yamamoto - yusuke at mac.com
 */
public final class GetRateLimitStatus {
    /**
     * Usage: java twitter4j.examples.account.GetRateLimitStatus
     *
     * @param args message
     */
    public static void main(String[] args) {
    	
    	ConfigurationBuilder conf = new ConfigurationBuilder();
		conf.setDebugEnabled(false);
		conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
		  conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
		  conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
		  conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
		  conf.setHttpProxyHost("cache-etu.univ-lille1.fr");
			conf.setHttpProxyPort(3128);
			
        try {
        	TwitterFactory tf = new TwitterFactory(conf.build());
    		Twitter twitter = tf.getInstance();
    		
            Map<String ,RateLimitStatus> rateLimitStatus = twitter.getRateLimitStatus();
            for (String endpoint : rateLimitStatus.keySet()) {
                RateLimitStatus status = rateLimitStatus.get(endpoint);
                System.out.println("Endpoint: " + endpoint);
                System.out.println(" Limit: " + status.getLimit());
                System.out.println(" Remaining: " + status.getRemaining());
                System.out.println(" ResetTimeInSeconds: " + status.getResetTimeInSeconds());
                System.out.println(" SecondsUntilReset: " + status.getSecondsUntilReset());
            }
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get rate limit status: " + te.getMessage());
            System.exit(-1);
        }
    }
}