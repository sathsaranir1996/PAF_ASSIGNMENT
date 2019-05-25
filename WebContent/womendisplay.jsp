<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*" %> 
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>

<%

String driver = "com.mysql.jdbc.Driver";
String connectionUrl = "jdbc:mysql://localhost:3307/";
String database = "test";
String userid = "root";
String password = "user";
try {
Class.forName(driver);
} catch (ClassNotFoundException e) {
e.printStackTrace();
}
Connection connection = null;
Statement statement = null;
ResultSet resultSet = null;
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<!--[if ie]><meta content='IE=8' http-equiv='X-UA-Compatible'/><![endif]-->
		<!-- bootstrap -->
		<link href="bootstrap/css/bootstrap.min.css" rel="stylesheet">      
		<link href="bootstrap/css/bootstrap-responsive.min.css" rel="stylesheet">
		
		<link href="themes/css/bootstrappage.css" rel="stylesheet"/>
		
		<!-- global styles -->
		<link href="themes/css/flexslider.css" rel="stylesheet"/>
		<link href="themes/css/main.css" rel="stylesheet"/>

		<!-- scripts -->
		<script src="themes/js/jquery-1.7.2.min.js"></script>
		<script src="bootstrap/js/bootstrap.min.js"></script>				
		<script src="themes/js/superfish.js"></script>	
		<script src="themes/js/jquery.scrolltotop.js"></script>
</head>
<body>
<%
String un=(String)session.getAttribute("un");
if(un == null){
	String msg = "Login First";
	String redirectURL = "http://localhost:8080/PAF/logReg.jsp?msg="+msg;
	response.sendRedirect(redirectURL);
}
%>
<h3 style = "margin-left: 10px; margin-top: 10px;"><a href = "entry/member/myProfile?name=<%=un%>"><%=un %></a></h3>
<div id="top-bar" class="container">
			<div class="row">
				<div class="span4">
					<form method="POST" class="search_form">
						<input type="text" class="input-block-level search-query" Placeholder="eg. Search...">
					</form>
				</div>
				<div class="span8">
					<div class="account pull-right">
						<ul class="user-menu">	
							<li><a href="AddPost.jsp">Add Post</a></li>	
							<li><a href="entry/member/myProfile?name=<%=un%>">My Account</a></li>
							<li><a href="orderprofile.jsp?name=<%=un%>">Your Cart</a></li>
							<li><a href="inventory.jsp">Inventory</a></li>
							<li><a href="payment.jsp">Payment</a></li>		
							<li><a href="Home.jsp">Home</a></li>			
							<li><a href="Logout.jsp" onclick = "AlertLogout()">Logout</a></li>		
						</ul>
					</div>
				</div>
			</div>
		</div>
		<div id="wrapper" class="container">
			<section class="navbar main-menu">
				<div class="navbar-inner main-menu">				
					<a href="index.html" class="logo pull-left"><img src="themes/images/logo.png" class="site_logo" alt=""></a>
					<nav id="menu" class="pull-right">
						<ul>
							<li><a href="womendisplay.jsp">Woman</a></li>		
							
																				
							<li><a href="mendisplay.jsp">Man</a></li>			
							<li><a href="sportdisplay.jsp">Sport</a>
							
							<li><a>Shipping</a>		
							<ul>
									<li><a href="shipuser.jsp">Shipping User</a></li>									
									<li><a href="shipprofile.jsp">Shipping Admin</a></li>
																	
								</ul>
										</li>	
								
													
							
						</ul>
					</nav>
				</div>
			</section>
			<section  class="homepage-slider" id="home-slider">
				<div class="flexslider">
					<ul class="slides">
						<li>
							<img src="themes/images/carousel/banner-1.jpg" alt="" />
						</li>
						<li>
							<img src="themes/images/carousel/banner-2.jpg" alt="" />
							<div class="intro">
								<h1>Mid season sale</h1>
								<p><span>Up to 50% Off</span></p>
								<p><span>On selected items online and in stores</span></p>
							</div>
						</li>
					</ul>
				</div>			
			</section>
			<section class="header_text">
				<!-- You can write here!!! -->	
			</section>
			<section class="main-content">
				<div class="row">
					<div class="span12">													
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">Our <strong>Products</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel" data-slide="prev"></a><a class="right button" href="#myCarousel" data-slide="next"></a>
									</span>
								</h4>
								<div id="myCarousel" class="myCarousel carousel slide">
									<div class="carousel-inner">
										<div class="active item">
											<ul class="thumbnails">												
												<li class="span3">
												
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a><img src="themes/images/ladies/1.jpg" alt="" /></a></p>
														<a class="title">Ut wisi enim ad</a><br/>
														<a class="category">Commodo consequat</a>
														<p class="price">$17.25</p>
													</div>
												</li>
												
												
												
												<li class="span3">
													<div class="product-box">
														<span class="sale_tag"></span>
														<p><a><img src="themes/images/ladies/6.jpg" alt="" /></a></p>
														<a class="title">Quis nostrud exerci tation</a><br/>
														<a class="category">Quis nostrud</a>
														<p class="price">$32.50</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a><img src="themes/images/ladies/8.jpg" alt="" /></a></p>
														<a class="title">Know exactly turned</a><br/>
														<a class="category">Quis nostrud</a>
														<p class="price">$14.20</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a><img src="themes/images/ladies/2.jpg" alt="" /></a></p>
														<a class="title">You think fast</a><br/>
														<a class="category">World once</a>
														<p class="price">$31.45</p>
													</div>
												</li>
											</ul>
										</div>
										<div class="item">
											<ul class="thumbnails">
												<li class="span3">
													<div class="product-box">
														<p><a><img src="themes/images/ladies/4.jpg" alt="" /></a></p>
														<a class="title">Know exactly</a><br/>
														<a class="category">Quis nostrud</a>
														<p class="price">$22.30</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a><img src="themes/images/ladies/7.jpg" alt="" /></a></p>
														<a class="title">Ut wisi enim ad</a><br/>
														<a class="category">Commodo consequat</a>
														<p class="price">$40.25</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a ><img src="themes/images/ladies/3.jpg" alt="" /></a></p>
														<a class="title">You think water</a><br/>
														<a class="category">World once</a>
														<p class="price">$10.45</p>
													</div>
												</li>
												<li class="span3">
													<div class="product-box">
														<p><a ><img src="themes/images/ladies/5.jpg" alt="" /></a></p>
														<a class="title">Quis nostrud exerci</a><br/>
														<a class="category">Quis nostrud</a>
														<p class="price">$35.50</p>
													</div>
												</li>																																	
											</ul>
										</div>
									</div>							
								</div>
							</div>						
						</div>
						<br/>
						<div class="row">
							<div class="span12">
								<h4 class="title">
									<span class="pull-left"><span class="text"><span class="line">All <strong>Products</strong></span></span></span>
									<span class="pull-right">
										<a class="left button" href="#myCarousel-2" data-slide="prev"></a><a class="right button" href="#myCarousel-2" data-slide="next"></a>
									</span>
								</h4>
								 <%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement= connection.createStatement();
String sql ="select * from `post` where category = 'women' ";
resultSet = statement.executeQuery(sql);
int i=0;
while(resultSet.next()){
%>

			 <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table">
              </i><h4></h4></div>
            <div class="card-body">
              <div class="table-responsive">
              <br>
              <br>
               
               <table class="table table-bordered" id="dataTable" width="80%" margin-top="30px" >
                
                  <thead>
                    
                
					
					 <tr>
						<td  class="topic" style="padding: 20px; font-size: 25px;">
						<center>
						<img width=200px height=250px  border="3" style = "border-color:  "src="<%=resultSet.getString("profilePic") %>">
						</center>
						
					</tr>
						
					
					  <tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> Product Name : <font color = "#2980b9"><b><%=resultSet.getString("productname") %></b> </font></p>
							
						</td>
					</tr>	
					  <tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> Price (Rs): <font color = "#2980b9"><b><%=resultSet.getString("price") %></b> </font></p>
							
						</td>
					</tr>	
					  <tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> Contact Number : <font color = "#2980b9"><b><%=resultSet.getString("contactnumber") %></b> </font></p>
							
						</td>
					</tr>	
					  <tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> Category : <font color = "#2980b9"><b><%=resultSet.getString("category") %></b> </font></p>
							
						</td>
					</tr>
					 <tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> Description : <font color = "#2980b9"><b><%=resultSet.getString("dec") %></b> </font></p>
							
						</td>
					</tr>
					<tr class="cont">
						<td  class="cont" style="padding: 10px; ">
							<p style = "font-size: 20px; margin-left:30px; "><font color = "red"><strong></strong></font> <a href = "AddOrder.jsp">Add To Cart</a></p>
							
						</td>
					</tr>
					
					
					
                  
                  </thead>
                  
                  
                  
                  
      </table>
      
      </div>
      </div>
      </div>
   
      
      
     
<%
i++;
}

} catch (Exception e) {
e.printStackTrace();
}
%>

			</section>
			
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contac Us</a></li>
							<li><a href="./cart.html">Your Cart</a></li>
							<li><a href="Logout.jsp" onclick = "AlertLogout()">Logout</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>My Account</h4>
						<ul class="nav">
							<li><a href="entry/member/myProfile?name=<%=un%>">My Account</a></li>
						</ul>
					</div>
					<div class="span5">
						<p class="logo"><img src="themes/images/logo.png" class="site_logo" alt=""></p>
						<p>To be successful, you have to use each day as an opportunity to improve, to be better, to get a little bit closer to your goals. It might sound like a lot of work—and with a busy schedule, next to impossible. But the best part is, the more you accomplish, the more you’ll want to do, the higher you’ll want to reach. So  as long as you have the hunger for success, you will always have the power within you to achieve it.</p>
						<br/>
						<span class="social_icons">
							<a class="facebook" href="#">Facebook</a>
							<a class="twitter" href="#">Twitter</a>
							<a class="skype" href="#">Skype</a>
							<a class="vimeo" href="#">Vimeo</a>
						</span>
					</div>					
				</div>	
			</section>
			<section id="copyright">
				<span>Copyright 2019 Sell'N'Bye Company. All right reserved.</span>
			</section>
		</div>
		<script src="themes/js/common.js"></script>
		<script src="themes/js/jquery.flexslider-min.js"></script>
		<script type="text/javascript">
			$(function() {
				$(document).ready(function() {
					$('.flexslider').flexslider({
						animation: "fade",
						slideshowSpeed: 4000,
						animationSpeed: 600,
						controlNav: false,
						directionNav: true,
						controlsContainer: ".flex-container" // the container that holds the flexslider
					});
				});
			});
			
			function AlertLogout(){
				alert("Logged out");
			}
		</script>	
	
</body>
</html>