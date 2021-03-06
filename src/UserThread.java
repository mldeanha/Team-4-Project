/**
 * This class will be used to communicate between the client GUI and 
 * the server GameLogic. Many threads can be launched in parallel to
 * allow multiplayer options
 * 
 * @author Eric Celerin
 * Date: 10/20/15
 * Class: CS3141 - Team Software Project
 *
 */

import java.awt.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class UserThread extends Thread {
	//Holds the reference to the game so the user can submit moves
	private GameLogic userGame;
	//Holds the client socket information
	private Socket clientSocket;
	private PrintWriter socketWriter;
	private Scanner scanner;
	private String name = "";
	private String textInput = "";
	private String currentPuzzle;
	private int score = 0;
	/**
	 * This is the constructor for the user thread in which
	 * the socket and game are preserved to be used in run
	 * 
	 * @param client
	 * @param game
	 * @throws IOException 
	 */
	public UserThread(Socket client, GameLogic game) throws IOException{
		this.clientSocket = client;
		this.userGame = game;
		socketWriter = new PrintWriter(clientSocket.getOutputStream());
		System.out.println("Client: " + " - "+clientSocket.getInetAddress() + " connected");	}

	/**
	 * This method is used to send data to and from the
	 * client in order to play the sudoku game
	 */
	public void run(){
		try {

			Scanner scanner = new Scanner(clientSocket.getInputStream());

			//This will repeatedly look for input from the GUI
			while (true) {

				//Format for input: "Command Input Input Input...
				String line = scanner.nextLine();
				String[] split = line.split(" ");
				int [] input = new int[split.length];
				int i = 0;

				if(split[0].equals("3")){//This will deal with an attempt to convert strings to integer error
					input[0] = 3;
					for(int h = 1;h < split.length; h++){
						textInput = textInput + split[h];
						if(h < split.length -1){
							textInput = textInput + " ";
						}
					}
				}else if(split[0].equals("5")){ //This case will be used for chat
					input[0] = 5;					
					textInput = "";
					for(int h = 1;h < split.length; h++){
						textInput = textInput + split[h];
						if(h < split.length -1){
							textInput = textInput + " ";
						}
					}
				}else{
					for(String convert : split){			 //This will convert from strings to integers so we can read 
						input[i] = Integer.parseInt(convert);//commands and input from user
						i++;
					}
				}

				//Command directory: This is what will convert actions in the GUI
				//into game changes and server interactions
				switch(input[0]){
				case 0:		//User Guess
					if(userGame.checkInput(input)){//Correct
						socketWriter.println("0");
						score += 100;
					}else{	//Wrong
						socketWriter.println("1");
						score -= 100;
					}
					break;
				case 1:		//Get Puzzle
					if(userGame.getPuzzle().equals(currentPuzzle)){ //if the puzzle hasn't changes don't send it
						socketWriter.println(userGame.isComplete());//Notifies that the puzzle is finished
						socketWriter.flush();
						if(userGame.isComplete()){
							socketWriter.println(userGame.findWinner());
						}
						//Doesn't send full puzzle
					}else{	//If it has changed send the updated puzzle
						socketWriter.println(userGame.getPuzzle());
						currentPuzzle = userGame.getPuzzle();
					}
					break;
				case 2:		//Get Score
					socketWriter.println(score);
					break;
				case 3:		//Set Name				
					name = textInput;
					continue;//Fixes not return flush error
				case 4:		//Get Timer update
					socketWriter.println(userGame.getTime());
					break;
				case 5:
					if(textInput != ""){
						userGame.chatSend(getPlayerName() + ": " + textInput);
					}
					socketWriter.println(userGame.getChat());
					break;
				default:	//This will occur if there is a wrong input
					socketWriter.println("Something went Wrong Command Not Valid");
					break;	
				}
				socketWriter.flush();//This part returns the response to the command
			}
		} catch (Exception e) { //This will happen if there is a major error
			System.out.println("User disconnected");
		}
	}
	/**
	 * Returns the score
	 * @return score this is the player score
	 */
	public int getScore(){
		return score;
	}
	/**
	 * Returns the name of the player
	 * @return name this is the name of the player
	 */
	public String getPlayerName(){
		return name;
	}
}
