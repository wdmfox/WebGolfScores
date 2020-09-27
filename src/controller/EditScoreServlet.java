/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 5 - Assessment
// ** Start Program for Web Golf Scores Assessment
// ** By Robert Fox, September 24, 2020
// *******************************************************
// **
// ** EditScoreServlet.java will update an existing 
// ** golf score.
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
 * Servlet implementation class EditScoreServlet
 */
@WebServlet("/editScoreServlet")
public class EditScoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditScoreServlet() {
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
		GolfScoreHelper dao = new GolfScoreHelper();
		
		// capture the course and score to be edited which was entered by the user
		String gCourse = request.getParameter("course");
		String gScore = request.getParameter("score");
		
		// capture the primary key "id" associated with the course and
		// score row in the database from the edit-score jsp
		Integer tempId = Integer.parseInt(request.getParameter("id"));
		
		// instantiate a GolfScore object using the searchForScoreById
		// method of the GolfScoreHelper object 
		GolfScore scoreToUpdate = dao.searchForScoreById(tempId);
		
		// use the setGolfCourse method to update the GolfScore
		// object's course property
		scoreToUpdate.setGolfCourse(gCourse);
		
		// use the setGolfScore method to update the GolfScore
		// object's score property
		scoreToUpdate.setGolfScore(Integer.parseInt(gScore));
		
		// update the GolfScore in the database using the 
		// GolfScoreHelper object's updateScore method
		dao.updateScore(scoreToUpdate);
		
		// display the current golf scores for the user
		getServletContext().getRequestDispatcher("/viewAllScoresServlet").forward(request, response);
		
	}

}
