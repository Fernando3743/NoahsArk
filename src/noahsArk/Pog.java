/*
 * Interactive Programming
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Noah's Ark Miniproject
 */
package noahsArk;

import java.util.Random;

// TODO: Auto-generated Javadoc
/**
 * The Class Pog.
 * Manages the pog's state and behavior.
 */
public class Pog {
	
	private int style = 0,avaliableStyles;
	private boolean flipped=false;
	int flipAmount=0;
	
	/**
	 * Instantiates a new pog.
	 *
	 * @param avaliableStyles the avaliable styles
	 */
	public Pog(int avaliableStyles) {
		this.avaliableStyles = avaliableStyles;
		Random random = new Random();
		style = random.nextInt(avaliableStyles)+1;
	}

	/**
	 * Gets the style.
	 *
	 * @return the style
	 */
	public int getStyle() {
		return style;
	}

	/**
	 * Sets the style.
	 *
	 * @param style the new style
	 */
	public void setStyle(int style) {
		this.style = style;
	}
	
	/**
	 * Update style.
	 * Updates the current style to a random style.
	 * @return the int
	 */
	public int updateStyle() {
		Random random = new Random();
		style = random.nextInt(this.avaliableStyles)+1;
		return style;
	}
	
	/**
	 * Flip.
	 */
	public void flip() {
		flipAmount++;
		if(flipAmount>1)
			flipped=true;
	}
	
	/**
	 * Gets the flipped.
	 * Returns true if the pog has been flipped more than once
	 * @return the flipped
	 */
	public boolean getFlipped() {
		return this.flipped;
	}
	

}
