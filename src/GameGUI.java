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
	
	//MenuBar items
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem menuItem;
	
	/**
	 * Constructor class for GameGUI
	 */
	public GameGUI(/*PrintWriter writer*/){
		
		//Build Jframe
		frame = new JFrame("SudokuPlus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setVisible(true);
		
		//Build Menu
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("Main Menu");
		menuBar.add(menu);
		
		menuItem = new JMenuItem("About");
		menuItem.getAccessibleContext().setAccessibleDescription(
				"Displays information about this program");
		menu.add(menuItem);
		
		//Finish JFrame
		label1 = new JLabel("Haha, a label");
		frame.getContentPane().add(label1, BorderLayout.CENTER);
		frame.setJMenuBar(menuBar);
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

