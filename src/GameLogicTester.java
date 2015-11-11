import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Test;

public class GameLogicTester {

	@Test
	//----------TEST 1----------
	//This test method will test the user checking method that we will use
	//to tell the user if the number they guess was correct or not.
	public void test1() throws FileNotFoundException {

		GameLogic test = new GameLogic();

		Random rand = new Random();

		int i =0;
		while(i<10){

			//Randomly Generated Numbers.
			//1.) A difficulty (1-3)
			//2.) A guess (1-9)
			//3.) An x-coordinate (0-8 for our array, but 1-9 to the user)
			//4.) A y-coordinate (0-8 for our array, but 1-9 to user)
			int desiredDifficulty = rand.nextInt((3-1)+1)+1;
			int randomGuess = rand.nextInt((9-1)+1)+1;
			int randomX = rand.nextInt((8-0)+1)+0;
			int randomY = rand.nextInt((8-0)+1)+0;

			test.setDifficulty(desiredDifficulty);
			test.readPuzzle();

			String[][] testPuzzleSolution = test.getPuzzleSolution();

			String correctAnswer = testPuzzleSolution[randomY][randomX];

			System.out.println("Test 1." + (i+1) + " : [" + (randomY+1) + "," + (randomX+1) + "]");

			assertNotEquals(randomGuess,correctAnswer);

			i++;
		}
		System.out.println();
	}

	@Test
	//----------Test 2----------
	//This method is going to test to make sure
	//the difficulty is being set correctly.
	public void test2(){

		GameLogic test = new GameLogic();

		Random rand = new Random();

		int i=0;
		while(i<10){

			int generatedDifficulty = rand.nextInt((3-1)+1)+1;

			test.setDifficulty(generatedDifficulty);
			int actualDifficulty = test.getDifficulty();

			System.out.println("Test 2." + (i+1) + " : [" + generatedDifficulty + "]");

			assertEquals(generatedDifficulty,actualDifficulty);
			i++;
		}
		System.out.println();
	}

	@Test
	//-----------Test 3----------
	//This method tests whether or not the isComplete (isCompleteTest**) is working correctly
	//It will generate a random puzzle and compare it with another arbitrary puzzle (in our case
	//it will be compared with the puzzle return from .getPuzzleSolution).
	//
	//Since the puzzle is randomly generated, there is almost NO way that it will
	//be equal to another puzzle. Part of this may be because it may not even follow
	//the rules of sudoku. All this test does is make sure that they can be compared using .equals.
	//
	//If at any point it were to return true, and then fail the tests, there would be an issue.
	//
	//NOTE: The assertion here should be false and in being false, it should pass the tests.
	public void test3(){

		GameLogic test = new GameLogic();
		String[][] tester = test.getPuzzleSolution();
		String[][] compareTo = new String[9][9];

		Random rand = new Random();

		int k=0;
		while(k<10){

			for(int i=0;i<9;i++){
				for(int j=0;j<9;j++){
					int randomGeneration = rand.nextInt((9-1)+1)+1;
					compareTo[i][j] = "" + randomGeneration;
				}
			}
			System.out.println("Test 3." + (k+1) + " : " + test.isCompleteTest(compareTo, tester));

			assertFalse(test.isCompleteTest(compareTo,tester));
			k++;
		}

		System.out.println();

	}
}
