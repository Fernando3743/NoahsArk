/*
 * Interactive Programming
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Noah's Ark Miniproject
 */
package noahsArk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class GameManager.
 * Sets up and manage the main game window.
 */
public class GameManager {
	
	private int level, points;
	private GameStatus gameStatus;
	private List<Pog> pogsList,pogsFlipped;
	private List<Integer> usedStyles;
	
	/**
	 * Instantiates a new game manager.
	 */
	public GameManager() {
		level=1;
		points = 100;
		gameStatus = GameStatus.Playing;
		pogsList = new ArrayList<Pog>();
		usedStyles = new ArrayList<Integer>();
		pogsFlipped = new ArrayList<Pog>();
	}
	
	/**
	 * Start game.
	 */
	public void startGame() {
		
		for(int i=0; i <level+1; i++) {
			if(pogsList.size()>0) {
				Pog potentialPog = new Pog(6);
				if(!usedStyles.contains(potentialPog.getStyle())) {
					Pog potentialPog2 = new Pog(6);
					potentialPog2.setStyle(potentialPog.getStyle());
					pogsList.add(potentialPog);
					pogsList.add(potentialPog2);
					usedStyles.add(potentialPog.getStyle());
				}
				else {
					i--;
				}
			}
			else {
				Pog potentialPog = new Pog(6);
				Pog potentialPog2 = new Pog(6);
				potentialPog2.setStyle(potentialPog.getStyle());
				pogsList.add(potentialPog);
				pogsList.add(potentialPog2);
				usedStyles.add(potentialPog.getStyle());
			}	
		}
	}
	
	/**
	 * Adds the points.
	 *
	 * @param points the pointsToAdd
	 */
	public void addPoints(int points) {
		this.points += points;
	}
	
	/**
	 * Lose points.
	 *
	 * @param negativePoints the negative points
	 * @return the game status
	 */
	public GameStatus losePoints(int negativePoints) {
		this.points -= negativePoints;
		if(this.points <= 0) {
			return GameStatus.GameOver;
		}
		return GameStatus.LosePoints;
	}
	
	/**
	 * Next level.
	 */
	public void nextLevel() {
		if(level < 5) {
			level++;
		}
		gameStatus = GameStatus.Playing;
		pogsList.clear();
		usedStyles.clear();
		pogsFlipped.clear();
	}
	
	/**
	 * Gets the pogs list.
	 *
	 * @return the pogs list
	 */
	public List<Pog> getPogsList() {
		Collections.shuffle(pogsList);
		return pogsList;
	}
	
	/**
	 * Gets the ordered pogs list.
	 *
	 * @return the ordered pogs list
	 */
	public List<Pog> getOrderedPogsList() {
		return pogsList;
	}
	
	/**
	 * Flip pog.
	 *
	 * @param pogToFlip the pog to flip
	 * @return the game status
	 */
	public GameStatus flipPog(Pog pogToFlip) {
		pogToFlip.flip();
		pogsFlipped.add(pogToFlip);
		
		if(pogsFlipped.size()>1) {
			if(pogToFlip.getStyle()==pogsFlipped.get(0).getStyle()) {
				points+=10;
				if(pogsList.size()==2) {
					return GameStatus.NextLevel;
				}
				return GameStatus.Win;
			}
			if(!pogsFlipped.get(0).getFlipped() || !pogsFlipped.get(1).getFlipped()) {
				return GameStatus.Playing;
			}
			return losePoints(10);
		}
		return GameStatus.stillPlaying;
		
	}
	
	/**
	 * Clear pogs flipped.
	 */
	public void clearPogsFlipped() {
		pogsFlipped.clear();
	}
	
	/**
	 * Gets the game status.
	 *
	 * @return the game status
	 */
	public GameStatus getGameStatus() {
		return gameStatus;
	}
	
	/**
	 * Gets the pogs flipped.
	 *
	 * @return the pogs flipped list.
	 */
	public List<Pog> getPogsFlipped() {
		return pogsFlipped;
	}
	
	/**
	 * Gets the level.
	 *
	 * @return the level
	 */
	public int getLevel() {
		return this.level;
	}
	
	/**
	 * Gets the points.
	 *
	 * @return the points
	 */
	public int getPoints() {
		return this.points;
	}
	
	//Testing
	
	/**
	 * Sets the level.
	 *
	 * @param level the new level
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	/**
	 * Show pogs.
	 */
	public void showPogs() {
		Collections.shuffle(pogsList);
		for(Pog p: pogsList) {
			System.out.println(p.getStyle());
		}
	}
	
}
