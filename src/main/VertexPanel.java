package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;

import data.MoveVertex;
import data.Shape;
import data.ShapeData;
import data.Vertex;

public class VertexPanel extends JPanel implements MouseListener, MouseMotionListener
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** The image to display in this panel */
	BufferedImage image;
	/** The maximum size of the panel */
	Dimension size = new Dimension(800,600);

	public VertexPanel()
	{
		addMouseListener(this);
		addMouseMotionListener(this);
		this.setVisible(true);
		this.setOpaque(false);
		//		this.setBackground(Color.BLACK);
		this.setPreferredSize(size);
		image = new BufferedImage(
				(int)ImagePanel.size.getWidth(),
				(int)ImagePanel.size.getHeight(),
				BufferedImage.TYPE_INT_ARGB
				);
	}

	public VertexPanel(ShapeData shapes) throws IOException //TODO redraw blah ask gerald
	{
		this();
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);
		ArrayList<Shape> shapes = God.shapeData.getShapes();
		for (Shape shape : shapes) 
		{ 
			drawShape(God.imagePanel.getGraphics(), shape);
		}
	}

	/**
	 * Draws all the vertices in the shape and draws lines between 
	 * each adjacent vertex
	 * @param shape
	 */
	private void drawShape(Graphics g, Shape shape) 
	{
		g.setColor(shape.getColor());
		ArrayList<Vertex> vertices = shape.getVertices();
		int size = vertices.size();

		for (int i = 0; i < size; i++) 
		{
			Vertex current = vertices.get(i);
			if (i != 0) 
			{
				Vertex previous = vertices.get(i - 1);
				drawLine(current, previous, shape.getColor());
			}
			drawVertex(current, shape.getColor());
		}
	}

	/**
	 * Draws a line from the first vertex to the next
	 * @param v1
	 * @param v2
	 */
	public void drawLine(Vertex v1, Vertex v2, Color color) 
	{
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(color);
		g.drawLine(v2.getX(), v2.getY(), v1.getX(), v1.getY());
	}

	/**
	 * Draws a circle around the given vertex of the given color
	 * @param v
	 * @param color
	 */
	private void drawVertex(Vertex v, Color color) 
	{
		Graphics2D g = (Graphics2D) this.getGraphics();
		g.setColor(color);
		g.fillOval(v.getX() - v.getRadius(), v.getY() - v.getRadius(), v.getRadius()*2, v.getRadius()*2);
	}


	public double EuclideanDistance(Vertex a, Vertex b)
	{
		double x1 = a.getX();
		double y1 = a.getY();
		double x2 = b.getX();
		double y2 = b.getY();
		return Math.sqrt(( Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2))); 
	}

	@Override
	public void mouseClicked(MouseEvent arg0) 
	{
		System.out.println(God.moveMode);
		if (!God.moveMode)
		{
			ArrayList<Shape> shapes = God.shapeData.getShapes();
			Vertex vertex = new Vertex(arg0.getX(), arg0.getY());
			if (shapes.size() == 0)
			{
				// TODO: Check necessary?
			}
			else if (arg0.getX() < image.getWidth() - vertex.getRadius()  && arg0.getY() < image.getWidth() - vertex.getRadius()) 
			{
				Shape lastShape = shapes.get(shapes.size() - 1);
				drawVertex(vertex, lastShape.getColor());
				if (lastShape.size() != 0) 
				{
					drawLine(vertex, lastShape.get(lastShape.size()-1), lastShape.getColor());
				}
				lastShape.add(vertex);
			}
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent arg0) 
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent arg0) 
	{	
		if(God.moveMode)
		{
			Vertex Mouse = new Vertex(arg0.getX(), arg0.getY());

			// stores closest vertex and shape index (0 vertex, 1 shape)
			int [] candidate_vertex = new int[2];
			int distance = Integer.MAX_VALUE;

			// TODO Polygon moving 
			if (Mouse.getX() < image.getWidth() && Mouse.getY()< image.getWidth()) 
			{
				/*** Finding vertex closest to mouse click ***/
				ShapeData shapeData = God.shapeData;
				// For each shape
				for (int shapeIndex = 0; shapeIndex < shapeData.shapes.size(); shapeIndex++)
				{
					Shape shape = shapeData.getShape(shapeIndex);
					for (int vertexIndex = 0; vertexIndex < shape.size(); vertexIndex++)
					{
						Vertex v = shape.get(vertexIndex);
						if((EuclideanDistance(Mouse, v) < distance) 
								&& (EuclideanDistance(Mouse, v) <= v.getRadius()))
						{

							candidate_vertex[0]= vertexIndex;
							candidate_vertex[1]= shapeIndex;
							distance = (int) EuclideanDistance(Mouse, v);
						}
					}
				}
				if(distance == Integer.MAX_VALUE)
				{
					System.out.println("no candidate vertex found near mouse");
					return;
				}
				else
				{
					God.moveVertex = new MoveVertex(candidate_vertex);
				}

				God.release = false;
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		if(God.moveMode)
		{
			God.release = true;
			// Place vertex here
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		if(God.moveMode)
		{
			// Redraw vertex
			if (God.image_dimension[0] > e.getX() && God.image_dimension[1] > e.getY())
			{
				God.shapeData.shapes.get(God.moveVertex.getShape()).remove(God.moveVertex.getVertex());
				God.shapeData.shapes.get(God.moveVertex.getShape()).addAt(God.moveVertex.getVertex(), new Vertex(e.getX(), e.getY()));
				God.layeredPanel.paint(this.getGraphics());
			}
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		// TODO Auto-generated method stub

	}

}