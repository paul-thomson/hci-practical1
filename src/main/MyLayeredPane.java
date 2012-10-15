package main;

import java.awt.BorderLayout;
import java.awt.Graphics;

import javax.swing.JPanel;

public class MyLayeredPane extends JPanel {
	public ImagePanel imagePanel;
	public VertexPanel vertexPanel;

	public MyLayeredPane(BorderLayout borderLayout) {
		super(borderLayout);
	}
	
	public void paint(Graphics g) {
		imagePanel.paintComponent(g);
		vertexPanel.paintComponent(g);
	}
	
}
