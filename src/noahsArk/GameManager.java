package noahsArk;

import java.util.ArrayList;

public class GameManager {
	
	private int level, points;
	private ArrayList<Pog> pogsList;
	private ArrayList<Integer> usedStyles;
	private ArrayList<Integer> stylesFlipped;
	private GameStatus gameStatus;
	
	public GameManager() {
		level=1;
		pogsList = new ArrayList<Pog>();
	}
	
}
