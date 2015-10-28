import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

/**
 * New button class capable of storing each button's
 * value.
 * 
 * @author Matt
 */
public class SButton extends JButton implements ActionListener{

	int xCoord;
	int yCoord;
	int value;
	int displayValue;
	
	/**
	 * String constructor for testing purposes.
	 * @param string
	 */
	public SButton(String string) {
		this.setText(string);
		xCoord = 0;
		yCoord = 0;
		value = 0;
		displayValue = 0;
	}

	/**
	 * Set the button's x and y coordinates on the grid.
	 * @param x
	 * @param y
	 */
	public void setCoords(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	/**
	 * Get the button's x coordinate.
	 * @return int xCoord
	 */
	public int getXCoord(){
		return xCoord;
	}
	
	/**
	 * Get the button's y coordinate.
	 * @return int yCoord
	 */
	public int getYCoord(){
		return yCoord;
	}
	
	/**
	 * Set the button's true value.
	 * @param v
	 */
	public void setValue(int v){
		value = v;
	}
	
	/**
	 * Get the button's true value.
	 * @return int value
	 */
	public int getValue(){
		return value;
	}
	
	/**
	 * Set the button's displaying value.
	 * @param v
	 */
	public void setDisplayValue(int v){
		displayValue = v;
		this.setText("" + displayValue);
	}
	
	/**
	 * Get the button's displaying value.
	 * @return int displayValue
	 */
	public int getDisplayValue(){
		return displayValue;
	}
	
	/**
	 * Returns true if the true value and the displayed value
	 * are equal. False otherwise.
	 * @return boolean
	 */
	public boolean valueAndDisplayEqual(){
		if( value == displayValue ) return true;
		return false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println(xCoord + ", " + yCoord);
		
	}
}
