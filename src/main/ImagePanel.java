package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel implements MouseListener, MouseMotionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The image to display in this panel */
	BufferedImage image;
	/** The maximum size of the panel */
	static Dimension size = new Dimension(800,600);

	public ImagePanel()
	{
		this.setVisible(true);
		this.setPreferredSize(size);

	}

	public ImagePanel(String imageName) throws IOException
	{
		this();
		setImage(imageName);
	}
	
	public void newImage(String imageName) throws IOException
	{
		setImage(imageName);
		this.repaint();
	}
	
	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		displayImage(g, false);
	}

	/**
	 * Display the loaded image
	 */
	private void displayImage(Graphics g, boolean screenshot)
	{
		
//		if (!screenshot) {
//			
//			g = this.getGraphics();
//		}

		if (image != null) 
		{
			g.drawImage(
					image, 0, 0, null);
		}
	}

	/**
	 * Attempts to load the image using size constraints. Will scale if 
	 * image is too large.
	 * @param path
	 * @throws IOException
	 */
	public void setImage(String path) throws IOException
	{

		image = ImageIO.read(new File(path));
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();
		if (image.getWidth() > width || image.getHeight() > height) 
		{
			int newWidth = image.getWidth() > width ? 
					width : 
						(image.getWidth() * height)/image.getHeight();
			int newHeight = image.getHeight() > height ? 
					height : 
						(image.getHeight() * width)/image.getWidth();

			System.out.println("SCALING TO " + newWidth + "x" + newHeight );

			Image scaledImage = image.getScaledInstance(newWidth, newHeight, Image.SCALE_FAST);
			image = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
			image.getGraphics().drawImage(scaledImage, 0, 0, this);
		}
	}
	public BufferedImage getScreenshot() {
		BufferedImage image = new BufferedImage(
				this.image.getWidth(),
				this.image.getHeight(),
				BufferedImage.TYPE_INT_RGB
				);
		// call the Component's paint method, using
		// the Graphics object of the image.
		this.displayImage(image.getGraphics(), true);

		return image;
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		God.vertexPanel.mouseDragged(arg0);
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		God.vertexPanel.mouseMoved(arg0);		
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		God.vertexPanel.mouseClicked(arg0);		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		God.vertexPanel.mouseEntered(arg0);		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		God.vertexPanel.mouseExited(arg0);		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		God.vertexPanel.mousePressed(arg0);		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		God.vertexPanel.mouseReleased(arg0);		
	}
	
	

}
