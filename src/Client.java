/**
 * This class file will create a socket and connect to the server based on user
 * input of an ip address. Then it will create a GameGUI and pass it the socket and
 * possibly the sudoku puzzle
 * 
 * THIS FILE IS OBSOLETE. RUN OptionGUI IF YOU WANT TO RUN THE CLIENT.
 * 
 * @author Eric Celerin
 * Date: 10/20/15
 * Class: CS3141 - Team Software Project
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Client {
	/**
	 * This is the main method for the client side of the SudokuPlus game application.
	 * It will setup the GameGUI and connection to the server. It will also create a socket
	 * that the GUI can use to connect to the server.
	 * 
	 * @param args
	 */
	@SuppressWarnings("resource")
	public static void main(String[] args) {

		String ip = JOptionPane.showInputDialog("Please enter server IP");
		
		//String name = JOptionPane.showInputDialog("Please enter Your name");
		
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
		
		int port = 7776;
		Socket socket;

		try {
			socket = new Socket(ip, port);
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Failed to connect to server");
			return;
		}
		Scanner scanner = null;
		try {

			scanner = new Scanner(socket.getInputStream());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			PrintWriter w = new PrintWriter(socket.getOutputStream());
			w.println(n);
			w.flush();		
			w.println("3 Eric Celerin");
			w.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		new GameGUI(socket, null);
	}
	
}
