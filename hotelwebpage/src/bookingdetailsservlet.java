

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import java.sql.*;

/**
 * Servlet implementation class bookingdetailsservlet
 */
@WebServlet("/bookingdetailsservlet")
public class bookingdetailsservlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public bookingdetailsservlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String hotel_id= request.getParameter("hotel_id");
		String user_id=request.getParameter("user_id");
		String total_number_of_pax= request.getParameter("total_number_of_pax");
		String check_in_date = request.getParameter("check_in_date");
		String check_out_date =request.getParameter("check_out_date");
		String type_of_room= request.getParameter("type_of_room");
		PrintWriter out=  response.getWriter();
		try {
			
			Class.forName("com.mysql.jdbc.Driver");
			Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/hotelwebpage","root","root");
			PreparedStatement ps= (PreparedStatement)con.prepareStatement("insert into booking_details(hotel_id,user_id,total_number_of_pax,check_in_date,check_out_date,type_of_room)values(?,?,?,?,?,?)");
			
			{
			ps.setString(1, hotel_id );
			ps.setString(2, user_id );
			ps.setString(3, total_number_of_pax);
			ps.setString(4,check_in_date);
			ps.setString(5, check_out_date);
			ps.setString(6, type_of_room);
			ps.executeUpdate();
			out.print("successfully");
			response.sendRedirect("userbookingdispalyservlet");
			}
		}
		catch(Exception e)
		{ 
			e.printStackTrace();
		}
	}

}
