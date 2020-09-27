/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 5 - Assessment
// ** Start Program for Web Golf Scores Assessment
// ** By Robert Fox, September 24, 2020
// *******************************************************
// **
// ** NavigationServlet.java evaluates the action chosen
// ** by the user (add, edit or delete a golf score) and 
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

/**
 * Servlet implementation class NavigationServlet
 */
@WebServlet("/navigationServlet")
public class NavigationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NavigationServlet() {
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
	
		GolfScoreHelper dao = new GolfScoreHelper();
		
		String act = request.getParameter("doThisToScore");
		
		// after all changes, we should redirect to the viewAllItems servlet
		// The only time we don't is if they select to add a new item or edit
		String path = "/viewAllScoresServlet";
		
		// if deleting a GolfScore then instantiate a GolfScore object
		// with the target score to delete and use the GolfScoreHelper
		// deleteScore method to get rid of it. 
		if (act.equals("delete")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				GolfScore scoreToDelete = dao.searchForScoreById(tempId);
				dao.deleteScore(scoreToDelete);
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a score");
			}
			
		// if editing a score, instantiate a GolfScore object with the 
		// target score to be edited and pass it to the edit-score jsp
		// to be picked up and displayed
		} else if (act.equals("edit")) {
			try {
				Integer tempId = Integer.parseInt(request.getParameter("id"));
				GolfScore scoreToEdit = dao.searchForScoreById(tempId);
				request.setAttribute("scoreToEdit", scoreToEdit);
				path = "/edit-score.jsp";
			} catch (NumberFormatException e) {
				System.out.println("Forgot to select a score");
			}
		} else if (act.equals("add")) {
			path = "/index.html";
		}
		
		// redirect to whichever jsp is in the path variable
		getServletContext().getRequestDispatcher(path).forward(request, response);
		
	}

}
