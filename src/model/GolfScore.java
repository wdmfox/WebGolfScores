/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 3 - Assessment
// ** GolfScore Class
// ** By Robert Fox, September 17, 2020
// *******************************************************
// **
// ** GolfScore.java is the POJO declaration for the golf scores
// ** objects using persistence.
*/

package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="scores")
public class GolfScore {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="COURSE")
	private String golfCourse;
	@Column(name="SCORE")
	private int golfScore;
	
	// default constructor
	public GolfScore() {
		super();
	}
	
	// non-default constructor
	public GolfScore(String gCourse, int score) {
		super();
		this.setGolfCourse(gCourse);
		this.setGolfScore(score);
		
	}

	// getters and setters
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGolfCourse() {
		return golfCourse;
	}

	public void setGolfCourse(String golfCourse) {
		this.golfCourse = golfCourse;
	}

	public int getGolfScore() {
		return golfScore;
	}

	public void setGolfScore(int golfScore) {
		this.golfScore = golfScore;
	}

	// returnScoreDetails returns a string with the values
	// of the entity instance variables GolfCourse and GolfScore
	public String returnScoreDetails() {
		
		return this.getGolfCourse() + ":" + this.getGolfScore();
		
	}
}
