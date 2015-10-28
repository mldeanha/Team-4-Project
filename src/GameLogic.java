import java.io.*;
import java.util.*;

/**
 * This class handles all of the game logic that
 * will be able to accessed by the server and the GUI.
 * 
 * @author Mitchell Baer
 * Date: 10/19/15
 * Class: CS3141 - Team Software Project
 *
 */
public class GameLogic {

	//CONTAINS 3 VALUES: [Input Value, Coordinate X, Coordinate Y]
	int[] userInputDataPosition;

	//Boolean tells the server whether or not the input was correct or not
	boolean guessVariable;

	//Contains all of the values of the current puzzle stored in an array.
	static String[][] currentPuzzle = new String[9][9];

	//Contains all of the values of the current puzzle's solution.
	static String[][] currentPuzzleSolution = new String[9][9];

	//Integer (1,2 or 3) that sets the difficulty of the puzzle.
	static int difficulty;

//	//MAIN METHOD
//	public static void main(String[] args)
//	{
//
//		//JUST FOR TESTING. THERE WILL NOT BE A MAIN METHOD IN FUTURE VERSIONS
//		GameLogic tester = new GameLogic();
//		int[] guess = {8,5,6};
//		int[] guess2 = {4,8,8};
//		int[] guess3 = {4,0,2};
//		tester.setDifficulty(3);
//
//		try {
//			tester.readPuzzle();
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		//PRINTS OUT CURRENT PUZZLE
//		System.out.println("THE PUZZLE THE USER WILL WORK ON: ");
//		for(int i=0;i<9;i++){
//			for(int j=0;j<9;j++){
//				System.out.print(currentPuzzle[i][j]);
//			}
//			System.out.println();
//		}
//
//		//PRINTS A LINE
//		System.out.println();
//
//		//PRINTS OUT THE COORESPONDING SOLUTION
//		System.out.println("THE SOLUTION TO THE PUZZLE THE USER WILL WORK ON: ");
//		for(int i=0;i<9;i++){
//			for(int j=0;j<9;j++){
//				System.out.print(currentPuzzleSolution[i][j]);
//			}
//			System.out.println();
//		}
//
//		//PRINTS A LINE
//		System.out.println();
//
//		//TO CHECK THESE INPUTS YOU HAVE TO GO ACROSS THEN DOWN!!!!!!!!!!
//		//GUESS 1
//		System.out.println("YOURE GUESSING THE NUMBER " + guess[0] + " IS AT: [" + guess[1] + "," + guess[2] + "]");
//		System.out.println(tester.checkInput(guess));
//		System.out.println();
//		//TO CHECK THIS INPUT YOU HAVE TO GO ACROSS THEN DOWN!!!!!!!!!
//		//GUESS 2
//		System.out.println("YOURE GUESSING THE NUMBER " + guess2[0] + " IS AT: [" + guess2[1] + "," + guess2[2] + "]");
//		System.out.println(tester.checkInput(guess2));
//		System.out.println();
//		//TO CHECK THIS INPUT YOU HAVE TO GO ACROSS THEN DOWN!!!!!!
//		//GUESS 3
//		System.out.println("YOURE GUESSING THE NUMBER " + guess3[0] + " IS AT: [" + guess3[1] + "," + guess3[2] + "]");
//		System.out.println(tester.checkInput(guess3));
//	}
//
//	/**
//	 * WORKING AS OF 10/22/15
//	 * 
//	 * This method is going to spit back whether or not the user has made a correct guess or an
//	 * incorrect guess in real time while they are playing the game.
//	 * 
//	 * It takes in a paramter (array) and pulls out the guess vaklue and the coordinates
//	 * and cross-references that information with currentPuzzleSolution.
//	 * 
//	 * Assuming the user made a correct guess, the method will return "true".
//	 * Assuming the user made an incorrect guess, the method will return "false".
//	 * 
//	 * @param userGuess - This will be a 3 element array that will contain the following information:
//	 * userGuess[0] = the users' "guess"
//	 * userGuess[1] = the users' guess y-coordinate
//	 * userGuess[2] = the users' guess x-coordinate
//	 * 
//	 * @return a boolean that will represent the accuracy of the users' guess.
//	 */
//	public boolean checkInput(int [] userGuess){
//
//		boolean guess = false;
//
//		int input = userGuess[0];
//		int guessY = userGuess[1];
//		int guessX = userGuess[2];
//
//		//Pulls out the number (in the form of a string) and converts it to an int
//		String correctNumber = currentPuzzleSolution[guessX][guessY];
//		int convertedNumber = Integer.parseInt(correctNumber);
//
//		//Checks if the guess was correct
//		if(convertedNumber == input){
//			guess = true;
//		}
//
//		//Returns true or false based off of guess accuracy
//		return guess;
//	}

	/**
	 * WORKING AS OF 10/22/15
	 * 
	 * This method is straight forward. It will be used to get the current puzzle.
	 *
	 * @return the current puzzle that the user will work on.
	 */
	public String getPuzzle(){
		String singleString = "";
		for(String[] level:currentPuzzle){
			for(String add:level){
				singleString = singleString + add;
			}
			singleString = singleString + "\n";
		}
		return singleString;
	}
	
	/**
	 * WORKING AS OF 10/27/15
	 * 
	 *  This method is straight forward. It wil lbe use to get the solution to the current puzzle.
	 * 
	 * @return the array storing the current puzzle's solution.
	 */
	public String[][] getPuzzleSolution(){
		return currentPuzzleSolution;
	}

	/**
	 * WORKING AS OF 10/22/15
	 * 
	 * This method is pretty straight forward. The parameter
	 * will come from the user and set the global variable to the desired difficulty.
	 * 
	 * @param difficulty - this will be an integer that comes from the GUI that will select the
	 * difficulty.
	 * 
	 * POSSIBLE OPTIONS:
	 * 1 - Easy
	 * 2 - Medium
	 * 3 - Hard
	 * 
	 */
	public void setDifficulty(int desiredDifficulty){

		if(desiredDifficulty == 1 || desiredDifficulty == 2 || desiredDifficulty == 3){
			difficulty = desiredDifficulty;
		}

		else{
			System.out.println("INCORRECT INPUT. INPUT MUST BE 1 (EASY), 2 (MEDIUM), OR 3 (HARD)!");
		}

	}
	
	/**
	 * WORKING AS OF 10/27/15
	 * 
	 * This method just returns the difficulty int.
	 * 
	 * Will be used MAINLY for testing purposes.
	 * 
	 * @return the integer that is storing the desired difficulty.
	 */
	public int getDifficulty(){
		return difficulty;
	}

	/**
	 * WORKING AS OF 10/22/15
	 * 
	 * This method handles all of the File I/O and converts all of the data from the specified .txt files
	 * into a 2D array that will be used later when constructing a GUI board as well as checking
	 * the users inputs to see if they're correct or not in real time.
	 * 
	 * This method takes advantage of the global variable "difficulty". This variable
	 * will be set by the user when he/she selects the desired difficulty in the GUI. Using this variable,
	 * it reads in that difficulties puzzle file and the cooresponding solutions. The method then generates a
	 * random number from 0-2 which will select 1 of 3 puzzles in each difficulties file.
	 * 
	 * Assuming all goes accordingly, the users randomly generated puzzle will be stored in 2D array called 
	 * "currentPuzzle", and the cooresponding solution will be stored in the 2D array called "currentPuzzleSolution".
	 * 
	 * @throws FileNotFoundException - This exception will be thrown if the specified file doesn't
	 * exist at the path.
	 */
	public void readPuzzle() throws FileNotFoundException{

		Scanner scanner = null;
		Scanner scanner1 = null;

		switch(difficulty){

		case 1:
			//IF DIFFICULTY = 1, EASY PUZZLE
			scanner = new Scanner(new File("SudokuPuzzles/easypuzzles.txt"));
			scanner1 = new Scanner(new File("SudokuPuzzles/easypuzzlessolutions.txt"));
			break;


		case 2:
			//IF DIFFICULTY = 2, MEDIUM PUZZLE (STANDARD)
			scanner = new Scanner(new File("SudokuPuzzles/mediumpuzzles.txt"));
			scanner1 = new Scanner(new File("SudokuPuzzles/mediumpuzzlessolutions.txt"));
			break;

		case 3:
			//IF DIFFICULTY = 3, HARD PUZZLE
			scanner = new Scanner(new File("SudokuPuzzles/hardpuzzles.txt"));
			scanner1 = new Scanner(new File("SudokuPuzzles/hardpuzzlessolutions.txt"));
			break;

		}

		//GENERATES A RANDOM NUMBER THAT WILL SELECT THE PUZZLE OF THE GIVEN DIFFICULTY!
		Random rand = new Random();
		int randomSelector = rand.nextInt((2-0)+1)+0;
		int puzzleSelectorNumber = ((randomSelector)*9)+1;

		//This variable is used in order to fill the currentPuzzle array.
		//It starts at 0 because it has to fill up starting from the first spot in the array.
		int j = 0;

		//This loop uses the randomly generated numbers to read in the correct puzzle from the
		//file that was selected in the CASE statements above.
		for(int i=1;i<=((randomSelector+1)*9);i++){

			//Makes sure the scanner starts at the correct line
			if(i<puzzleSelectorNumber){
				scanner.nextLine();
				continue;
			}

			//Does all of the work pulling in the information from the file.
			String line = scanner.nextLine();
			String[] split = line.split(",");
			currentPuzzle[j] = split;
			j++;
		}

		//Same as above, but is different cause we're working with a new array.
		int k = 0;

		//This loop serves the same purpose as above. Takes in the randomly generated number
		//and finds the correct puzzle solution from the text file that was selected
		//in the CASE statements above.
		for(int i=1;i<=((randomSelector+1)*9);i++){

			//Sets the scanner to the correct position
			if(i<puzzleSelectorNumber){
				scanner1.nextLine();
				continue;
			}

			//Does all of the work reading in the numbers from the .txt file.
			String line = scanner1.nextLine();
			String[] split = line.split(",");
			currentPuzzleSolution[k] = split;
			k++;
		}

	}
}
