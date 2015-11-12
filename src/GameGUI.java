import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
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
	private JPanel panel;
	private JPanel play;
	private JPanel pick;
	private JTextField field;
	private JTextArea area;

	//MenuBar items
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem aboutMenuItem;

	//Etc
	private PrintWriter writer;
	private SButton [][] buttonGrid;
	private SButton [] buttonRow;
	private Timer timer;
	private Scanner scanner;
	Scanner keyboard = new Scanner(System.in);
	private int score = 0;
	
	private int currentNumber = -1;

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

		//this.writer = writer;
		
		//Build Jframe
		frame = new JFrame("SudokuPlus");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 600);
		//frame.setLayout(new GridLayout());

		//Build Menu
		menuBar = new JMenuBar();
		menu = new JMenu("File");
		menu.getAccessibleContext().setAccessibleDescription("Main Menu");
		menuBar.add(menu);

		aboutMenuItem = new JMenuItem("About");
		aboutMenuItem.getAccessibleContext().setAccessibleDescription(
				"Displays information about this program");
		menu.add(aboutMenuItem);
		aboutMenuItem.addActionListener(this);

		//Build Panels
		panel = new JPanel(new GridBagLayout());
		panel.setBorder(BorderFactory.createLineBorder(Color.black));

		//Button Grid
		play = new JPanel(new GridLayout(9,9));
		play.setSize(400, 400);
		panel.setSize(400, 600);
		buttonGrid = new SButton[9][9];
		for(int i = 0; i < 9; i++){
			int top = 1, left = 1,bottom = 1,right = 1;
			for(int k = 0; k < 9; k++){
				SButton current = new SButton("");
				play.add(current);
				buttonGrid[i][k] = current;
				current.setCoords(i, k);
				current.addActionListener(this);
				if((i + 1) % 3 == 0){
					bottom = 3;
				}
				if((k + 1) % 3 == 0){
					right = 3;
				}
				if(i == 0){
					top = 3;
				}
				if(k == 0){
					left = 3;
				}
				current.setBackground(new Color(249,241,220));
				current.setFocusable(false);
				current.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, new Color(66,36,26)));
				top = 1;
				left = 1;
				bottom = 1;
				right = 1;

				
			}
		}
		//play.setBackground(Color.BLACK);
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
			current.setBackground(new Color(245,148,118));
			current.addActionListener(this);

		}
		pick.setVisible(true);

		//Chat Window and Input field
		field = new JTextField(20);
		area = new JTextArea(20,20);
		area.setEditable(false);
		area.setWrapStyleWord(true);
		area.setBorder(BorderFactory.createLineBorder(Color.black));
		
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.weightx = 1.0;
		c.weighty = 1.0;
		panel.add(play, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 0;
		c.gridwidth = 1;
		c.gridheight = 2;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		c.anchor = GridBagConstraints.FIRST_LINE_END;
		c.weightx = 0.5;
		c.weighty = 1.0;
		panel.add(area, c);
		
		c = new GridBagConstraints();
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = 2;
		c.gridheight = 1;
		c.fill = GridBagConstraints.BOTH;
		c.ipadx = 20;
		c.ipady = 20;
		c.anchor = GridBagConstraints.LAST_LINE_START;
		c.weightx = 1.0;
		panel.add(pick, c);
		
		c = new GridBagConstraints();
		c.gridx = 2;
		c.gridy = 2;
		c.gridwidth = 1;
		c.gridheight = 1;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.ipadx = 20;
		c.ipady = 20;
		c.weightx = 0.5;
		panel.add(field, c);

		panel.setOpaque(false);
		panel.setVisible(true);		

		//Finish JFrame
		frame.setJMenuBar(menuBar);
		frame.add(panel);
		frame.setMinimumSize(new Dimension(700, 450));
		frame.setVisible(true);
		timer = new Timer(250, this);		
		timer.setInitialDelay(0);
		timer.start();
	}

	/**
	 * ActionListener method for detecting user input.
	 * All ActionListener elements use this method. Possible to
	 * be improved with more specialized listeners.
	 * 
	 * @param e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == timer){ //This action performs at every timer tick
			String string = "";
			String [] line;
			sendCommand("2");		//Command 2: Update Score
			string = string + scanner.nextLine();				
			score = Integer.parseInt(string);
			System.out.println(score);
			string = "";
			sendCommand("1");		//Command 1: Check for an update to the puzzle
			string = string + scanner.nextLine();				
			line = string.split(" ");
			if(line.length == 81){ //if the input was the puzzle change the buttons
				
				for(int i = 0, k = 0; i < 81; i++){
					if(k == 9){
						k = 0;
					}
					buttonGrid[(int) Math.floor(i/9)][k].setDisplayValue(Integer.parseInt(line[i]));
					buttonGrid[(int) Math.floor(i/9)][k].setBackground(new Color(249,241,220));
					if(Integer.parseInt(line[i]) != 0){		
						buttonGrid[(int) Math.floor(i/9)][k].setValue(Integer.parseInt(line[i]));
						buttonGrid[(int) Math.floor(i/9)][k].setForeground(Color.black);

					}
					k++;
				}	
				//area.setText(string);//debug purposes
			}else if(string.equals("true")){ //Win Condition
				System.out.println("Game Over");
				timer.stop();
				JOptionPane.showMessageDialog(null, "WINNER!\n\nPlease relaunch the server and play again!");
				System.exit(1);
				
			}
			area.setText(frame.getWidth() + ", " + frame.getHeight());//windowsize debug
		}
		
		if(e.getSource() == aboutMenuItem){ //This action performs the function of the About menubar item
			JOptionPane.showMessageDialog(null, "SudokuPlus\nA Sudoku game for multiple people\n\n"
					+ "Developed by:\n-Mitchell Baer     -Eric Celerin     -Matt Dean-Hall\n\nCreated 2015");
			
			return;
		}
		
		for(SButton check : buttonRow){		//This action listens for the user's use of the number selection row
			
			if(e.getSource() == check){
				currentNumber = check.getValue();
				field.setText("" + currentNumber);
				for(SButton renew : buttonRow){
					renew.setBackground(new Color(245,148,118));
					check.setSelected(false);
				}
				check.setSelected(true);
				check.setBackground(new Color(169,169,169));
				
				return;
			}
		}
		
		
		for(SButton[] current : buttonGrid){	//This action listens for the user's use of a main grid button
			for(SButton check : current){
				if(e.getSource() == check){
				
					if(currentNumber < 0 && !check.valueAndDisplayEqual()){
						JOptionPane.showMessageDialog(this,"Please Select a Number for the Cell.");
						return;
				}else if (check.getValue() < 0){
						
					
						sendCommand("0 "+currentNumber + " " + check.getYCoord() + " " + check.getXCoord());	//Command 0: Send the selected box to the server
						String string = "";
						string = string + scanner.nextLine();						
						check.setDisplayValue(currentNumber);
						if(string.equals("0")){
							//check.setEnabled(false);
							check.setBackground(new Color(249,241,220));
						}else{
							check.setForeground(new Color(100,0,0));
							check.setBackground(new Color(225,0,0));
						}
						
						return;
					}else{
						for(SButton[] row : buttonGrid){
							for(SButton highlight : row){
								
								highlight.setBackground(new Color(249,241,220));
								if(highlight.getValue()==check.getValue()){
									highlight.setBackground(new Color(135,206,250));
								}
							}
						}
					}
					
				}
			}
		} //End ButtonGrid Check
		
	} //End ActionListener
	
	
	/**
	 * Command input method that sends commands through
	 * the user thread to the server.
	 * 
	 * Format for Commands: "Command#, input, input, ..."
	 * @param command
	 */
	public void sendCommand(String command){
		writer.println(command);
		writer.flush();
	}
}

