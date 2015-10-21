import java.io.*;
import java.util.*;

/**
 * This class handles all of the game logic. That
 * will be able to accessed by the server and the GUI.
 * 
 * @author Mitchell Baer
 * Date: 10/19/15
 * Class: CS3141 - Team Software Project
 *
 */
public class GameLogic {

	//Global Scanner that can be set 

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

	public static void main(String[] args)
	{

		GameLogic tester = new GameLogic();
		difficulty = 1;

		try {
			tester.readPuzzle();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(currentPuzzle[i][j]);
			}
			System.out.println();
		}

		System.out.println();

		for(int i=0;i<9;i++){
			for(int j=0;j<9;j++){
				System.out.print(currentPuzzleSolution[i][j]);
			}
			System.out.println();
		}
	}

	/**
	 * 
	 * @return
	 */
	public boolean checkInput(){
		return true;
	}

	/**
	 * 
	 * @param selection
	 * @return
	 */
	public int[][] getPuzzle(int selection){
		return null;
	}

	/**
	 * 
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty){

	}

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

		int j = 0;

		for(int i=1;i<=((randomSelector+1)*9);i++){

			if(i<puzzleSelectorNumber){
				scanner.nextLine();
				continue;
			}

			String line = scanner.nextLine();
			String[] split = line.split(",");
			currentPuzzle[j] = split;
			j++;
		}

		int k = 0;

		for(int i=1;i<=((randomSelector+1)*9);i++){

			if(i<puzzleSelectorNumber){
				scanner1.nextLine();
				continue;
			}

			String line = scanner1.nextLine();
			String[] split = line.split(",");
			currentPuzzleSolution[k] = split;
			k++;
		}

	}
}
