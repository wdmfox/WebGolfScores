/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 6 - Assessment
// ** GolfTrip Class
// ** By Robert Fox, October 5, 2020
// *******************************************************
// **
// ** GolfTrip.java is the POJO declaration for the golf trip
// ** objects using persistence.
*/

package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: GolfTrip
 *
 */
@Entity
@Table(name="golf_trip")
public class GolfTrip {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="TRIP_ID")
	private int tripId;
	@Column(name="TRIP_DATE")
	private LocalDate tripDate;
	@ManyToOne (cascade=CascadeType.PERSIST)
		@JoinColumn(name="GOLF_SCORE")
	private GolfScore gScore;
	
	public GolfTrip() {
		   
		super();
	}

	public int getTripId() {
		return tripId;
	}
	
	public GolfTrip(int tripId, LocalDate tripDate, GolfScore gScore) {
		
		super();
		this.tripId = tripId;
		this.tripDate = tripDate;
		this.gScore = gScore;
	}

	public GolfTrip(LocalDate tripDate, GolfScore gScore) {
		
		super();
		this.tripDate = tripDate;
		this.gScore = gScore;
	}
	public void setTripId(int tripId) {
		this.tripId = tripId;
	}

	public LocalDate getTripDate() {
		return tripDate;
	}

	public void setTripDate(LocalDate tripDate) {
		this.tripDate = tripDate;
	}

	public GolfScore getgScore() {
		return gScore;
	}

	public void setgScore(GolfScore gScore) {
		this.gScore = gScore;
	}
	
	@Override
	public String toString() {
		
		// instantiate DateTimeFormatter object to format the tripDate later
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		
		String golfTripDetails = "";
	
		// assign the shopping trip attributes to the shoopingListDetails object
		golfTripDetails = "List [id=" + tripId + ", Trip Date=" + tripDate.format(dateFormat) +
			", Golf Course=" + gScore.getGolfCourse() + ", Score=" + gScore.getGolfScore();
		
		return golfTripDetails;
	}
}
	
