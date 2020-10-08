/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** AddTripServlet for Web Golf Scores Assessment
// ** By Robert Fox, October 7, 2020
// *******************************************************
// **
// ** AddTripServlet.java instantiates a GolfScoreHelper
// ** object to generate a list of golf scores to be 
// ** displayed by new-trip.jsp so the user can select
// ** which golf score instance to associate with the
// ** golf trip object.
*/

package controller;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GolfScore;
import model.GolfTrip;

/**
 * Servlet implementation class AddTripServlet
 */
@WebServlet("/addTripServlet")
public class AddTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// instantiate a GolfScoreHelper object
		GolfScoreHelper dao = new GolfScoreHelper();
		
		// set the list of all golf score instances in the 
		// database to be picked up by new-trip.jsp
		request.setAttribute("allGolfScores", dao.showAllScores());
		
		// set the path to be new-trip.jsp
		String path = "/new-trip.jsp";
		
		// if there are no golf scores to display
		// then set the path to index.html
		if (dao.showAllScores().isEmpty()) {
			path = "/index.html";
		}
		
		// fire up the next path
		getServletContext().getRequestDispatcher(path).forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
