

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class userbookingdispalyservlet
 */
@WebServlet("/userbookingdispalyservlet")
public class userbookingdispalyservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public userbookingdispalyservlet() {
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
		int booking_id,hotel_id;
		String check_in_date,check_out_date,type_of_room;
		
		try
		 {
			
			int s=(int)session.getAttribute("user_id");
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			PreparedStatement ps= null;
			ResultSet rs=null;
			String query="select id,hotel_id,check_in_date,check_out_date,type_of_room from booking_details where user_id = ?";
			ps=con.prepareStatement(query);
			ps.setInt(1, s);
			rs=ps.executeQuery();
			while(rs.next())  
			{
				 booking_id= rs.getInt(1);
				 hotel_id=rs.getInt(2);
				 check_in_date=rs.getString(3);
				 check_out_date=rs.getString(4);
				 type_of_room =rs.getString(5);
				
				/*
				out.println("booking_id:"+booking_id);
				out.println("hotel_id :"+hotel_id);
				out.println("check-in_date :"+check_in_date);
				out.println("check_out_date :"+check_out_date);
				out.println("type-of_room_booked :"+type_of_room); */
				
				//out.print("\n");
				 out.print("hello");
				 session.setAttribute("booking_id",  booking_id);
				 session.setAttribute("hotel_id",  hotel_id);
				 session.setAttribute("check_in_date", check_in_date);
				 session.setAttribute("check_out_date",check_out_date  );
				 session.setAttribute("type_of_room", type_of_room );
				 response.sendRedirect("mybooking.jsp");
				 
			}
		
			
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
