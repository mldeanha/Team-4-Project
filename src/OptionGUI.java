import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.swing.*;

public class OptionGUI extends JFrame implements ActionListener{
	
	private JFrame frame;
	private JLabel label;
	private JTextField usernameField;
	private JTextField ipField;
	private JPanel panel;
	
	private JPanel radioPanel;
	private ButtonGroup group;
	private JRadioButton easy;
	private JRadioButton medium;
	private JRadioButton hard;
	
	private JPanel butPanel;
	private JButton cancel;
	private JButton submit;
	
	private String username;
	private String ipAddr;
	private int difficulty;
	
	public OptionGUI(){
		frame = new JFrame("Opening Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 200);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		panel = new JPanel();
		
		label = new JLabel("Please select your difficulty:");
		panel.add(label);
		
		easy = new JRadioButton("Easy");
		easy.setSelected(true);
		
		medium = new JRadioButton("Medium");
		
		hard = new JRadioButton("Hard");
		
		group = new ButtonGroup();
		group.add(easy);
		group.add(medium);
		group.add(hard);

		radioPanel = new JPanel();
		radioPanel.add(easy);
		radioPanel.add(medium);
		radioPanel.add(hard);
		radioPanel.setVisible(true);
		
		panel.add(radioPanel);
		
		label = new JLabel("Please enter a username:");
		panel.add(label);
		
		usernameField = new JTextField();
		usernameField.setPreferredSize(new Dimension(300, 20));
		panel.add(usernameField);
		
		label = new JLabel("Please enter an IP address to connect to:");
		panel.add(label);
		
		ipField = new JTextField();
		ipField.setPreferredSize(new Dimension(300, 20));
		panel.add(ipField);
		
		cancel = new JButton("Cancel");
		cancel.addActionListener(this);
		
		submit = new JButton("Submit");
		submit.addActionListener(this);
		
		butPanel = new JPanel(new GridLayout(1, 2));
		butPanel.add(cancel);
		butPanel.add(submit);
		butPanel.setVisible(true);
		panel.add(butPanel);
		
		panel.setVisible(true);
		frame.add(panel);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == cancel){
			System.exit(0);
		}
		
		if(e.getSource() == submit){
			username = usernameField.getText();
			
			if(username.equals(null) || username.equals("")) {
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
			Scanner scanner = null;
			try {

				scanner = new Scanner(socket.getInputStream());

			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			try {
				PrintWriter w = new PrintWriter(socket.getOutputStream());
				w.println(difficulty);
				w.flush();
			} catch (IOException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
			new GameGUI(socket, username);
			frame.dispose();
		}
		
	}


	public static void main(String[] args){
		OptionGUI opt = new OptionGUI();
	}
}
