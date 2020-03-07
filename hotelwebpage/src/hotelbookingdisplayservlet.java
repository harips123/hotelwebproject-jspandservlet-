

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
//import java.util.ArrayList;

//import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class hotelbookingdisplayservlet
 */
@WebServlet("/hotelbookingdisplayservlet")
public class hotelbookingdisplayservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public hotelbookingdisplayservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();
		//ArrayList row = new ArrayList();
		// ArrayList<hotelbookingdisplayservlet> hotelbookings = new ArrayList<hotelbookingdisplayservlet>();
		// hotelbookingdisplayservlet hotelbooking = null;
		out.println("<html><body>");
		try
		 {
			
			int s=(int)session.getAttribute("hotel_id");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			PreparedStatement ps= null;
			ResultSet rs=null;
			String query="select id,user_id,check_in_date,check_out_date,type_of_room from booking_details where hotel_id = ?";
			ps=con.prepareStatement(query);
			ps.setInt(1, s);
			rs=ps.executeQuery();
			out.print("<hr align=center size=5 width=100%>");
			out.print("<table border=1 bgcolor=lightblue>");
			out.println("<tr><th>booking_id</th><th>user_id</th><th>check_in_date</th><th>check_out_date</th><th>type_of_room</th><tr>"); 
			while(rs.next())  
			{ 
				int booking_id= rs.getInt(1);
				int user_id = rs.getInt(2);
				String check_in_date = rs.getString(3);
				String check_out_date = rs.getString(4);
				String type_of_room = rs.getString(5);
				
				out.println("<tr><td>" + booking_id + "</td><td>" +user_id+ "</td><td>" + check_in_date + "</td><td>"+check_out_date+"</td><td>"+type_of_room+"</td></tr>"); 
				
				/*out.println("booking_id 	:"+booking_id);
				out.println("user_id 		:  "+user_id);
				out.println("check_in_date  :"+check_in_date);
				out.println("check_out_date :"+check_out_date);
				out.println("type_of_room 	:"+type_of_room);
				out.print("\n");*/
	
				//request.setAttribute("booking_id", booking_id);
				
				//response.sendRedirect("view.jsp");
			
			}
			out.print("</center>");
			 out.println("</table>");  
            out.println("</html></body>");
			}catch(Exception e)
		{
				e.printStackTrace();
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
