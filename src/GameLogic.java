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

	//CONTAINS 3 VALUES: [Input Value, Coordinate X, Coordinate Y]
	int[] userInputDataPosition;

	//Boolean tells the server whether or not the input was correct or not
	boolean guessVariable;

	//Contains all of the values of the current puzzle stored in an array.
	static ArrayList<String> currentPuzzle = new ArrayList<String>();

	//Contains all of the valeus of the current puzzle's solution.
	static ArrayList<String> currentPuzzleSolution = new ArrayList<String>();

	//Integer (1,2 or 3) that sets the difficulty of the puzzle.
	int difficulty;

	public static void main(String[] args)
	{

		GameLogic tester = new GameLogic();

		try {
			tester.readPuzzle();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i=0;i<81;i++){
			System.out.println("PUZZLE ELEMENT " + (i+1) + ":" + currentPuzzle.get(i));
		}

		for(int i=0;i<81;i++){
			System.out.println("PUZZLE SOLUTION ELEMENT " + (i+1) + ":" + currentPuzzleSolution.get(i));
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

		Scanner scanner = new Scanner(new File("SudokuPuzzles/easypuzzle1.txt"));
		Scanner scanner1 = new Scanner(new File("SudokuPuzzles/easypuzzle1solution.txt"));

		for(int i=0;i<81;i++){
			currentPuzzle.add(i,scanner.nextLine());
		}

		for(int i=0;i<81;i++){
			currentPuzzleSolution.add(i,scanner1.nextLine());
		}
	}
}
