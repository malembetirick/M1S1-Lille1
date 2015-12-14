package com.tweets.controleur.test;



import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.tweets.controleur.algos.TweetPool;
import com.tweets.controleur.algos.Bayesien.FrequenceBayesien;
import com.tweets.controleur.algos.Bayesien.PresenceBayesien;
import com.tweets.controleur.algos.KNN.KNNClassifier;
import com.tweets.controleur.algos.dictionnaire.DictionaryClassifier;
import com.tweets.controleur.algos.statistiques.ValidationCroisee;


public class Test {
	
	 public static void main(String [] args) throws IOException {

		 TweetPool bo = new TweetPool("ressources/tweets.csv");
			//DictionaryClassifier bert = new DictionaryClassifier("ressources/positive.txt", "ressources/negative.txt");
			//bert.isCrossValidable();
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

		PresenceBayesien mk = new PresenceBayesien(bo, s, unigramme);
		 mk.isCrossValidable();


		//String jk = mk.classifies(" tu es mechant ").toString();

		 //FrequenceBayesien lk = new FrequenceBayesien(bo,s,unibigramme);
		 //lk.isCrossValidable();
		//String hj = lk.classifies("je t'emmerde").toString();
		//System.out.println(hj);

		 //KNNClassifier po = new KNNClassifier(bo, 1);
		 //po.isCrossValidable();
		 //String fh = po.classifies("j'adore vlille").toString();
		 ValidationCroisee v = new ValidationCroisee(bo,mk,132);
		 Double res = v.evaluer();
		 System.out.println(res);
		    //System.out.println(fh);

		    
		   
		   
}

}
