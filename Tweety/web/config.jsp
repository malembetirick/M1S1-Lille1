<%--
  Created by IntelliJ IDEA.
  User: malem
  Date: 02/12/2015
  Time: 00:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    String x = request.getParameter("tweet");
    if(x!=""){
        ConfigurationBuilder conf = new ConfigurationBuilder();

        conf.setDebugEnabled(false);
        conf.setOAuthConsumerKey("cNd1WhJ3zoWbM5e0OrB7KhxGD");
        conf.setOAuthConsumerSecret("uVeSoZ9b7hEFCRLMElZaE6QM9gXnZmL9wgPsXE4z7aUMtJWMMo");
        conf.setOAuthAccessToken("2414948184-o4p4is9NUY8u2ZzA5Bqe5s7LHanv2z0jRDm3Bt2");
        conf.setOAuthAccessTokenSecret("OqGqrnqOI1dbmjhcjj8ipFxotwMPdFfkPQsxXcEnUCRe0");
        //conf.setHttpProxyHost("cache-etu.univ-lille1.fr");
        //conf.setHttpProxyPort(3128);

        TwitterFactory tf = new TwitterFactory(conf.build());
        Twitter twitter = tf.getInstance();
        Query query = new Query(x);
        query.setLocale("fr");
        query.setLang("fr");
        QueryResult result;
        try {
            result = twitter.search(query);
            for (Status status : result.getTweets()) {
                List<Status> li = result.getTweets();
                List <Tweet> res = new ArrayList< Tweet >();
                int i=0;
                while(i<result.getCount() && li.size()>0){
                    status = li.get(i);
                    res.add( new Tweet( status, x, Feeling.NonPolarise ) );
                }
                try{
                    Boolean s = true;
                    List<Integer> unigramme = new ArrayList<Integer>();
                    unigramme.add(5);
                    List <Integer> bigramme = new ArrayList<Integer>();
                    bigramme.add(2);
                    List <Integer> unibigramme = new ArrayList<Integer>();
                    unibigramme.add(1);
                    unibigramme.add(2);
                    TweetPool ij = new TweetPool("./tweets.csv");
                    PresenceBayesien bj = new PresenceBayesien(ij,s,unibigramme);
                    PieChart d = new PieChart(bj,x, res );
%>
