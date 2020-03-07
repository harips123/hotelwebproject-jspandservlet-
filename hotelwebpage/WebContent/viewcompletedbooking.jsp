<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<title>Insert title here</title>
</head>
<body>

<%@ page import="java.sql.*" %>
<%
out.println("<html><body>");

try{
	int s=(int)session.getAttribute("user_id");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
	PreparedStatement ps=null;
	ResultSet rs=null;
	String query="select * from booking_details where user_id = ? and check_in_date < curdate() order by check_in_date";
	ps=con.prepareStatement(query);
	ps.setInt(1, s);
	rs=ps.executeQuery();
	out.print("<div class='container'>");
	out.print("<h2>completed Bookings</h2>");
	out.print(" <table class= 'table table-striped'>");
	out.println("<thead><tr><th>booking_id</th><th>hotel_id</th><th>check_in_date</th><th>check_out_date</th><th>type_of_booked</th><tr></thead>"); 
	while(rs.next())
	{
		int booking_id= rs.getInt(1);
		int hotel_id = rs.getInt(2);
		String check_in_date = rs.getString(5);
		String check_out_date = rs.getString(6);
		String type_of_room = rs.getString(7);
		
	
		out.println("<tbody><tr><td>" + booking_id + "</td><td>" +hotel_id+ "</td><td>" +check_in_date + "</td><td>"+check_out_date+"</td><td>"+type_of_room+"</td></tr></tbody>");
		
	}
	
	 out.println("</table>");  
	 out.println("</div>");
   out.println("</html></body>");
}catch(Exception e)
{
	out.print(e);
}

%>

</body>
</html>