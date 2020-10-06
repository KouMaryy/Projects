<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>CINEMAOYA</title>
<style>
button {
	position: fixed;
	bottom: 30px;
	right: 30px;
	font-size: 20px;
	z-index: 999;
	background: #0ECEC4;
	background: -moz-radial-gradient(center, #A9122F 0%, #FF1C49 50%, #D8183E 100%);
	background: -webkit-radial-gradient(center, #A9122F 0%, #FF1C49 50%, #D8183E 100%);
	background: radial-gradient(ellipse at center, #A9122F 0%, #FF1C49 50%, #D8183E 100%
		);
	outline: none;
	border: 2px solid #000033;
	padding: 10px 20px;
	border-radius: 15px;
	cursor: pointer;
}

button:hover {
	color: white;
	background: #FF9700;
	background: -moz-radial-gradient(center, #FF9700 0%, #E88A00 50%, #875000 100%);
	background: -webkit-radial-gradient(center, #FF9700 0%, #E88A00 50%, #875000 100%);
	background: radial-gradient(ellipse at center, #FF9700 0%, #E88A00 50%, #875000 100%
		);
}

div {
	background-image: url('back.jpg');
	background-size: 1550px 600px;
	padding: 0px;
	margin: 0px;
	background-repeat: no-repeat;
}

.navbar {
	width: 30%;
	margin: auto;
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

td {
	text-align: left;
	font-family: arial;
}

table {
	padding: 10px;
	border-collapse: collapse;
	font-family: arial;
	text-align: left;
	background: white;
	border-radius: 10px;
	border-spacing: 40px;
	margin: auto;
	font-size: 18px;
}
</style>
</head>
<body>
	<div id="top">
		<br> <br> <br> <a href="index.jsp"><h1
				style="text-align: center; font-size: 50px; color: #17202A; text-shadow: #17202A 0 -1px 4px, #D1F2EB 0 -2px 10px, #D1F2EB 0 -10px 20px, #D1F2EB 0 -18px 40px;">CINEMAOYA</h1></a>
		<br> <br> <br>


		<div class="navbar">
			<ul>
				<li><a href="index.jsp">Home</a></li>
				<li><a href="login.jsp">Log out</a></li>
				<li><a href="contadmin.jsp">Actions</a></li>
			</ul>
		</div>

		<br> <br> <br> <br>
	</div>


<form method = "post" action = "Content_Admin">
         <table>
         <caption style="font-size:20px;">Insert new Movie</caption>
			<tr>
				<td>Name</td>
				<td><input type="text" name="movie_name"></td>
			</tr>
			<tr>
				<td>Category</td>
				<td><input type="text" name="movie_category"></td>
			</tr>
			<tr>
				<td>Duration</td>
				<td><input type="text" name="movie_duration"></td>
			</tr>
			<tr>
				<td>Description</td>
				<td><input type="text" name="movie_description"></td>
			</tr>
			<tr>
				<td>Rate</td>
				<td><input type="text" name="movie_rate"></td>
			</tr>
			<tr>
				<td><input type="submit" value="Insert Movie" ></td>
			</tr>
			
			
		</table>
		

</form>
<a href="#top"><button onclick=toTop()>&#8679;</button></a>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>

</body>
</html>