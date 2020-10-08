/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** ViewAllTripsServlet for Web Golf Scores Assessment
// ** By Robert Fox, October 7, 2020
// *******************************************************
// **
// ** ViewAllTripsServlet.java uses a GolfTripHelper		
// ** object to get and display the golf trips stored		 
// ** in the database.
*/

package controller;

import java.util.List;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GolfTrip;

/**
 * Servlet implementation class ViewAllTripsServlet
 */
@WebServlet("/viewAllTripsServlet")
public class ViewAllTripsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllTripsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			// instantiate a GolfTripHelper object so
			// we can use the showAllTrips method
			GolfTripHelper gth = new GolfTripHelper();
			
			// get a list of all the golf trips in the
			// database
			List<GolfTrip> abc = gth.showAllTrips();
			
			// set the list of all golf trips to be
			// picked up by golf-trips-list.jsp
			request.setAttribute("allTrips", abc);
			
			if (abc.isEmpty()) {
				request.setAttribute("allTrips", " ");
			}
			
			// fire up golf-trips-list.jsp
			getServletContext().getRequestDispatcher("/golf-trips-list.jsp").forward(request, response);
		}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
