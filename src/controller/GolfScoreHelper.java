/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 3 - Assessment
// ** GolfScoreHelper for Assessment
// ** By Robert Fox, September 17, 2020
// *******************************************************
// **
// ** GolfScoreHelper.java contains the methods for adding, deleting,
// ** retrieving and updating GolfScore class objects.
*/

package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GolfScore;

public class GolfScoreHelper {

	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebGolfTrips");
	
	// insertScore is executed to take in the GolfScore parameter
	// and add it to the SCORES table
	public void insertScore(GolfScore li) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(li);    // save the new score
		em.getTransaction().commit();
		em.close();
		
	}
	
	// showAllScores returns a List of GolfScore objects consisting
	// of the rows from the SCORES table
	public List<GolfScore> showAllScores() {
		
		EntityManager em = emfactory.createEntityManager();
		List<GolfScore> allScores = em.createQuery("SELECT i FROM GolfScore i").getResultList();
		return allScores;
	}
	
	// deleteScore deletes the first occurrence in the SCORES table
	// where the course and score matches what is sent in as the GolfScore parameter
	public void deleteScore(GolfScore toDelete) {
		
		String queryStr = "select li from GolfScore li where li.golfCourse = :selectedCourse and li.golfScore = :selectedScore";
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery<GolfScore> typedQuery = em.createQuery(queryStr, GolfScore.class);
		
		typedQuery.setParameter("selectedCourse",  toDelete.getGolfCourse());
		typedQuery.setParameter("selectedScore", toDelete.getGolfScore());
		
		typedQuery.setMaxResults(1);
		
		GolfScore result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	
	// searchForScoreById returns the instance in 
	// the SCORES table that matches the key passed
	// in as the parameter.
	public GolfScore searchForScoreById(int idToEdit) {
	
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GolfScore found = em.find(GolfScore.class, idToEdit);
		em.close();
		return found;
	}

	// updateScore Updates the properties of the SCORES
	// table instance passed in as the parameter
	public void updateScore(GolfScore toEdit) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
		
	}

	// searchForScoreByCourse returns a list of scores from the SCORES table
	// that match the course name passed in as the parameter
	public List<GolfScore> searchForScoreByCourse(String courseName) {
		
		String queryStr = "select li from GolfScore li where li.golfCourse = :selectedCourse";
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery <GolfScore> typedQuery = em.createQuery(queryStr, GolfScore.class);
		typedQuery.setParameter("selectedCourse", courseName);
		List <GolfScore> foundScores = typedQuery.getResultList();
		em.close();
		return foundScores;
	}

	// searchForScoreByScore returns a List of GolfScore objects
	// from the SCORES table that match the score passed in
	// as a parameter
	public List<GolfScore> searchForScoreByScore(int score) {
		
		String queryStr = "select li from GolfScore li where li.golfScore = :selectedScore";
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		TypedQuery <GolfScore> typedQuery = em.createQuery(queryStr, GolfScore.class);
		typedQuery.setParameter("selectedScore",  score);
		List <GolfScore> foundScores = typedQuery.getResultList();
		em.close();
		return foundScores;
	}
	
	// searchForScoreByScore returns a List of GolfScore objects
	// from the SCORES table that match the score passed in
	// as a parameter
	public GolfScore searchForGolfScoreById(Integer tempId) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GolfScore found = em.find(GolfScore.class, tempId);
		em.close();
		return found;
	}
	
	// cleanUp closes the database connection
	public void cleanUp() {
		
		emfactory.close();
		
	}
}