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
	PrintWriter socketWriter;
	Scanner scanner;
	private String currentPuzzle;
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

		System.out.println("Client " + clientSocket.getInetAddress() + " connected");	}
	
	/**
	 * This method is used to send data to and from the
	 * client in order to play the sudoku game
	 */
	public void run(){
		try {

			Scanner scanner = new Scanner(clientSocket.getInputStream());
			
			while (true) {
								//Format for input: "Command Input Input Input...

				String line = scanner.nextLine();
				String[] split = line.split(" ");
				int [] input = new int[split.length];
				int i = 0;
				for(String convert : split){
					input[i] = Integer.parseInt(convert);
					i++;
				}
				//Command directory: This is what will convert actions in the GUI
				//into game changes and server interactions
				switch(input[0]){
				case 0:
					if(userGame.checkInput(input)){
						socketWriter.println("0");
						//Change Server Puzzle Here!
					}else{
						socketWriter.println("1");
					}
					socketWriter.flush();
					break;
				case 1://Get Puzzle
					if(userGame.getPuzzle().equals(currentPuzzle)){ //if the puzzle hasn't changes don't send it
						socketWriter.println(""); //Doesn't send full puzzle
					}else{
						socketWriter.println(userGame.getPuzzle());//If it's has changed
						currentPuzzle = userGame.getPuzzle();
					}
					socketWriter.flush();

					break;
				default:

					break;	
				}
				
			}
			
		} catch (Exception e) {
			System.out.println("User disconnected");
		}
	}
	
}
