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



<%! String cancel_id,booking_id,user_id,reason_to_cancel; %>
<%@ page import="java.sql.*" %>
<%
out.println("<html><body>");

try{
	int s=(int)session.getAttribute("user_id");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
	PreparedStatement ps=null;
	ResultSet rs=null;
	String query="select * from booking_cancel_details where user_id = ?";
	ps=con.prepareStatement(query);
	ps.setInt(1, s);
	rs=ps.executeQuery();
	out.print("<div class='container'>");
	out.print("<h2>Cancelled Bookings</h2>");
	out.print(" <table class= 'table table-striped'>");
	out.println("<thead><tr><th>cancel_id</th><th>booking_id</th><th>user_id</th><th>User Name</th><th>reason to cancel</th><tr></thead>"); 
	while(rs.next())
	{
		cancel_id=rs.getString(1);
		booking_id=rs.getString(2);
		user_id=rs.getString(3);
		reason_to_cancel=rs.getString(4);
		out.println("<tbody><tr><td>" + cancel_id + "</td><td>" +booking_id+ "</td><td>" + user_id + "</td><td>"+session.getAttribute("user_name")+"</td><td>"+reason_to_cancel+"</td></tr></tbody>");
		
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