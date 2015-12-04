
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="twitter4j.conf.ConfigurationBuilder" %>
<%@ page import="com.tweets.clean.MessageClean" %>
<%@ page import="twitter4j.*" %>
<%@ page import="com.tweets.algos.dictionnaire.DictionaryClassifier" %>
<%@ page import="java.io.IOException" %>
<%@ page import="com.tweets.algos.statistiques.PieChart" %>
<%@ page import="com.tweets.algos.Bayesien.PresenceBayesien" %>
<%@ page import="com.tweets.algos.TweetPool" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.tweets.clean.Tweet" %>
<%@ page import="com.tweets.clean.Feeling" %>
<!DOCTYPE HTML>
<html>
<head>
<title> Projet Twitter </title>
<link href="css/bootstrap.css" rel="stylesheet" type="text/css" media="all">
	<link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.4.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" media="all">
	<link href="css/style1.min.css" rel="stylesheet" type="text/css" media="all">
	<link href="css/bootstrap-switch.css" rel="stylesheet" type="text/css" media="all">
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="js/jquery-1.11.0.min.js"></script>
	<script src="js/jquery.bootpag.min.js"></script>
<!-- Custom Theme files -->
<link href="css/style.css" rel="stylesheet" type="text/css" media="all"/>
<!-- Custom Theme files -->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="twitter sentiment analysis, tweets" />
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<!--Google Fonts-->

<!-- start-smoth-scrolling -->
<script type="text/javascript" src="js/move-top.js"></script>
<script type="text/javascript" src="js/easing.js"></script>
	<script type="text/javascript">
			jQuery(document).ready(function($) {
				$(".scroll").click(function(event){
					event.preventDefault();
					$('html,body').animate({scrollTop:$(this.hash).offset().top},1000);
				});
			});
	</script>
<!-- //end-smoth-scrolling -->
<link href="css/jquery.countdown.css" rel="stylesheet" type="text/css" media="all" />
<script src="js/jquery.countdown.js"></script>
<script src="js/script.js"></script>
<!--responsive tab script here-->
<script src="js/easyResponsiveTabs.js" type="text/javascript"></script>
		    <script type="text/javascript">
			    $(document).ready(function () {
			        $('#horizontalTab').easyResponsiveTabs({
			            type: 'default', //Types: default, vertical, accordion
			            width: 'auto', //auto or any width like 600px
			            fit: true   // 100% fit in a container
			        });
			    });
			   </script>
<!--//resposive tab-->
<script>$(document).ready(function(c) {
	$('.cros').on('click', function(c){
		$('.user-profile').fadeOut('slow', function(c){
	  		$('.user-profile').remove();
		});
	});
});
</script>

</head>
<body>

<!--header start here-->
<div class="header">
	<h3 class="main-head">Projet d'analyse de comportements avec Twitter</h3>
	    <div class="head-strip">
	    	<div class="head-strip-left">
	    		<span class="joe"><img src="images/twitter-48.png" alt=""> </span>
	    		<div class="joe-text">
	    			<h2>Bienvenue sur Tweety</h2>
					<br>
	    			<p>L'application d'analyse de Tweets revolutionnaire!</p>
	    		</div>
	    	</div>
	    	<div class="head-strip-right">
	    		<ul class="strip-date">
	    			<li><span class="cal"> </span><h4><% SimpleDateFormat sdf1 = new SimpleDateFormat("dd:MM:yyyy"); %>
						<%= sdf1.format(new Date()) %></h4></li>
	    			<li><span class="clock"> </span><h4><% SimpleDateFormat sdf2 = new SimpleDateFormat("HH:mm:ss"); %>
						<%= sdf2.format(new Date()) %></h4></li>
	    			<li><span class="sun"> </span><h4>Lille,Nord pas de calais</h4></li>
	    		</ul>
	    		<div class="settiing">
	    			<div class="menu2">
					<span class="menu-at-on"><img src="images/setter.png" style="width:30px;" alt=""> </span>
					<ul>
						<li><a href="#">Configurer le Proxy</a></li>
					</ul>
					<script>
						$("span.menu-at-on").click(function(){
							$(".menu2 ul").slideToggle(500, function(){
							});
						});
					</script>
				</div>
						</div>
	    		</div>
	    		  <div class="clearfix"> </div>
	    	</div>

<!--header bottom start here-->
	    <div class="header-bottom">
	    	<div class="col-md-4 header-bot-left">
<!--user-profile start here-->
	    		<div class="user-profile">
	    			<div class="ring-states">
                	<div class="today-status">
                		<div class="today-text">
                		    <h6>Statistiques</h6>
                	    </div>
                	    <span class="todat-start"><img src="images/start.png" alt=""></span>
                	    <div class="clearfix"> </div>
                	</div>
                	<div class="skill-grid">
							<div class="every" id="circles-1"> </div>

							</div>
							<!---->
				            <script type="text/javascript" src="js/circles.js"></script>
					           <script>
								var colors = [
										['#DFDFDF', '#41c0c2']
									];
								for (var i = 1; i <= 5; i++) {
									var child = document.getElementById('circles-' + i),
										percentage = 50 + (i * 10);

									Circles.create({
										id:         child.id,
										percentage: percentage,
										radius:     115,
										width:      10,
										number:   	percentage / 10,
										text:       '%',
										colors:     colors[i - 1]
									});
								}

				         </script>
				        <!--/-->
				        <div class="tabs">
				        	<div class="sap_tabs">
				     <div id="horizontalTab" style="display: block; width: 100%; margin: 0px;">
						  <ul class="resp-tabs-list">
						  	  <li class="resp-tab-item" aria-controls="tab_item-0" role="tab"><span>Terminaison</span></li>
							  <li class="resp-tab-item" aria-controls="tab_item-1" role="tab"><span>Limite</span></li>
							  <li class="resp-tab-item" aria-controls="tab_item-2" role="tab"><span>Reste <small class="num3"> </small> </span></li>
						  </ul>
							<div class="resp-tabs-container">
							    <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-0">
									<div class="facts">
									  <ul class="tab_list">
									  	<li><a href="#"></a></li>
									  </ul>
							        </div>
							     </div>
							      <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-1">
									<div class="facts">
									  <ul class="tab_list">
									    <li><a href="#"></a></li>
									  </ul>
							        </div>
							     </div>
							      <div class="tab-1 resp-tab-content" aria-labelledby="tab_item-2">
									<ul class="tab_list">
									  	<li><a href="#"> </a></li>
									  </ul>
							     </div>
							  <div class="clearfix"> </div>
							 </div>
					      </div>
					 </div>

				        </div>
                 </div>
	    		</div>
				<div class="row">
					<div class="col-sm-6" style="width:490px;">
						<div class="box">
							<div class="box-header">
								<h2><i class="fa fa-list-alt"></i><span class="break"></span>Graphique Camembert</h2>
								<div class="box-icon">
									<a href="#" class="btn-setting"><i class="fa fa-wrench"></i></a>
									<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i>
									</a>
									<a href="#" class="btn-close"><i class="fa fa-times"></i></a>
								</div>
							</div>
							<script>

								function toggle(elem)
								{
									$('.box-content').each(function(index) {

										if ($(this).attr("id") == elem) {

											if ($(this).is(":hidden")) { // si la r�ponse �tait bien cach�e on l affiche

												$(this).slideDown(300);

												$(this).parent().addClass('active');

											}


										}

										else { // on ferme toutes les r�ponses qui ne correspondent pas � celle choisie.

											$(this).slideUp(200);

											$(this).parent().removeClass('active');

										}

									});

								}
							</script>


							<div class="box-content" id="gt">

							</div>
						</div>
					</div><!--/col-->
					<!--/modal-->
					<div class="modal fade" id="myModal">
						<div class="modal-dialog">
							<div class="modal-content">
								<div class="modal-header">
									<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
									<h4 class="modal-title">Modal title</h4>
								</div>
								<div class="modal-body">
									<p>Here settings can be configured...</p>
								</div>
								<div class="modal-footer">
									<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
									<button type="button" class="btn btn-primary">Save changes</button>
								</div>
							</div><!-- /.modal-content -->
						</div><!-- /.modal-dialog -->
					</div><!-- /.modal -->
<!--user-profile end here-->
<!--ring states start here-->

<!--mountain-img start here-->

               </div>
	    	</div>
<!--header-bot-right start here-->
	    	<div class="col-md-8 header-bot-right">
<!--analytic start here-->
	    		<div class="analytic hidden">
	    			<div class="analytic-top">
	    				<div class="infograpy"><h5>SALES INFOGRAPHIC</h5></div>
	    				<span class="share"> </span>
	    			  <div class="clearfix"> </div>
	    			</div>
	    				<div class="analttic-right">
	    					 <div class="graph-grid">
	    			<!--graph-->
								<link rel="stylesheet" href="css/graph.css">
								<script src="js/jquery.flot.min.js"></script>
					<!--//graph-->
					<script>
										$(document).ready(function () {

											// Graph Data ##############################################
											var graphData = [{
													// Returning Visits
													data: [ [6, 4500], [7,3500], [8, 6550], [9, 7600], ],
													color: '#59676B',
													points: { radius: 4, fillColor: '#59676B' }
												}
											];

											// Lines Graph #############################################
											$.plot($('#graph-lines'), graphData, {
												series: {
													points: {
														show: true,
														radius: 1
													},
													lines: {
														show: true
													},
													shadowSize: 0
												},
												grid: {
													color: '#59676B',
													borderColor: 'transparent',
													borderWidth: 10,
													hoverable: true
												},
												xaxis: {
													tickColor: 'transparent',
													tickDecimals: false
												},
												yaxis: {
													tickSize: 1200
												}
											});

											// Bars Graph ##############################################
											$.plot($('#graph-bars'), graphData, {
												series: {
													bars: {
														show: true,
														barWidth: .9,
														align: 'center'
													},
													shadowSize: 0
												},
												grid: {
													color: '#fff',
													borderColor: 'transparent',
													borderWidth: 20,
													hoverable: true
												},
												xaxis: {
													tickColor: 'transparent',
													tickDecimals: 2
												},
												yaxis: {
													tickSize: 1000
												}
											});

											// Graph Toggle ############################################
											$('#graph-bars').hide();

											$('#lines').on('click', function (e) {
												$('#bars').removeClass('active');
												$('#graph-bars').fadeOut();
												$(this).addClass('active');
												$('#graph-lines').fadeIn();
												e.preventDefault();
											});

											$('#bars').on('click', function (e) {
												$('#lines').removeClass('active');
												$('#graph-lines').fadeOut();
												$(this).addClass('active');
												$('#graph-bars').fadeIn().removeClass('hidden');
												e.preventDefault();
											});

											// Tooltip #################################################
											function showTooltip(x, y, contents) {
												$('<div id="tooltip">' + contents + '</div>').css({
													top: y - 16,
													left: x + 20
												}).appendTo('body').fadeIn();
											}

											var previousPoint = null;

											$('#graph-lines, #graph-bars').bind('plothover', function (event, pos, item) {
												if (item) {
													if (previousPoint != item.dataIndex) {
														previousPoint = item.dataIndex;
														$('#tooltip').remove();
														var x = item.datapoint[0],
															y = item.datapoint[1];
															showTooltip(item.pageX, item.pageY, y+ x );
													}
												} else {
													$('#tooltip').remove();
													previousPoint = null;
												}
											});

										});
										</script>
					<!-- Graph HTML -->
								<div id="graph-wrapper" class ="hidden">
									<div class="graph-container">
										<div id="graph-lines"> </div>
										<div id="graph-bars"> </div>
									</div>
								</div>
							<!-- end Graph HTML -->
                     </div>
                    </div>
					<div class="analytic-bottom">
						<ul>
							<li><h3><a href="#">$157,182</a></h3><p>Total Earnings</p></li>
							<li><h3><a href="#">$38,952</a></h3><p>Revenue</p></li>
							<li><h3><a href="#">+800k</a></h3><p>New Customers</p></li>
						</ul>
					</div>
			 </div>
<!--banner start here-->
	    		<div class="banner">
	    			<div class="bann-left">
	    				<span class="bann-part"> </span>
	    				<div class="bann-text">
	    					<h1>Analyser les tweets?</h1>
							<br>
	    					<p>Recolter les sentiments utilisateurs, et y ressortir les resultats sous forme graphique n'a jamait ete aussi simple.</p>
	    				</div>
	    			</div>
	    			<div class="bann-rit">
	    				<a href="#">Explorer</a>
	    			</div>
	    		  <div class="clearfix"> </div>
	    		</div>
<!--part1 start here-->
                <div class="bar-kit">
	    		<div class="col-md-5 header-bot-right-part-1">
<!--latest activity strta here-->
	    			<div class="latest-activity" style="
						height: 628px;
						background: #FFF none repeat scroll 0% 0%;
						overflow-y: scroll;
						padding: 2em 1em;">
	    				<div class="latest-act-top">
	    					<h4>Tweets recents</h4>
	    					<span class="rocket"> </span>
	    				  <div class="clearfix"> </div>
	    				</div>
	    				<div class="latest-act-bot">
	    					<ul>
	    						<li><span class="box"> </span></li>
	    						<li><span class="line"> </span></li>
	    						<li><span class="box"> </span></li>
	    						<li><span class="line"> </span></li>
	    						<li><span class="box"> </span></li>
	    						<li><span class="line"> </span></li>
	    						<li><span class="box"> </span></li>
	    						<li><span class="line"> </span></li>
	    						<li><span class="box"> </span></li>
	    						<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>
								<li><span class="line"> </span></li>
								<li><span class="box"> </span></li>

	    					</ul>
	    					<div class="latest-today">

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




													 MessageClean eft = new MessageClean();
													 eft.cleanText(status.getText());

								 %>
								<h4><%= "ID du tweet:"+ status.getId() %></h4>
								<h3><%= "Utilisateur:"+eft.cleanText(status.getUser().getName()).toString() %></h3>
								<h3><%= "Date de publication:"+status.getCreatedAt() %></h3>
								<h3><%= "Requete utilisee:" +result.getQuery() %></h3>
									 <p><%= "Texte du tweet:"+eft.cleanText(status.getText()).toString() %></p>
								<h3>Polarite :

									<%= bj.classifies(status.getText()).toString() %>
								</h3>
								<%
									}catch(IOException e){
										e.printStackTrace();
									}
								%>
									 <%
												 } }catch(TwitterException et){
												 et.printStackTrace();
											 }
										 }
									 %>
	    					</div>

	    				</div>
	    				<div class="late-btn">

	    					<a href="#" name="save">Sauvegarder</a>
	    				</div>
	    			</div>
<!--video start here-->
	    			<div class="video hidden">
	    				<div class="content-middle-top2">
		<div class=" video-grid">
					<div id="jp_container_1" class="jp-video jp-video-360p" role="application" aria-label="media player">
						<div class="jp-type-single">
							<div id="jquery_jplayer_1" class="jp-jplayer"></div>
							<div class="jp-gui">
								<div class="jp-video-play">
									<button class="jp-video-play-icon" role="button" tabindex="0">play</button>
								</div>
								<div class="jp-interface">
									<div class="jp-progress">
										<div class="jp-seek-bar">
											<div class="jp-play-bar"></div>
										</div>
									</div>
									<div class="jp-current-time" role="timer" aria-label="time">&nbsp;</div>
									<div class="jp-duration" role="timer" aria-label="duration">&nbsp;</div>
									<div class="jp-controls-holder">
										<div class="jp-controls">
											<button class="jp-play" role="button" tabindex="0">play</button>
											<button class="jp-stop" role="button" tabindex="0">stop</button>
										</div>
										<div class="jp-volume-controls">
											<button class="jp-mute" role="button" tabindex="0">mute</button>
											<button class="jp-volume-max" role="button" tabindex="0">max volume</button>
											<div class="jp-volume-bar">
												<div class="jp-volume-bar-value"></div>
											</div>
										</div>
										<div class="jp-toggles">
											<button class="jp-repeat" role="button" tabindex="0">repeat</button>
											<button class="jp-full-screen" role="button" tabindex="0">full screen</button>
										</div>
									</div>

								</div>
							</div>
							<div class="jp-no-solution">
								<span>Update Required</span>
								To play the media you will need to either update your browser to a recent version or update your <a href="http://get.adobe.com/flashplayer/" target="_blank">Flash plugin</a>.
							</div>
						</div>
					</div>
				</div>
	</div>
	    			</div>
	    		</div>
<!--part2 start here-->
	    		<div class="col-md-7 header-bot-right-part-2">
<!--meeting strta here-->
                    <div class="meeting">
                    	<div class="meeting-top">
                    		<h3>Rechercher des Tweets</h3>
                    		<div class="menu3">
								<span class="menu-at-on1"><img src="images/menu1.png" alt=""> </span>
								<ul style="width:130px;">
									<li><a href="#">Algorithmes</a></li>
									<li><a href="motcles.jsp" target="_blank">Mots-cles</a></li>
									<li><a href="knn.jsp" target="_blank" >KNN</a></li>
									<li><a href="bayeNaif.jsp" target="_blank" >Bayesien Naif</a></li>
									<li><a href="bayePre.jsp" target="_blank" >Bayesien par presence</a></li>
									<li><a href="bayeFre.jsp" target="_blank" >Bayesien par frequence</a></li>
								</ul>
								<script>
									$("span.menu-at-on1").click(function(){
										$(".menu3 ul").slideToggle(500, function(){
										});
									});
								</script>
							</div>
						   <div class="clearfix"> </div>
                    	</div>
                    	<div class="meet-search">
							<form method="get" action="index.jsp">
								<input type="text" value="Recherche" name="tweet" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Recherche';}"/>
								<input type="submit" value="" name="submit">
							</form>

                    	</div>
                    	<div class="menu_vertical hidden">
					         	 	<section class="accordation_menu">
									  <div>
									    <input id="label-1" name="lida" type="radio" checked/>
									   <label for="label-1" id="item1"><i class="ferme"> </i> <span class="tickimg"><img src="images/check.png" alt=""><i> </i>Summer Campaing<strong  class="tad-timer"> <i class="tab-text-time">Today,3.20AM</i></strong></span><small class="pen"> </small> <i class="icon-plus-sign i-right1"> </i> <i class="icon-minus-sign i-right2"></i> </label>
									    <div class="content1" id="a1">
									       <ul class="news_list">
									       	<li><img src="images/1.png" alt=""> </li>
											 <li class="date_desc-auther"><p>Joe Black</p></li>
							  			   	 <li class="date_desc-timer"><p>2h to Complete the task</p></li>
							  			  </ul>
							  			  <p class="auther-para">Etiam feugiat lectus nisl, in euismod lectus viverra et.Sed et scelerisque felis. Integer vel volutpat massa Donec id justo nisl Vivamus</p>
									        <div class="involed-people">
									        	 <h4>PEOPLE INVOLVED:</h4>
									        	 <img src="images/invitation.png" alt="">
									        </div>
									    </div>
									  </div>
									  <div>
									    <input id="label-2" name="lida" type="radio"/>
									    <label for="label-2" id="item2"><i class="ferme"> </i> <span class="tickimg"><img src="images/check.png" alt=""><i> </i>System Maintenance<strong  class="tad-timer"> <i class="tab-text-time">Today,3.20AM</i></strong></span><small class="pen"> </small> <i class="icon-plus-sign i-right1"> </i> <i class="icon-minus-sign i-right2"></i> </label>
									    <div class="content1" id="a2">
									       <ul class="news_list">
									       	<li><img src="images/1.png" alt=""> </li>
											 <li class="date_desc-auther"><p>Joe Black</p></li>
							  			   	 <li class="date_desc-timer"><p>2h to Complete the task</p></li>
							  			  </ul>
							  			  <p class="auther-para">Etiam feugiat lectus nisl, in euismod lectus viverra et.Sed et scelerisque felis. Integer vel volutpat massa Donec id justo nisl Vivamus</p>
									    </div>
									  </div>
									  <div>
									    <input id="label-3" name="lida" type="radio"/>
									     <label for="label-3" id="item3"><i class="ferme"> </i> <span class="tickimg"><img src="images/check.png" alt=""><i> </i>Website Update<strong  class="tad-timer"> <i class="tab-text-time">Today,3.20AM</i></strong></span><small class="pen"> </small> <i class="icon-plus-sign i-right1"> </i> <i class="icon-minus-sign i-right2"></i> </label>
									    <div class="content1" id="a3">
							  			  <p class="auther-para">Etiam feugiat lectus nisl, in euismod lectus viverra et.Sed et scelerisque felis. Integer vel volutpat massa Donec id justo nisl Vivamus Viris phaedrum ad cum, in usu ipsum percipit. Ut ponderum percipitur este Lorem ipsum dolor sit amet, consectetuer adipiscing elit, sed diam nonummy nibh euismod tincidunt ut laoreet dolore magna aliquam </p>
									    </div>
									  </div>
									  <div>
									    <input id="label-4" name="lida" type="radio"/>
									     <label for="label-4" id="item4"><i class="ferme"> </i> <span class="tickimg"><img src="images/check.png" alt=""><i> </i>Financial Report<strong  class="tad-timer"> <i class="tab-text-time">02/15,2.20AM</i></strong></span><small class="pen"> </small> <i class="icon-plus-sign i-right1"> </i> <i class="icon-minus-sign i-right2"></i> </label>
									    <div class="content1" id="a4">
									      <ul class="news_list">
									       	<li><img src="images/1.png" alt=""> </li>
											 <li class="date_desc-auther"><p>Joe Black</p></li>
							  			   	 <li class="date_desc-timer"><p>2h to Complete the task</p></li>
							  			  </ul>
							  			  <p class="auther-para">Etiam feugiat lectus nisl, in euismod lectus viverra et.Sed et scelerisque felis. Integer vel volutpat massa Donec id justo nisl Vivamus</p>
									        <div class="involed-people">
									        	 <h4>PEOPLE INVOLVED:</h4>
									        	 <img src="images/invitation.png" alt="">
									        </div>
									    </div>
									  </div>
									<div class="clearfix"> </div>
									</section>
                              </div>
                         </div>
<!--map start here-->
	    			<div class="map hidden">
	    				<iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d127640.75918330808!2d103.8466694772479!3d1.3111268075660079!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x31da11238a8b9375%3A0x887869cf52abf5c4!2sSingapore!5e0!3m2!1sen!2sin!4v1436965675589"> </iframe>
                     </div>
	    			<div class="location hidden">
	    				<div class="col-md-6 rids">
	    					<span class="localpointer"> </span>
	    					<div class="rid-text">
		    					<h3>RIDS</h3>
		    					<p>2College st</p>
		    					<p>Providence,Ri 02903</p>
		    					<p>United States</p>
	    					</div>
	    				</div>
	    				<div class="col-md-6 rids-btns">
	    					<div class="print-btn">
	    						<a href="#">PRINT</a>
	    					</div>
	    					<div class="print-btn">
	    						<a href="#">SHARE</a>
	    					</div>
	    				</div>
	    			  <div class="clearfix"> </div>
	    			</div>
	    		</div>
	    		 <div class="clearfix"> </div>
	      </div>
	  </div>
   <div class="clearfix"> </div>
</div>
 <div class="clearfix"> </div>
</div>
        <div class="copy-right">
			   <p> 2015 Malembeti Rick  <a href="http://www.univ-lille1.fr/" target="_blank">  Universite Lille1 </a></p>
		        <script type="text/javascript">
										$(document).ready(function() {
											/*
											var defaults = {
									  			containerID: 'toTop', // fading element id
												containerHoverID: 'toTopHover', // fading element hover id
												scrollSpeed: 1200,
												easingType: 'linear'
									 		};
											*/

											$().UItoTop({ easingType: 'easeOutQuart' });

										});
									</script>
						<a href="#" id="toTop" style="display: block;"> <span id="toTopHover" style="opacity: 1;"> </span></a>

		   </div>
<!--header bottom end here-->
<!--header end here-->
</body>
</html>
