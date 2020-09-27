/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 5 - Assessment
// ** Start Program for Web Golf Scores Assessment
// ** By Robert Fox, September 24, 2020
// *******************************************************
// **
// ** AddScoreServlet.java will add a new golf score
// ** that is entered by the user
*/

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.GolfScore;

/**
 * Servlet implementation class AddScoreServlet
 */
@WebServlet("/addScoreServlet")
public class AddScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddScoreServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Capture the input values for course and score entered by the user
		String course = request.getParameter("course");
		String scoreStr = request.getParameter("score");
		
		// Cast the string containing the score to an int
		int score = Integer.parseInt(scoreStr);
		
		// Instantiate a GolfScore object to store the course and score
		GolfScore gs = new GolfScore(course, score);
		
		// Instantiate a GolfScoreHelper object 
		GolfScoreHelper dao = new GolfScoreHelper();
		
		// Use the GolfScoreHelper insertScore method to 
		// insert the GolfScore object into the database
		dao.insertScore(gs);
		
		// Send user to the index.html page
		getServletContext().getRequestDispatcher("/index.html").forward(request, response);
		
	}

}
