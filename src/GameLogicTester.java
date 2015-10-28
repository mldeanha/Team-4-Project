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


	}

}
