<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
  <link rel="stylesheet" type="text/css" href="css/userwelcomepage.css"></link>   
<title>Insert title here</title>
</head>
<body>
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
<div class="media border p-3">
<img src="image/download(1).jpg"  style="width:25px;"></div>
  <!-- Links -->
  <ul class="navbar-nav">
    <li class="nav-item">
      <a class="nav-link" href="userwelcome.jsp">hotel</a>
    </li>
        <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="" id="navbardrop" data-toggle="dropdown">
        My Booking
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="viewallmybookings.jsp">view all my bookings</a>
        <a class="dropdown-item" href="viewcancelledbooking.jsp">view cancelled bookings</a>
        <a class="dropdown-item" href="viewcompletedbooking.jsp">completed </a>
        <a class="dropdown-item" href="viewupcomingbooking.jsp">upcoming </a>
      </div>
    </li>
    <!-- Dropdown -->
    <li class="nav-item dropdown">
      <a class="nav-link dropdown-toggle" href="profile.jsp" id="navbardrop" data-toggle="dropdown">
        user profile
      </a>
      <div class="dropdown-menu">
        <a class="dropdown-item" href="userviewpage.jsp">view profile</a>
        <a class="dropdown-item" href="userlogoutservlet">logout</a>
        <a class="dropdown-item" href="userdeleteservlet">delete account</a>
      </div>
    </li>
  </ul>
</nav>
<%@ page import="java.sql.*" %>
<% String  check_in_date,check_out_date,type_of_room;
  int booking_id,hotel_id,dif,price; 
  float price_to_be_paid;%>
<%
out.println("<html><body>");

try{

	int s=(int)session.getAttribute("user_id");
	Class.forName("com.mysql.jdbc.Driver");
	Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
	PreparedStatement ps= null;
	ResultSet rs=null;
	String query="select id,hotel_id,check_in_date,check_out_date,type_of_room,price from booking_details where user_id = ? and is_cancelled = 0 "; 
	ps=con.prepareStatement(query);
	ps.setInt(1, s);
	rs=ps.executeQuery();
	
	out.print("<div class='container'>");
	out.print("<h2>All My Bookings</h2>");
	out.print(" <table class= 'table table-striped'>");
	out.println("<thead><tr><th>booking_id</th><th>hotel_id</th><th>check_in_date</th><th>check_out_date</th><th>type_of_room</th><th>price to be paid </th><tr></thead>"); 
	while(rs.next())
	{
		 booking_id= rs.getInt(1);
		 hotel_id=rs.getInt(2);
		 check_in_date=rs.getString(3);
		 check_out_date=rs.getString(4);
		 type_of_room =rs.getString(5);
		 price=rs.getInt(6);
	//	out.println("<text-align=center>");
         out.println("<tr><td>" + booking_id + "</td><td>" +hotel_id + "</td><td>" + check_in_date + "</td><td>"+check_out_date+"</td><td>"+type_of_room+"</td><td>"+price+"</td><td><a href='roomcancel.jsp?booking_id="+booking_id+"&amp;hotel_id="+hotel_id+"'>cancel</a></td></tr>"); 
         session.setAttribute("hotel_id",  hotel_id);
		//out.println("booking_id:"+booking_id);
	//	out.println("hotel_id :"+hotel_id);
		//out.println("check-in_date :"+check_in_date);
		//out.println("check_out_date :"+check_out_date);
		//out.println("type-of_room_booked :"+type_of_room); 
		
		
		out.print("\n");
		/* out.print("hello");
		 session.setAttribute("booking_id",  booking_id);
		 session.setAttribute("hotel_id",  hotel_id);
		 session.setAttribute("check_in_date", check_in_date);
		 session.setAttribute("check_out_date",check_out_date  );
		 session.setAttribute("type_of_room", type_of_room );
		 response.sendRedirect("mybooking.jsp");*/
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