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
			System.out.println("g");
			
			socketWriter.println("Now it is");
			socketWriter.flush();
		
			
			while (true) {
				
				String line = scanner.nextLine();

				//Format for input: "Command,Input,Input,Input...
				String[] split = line.split(",");

				//Command directory: This is what will convert actions in the GUI
				//into game changes and server interactions
				switch(Integer.parseInt(split[0])){
				case 0:
					break;
				case 1:
					break;
				default:
					break;	
				}
				

				try {//Move this to separate thread
					//add GUI interactions here
					//Game to GUI send home the puzzles
					socketWriter.println("Command");
					socketWriter.flush();
					socketWriter.println(userGame.getPuzzle());
					socketWriter.flush();
					
					
				} catch (Exception e) {
					System.err.println("Client " + clientSocket.getInetAddress() + " issued an incorrect command: ERR" );
				}
			}
			
		} catch (Exception e) {
			System.out.println("User disconnected");
		}
	}
	
}
