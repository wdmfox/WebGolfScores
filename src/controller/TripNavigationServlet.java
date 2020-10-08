/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** TripNavigationServlet for Web Golf Scores Assessment
// ** By Robert Fox, October 7, 2020
// *******************************************************
// **
// ** TripNavigationServlet.java evaluates the action chosen
// ** by the user (add, edit or delete a golf trip) and 
// ** redirects the application accordingly
*/

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GolfScore;
import model.GolfTrip;

/**
 * Servlet implementation class TripNavigationServlet
 */
@WebServlet("/tripNavigationServlet")
public class TripNavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TripNavigationServlet() {
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
		GolfTripHelper dao = new GolfTripHelper();
		
		// grab the action selected by the user on golf-trips-list.jsp
		String act = request.getParameter("doThisToTrip");
		
		// after all changes, we should redirect to the viewAllTrips servlet
		// The only time we don't is if they select to add a new item or edit
		String path = "/viewAllTripsServlet";
		
		// if deleting a GolfTrip then instantiate a GolfTrip object
		// with the golf trip to delete and use the GolfTripHelper
		// deleteTrip method to get rid of it. 
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				GolfTrip tripToDelete = dao.searchForTripById(tempId);
				dao.deleteTrip(tripToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a trip");
			}
			
		// if editing a trip, instantiate a GolfTrip object with the 
		// golf trip to be edited and pass it to the edit-trip.jsp
		// to be picked up and displayed
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				GolfTrip tripToEdit = dao.searchForTripById(tempId);
				request.setAttribute("tripToEdit", tripToEdit);
				GolfScoreHelper gsh = new GolfScoreHelper();
				request.setAttribute("allGolfScores", gsh.showAllScores());
				path = "/edit-trip.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a trip");
			}
		} else if (act.equals("add")) {
			path = "/index.html";
		}
		
		// redirect to whichever jsp is in the path variable
		getServletContext().getRequestDispatcher(path).forward(request, response);
		
	}

}
