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
		this.paintComponent(this.getGraphics());
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
		// Set image to file path
		image = ImageIO.read(new File(path));
		int width = (int) size.getWidth();
		int height = (int) size.getHeight();

		// Get image dimenions and store statically
		God.image_dimension[0] = image.getWidth();
		God.image_dimension[1] = image.getHeight();

		// Scaling image
		if (God.image_dimension[0] > width || God.image_dimension[1] > height) 
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
}
