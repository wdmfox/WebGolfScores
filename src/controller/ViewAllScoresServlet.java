/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 5 - Assessment
// ** Start Program for Web Golf Scores Assessment
// ** By Robert Fox, September 24, 2020
// *******************************************************
// **
// ** ViewAllScoresServlet.java uses a GolfScoreHelper		
// ** object to get and display the golf scores stored		 
// ** in the database.
*/

package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * Servlet implementation class ViewAllScoresServlet
 */
@WebServlet("/viewAllScoresServlet")
public class ViewAllScoresServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewAllScoresServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// instantiate a GolfScoreHelper object
		GolfScoreHelper dao = new GolfScoreHelper();
		
		// use the GolfScoreHelper object's showAllScores method
		// to obtain a list of all the scores and get it ready 
		// for the golfscores-list jsp
		request.setAttribute("allScores",  dao.showAllScores());
		
		// set the path to redirect to the golfscores-list jsp
		String path = "/golfscores-list.jsp";
		
		// if there are no scores to display, then set the 
		// path to redirect to the index.html
		if (dao.showAllScores().isEmpty()) {
			path = "/index.html";
		}
		
		// redirect the user to the next path
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
