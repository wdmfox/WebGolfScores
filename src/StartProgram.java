/* *******************************************************
// ** CIS175 - CRN 13941
// ** Week 3 - Assessment
// ** Start Program for Golf Scores Assessment
// ** By Robert Fox, September 17, 2020
// *******************************************************
// **
// ** StartProgram.java displays a menu on the console enabling the
// ** user to add, delete, display and update data in the scores
// ** table.
*/

import java.util.List;
import java.util.Scanner;

import controller.GolfScoreHelper;
import model.GolfScore;

public class StartProgram {

		static Scanner in = new Scanner(System.in);  // instantiate Scanner object to capture console input
		static GolfScoreHelper lih = new GolfScoreHelper();  // instantiate GolfScoreHelper object

		// addAScore is executed to add an item to the SCORES table
		private static void addAScore() {
		
			System.out.print("Enter a course: ");
			String course = in.nextLine();
			System.out.print("Enter a score: ");
			int score = in.nextInt();
			
			GolfScore toAdd = new GolfScore(course,score);
			lih.insertScore(toAdd);

		}

		private static void deleteAScore() {
			
			System.out.print("Enter the course to delete: ");
			String course = in.nextLine();
			System.out.print("Enter the score to delete: ");
			int score = in.nextInt();
			
			GolfScore toDelete = new GolfScore(course,score);
			
			lih.deleteScore(toDelete);

		}

		private static void editAScore() {
			// TODO Auto-generated method stub
			System.out.println("How would you like to search? ");
			System.out.println("1 : Search by Course");
			System.out.println("2 : Search by Score");
			int searchBy = in.nextInt();
			in.nextLine();
			List<GolfScore> foundScores;
			if (searchBy == 1) {
				System.out.print("Enter the course name: ");
				String courseName = in.nextLine();
				foundScores = lih.searchForScoreByCourse(courseName);
			} else {
				System.out.print("Enter the score: ");
				int score = in.nextInt();
				foundScores = lih.searchForScoreByScore(score);
				

			}

			if (!foundScores.isEmpty()) {
				System.out.println("Found Results.");
				for (GolfScore l : foundScores) {
					System.out.println(l.getId() + " : " + l.returnScoreDetails());
				}
				System.out.print("Which ID to edit: ");
				int idToEdit = in.nextInt();

				GolfScore toEdit = lih.searchForScoreById(idToEdit);
				System.out.println("Retrieved " + toEdit.getGolfScore() + " from " + toEdit.getGolfCourse());
				System.out.println("1 : Update Course");
				System.out.println("2 : Update Score");
				int update = in.nextInt();
				in.nextLine();

				if (update == 1) {
					System.out.print("New Course: ");
					String newCourse = in.nextLine();
					toEdit.setGolfCourse(newCourse);
				} else if (update == 2) {
					System.out.print("New Score: ");
					int newScore = in.nextInt();
					toEdit.setGolfScore(newScore);
				}

				lih.updateScore(toEdit);

			} else {
				System.out.println("---- No results found");
			}
		}

		public static void main(String[] args) {
			// TODO Auto-generated method stub
			runMenu();

		}

		public static void runMenu() {
			boolean goAgain = true;
			System.out.println("--- Welcome to our awesome shopping list! ---");
			while (goAgain) {
				System.out.println("*  Select an item:");
				System.out.println("*  1 -- Add an score");
				System.out.println("*  2 -- Edit an score");
				System.out.println("*  3 -- Delete an score");
				System.out.println("*  4 -- View the list");
				System.out.println("*  5 -- Exit the awesome program");
				System.out.print("*  Your selection: ");
				int selection = in.nextInt();
				in.nextLine();

				if (selection == 1) {
					addAScore();
				} else if (selection == 2) {
					editAScore();
				} else if (selection == 3) {
					deleteAScore();
				} else if (selection == 4) {
					viewTheList();
				} else {
					lih.cleanUp();
					System.out.println("   Goodbye!   ");
					goAgain = false;
				}

			}

		}

		private static void viewTheList() {
			
			List<GolfScore> allScores = lih.showAllScores();
			for(GolfScore singleScore : allScores) {
				System.out.println(singleScore.returnScoreDetails());
			}
			

		}

	}