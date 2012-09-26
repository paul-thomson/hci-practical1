package main;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class MainWindow extends JFrame
{
	
	private class MyDispatcher implements KeyEventDispatcher {
        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
        	if (e.getID() == KeyEvent.KEY_TYPED) {
        		
        		if (e.getKeyChar() == KeyEvent.VK_ENTER) {
        			imagePanel.endShape();
        		}
            }
            return false;
        }
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JPanel mainPanel;
	ImagePanel imagePanel;
	Toolbox toolbox;
	
	Dimension minimumSize = new Dimension(1000,600);
	
	String imageName = "res/kirby.jpg";

	/**
	 * Create the panel.
	 */
	public MainWindow() 
	{
		
		this.addWindowListener(new WindowAdapter() {
		  	public void windowClosing(WindowEvent event) {
		    	System.exit(0);
		  	}
		  	public void componentResized(ComponentEvent e) {
		        
		    }
		});

	  	KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(new MyDispatcher());
		
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
		
		toolbox = new Toolbox();
		
		try {
			imagePanel.setImage(imageName);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		mainPanel.add(imagePanel);
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
