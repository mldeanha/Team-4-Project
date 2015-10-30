import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

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
	private SButton [][] buttonGrid;
	private SButton [] buttonRow;
	private Timer timer;
	private Scanner scanner;
	
	private int currentNumber;

	/**
	 * Constructor class for GameGUI
	 */
	public GameGUI(Socket socket){
		try {
			writer = new PrintWriter(socket.getOutputStream());
			scanner = new Scanner(socket.getInputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.writer = writer;
		
		//Build Jframe
		frame = new JFrame("SudokuPlus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		frame.setLayout(new GridLayout());

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
		buttonGrid = new SButton[9][9];
		for(int i = 0; i < 9; i++){
			for(int k = 0; k < 9; k++){
				SButton current = new SButton((i) + ", " + (k));
				play.add(current);
				buttonGrid[i][k] = current;
				current.setCoords(i, k);
				current.addActionListener(this);
			}
		}
		play.setVisible(true);
		
		//Number Selection Row
		pick = new JPanel();
		pick.setSize(400,200);
		buttonRow = new SButton[9];
		for(int i = 0; i < 9; i++){
			SButton current = new SButton("" + (i + 1));
			pick.add(current);
			buttonRow[i] = current;
			current.setCoords(0, 0);
			current.setValue(i + 1);
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
		timer = new Timer(1000, this);
		timer.start();

	}

	/**
	 * ActionListener method for detecting user input
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == timer){
			System.out.println("Timer Proc");
			writer.println("1");
			writer.flush();
			String string = "";
			String [] line;
			string = string + scanner.nextLine();
			line = string.split(" ");
			
			for(int i = 0, k = 0; i < 81; i++){
				if(k == 9){
					k = 0;
				}
				System.out.println(line[i]);
				buttonGrid[(int) Math.floor(i/9)][k].setDisplayValue(Integer.parseInt(line[i]));
				k++;
			}				
			area.setText(string);
			System.out.println(string);
		}
		
		if(e.getSource() == menu){
			
			
			return;
		}
		
		for(SButton check : buttonRow){
			if(e.getSource() == check){
				currentNumber = check.getValue();
				field.setText("" + currentNumber);
				return;
			}
		}
		
		for(SButton[] current : buttonGrid){
			for(SButton check : current){
				if(e.getSource() == check){
					return;
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
		
		Socket socket = null;
		try {
			socket = new Socket("127.1.1", 7776);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		new GameGUI(socket);
	}
}

