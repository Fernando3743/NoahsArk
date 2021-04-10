/*
 * Interactive Programming
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Noah's Ark Miniproject
 */
package noahsArk;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

// TODO: Auto-generated Javadoc
/**
 * The Class Main Window.
 * Sets up and manage the main window.
 */
public class MainWindow extends JFrame {
	
	private GameManager gameManager;
	private Panel mainPanel, utilitiesPanel, gamePanel;
	private JLabel timerLabel, pointsLabel;
	private ArrayList<JLabel> pogsLabels;
	private ArrayList<Integer> gamePanelSizes, pogSizes;
	private Listener listener;
	private Timer winCaseTimer, losePointsCaseTimer, gameOverCaseTimer, nextLevelCaseTimer, timeTimer, playingCaseTimer;
	private int time = 0;
	private JButton exitButton;
	
	/**
	 * Instantiates a new main window.
	 */
	public MainWindow() {
		gameManager = new GameManager();
		mainPanel = new Panel();
		utilitiesPanel = new Panel();
		gamePanel = new Panel();
		pogsLabels = new ArrayList<JLabel>();
		listener = new Listener();
		winCaseTimer = new Timer(700,listener);
		losePointsCaseTimer = new Timer(400,listener);
		gameOverCaseTimer = new Timer(700,listener);
		nextLevelCaseTimer = new Timer(1000,listener);
		playingCaseTimer = new Timer(700,listener);
		timeTimer = new Timer(1000,listener);
		
		gamePanelSizes = new  ArrayList<Integer>();
		gamePanelSizes.add(350);
		gamePanelSizes.add(500);
		gamePanelSizes.add(650);
		gamePanelSizes.add(800);
		gamePanelSizes.add(800);
		gamePanelSizes.add(800);
		gamePanelSizes.add(800);
		gamePanelSizes.add(800);
		
		pogSizes = new ArrayList<Integer>();
		pogSizes.add(150);
		pogSizes.add(150);
		pogSizes.add(130);
		pogSizes.add(130);
		pogSizes.add(130);
		pogSizes.add(130);
		pogSizes.add(130);
		pogSizes.add(130);
		
		this.setTitle("Noah's Ark");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMainWindow();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the main window.
	 */
	private void initMainWindow() {
		exitButton = new JButton("Exit");
		exitButton.addActionListener(listener);
		timeTimer.start();
		gameManager.startGame();
		
		mainPanel.setImagen("/images/MainBackground.png");
		mainPanel.setPreferredSize(new Dimension(883,498));
		
		add(mainPanel);
		
		timerLabel = new JLabel("Time :");
		timerLabel.setFont(new Font("TimesRoman",Font.BOLD,28));
		utilitiesPanel.setPreferredSize(new Dimension(883,50));
		utilitiesPanel.setImagen("/images/utilitiesUI.png");
		utilitiesPanel.add(timerLabel);
		
		utilitiesPanel.add(Box.createRigidArea(new Dimension(150,40)));
		
		pointsLabel = new JLabel("Points :");
		pointsLabel.setFont(new Font("TimesRoman",Font.BOLD,28));
		utilitiesPanel.add(pointsLabel);
		pointsLabel.setText("Points :"+gameManager.getPoints());
		
		utilitiesPanel.add(Box.createRigidArea(new Dimension(80,40)));
		utilitiesPanel.add(exitButton);
		
		gamePanel = new Panel();
		gamePanel.setPreferredSize(new Dimension(gamePanelSizes.get(gameManager.getLevel()-1),448));
		
		mainPanel.add(utilitiesPanel);
		mainPanel.add(gamePanel);
		
		
		for(int i=0; i<gameManager.getPogsList().size();i++) {
			JLabel pogLabel = new JLabel(scalePogIcon(20,
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1)));
			pogLabel.addMouseListener(listener);				
			pogsLabels.add(pogLabel);
		}
		
		for(JLabel pogLabel: pogsLabels) {
			gamePanel.add(pogLabel);
		}
	}
	
	/**
	 * Start game.
	 */
	private void startGame() {
		
		gamePanel.removeAll();
		System.out.println(gameManager.getLevel());
		gamePanel.setPreferredSize(
				new Dimension(gamePanelSizes.get(gameManager.getLevel()-1),448));
		
		for(int i=0; i<gameManager.getPogsList().size();i++) {
			JLabel pogLabel = new JLabel(scalePogIcon(20,
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1)));
			pogLabel.addMouseListener(listener);				
			pogsLabels.add(pogLabel);
		}
		
		for(JLabel pogLabel: pogsLabels) {
			gamePanel.add(pogLabel);
		}
		this.pack();
	}
	
	/**
	 * Scale pog icon.
	 *
	 * @param style the style
	 * @param width the width
	 * @param height the height
	 * @return the image icon
	 */
	private ImageIcon scalePogIcon(int style,int width, int height ) {
		ImageIcon scaledImage = new ImageIcon();
		
		try {
			scaledImage = new ImageIcon(
					ImageIO.read(new File("src/images/"+style+".png"))
					.getScaledInstance(width, height, Image.SCALE_DEFAULT));
		}
		catch(IOException e) {
			
		}
		
		return scaledImage;
	}
	
	/**
	 * Flip pog.
	 *
	 * @param indexPogClicked the index pog clicked
	 */
	private void flipPog(int indexPogClicked) {
		Pog pogClicked = gameManager.getOrderedPogsList().get(indexPogClicked);
		if(!gameManager.getPogsFlipped().contains(pogClicked)) {
			pogsLabels.get(indexPogClicked).setIcon(scalePogIcon(pogClicked.getStyle(),
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1)));
			
			GameStatus gameStatus = gameManager.flipPog(pogClicked);
			System.out.println(gameStatus);
			
			switch(gameStatus) {
			case Win:
				if(pogsLabels.size()>1) {
					winCaseTimer.start();
				}	
				break;	
			case LosePoints:
				losePointsCaseTimer.start();
				break;
			case Playing:
				playingCaseTimer.start();
				break;
			case NextLevel:
				nextLevelCaseTimer.start();
				break;
			case GameOver:
				gameOverCase();
				break;
			}
		}
	}
	
	/**
	 * Win case.
	 */
	private void winCase() {
		pointsLabel.setText("Points :"+gameManager.getPoints());
		for(Pog pog: gameManager.getPogsFlipped()) {
			int pogIndex = gameManager.getOrderedPogsList().indexOf(pog);
			gameManager.getOrderedPogsList().remove(pogIndex);
			pogsLabels.get(pogIndex).setIcon(new ImageIcon(new BufferedImage(
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1),
					BufferedImage.TYPE_INT_ARGB)));
			pogsLabels.get(pogIndex).removeMouseListener(listener);
			pogsLabels.remove(pogIndex);
		}
		gamePanel.repaint();
		gameManager.clearPogsFlipped();
		winCaseTimer.stop();
	}
	
	/**
	 * Lose points case.
	 */
	private void losePointsCase() {
		pointsLabel.setText("Points :"+gameManager.getPoints());
		for(Pog pog: gameManager.getPogsFlipped()) {
			int pogIndex = gameManager.getOrderedPogsList().indexOf(pog);
			pogsLabels.get(pogIndex).setIcon(scalePogIcon(20,
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1)));
		}
		gameManager.clearPogsFlipped();
		losePointsCaseTimer.stop();
	}
	
	/**
	 * Playing case.
	 */
	private void playingCase() {
		for(Pog pog: gameManager.getPogsFlipped()) {
			int pogIndex = gameManager.getOrderedPogsList().indexOf(pog);
			pogsLabels.get(pogIndex).setIcon(scalePogIcon(20,
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1)));
		}
		gameManager.clearPogsFlipped();
		playingCaseTimer.stop();
	}
	
	/**
	 * Next level case.
	 */
	private void nextLevelCase() {
		gameManager.addPoints(50);
		pointsLabel.setText("Points :"+gameManager.getPoints());
		for(Pog pog: gameManager.getPogsFlipped()) {
			int pogIndex = gameManager.getOrderedPogsList().indexOf(pog);
			gameManager.getOrderedPogsList().remove(pogIndex);
			pogsLabels.get(pogIndex).setIcon(new ImageIcon(new BufferedImage(
					pogSizes.get(gameManager.getLevel()-1),
					pogSizes.get(gameManager.getLevel()-1),
					BufferedImage.TYPE_INT_ARGB)));
			pogsLabels.get(pogIndex).removeMouseListener(listener);
			pogsLabels.remove(pogIndex);
		}
		nextLevelCaseTimer.stop();
		gameManager.nextLevel();
		gameManager.startGame();
		pogsLabels.clear();
		startGame();
	}
	
	/**
	 * Game over case.
	 */
	private void gameOverCase() {
		int i = JOptionPane.showOptionDialog(null, "Points : "+gameManager.getPoints(),
				"Game Over Window",JOptionPane.DEFAULT_OPTION,JOptionPane.INFORMATION_MESSAGE,null,null,null);
		if(i == JOptionPane.OK_OPTION) {
			new MainMenuWindow();
			this.dispose();
		}
		else {
			new MainMenuWindow();
			this.dispose();
		}
	}
	
	/**
	 * The Class Listener.
	 */
	private class Listener extends MouseAdapter implements ActionListener {
		
		
		/**
		 * Action performed.
		 *
		 * @param e the event
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stud
			if(e.getSource()==winCaseTimer) {
				System.out.println("win case");
				winCase();
			}
			if(e.getSource()==losePointsCaseTimer) {
				losePointsCase();
			}
			if(e.getSource()==nextLevelCaseTimer) {
				nextLevelCase();
			}
			if(e.getSource()==playingCaseTimer) {
				playingCase();
			}
			if(e.getSource()==timeTimer) {
				time+=1000;
				String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(time),
			            TimeUnit.MILLISECONDS.toMinutes(time) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(time)),
			            TimeUnit.MILLISECONDS.toSeconds(time) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(time)));
				timerLabel.setText("Time : "+hms);
			}
			if(e.getSource()==exitButton) {
				System.out.println("Button click");
				gameOverCase();
			}
		}
		
		/**
		 * Mouse pressed.
		 *
		 * @param e the event
		 */
		public void mousePressed(MouseEvent e) {
			int indexPogClicked = pogsLabels.indexOf(e.getSource());
			if(indexPogClicked>=0) {
				if(gameManager.getPogsFlipped().size()<2) {
					flipPog(indexPogClicked);
				}
			}
		}
		
	}
}
