/*
 * Interactive Programming
 * Author: Luis Fernando Lara S - 2024730-3743
 * Email: luis.fernando.lara@correounivalle.edu.co
 * Noah's Ark Miniproject
 */
package noahsArk;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

// TODO: Auto-generated Javadoc
/**
 * The Class Main Menu Window.
 * Sets up and manage the main menu window.
 */
public class MainMenuWindow extends JFrame {
	
	private Panel mainMenu;
	private JButton playButton;
	private ImageIcon playButtonImage, playButtonEnteredImage;
	private Listener listener;
	
	/**
	 * Instantiates a new main menu window.
	 */
	public MainMenuWindow() {
		mainMenu = new Panel();
		listener = new Listener();
		this.setTitle("Main Menu");
		this.setResizable(false);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMainMenu();
		this.pack();
		this.setLocationRelativeTo(null);
	}
	
	/**
	 * Inits the main menu.
	 */
	private void initMainMenu() {
		
		mainMenu.setLayout(new BoxLayout(mainMenu,BoxLayout.Y_AXIS));
		ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/PlayButton.png"));
		playButtonImage = new ImageIcon(playButtonIcon.getImage().getScaledInstance(216, 71,Image.SCALE_DEFAULT));
		playButton = new JButton(playButtonImage);
		playButtonIcon = new ImageIcon(getClass().getResource("/images/PlayButtonPressed.png"));
		playButtonEnteredImage = new ImageIcon(playButtonIcon.getImage().getScaledInstance(216, 71,Image.SCALE_DEFAULT));
		
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setOpaque(false);
		playButton.setFocusPainted(false);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		playButton.addMouseListener(listener);
		playButton.addActionListener(listener);
		
		mainMenu.setImagen("/images/NoahsBackground.png");	
		mainMenu.setPreferredSize(new Dimension(682,576));
		mainMenu.add(Box.createRigidArea(new Dimension(220,300)));
		mainMenu.add(playButton);
		
		add(mainMenu);
		
	}
	
	/**
	 * Start game.
	 */
	public void startGame () {
		new MainWindow();
		this.dispose();
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
			// TODO Auto-generated method stub
			startGame();
		}
		
		/**
		 * Mouse entered.
		 *
		 * @param e the event
		 */
		public void mouseEntered(MouseEvent e) {
			playButton.setIcon(playButtonEnteredImage);
		}
		
		/**
		 * Mouse exited.
		 *
		 * @param e the event
		 */
		public void mouseExited(MouseEvent e) {
			playButton.setIcon(playButtonImage);
		}
	}

}
