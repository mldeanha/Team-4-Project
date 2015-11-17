import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	public OptionGUI(){
		frame = new JFrame("Opening Options");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400, 400);
		frame.setAlwaysOnTop(true);
		frame.setMaximumSize(frame.getSize());
		frame.setMinimumSize(frame.getSize());
		
		panel = new JPanel();
		
		label = new JLabel("Please select your difficulty:");
		panel.add(label);
		
		easy = new JRadioButton("Easy");
		easy.setSelected(true);
		easy.addActionListener(this);
		
		medium = new JRadioButton("Medium");
		medium.addActionListener(this);
		
		hard = new JRadioButton("Hard");
		hard.addActionListener(this);
		
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
		usernameField.addActionListener(this);
		panel.add(usernameField);
		
		label = new JLabel("Please enter an IP address to connect to:");
		panel.add(label);
		
		ipField = new JTextField();
		ipField.setPreferredSize(new Dimension(300, 20));
		ipField.addActionListener(this);
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
		// TODO Auto-generated method stub
		
	}

	public static void main(String[] args){
		OptionGUI opt = new OptionGUI();
	}
}
