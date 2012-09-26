package main;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame 
{
	ImagePanel imagePanel;
	JPanel toolbox;
	
	String imageName = "res/kirby.jpg";

	/**
	 * Create the panel.
	 */
	public MainWindow() 
	{
		getContentPane().setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = (float) 2/3;
		gbc.weighty = (float) 1;
		gbc.gridx = 0;
		gbc.gridy = 0;
		imagePanel = new ImagePanel();
		getContentPane().add(imagePanel, gbc);
		
		GridBagConstraints gbc2 = new GridBagConstraints();
		gbc2.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc2.fill = GridBagConstraints.BOTH;
		gbc2.weightx = (float) 1 - gbc.weightx;
		gbc2.weighty = (float) 1;
		gbc2.gridx = 1;
		gbc2.gridy = 0;
		
		toolbox = new JPanel();
		toolbox.setBackground(Color.red);
		getContentPane().add(toolbox, gbc2);
		
//		try {
//			imagePanel.setImage(imageName);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
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
