<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CINEMAOYA</title>
</head>
<body>
<style>
div {
	background-image: url('back.jpg');
	background-size: 1550px 600px;
	padding: 0px;
	margin: 0px;
	background-repeat: no-repeat;
}

.navbar {
	width: 45%;
	margin:auto;
	height: 75px;
	border-radius: 20px;
	background: #C33737;
	opacity: 0.6;
}

ul {
	list-style: none;
	padding: 0px;
	margin: 0px;
	position: absolute;
}

ul li {
	float: left;
	margin-top: 25px;
	margin-left: 60px;
}

a {
	text-decoration: none;
	color: #17202A;
	font-size: 20px;
}

a:hover {
	background: #FFCC00;
	border-radius: 15px
}

.a:hover {
	background: none;
}
</style>
</head>
<body style="text-align: center;">
	<div>
	<% 
		session.setAttribute("username", "Guest");
		session.setAttribute("usertype", 4);
		session.setAttribute("isActive", 0);
		 
		String name = session.getAttribute("username").toString();
		 out.println("<h1 style='text-align:right; color:white;'>" + name +"</h1>");
		 %>
		<br> <br> <br> <a href="index.jsp"><h1
				style="text-align: center; font-size: 50px; color: #17202A; text-shadow: #17202A 0 -1px 4px, #D1F2EB 0 -2px 10px, #D1F2EB 0 -10px 20px, #D1F2EB 0 -18px 40px;">CINEMAOYA</h1></a>
		<br> <br>
		
		
		
		<br>
		<div class="navbar">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="login.jsp">Log in</a></li>
				<li><a href="register_page.jsp">Register</a></li>
			</ul>
		</div>
		<br> <br> <br> <br>
	</div>
	<br>
	<h1>Here you can see all the available movies!</h1>
	<form method="get" action="ContentAdmin_Panel">
		<input type="submit" value="Show All Movies">
	</form>

</body>
</html>