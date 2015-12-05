import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;

import javax.swing.Timer;

/**
 * This class handles all of the game logic that
 * will be able to accessed by the server and the GUI.
 * 
 * @author Mitchell Baer
 * Date: 10/19/15
 * Class: CS3141 - Team Software Project
 *
 */
public class GameLogic implements ActionListener {

	//CONTAINS 3 VALUES: [Input Value, Coordinate X, Coordinate Y]
	private int[] userInputDataPosition;

	//Boolean tells the server whether or not the input was correct or not
	private boolean guessVariable;

	//Contains all of the values of the current puzzle stored in an array.
	private String[][] currentPuzzle = new String[9][9];

	//Contains all of the values of the current puzzle's solution.
	private String[][] currentPuzzleSolution = new String[9][9];

	//Integer (1,2 or 3) that sets the difficulty of the puzzle.
	private int difficulty;

	//This boolean will translate whether or not the user has completed the puzzle
	private boolean completed;

	//These are the variables that establish our timer which is vital to the updating of our online game.
	private Timer timer;
	private int time = 0;
	
	private String chatText = "";
	
	private ArrayList<UserThread> currentPlayers = new ArrayList<UserThread>();

	//Constructor with a difficulty parameter. This will be used later when the user selects the difficulty when he/she
	//starts up the server.
	public GameLogic(){}

	public GameLogic(int diff){
		setDifficulty(diff);

		try {
			readPuzzle();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		timer = new Timer(1000, this);		
		timer.setInitialDelay(0);
		timer.start();		
	}
	
	public void addPlayer(UserThread person){
		currentPlayers.add(person);
	}
	

	/**
	 * WORKING AS OF 10/22/15
	 * 
	 * This method is going to spit back whether or not the user has made a correct guess or an
	 * incorrect guess in real time while they are playing the game.
	 * 
	 * It takes in a paramter (array) and pulls out the guess vaklue and the coordinates
	 * and cross-references that information with currentPuzzleSolution.
	 * 
	 * Assuming the user made a correct guess, the method will return "true".
	 * Assuming the user made an incorrect guess, the method will return "false".
	 * 
	 * @param userGuess - This will be a 3 element array that will contain the following information:
	 * userGuess[0] = the users' "guess"
	 * userGuess[1] = the users' guess y-coordinate
	 * userGuess[2] = the users' guess x-coordinate
	 * 
	 * @return a boolean that will represent the accuracy of the users' guess.
	 */
	public boolean checkInput(int [] userGuess){

		boolean guess = false;

		int input = userGuess[1];
		int guessY = userGuess[2];
		int guessX = userGuess[3];

		//Pulls out the number (in the form of a string) and converts it to an int
		String correctNumber = currentPuzzleSolution[guessX][guessY];
		int convertedNumber = Integer.parseInt(correctNumber);
		//Checks if the guess was correct
		if(convertedNumber == input){

			guess = true;
			currentPuzzle[guessX][guessY] = "" + input;
		}

		//Returns true or false based off of guess accuracy
		return guess;
	}

	/**
	 * WORKING AS OF 10/22/15
	 * 
	 * This method is straight forward. It will be used to get the current puzzle.
	 *
	 * @return the current puzzle that the user will work on.
	 */
	public String getPuzzle(){
		String singleString = "";
		for(int i = 0; i < 9; i++){
			for(int k = 0; k < 9; k++){

				//This chunk of code is when we convert it from current puzzle into a long list of the numbers
				//in the puzzle
				singleString = singleString + currentPuzzle[i][k];

				//This method is where it tests if the loops have reached the end.
				//If it hasn't, it adds a space in between successive numbers.
				if(i * k != 64){
					singleString = singleString + " ";
				}
			}

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

		//Makes sure the input numbers for easy, medium, hard are 1,2 or 3.
		if(desiredDifficulty == 1 || desiredDifficulty == 2 || desiredDifficulty == 3){
			difficulty = desiredDifficulty;
		}

		//Throws error if the input isn't 1,2, or 3.
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

	/**
	 * WORKING AS OF 11/10/15
	 * 
	 * All this method does is return true/false based off if the user has completed
	 * the current puzzle or not.
	 * 
	 * @return boolean (true/false) if the user has completed the puzzle or not.
	 */
	public boolean isComplete(){

		//Checks if currentPuzzle == currentPuzzleSolution.
		//The reason we can make such a comparison is because every correct move
		//updates the currentPuzzle array.
		if(Arrays.deepEquals(currentPuzzle, currentPuzzleSolution)){
			return true;
		}

		else{
			return false;
		}
	}

	/**
	 * 
	 * WORKING AS OF 11/18/15
	 * 
	 * This method is strictly for testing purposes. It will have the same logic as 
	 * isComplete, except it will have parameters. We don't have a method that can be used
	 * to set the puzzle, so in order to test it from a separate class, we need this method.
	 * 
	 * @param puzzleOne - The randomly generated puzzle
	 * @param puzzleTwo - The solution to the puzzle that is retrieved from getPuzzleSolution
	 * 
	 * @return true/false based off of equality.
	 */
	public boolean isCompleteTest(String[][] puzzleOne,String[][] puzzleTwo){

		if(Arrays.deepEquals(puzzleOne, puzzleTwo)){
			return true;
		}
		return false;
	}

	/**
	 * 
	 * WORKING AS OF 11/18/15
	 * 
	 * This method just increments the timer each timer pings.
	 * This is used in the application to keep a running time total.
	 */
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == timer){
			time++;
		}
	}

	/**
	 * 
	 * WORKING AS OF 11/18/15
	 * 
	 * This method just returns the total time that is generated from the server run time.
	 * 
	 * @return time (the total that is generated from the actionPerformed method)
	 */
	public int getTime(){
		return time;		
	}
	/**
	 * 
	 * WORKING AS OF 11/21/15
	 * 
	 * This method finds who won the game
	 * 
	 * @return winner.getPlayerName() (the total that is generated from the actionPerformed method)
	 */
	public String findWinner(){
		UserThread winner = null;

		winner = currentPlayers.get(0);

		for(UserThread current : currentPlayers){
			if(current.getScore() > winner.getScore()){
				winner = current;
			}
		}

		return winner.getPlayerName();
	}
	/**
	 * This method will update the chat
	 * 
	 * @param Line - This is the new line being added to the chat
	 */
	public void chatSend(String Line){
		if(chatText == ""){
			chatText = Line;
		}else{
			chatText = chatText + "~" + Line;
		}
	}
	/**
	 * Send it to the UserThread
	 * @return chatText - This is the entire chat text
	 */
	public String getChat(){
		return chatText;
	}
}
