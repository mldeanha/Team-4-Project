import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * This class file will accept clients trying to connect to the 
 * sudoku game it will then make a new thread for the client user
 * to interact with the game logic
 * 
 * @author Eric Celerin
 * Date: 10/20/15
 * Class: CS3141 - Team Software Project
 */
public class SudokuPlusServer {

	/**
	 * This method will be the server that accepts clients and makes new threads. 
	 * It will continue to run until the game ends
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args){



		GameLogic easy = new GameLogic(1);
		GameLogic medium = new GameLogic(2);
		GameLogic hard = new GameLogic(3);


		int port = 7776;
		ServerSocket serverSocket;

		try {
			serverSocket = new ServerSocket(port);
		} catch (IOException e) {
			e.printStackTrace();
			return;	
		}

		while (true) {
			try {
				Socket clientSocket = serverSocket.accept();
				Scanner scanner = new Scanner(clientSocket.getInputStream());
				int Diff = Integer.parseInt(scanner.nextLine());
				UserThread customThread = null;

				switch(Diff){
				case 0:
					if(easy.isComplete()){
						easy = new GameLogic(1);
					}
					customThread = new UserThread(clientSocket, easy);
					easy.addPlayer(customThread);
					break;
				case 1:
					if(medium.isComplete()){
						medium = new GameLogic(2);
					}
					customThread = new UserThread(clientSocket, medium);
					medium.addPlayer(customThread);
					break;
				case 2:
					if(hard.isComplete()){
						hard = new GameLogic(3);
					}
					customThread = new UserThread(clientSocket, hard);
					hard.addPlayer(customThread);
					break;
				default:
					break;
				}

				customThread.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
