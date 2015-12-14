package com.tweets.config;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * Created by malem on 14/11/2015.
 */
public class Configuration {

    public static void main(String[] args){

        ConfigurationBuilder conf = new ConfigurationBuilder();

       ConfigurationBuilder Conskey = conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD"),
         consSecret = conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo"),
        accessToken = conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2"),
        TokenSecret = conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0"),
               proxyHost = conf.setHttpProxyHost("cache-etu.univ-lille1.fr"),
        proxyPort = conf.setHttpProxyPort(3128);

        TwitterFactory tf = new TwitterFactory(conf.build());
        Twitter twi = tf.getInstance();
    }

}
