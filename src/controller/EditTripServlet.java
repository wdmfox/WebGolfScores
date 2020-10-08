/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** EditTripServlet for Web Golf Scores Assessment
// ** By Robert Fox, October 7, 2020
// *******************************************************
// **
// ** EditTripServlet.java executes after the user has
// ** edited an existing golf trip on edit-trip.jsp.  
// ** The golf trip date and golf score are brought
// ** in from edit-trip.jsp and merged back to the 
// ** database.
*/

package controller;

import java.io.IOException;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GolfScore;
import model.GolfTrip;

/**
 * Servlet implementation class EditTripServlet
 */
@WebServlet("/editTripServlet")
public class EditTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditTripServlet() {
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
		// instantiate a GolfScoreHelper object
		GolfTripHelper dao = new GolfTripHelper();
		
		// get the month, day and year off of edit-trip.jsp
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		System.out.println("Date entered is " + month + "-" + day + "-" + year);
		
		// instantiate a LocalDate object to hold the
		// trip date.  if the date is invalid, then 
		// use the current date.
		LocalDate ld;
		try {
			ld = LocalDate.of(Integer.parseInt(year),
							  Integer.parseInt(month),
							  Integer.parseInt(day));
		} catch (NumberFormatException ex) {
			ld = LocalDate.now();
		}
		
		// instantiate a GolfScoreHelper object so we can
		// later use the searchForGolfScoreById method
		GolfScoreHelper gsh = new GolfScoreHelper();
		
		// get the selected golf score from edit-trip.jsp
		String[] selectedScore = request.getParameterValues("theGolfScore");
		
		// instantiate a GolfScore object to store
		// the golf score
		GolfScore gs = new GolfScore();
		
		// make sure one and only one score was selected.  otherwise set the golf score as 
		// course = Invalid and score = zero
		if (selectedScore != null && selectedScore.length == 1) {
				System.out.println(selectedScore[0]);
				gs = gsh.searchForGolfScoreById(Integer.parseInt(selectedScore[0]));
		} else {
			Integer tempId = Integer.parseInt(request.getParameter("scoreid"));
			gs = gsh.searchForGolfScoreById(tempId);
		}
		
		// capture the primary key "id" associated with the golf trip
		// instance in the database from the edit-trip.jsp
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		// instantiate a GolfTrip object using the searchForTripById
		// method of the GolfTripHelper object 
		GolfTrip tripToUpdate = dao.searchForTripById(tempId);
		
		// set the trip date
		tripToUpdate.setTripDate(ld);
		
		// use the setgScore method to update the GolfTrip's
		// object's golf score property
		tripToUpdate.setgScore(gs);
		
		// update the golf trip
		dao.updateTrip(tripToUpdate);
		
		// display the current golf tripss for the user
		getServletContext().getRequestDispatcher("/viewAllTripsServlet").forward(request, response);
		
	}

}
