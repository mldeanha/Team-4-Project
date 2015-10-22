import java.awt.*;
import java.awt.event.*;
import java.io.PrintWriter;

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
	
	//MenuBar items
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	//Etc
	private int buttonID;

	/**
	 * Constructor class for GameGUI
	 */
	public GameGUI(/*PrintWriter writer*/){
		
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
		panel = new JPanel(new GridLayout(1,1));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		play = new JPanel(new GridLayout(9,9));
		play.setSize(400, 400);
		panel.setSize(400, 400);
		buttonID = 1;
		while(buttonID <= 81){
			JButton current = new JButton("" + buttonID);
			play.add(current);
			buttonID++;
		}
		play.setVisible(true);
		
		panel.add(play);
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
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Main method for running GUI
	 */
	public static void main(String args[]){
		new GameGUI();
	}
}

