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
 * Servlet implementation class Book
 */
@WebServlet("/Book")
public class Book extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Book() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PreparedStatement prepared_stmt = null;
		ResultSet rs = null;
		Connection c = null;
		String film_name , date , time ;
		int room , id, price=7;
		try {
			
			HttpSession session = request.getSession();
			
			response.setContentType("html\text");
			c = DriverManager
	        		.getConnection("jdbc:postgresql://localhost:5432/WEB_programming",
	                        "postgres", "12345678910");
			String sql = "select schedule_id,film_name , stime , sdate, room_id from schedule order by film_name ;" ;
			
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
			
				out.print(
						"			<ul>\r\n" + 
						"				<li><a href=\"index.jsp\">Home</a></li>\r\n" + 
						"				<li><a href=\"successful.jsp\">Actions</a></li>\r\n" + 
						"				<li><a href=\"logout.jsp\">Log out</a></li>\r\n" + 
						"			</ul>\r\n" + 
						"		</div>\r\n" + 
						"\r\n" + 
						"		<br> <br> <br> <br>\r\n" +  
						"	</div>\r\n" + 
						"<table style='text-align:center;'><tr><td><b>Id</b></td><td> </td><td>FilmName</td><td> </td><td>Date</td><td>  </td><td>  </td><td>Time</td><td> </td><td>Room</td><td> </td><td>Price</td></tr>");
				

					while (rs.next()) {
				film_name = rs.getString(2);
				time = rs.getString(3);
				date = rs.getString(4);
				id = rs.getInt(1);
				room= rs.getInt(5);
		
				out.println("<tr><td><b>" + Integer.toString(id) + "</b></td><td> </td><td>" + film_name + "</td><td> </td><td>" + date + "</td><td>  </td><td>  </td><td>" + time + "</td><td> </td><td>" + Integer.toString(room)
						+ "</td><td> </td><td>" + Integer.toString(price) + "</td></tr>");


					}
			out.println("</table><form action='BookAction' method= 'post'><table><caption>To book your movie enter the right id</caption><tr><td>Enter id:</td><td><input type='text' name='id'></td></tr>");
			out.println("<tr><td></td><td><input type='submit' value='Book it'></td></tr></table></form></body></html>");
		} catch (Exception e) {

			System.out.println(e);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
