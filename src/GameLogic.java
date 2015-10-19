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
	int[][] currentPuzzle;

	//Contains all of the valeus of the current puzzle's solution.
	int[][] currentPuzzleSolution;

	//Integer (1,2 or 3) that sets the difficulty of the puzzle.
	int difficulty;

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

}
