/**
 * This class file will create a socket and connect to the server based on user
 * input of an ip address. Then it will create a GameGUI and pass it the socket and
 * possibly the sudoku puzzle
 * 
 * @author Eric Celerin
 * Date: 10/20/15
 * Class: CS3141 - Team Software Project
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import javax.swing.JOptionPane;

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

				int port = 7776;
				Socket socket;

				try {
					socket = new Socket(ip, port);
				} catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null,"Failed to connect to server");
					return;
				}

				PrintWriter socketWriter;
				try {
					socketWriter = new PrintWriter(socket.getOutputStream());
				} catch (IOException e) {
					JOptionPane.showMessageDialog(null, "Failed to attach printwriter to socket!");
					e.printStackTrace();
					return;
				}
				
				//Ask for a puzzle to do
				
				
				
				//new GameGUI(socketWriter);
	}
}
