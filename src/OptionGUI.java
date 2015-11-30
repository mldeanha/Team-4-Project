import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

/**
 * This class implements an opening prompt for client users:
 * prompts for difficulty, username, and the ip of the server they wish to connect to.
 * 
 * This class also contains the client code. After the user hits submit, the window is
 * disposed of and this class then run the game and creates the client socket.
 * 
 * @author Mathonwy Dean-Hall
 *
 */
public class OptionGUI extends JFrame implements ActionListener{
	
	//GUI frame and fields
	private JFrame frame;
	private JLabel label;
	private JTextField usernameField;
	private JTextField ipField;
	private JPanel panel;
	
	//Radio buttons
	private JPanel radioPanel;
	private ButtonGroup group;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	
	//Cancel / Submit buttons
	private JPanel butPanel;
	private JButton cancel;
	private JButton submit;
	
	private String username;
	private String ipAddr;
	private int difficulty;
	
	/**
	 * Class constructor.
	 */
	public OptionGUI(){
		
		//Window size
		frame = new JFrame("Opening Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		//Panel to contain all elements
		panel = new JPanel();
		
		label = new JLabel("Please select your difficulty:");
		panel.add(label);
		
		//Creating and setting radio buttons
		easy = new JRadioButton("Easy");
		easy.setSelected(true);
		medium = new JRadioButton("Medium");
		hard = new JRadioButton("Hard");
		//Radio button group
		group = new ButtonGroup();
		group.add(easy);
		group.add(medium);
		group.add(hard);
		//Give them their own panel
		radioPanel = new JPanel();
		radioPanel.add(easy);
		radioPanel.add(medium);
		radioPanel.add(hard);
		radioPanel.setVisible(true);
		//Add radio buttons to main panel
		panel.add(radioPanel);
		
		label = new JLabel("Please enter a username:");
		panel.add(label);
		
		//Username input field
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(300, 20));
		panel.add(usernameField);
		
		label = new JLabel("Please enter an IP address to connect to:");
		panel.add(label);
		
		//IPAddress input field
		ipField = new JTextField();
		ipField.setPreferredSize(new Dimension(300, 20));
		panel.add(ipField);
		
		//Cancel and Submit buttons
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
		
		butPanel = new JPanel(new GridLayout(1, 2));
		butPanel.add(cancel);
		butPanel.add(submit);
		butPanel.setVisible(true);
		panel.add(butPanel);
		
		//Finish panel and frame setup
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//Exit program if user cancels input
		if(e.getSource() == cancel){
			System.exit(0);
		}
		
		//Submit all fields.
		//If username is empty, prompt user to try again.
		if(e.getSource() == submit){
			username = usernameField.getText();
			
			if(username.equals(null) || username.equals("")) {	//Fails to submit with bad username
				JOptionPane.showMessageDialog(null, "You must enter a username.");
				return;
			}
			
			ipAddr = ipField.getText();
			
			if( hard.isSelected() ) difficulty = 2;
			else if( medium.isSelected() ) difficulty = 1;
			else difficulty = 0;
			
			int port = 7776;
			Socket socket;

			try {
				socket = new Socket(ipAddr, port);
			} catch (Exception ex) {
				ex.printStackTrace();
				JOptionPane.showMessageDialog(null,"Failed to connect to server");
				return;
			}
		
			try {
				PrintWriter w = new PrintWriter(socket.getOutputStream());
				w.println(difficulty);
				w.flush();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
			new GameGUI(socket, username);
			frame.dispose();
		}
		
	}

	/**
	 * Run the client. No args.
	 * @param args
	 */
	public static void main(String[] args){
		OptionGUI opt = new OptionGUI();
	}
}
