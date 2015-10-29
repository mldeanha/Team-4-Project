import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Arrays;

import javax.swing.*;

/**
 * Class which contains the Graphical Use Interface for
 * the Team 4 project SudokuPlus
 * 
 * 					Does it need to extend JFrame?
 * 					Do more research
 * @author Matt
 *
 */
public class GameGUI extends JFrame implements ActionListener{

	//Base frame
	private JFrame frame;
	private JLabel label1;
	private JPanel panel;
	private JPanel play;
	private JPanel pick;
	private JTextField field;
	private JTextArea area;

	//MenuBar items
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;

	//Etc
	private PrintWriter writer;
	private SButton [][] buttons;

	/**
	 * Constructor class for GameGUI
	 */
	public GameGUI(PrintWriter writer){

		this.writer = writer;
		
		//Build Jframe
		frame = new JFrame("SudokuPlus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new SpringLayout());

		//Build Menu
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("Main Menu");
		menuBar.add(menu);

		menuItem = new JMenuItem("About");
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Displays information about this program");
		menu.add(menuItem);

		//Build Panels
		panel = new JPanel(new GridLayout(2,2));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Button Grid
		play = new JPanel(new GridLayout(9,9));
		play.setSize(400, 400);
		panel.setSize(400, 600);
		buttons = new SButton[9][9];
		for(int i = 0; i < 9; i++){
			for(int k = 0; k < 9; k++){
				SButton current = new SButton((i) + ", " + (k));
				play.add(current);
				buttons[i][k] = current;
				current.setCoords(i, k);
				current.addActionListener(this);
			}
		}
		play.setVisible(true);
		
		//Number Selection Row
		pick = new JPanel();
		pick.setSize(400,200);
		for(int i = 0; i < 9; i++){
			JButton current = new JButton("" + (i + 1));
			pick.add(current);
			current.addActionListener(this);

		}
		pick.setVisible(true);

		//Chat Window and Input field
		field = new JTextField(20);
		area = new JTextArea(10,20);
		area.setEditable(false);
		
		panel.add(play);
		panel.add(area);
		panel.add(pick);
		panel.add(field);

		panel.setOpaque(false);
		panel.setVisible(true);		

		//Finish JFrame
		frame.setJMenuBar(menuBar);
		frame.add(panel);
		frame.setVisible(true);

	}

	/**
	 * ActionListener method for detecting user input
	 * @param arg0
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == menu){
			
			
			return;
		}
		
		for(SButton[] current : buttons){
			for(SButton check : current){
				if(e.getSource() == check){
				
				}
			}
		}

	}
	
	//Temporary command setup
	//Format for Commands: "Command,input,input..."
	public void sendCommand(){
		
	}

	/**
	 * Main method for running GUI
	 */
	public static void main(String args[]){
		String temp = "Project Assets/tester.txt";
		PrintWriter tempWriter = null;
		try {
			tempWriter = new PrintWriter(temp);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		new GameGUI(tempWriter);
	}
}

