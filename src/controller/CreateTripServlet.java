/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** AddTripServlet for Web Golf Scores Assessment
// ** By Robert Fox, October 7, 2020
// *******************************************************
// **
// ** CreateTripServlet.java executes after the user has
// ** submitted a new golf trip on new-trip.jsp.  
// ** The golf trip date and golf score are brought
// ** in from new-trip.jsp and persisted to the 
// ** database.
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
 * Servlet implementation class CreateTripServlet
 */
@WebServlet("/createTripServlet")
public class CreateTripServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreateTripServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// get the month, day and year 
		String month = request.getParameter("month");
		String day = request.getParameter("day");
		String year = request.getParameter("year");
		
		System.out.println("Date entered is " + month + "-" + day + "-" + year);
		
		// instantiate a LocalDate object to hold the 
		// golf trip date.  If the date is not valid,
		// then use the current date.
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
		
		// pull in the selected golf score from new-trip.jsp
		String[] selectedScore = request.getParameterValues("theGolfScore");
		
		// instantiate a GolfScore object to hold the golf
		// score
		GolfScore gs = new GolfScore();
		
		// make sure one and only one score was selected.  otherwise set the golf score as 
		// course = Invalid and score = zero
		if (selectedScore != null && selectedScore.length == 1) {
				System.out.println(selectedScore[0]);
				gs = gsh.searchForGolfScoreById(Integer.parseInt(selectedScore[0]));
		} else {
			gs.setGolfCourse("Invalid");
			gs.setGolfScore(0);
		}
		
		// instantiate a GolfTripHelper object
		// so we can insert the golf trip to the
		// database
		GolfTripHelper gth = new GolfTripHelper();
		
		// instantiate a GolfTrip object passing
		// the trip date and golf score to the 
		// constructor
		GolfTrip gt = new GolfTrip(ld, gs);

		// add the golf trip to the database
		gth.insertTrip(gt);
		
		System.out.println("Success!");
		System.out.println(gt.toString());
		
		// fire up the view all trips servlet to 
		// display an updated list of golf trips
		getServletContext().getRequestDispatcher("/viewAllTripsServlet").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
