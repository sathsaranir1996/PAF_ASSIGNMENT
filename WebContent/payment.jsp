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
							<li><a href="entry/member/myProfile?name=<%=un%>">My Account</a></li>
							<li><a href="orderprofile.jsp?name=<%=un%>">Your Cart</a></li>
							
								
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
								<h1>Sell'n'bye way to the best</h1>
								<p><span>Up to 10000+ customers</span></p>
								<p><span>Reliable and efficient service 24/7</span></p>
								<p><span>Top Most e-commerce System</span></p>
							</div>
						</li>
					</ul>
				</div>			
			</section>
			<br/>
			<br/>
			<br/>
			 <!-- DataTables Example -->
          <div class="card mb-3">
            <div class="card-header">
              <i class="fas fa-table">
              </i><h4>Payment Details</h4><br></div>
            <div class="card-body">
              <div class="table-responsive">
              <br>
              <br>
                <table class="table table-bordered" id="dataTable">
                  <thead>
                    <tr>
                     
                      <th>product Name</th>
                      <th>Payment Type</th>
                      <th>Payment Status</th>
                     
                     
                    </tr>
                     <%
try{
connection = DriverManager.getConnection(connectionUrl+database, userid, password);
statement= connection.createStatement();
String sql ="select * from `payment`";
resultSet = statement.executeQuery(sql);
int i=0;
while(resultSet.next()){
%>
                  </thead>
                  
                   <tbody>
                    <tr>
                       <td><%=resultSet.getString("productname") %></td>
                      
                      <td><%=resultSet.getString("paymenttype") %></td>
                      <td><%=resultSet.getString("paymentstatus") %></td>
                      
                     <td> <a href = "entry/payment/generateUpdateForm?paymentid=<%=resultSet.getString("paymentid")%>">Update</a></td> 
                     <td> <a href = "entry/payment/deactivate?paymentid=<%=resultSet.getString("paymentid")%>">Delete</a></td> 
                     
                    
                      
                      
                      
                    </tr>
<%
i++;
}

} catch (Exception e) {
e.printStackTrace();
}
%>
                    
                  
                   

                  
                  
                </table>
              </div>
            </div>
            
          </div>
						
					</div>
						<br/>
			<br/>
			<br/>
			
			<section id="footer-bar">
				<div class="row">
					<div class="span3">
						<h4>Navigation</h4>
						<ul class="nav">
							<li><a href="./index.html">Homepage</a></li>  
							<li><a href="./about.html">About Us</a></li>
							<li><a href="./contact.html">Contact Us</a></li>
							<li><a href="logReg.jsp">Login</a></li>							
						</ul>					
					</div>
					<div class="span4">
						<h4>Others</h4>
						<ul class="nav">
							<li><a href="#">Our Clients</a></li>
							<li><a href="#">FAQ</a></li>
							<li><a href="#">Register</a></li>
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
				<span>Copyright 2019 Sell'N'Bye Company, All right reserved.</span>
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