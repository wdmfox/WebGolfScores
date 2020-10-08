/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** GolfTripHelper for Assessment
// ** By Robert Fox, October 5, 2020
// *******************************************************
// **
// ** GolfTripHelper.java contains the methods for adding, deleting,
// ** retrieving and updating GolfTrip class objects.
*/

package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import model.GolfScore;
import model.GolfTrip;

public class GolfTripHelper {
	
	static EntityManagerFactory emfactory = Persistence.createEntityManagerFactory("WebGolfTrips");

	// insertTrip is executed to take in the GolfTrip parameter
	// and add it to the golf_trip table
	public void insertTrip(GolfTrip gt) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(gt);    // save the new score
		em.getTransaction().commit();
		em.close();
		
	}
	
	// showAllScores returns a List of GolfScore objects consisting
	// of the rows from the SCORES table
	public List<GolfTrip> showAllTrips() {
		
		EntityManager em = emfactory.createEntityManager();
		List<GolfTrip> allTrips = em.createQuery("SELECT i FROM GolfTrip i").getResultList();
		return allTrips;
	}
	
	// deleteTrip deletes the first occurrence in the SCORES table
	// where the trip ID matches what is sent in with the GolfTrip parameter
	public void deleteTrip(GolfTrip toDelete) {
		
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<GolfTrip> typedQuery  = 
				em.createQuery("select gTrip from GolfTrip gTrip where gTrip.tripId = :selectedTrip", GolfTrip.class);
		
		typedQuery.setParameter("selectedTrip", toDelete.getTripId());
		
		typedQuery.setMaxResults(1);
		
		GolfTrip result = typedQuery.getSingleResult();
		
		em.remove(result);
		em.getTransaction().commit();
		em.close();
	}

	// searchForTripById returns the instance in 
	// the golf_trip table that matches the key passed
	// in as the parameter.
	public GolfTrip searchForTripById(int idToEdit) {
	
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		GolfTrip found = em.find(GolfTrip.class, idToEdit);
		em.close();
		return found;
	}
	
	// updateTrip merges changes made to a golf trip
	// object back to the database instance
	public void updateTrip(GolfTrip toEdit) {
		EntityManager em = emfactory.createEntityManager();
		em.getTransaction().begin();
		em.merge(toEdit);
		em.getTransaction().commit();
		em.close();
	}
}
