package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class ImagePanel extends JPanel 
{
	/** The image to display in this panel */
	BufferedImage image;
	/** The maximum size of the panel */
	Dimension size = new Dimension(600,400);

	
	public ImagePanel()
	{
		this.setVisible(true);
//		this.setPreferredSize(size);
	}
	
	public ImagePanel(String imageName) throws IOException
	{
		this();
		setImage(imageName);
	}
	
	@Override
	public void paint(Graphics g) 
	{
		super.paint(g);
		
		displayImage();
		
	}

	private void displayImage() 
	{
		Graphics g = this.getGraphics();
		
		if (image != null) {
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

}
