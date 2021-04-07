package noahsArk;

import java.util.Random;

public class Pog {
	
	int style = 0;
	
	public Pog(int avaliableStyles) {
		Random random = new Random();
		style = random.nextInt()+1;
	}

	public int getStyle() {
		return style;
	}

	public void setStyle(int style) {
		this.style = style;
	}
	
	public int updateStyle() {
		Random random = new Random();
		style = random.nextInt()+1;
		return style;
	}
	

}
