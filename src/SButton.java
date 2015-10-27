import javax.swing.JButton;

/**
 * New button class capable of storing each button's
 * value.
 * 
 * @author Matt
 */
public class SButton extends JButton{

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
	}

	/**
	 * Set the button's x and y coordinates on the grid.
	 * @param x
	 * @param y
	 */
	private void setCoords(int x, int y){
		xCoord = x;
		yCoord = y;
	}
	
	/**
	 * Get the button's x coordinate.
	 * @return int xCoord
	 */
	private int getXCoord(){
		return xCoord;
	}
	
	/**
	 * Get the button's y coordinate.
	 * @return int yCoord
	 */
	private int getYCoord(){
		return yCoord;
	}
	
	/**
	 * Set the button's true value.
	 * @param v
	 */
	private void setValue(int v){
		value = v;
	}
	
	/**
	 * Get the button's true value.
	 * @return int value
	 */
	private int getValue(){
		return value;
	}
	
	/**
	 * Set the button's displaying value.
	 * @param v
	 */
	private void setDisplayValue(int v){
		displayValue = v;
		this.setText("" + displayValue);
	}
	
	/**
	 * Get the button's displaying value.
	 * @return int displayValue
	 */
	private int getDisplayValue(){
		return displayValue;
	}
	
	/**
	 * Returns true if the true value and the displayed value
	 * are equal. False otherwise.
	 * @return boolean
	 */
	private boolean valueAndDisplayEqual(){
		if( value == displayValue ) return true;
		return false;
	}
}
