package com.tweets.test;

import com.tweets.algos.Bayesien.FrequenceBayesien;
import com.tweets.algos.Bayesien.PresenceBayesien;
import com.tweets.algos.KNN.KNNClassifier;
import com.tweets.algos.TweetPool;
import com.tweets.algos.dictionnaire.DictionaryClassifier;
import com.tweets.algos.statistiques.PieChart;
import com.tweets.algos.statistiques.ValidationCroisée;
import com.tweets.clean.Tweet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Test {
	
	 public static void main(String [] args) throws IOException {

		 TweetPool bo = new TweetPool("./tweets.csv");
			DictionaryClassifier bert = new DictionaryClassifier("./positive.txt", "./negative.txt");
		 //String th = bert.classifies("").toString();
		 //System.out.println(th);

		 Boolean s = true;
		 List <Integer> unigramme = new ArrayList<Integer>();
		 unigramme.add(5);
		 List <Integer> bigramme = new ArrayList<Integer>();
		 bigramme.add(2);
		 List <Integer> unibigramme = new ArrayList<Integer>();
		 unibigramme.add(1);
		 unibigramme.add(2);

		//PresenceBayesien mk = new PresenceBayesien(bo, s, unibigramme);
		 //mk.isCrossValidable();


		//String jk = mk.classifies(" tu es mechant ").toString();

		 //FrequenceBayesien lk = new FrequenceBayesien(bo,s,unibigramme);
		//String hj = lk.classifies("je t'emmerde").toString();
		//System.out.println(hj);

		 KNNClassifier po = new KNNClassifier(bo, 1);
		 po.isCrossValidable();
		 //String fh = po.classifies("j'adore vlille").toString();
		 ValidationCroisée v = new ValidationCroisée(bo,po,10);
		 Double res = v.evaluer();
		 System.out.println(res);
		    //System.out.println(fh);

		    
		   
		   
}

}
