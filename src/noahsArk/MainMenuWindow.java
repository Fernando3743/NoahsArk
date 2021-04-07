package noahsArk;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

public class MainMenuWindow extends JFrame {
	private Panel mainMenu;
	private JButton playButton;
	
	
	public MainMenuWindow() {
		mainMenu = new Panel();
		this.setTitle("Noah's Ark");
		this.setResizable(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		initMainMenu();
		this.pack();
	}
	
	private void initMainMenu() {
		
		mainMenu.setLayout(new BoxLayout(mainMenu,BoxLayout.Y_AXIS));
		ImageIcon playButtonIcon = new ImageIcon(getClass().getResource("/images/PlayButton.png"));
		ImageIcon scaledButton = new ImageIcon(playButtonIcon.getImage().getScaledInstance(216, 71,Image.SCALE_DEFAULT));
		playButton = new JButton(scaledButton);
		
		playButton.setBorderPainted(false);
		playButton.setContentAreaFilled(false);
		playButton.setOpaque(false);
		playButton.setFocusPainted(false);
		playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		mainMenu.setImagen("/images/NoahsBackground.png");	
		mainMenu.setPreferredSize(new Dimension(682,576));
		mainMenu.add(Box.createRigidArea(new Dimension(220,300)));
		mainMenu.add(playButton);
		
		add(mainMenu);
		
	}

}
