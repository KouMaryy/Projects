package progr;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class ContentAdmin_Panel
 */
@WebServlet("/ContentAdmin_Panel")
public class ContentAdmin_Panel extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContentAdmin_Panel() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PreparedStatement prepared_stmt = null;
		ResultSet rs = null;
		Connection c = null;
		String name,cookname;
		String category;
		String duration;
		String desc;
		int rate;
		try {


			HttpSession session = request.getSession();
			

			response.setContentType("html\text");
			c = DriverManager
	        		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
	                        "postgres", "12345678910");
			String sql = "SELECT * FROM Movie";
			prepared_stmt = c.prepareStatement(sql);
			rs = prepared_stmt.executeQuery();
			PrintWriter out = response.getWriter();
			out.println(
					"<html>\r\n" + 
					"<head>\r\n" + 
					"<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\r\n" + 
					"<title>CINEMAOYA</title>\r\n" + 
					"<style>\r\n" + 
					"div {\r\n" + 
					"	background-image: url('back.jpg');\r\n" + 
					"	background-size: 1550px 600px;\r\n" + 
					"	padding: 0px;\r\n" + 
					"	margin: 0px;\r\n" + 
					"	background-repeat: no-repeat;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					".navbar {\r\n" + 
					"	width: 45%;\r\n" + 
					"	margin:auto;\r\n" + 
					"	height: 75px;\r\n" + 
					"	border-radius: 20px;\r\n" + 
					"	background: #C33737;\r\n" + 
					"	opacity: 0.6;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"ul {\r\n" + 
					"	list-style: none;\r\n" + 
					"	padding: 0px;\r\n" + 
					"	margin: 0px;\r\n" + 
					"	position: absolute;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"ul li {\r\n" + 
					"	float: left;\r\n" + 
					"	margin-top: 25px;\r\n" + 
					"	margin-left: 60px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"a {\r\n" + 
					"	text-decoration: none;\r\n" + 
					"	color: #17202A;\r\n" + 
					"	font-size: 20px;\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"a:hover {\r\n" + 
					"	background: #FFCC00;\r\n" + 
					"	border-radius: 15px\r\n" + 
					"}\r\n" + 
					"\r\n" + 
					"p {\r\n" + 
					"	style =\"font-size: 20px\";\r\n" + 
					"}\r\n" + 
					"table {\r\n" + 
					"	padding: 10px;\r\n" + 
					"	border-collapse: collapse;\r\n" + 
					"	font-family: arial;\r\n" + 
					"	text-align: left;\r\n" + 
					"	background: white;\r\n" + 
					"	border-radius: 10px;\r\n" + 
					"	border-spacing: 40px;\r\n" + 
					"	margin: auto;\r\n" + 
					"	font-size: 18px;\r\n" + 
					"}\r\n" + 
					"</style>\r\n" + 
					"</head>\r\n" + 
					"<body>\r\n" + 
					"\r\n" + 
					"	<div>\r\n" + 
					"		<br> <br> <br> <a href=\"index.jsp\"><h1\r\n" + 
					"				style=\"text-align: center; font-size: 50px; color: #17202A; text-shadow: #17202A 0 -1px 4px, #D1F2EB 0 -2px 10px, #D1F2EB 0 -10px 20px, #D1F2EB 0 -18px 40px;\">CINEMAOYA</h1></a>\r\n" + 
					"		<br> <br> <br>\r\n" + 
					"\r\n" + 
					"\r\n" + 
					"		<div class=\"navbar\">\r\n");
			
			
			System.out.println(session.getAttribute("usertype").toString());
			if(session.getAttribute("usertype").equals(4)) {
				out.print(
						"			<ul>\r\n" + 
						"				<li><a href=\"index.jsp\">Home</a></li>\r\n" + 
						"				<li><a href=\"login.jsp\">Log in</a></li>\r\n" + 
						"				<li><a href=\"register_page.jsp\">Register</a></li>\r\n" + 
						"			</ul>\r\n" + 
						"		</div>\r\n" + 
						"\r\n" + 
						"		<br> <br> <br> <br>\r\n" + 
						"	</div>\r\n" + 
						"<h1>FILMS</h1><table><tr><td>Name</td><td>Category</td><td>Duration</td><td>Description</td><td>Rate</td>");
				
			}else {
			out.print(
					"			<ul>\r\n" + 
					"				<li><a href=\"index.jsp\">Home</a></li>\r\n" +  
					"				<li><a href=\"successful.jsp\">Navigate as a guest</a></li>\r\n" +  
					"			</ul>\r\n" + 
					"		</div>\r\n" + 
					"\r\n" + 
					"		<br> <br> <br> <br>\r\n" + 
					"	</div>\r\n" + 
					"<h1>FILMS</h1><table><tr><td>Name</td><td>Category</td><td>Duration</td><td>Description</td><td>Rate</td>");
			}
					while (rs.next()) {
				name = rs.getString(1);
				category = rs.getString(2);
				duration = rs.getString(3);
				desc = rs.getString(4);
				rate = rs.getInt(5);
				out.println("<tr><td>" + name + "</td><td>" + category + "</td><td>" + duration + "</td><td>" + desc
						+ "</td><td>" + rate + "</td>");

				
			}
			out.println("</table></body></html>");

		} catch (Exception e) {

			System.out.println("Something went wrong");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
