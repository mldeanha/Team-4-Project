import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

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
		
		Object[] options = {"Easy",
				"Medium",
		"Hard"};
		
		int n = JOptionPane.showOptionDialog(null,
				"Choose a Difficulty",
				"Setup",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[2]);
		
		GameLogic game = new GameLogic(n + 1);

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

				Thread customThread = new UserThread(clientSocket, game);

				customThread.start();

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}


}
