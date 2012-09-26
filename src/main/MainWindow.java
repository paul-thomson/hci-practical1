package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame 
{
	JPanel mainPanel;
	ImagePanel imagePanel;
	JPanel toolbox;
	
	Dimension minimumSize = new Dimension(1000,600);
	
	String imageName = "res/kirby.jpg";

	/**
	 * Create the panel.
	 */
	public MainWindow() 
	{
		this.setMinimumSize(minimumSize);
		
		mainPanel = new JPanel();
		mainPanel.setBackground(Color.yellow);
		this.setLayout(new BoxLayout(mainPanel, BoxLayout.X_AXIS));
		this.setContentPane(mainPanel);
				
		try {
			imagePanel = new ImagePanel(imageName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		imagePanel.setOpaque(true); //content panes must be opaque
		mainPanel.add(imagePanel);
		
		toolbox = new JPanel();
		toolbox.setBackground(Color.red);
		
//		try {
//			imagePanel.setImage(imageName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
		mainPanel.add(toolbox);
		
		this.pack();
        this.setVisible(true);
	
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		imagePanel.paint(g); //update image panel
	}
	
	/**
	 * Runs the program
	 * @param argv path to an image
	 */
	public static void main(String argv[]) {
		try {
			//create a window and display the image
			MainWindow window = new MainWindow();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
