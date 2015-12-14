package com.tweets.controleur.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.tweets.controleur.algos.dictionnaire.DictionaryClassifier;
import com.tweets.controleur.clean.MessageClean;

import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;


public class TwitterCon {

             public static void main(String[] args) {
                    
ConfigurationBuilder conf = new ConfigurationBuilder();

		conf.setDebugEnabled(false);
		conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
		  conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
		  conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
		  conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
		  conf.setHttpProxyHost("cache-etu.univ-lille1.fr");
			conf.setHttpProxyPort(3128);
                        
		TwitterFactory tf = new TwitterFactory(conf.build());
		Twitter twitter = tf.getInstance();
	    
	    Query query = new Query(" danse ");
	    query.lang("fr");
	    query.locale("fr");
	    QueryResult result;
               try {
                    result = twitter.search(query);
                    for (Status status : result.getTweets()) {
                        
        
                           
                    	MessageClean eft = new MessageClean();
                    	
	        System.out.println("ID du tweet:"+ status.getId()+"Utilisateur:"+eft.cleanText(status.getUser().getName()).toString()+"Texte du tweet:"+eft.cleanText(status.getText()).toString() +"Date de publication:"+status.getCreatedAt()+"Requete utilisée:"+ result.getQuery());
                        DictionaryClassifier bj = new DictionaryClassifier("./positive.txt","./negative.txt");
                        System.out.println( bj.classifies(result.getTweets().toString()));
                        
                try{
          
               
               // File file = new File("./tweets.csv");
                
                //if (!file.exists()) {
				//file.createNewFile();
			//}
               
               // FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			//BufferedWriter bw = new BufferedWriter(fw);

                    //String str = status.getId()+","+eft.cleanText(status.getUser().getName()).toString()+","+eft.cleanText(status.getText()).toString()+","+status.getCreatedAt()+","+result.getQuery()+","+"0";
                        
                            //for (int i = 0; i < str.length(); i++) {
                            			
                                        	//bw.newLine();
                                        
                                        //bw.write(str);
                                        
                                      
                                        
                                        
                    //}
                       //bw.flush();
                               // bw.close();
                   
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
                }
                catch (TwitterException ex) {
                    ex.printStackTrace();
                }
	    
            }
             
             
}
