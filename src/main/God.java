package main;

import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import data.MoveVertex;
import data.Shape;
import data.ShapeData;
import fileio.FileIOPanel;

public class God {

	// Panel stuff
	public static MainWindow mainWindow;
	public static ImagePanel imagePanel; 
	public static Toolbox toolBox;
	public static FileIOPanel fileIOPanel;
	public static VertexPanel vertexPanel;
	public static JPanel layeredPanel;
	public static ShapeList shapeList;

	// Data structure stuff
	public static ShapeData shapeData;
	/**
	 *  moveMode 	0 - STANDBY
	 *  			1 - Move individual vertex
	 *  			2 - Move entire polygons - Unnecessary?
	 */			
	public static int moveMode;
	public static MoveVertex moveVertex;
	public static boolean dirtyFlag;
	
	//public static int shapeIndex;
	//public static int[] polygonTranslation = new int[2];
	public static int[] imageDimension = new int[2];

	public static void resetMoveVertex()
	{
		moveVertex = null;
	}

	public static boolean requestLabel() {
		String s = (String) JOptionPane.showInputDialog("Please enter label");
		// If they press cancel
		if(s == null)
		{
			return false;
		}
		// If they enter an invalid label (no characters, only white space)
		if (s.trim().length() < 1)
		{
			System.out.println("haha");
			return false;
		}
		ArrayList<Shape> shapes = God.shapeData.getShapes();
		if (s != null) {
			shapes.get(shapes.size()-1).setLabel(s);
		}
		return true;
	}
	
	static KeyListener keyDispatcher()
	{
		return new KeyListener()
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				System.out.println("hahshdah");
				if (e.getID() == KeyEvent.KEY_TYPED) 
				{
					// Complete polygon
					if (e.getKeyChar() == KeyEvent.VK_ENTER ) 
					{	
						Shape lastShape = God.shapeData.getShape(God.shapeData.getIndex());
						if(lastShape.size() > 2)
						{
							if(God.requestLabel())
							{
								lastShape = God.shapeData.endShape(God.shapeData.getIndex());

								if (lastShape != null) 
								{
									BufferedImage screenshot = imagePanel.getScreenshot();
									Rectangle r = lastShape.getBoundingBox();
									lastShape.setThumbnail(screenshot.getSubimage(r.x,r.y,r.width,r.height));

									God.vertexPanel.drawLine(lastShape.get(lastShape.size() - 2), lastShape.get(0), lastShape.getColor());
									God.shapeData.addShape(new Shape());	
								}
							}
							else
							{
								return;
							}
						}
						else
						{
							JOptionPane.showMessageDialog(null, "Polygon requires at least 3 vertices!");
						}
					}

					// Delete vertex
					if (e.getKeyChar() == KeyEvent.VK_DELETE || e.getKeyChar() == KeyEvent.VK_BACK_SPACE ) 
					{
						if (God.moveMode == 1)
						{
							if(God.moveVertex != null)
							{
								// delete vertex
								God.shapeData.shapes.get(God.moveVertex.getShape()).remove(God.moveVertex.getVertex());
								// If vertex is head, must remove tail
								if(God.moveVertex.getVertex() == 0)
								{
									God.shapeData.shapes.get(God.moveVertex.getShape()).
									remove(God.shapeData.shapes.get(God.moveVertex.getShape()).size() - 1);
									// Push new head to tail
									God.shapeData.shapes.get(God.moveVertex.getShape()).
									add(God.shapeData.shapes.get(God.moveVertex.getShape()).getHead());
								}
								God.moveVertex = null;
								God.vertexPanel.repaint();
							}
						}
					}
				}
				return ;

			}

			@Override
			public void keyReleased(KeyEvent e) {
				System.out.println("xd");
				// TODO Auto-generated method stub

			}

			@Override
			public void keyTyped(KeyEvent e) {
				System.out.println("dx");
				// TODO Auto-generated method stub

			}

		};
	}
}