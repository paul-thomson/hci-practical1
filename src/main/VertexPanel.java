package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
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

	public VertexPanel(ShapeData shapes) throws IOException 
	{
		//TODO Loading a session with vertices already
		this();
	}

	@Override
	public void paintComponent(Graphics g) 
	{
		//	System.out.println("call2");
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
		if (God.moveMode == 0)
		{
			ArrayList<Shape> shapes = God.shapeData.getShapes();
			Vertex vertex = new Vertex(arg0.getX(), arg0.getY());
			if (shapes.size() == 0)
			{
				// TODO: Check necessary?
			}
			else if (arg0.getX() < God.imageDimension[0] - vertex.getRadius()  && arg0.getY() < God.imageDimension[1] - vertex.getRadius()) 
			{
				System.out.println("Point at : "+ arg0.getX() + " " + arg0.getY());
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
		if(God.moveMode == 1)
		{
			Vertex mouse = new Vertex(arg0.getX(), arg0.getY());

			// stores closest vertex and shape index (0 vertex, 1 shape, 2 head vertex)
			int [] candidate_vertex = new int[2];
			int distance = Integer.MAX_VALUE;

			// TODO Polygon moving 
			if (mouse.getX() < God.imageDimension[0] && mouse.getY()< God.imageDimension[1]) 
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
						if((EuclideanDistance(mouse, v) < distance) 
								&& (EuclideanDistance(mouse, v) <= v.getRadius()))
						{
							candidate_vertex[0]= vertexIndex;
							candidate_vertex[1]= shapeIndex;
							distance = (int) EuclideanDistance(mouse, v);
						}
					}
				}

				// Vertex is start (and end) vertex, force vertex to be head
				if(shapeData.getShape(candidate_vertex[1]).get(candidate_vertex[0]).
						equals(shapeData.getShape(candidate_vertex[1]).getHead()))
				{
					candidate_vertex[0] = 0;
				}

				if(distance == Integer.MAX_VALUE)
				{
					System.out.println("no candidate vertex found near mouse");
					/*
					// Check if mouse is within (contained) a polygon (first one to appear in list)
					ArrayList<Shape> shapes = God.shapeData.getShapes();
					for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++)
					{
						Shape shape = shapes.get(shapeIndex);
						if(shape.contains(Mouse))
						{
							// Move entire polygon 
							God.moveMode = 2;
							God.shapeIndex = shapeIndex;
							God.polygonTranslation [0] = Mouse.getX();
							God.polygonTranslation [1] = Mouse.getY();
							System.out.println("moving entire polygon, mouse @ " + Mouse.getX() + " " + Mouse.getY());
							break;

						}
					}
					 */
					return;
				}
				else
				{
					God.moveVertex = new MoveVertex(candidate_vertex);
				}
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent arg0) 
	{
		/*
		if(God.moveMode == 2)
		{
			System.out.println("no more moving");
			God.moveMode = 1;
		}
		 */
		God.moveVertex = null;
	}

	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// Drag and move individual vertices
		if(God.moveMode == 1 && God.moveVertex != null)
		{
			// If mouse is within image boundaries
			if (God.imageDimension[0] > e.getX() && God.imageDimension[1] > e.getY())
			{
				// Redraw vertex
				God.shapeData.shapes.get(God.moveVertex.getShape()).remove(God.moveVertex.getVertex());
				God.shapeData.shapes.get(God.moveVertex.getShape()).addAt(God.moveVertex.getVertex(), new Vertex(e.getX(), e.getY()));
				// If vertex is head, must move tail too
				if(God.moveVertex.getVertex() == 0)
				{
					God.shapeData.shapes.get(God.moveVertex.getShape()).remove(God.shapeData.shapes.get(God.moveVertex.getShape()).size() - 1);
					God.shapeData.shapes.get(God.moveVertex.getShape()).add(new Vertex(e.getX(), e.getY()));
				}
				God.layeredPanel.paint(this.getGraphics());
			}
		}
		// Drag and move entire polygons DOESNT WORK
		/*
		else if(God.moveMode == 2)
		{
			System.out.println("moving porygon");
			int xTranslate = e.getX() - God.polygonTranslation[0];
			int yTranslate = e.getY() - God.polygonTranslation[1];
			System.out.println("translation @ " + xTranslate + " " + yTranslate);
			Shape shape = God.shapeData.getShape(God.shapeIndex);
			for (int i = 0; i < shape.size(); i++)
			{
				int newX = shape.get(i).getX() + xTranslate;
				int newY = shape.get(i).getY() + yTranslate;
				System.out.println(newX + " " + newY);
				shape.get(i).set(new Vertex(newX, newY));
			}
			God.polygonTranslation[0] = e.getX();
			God.polygonTranslation[1] = e.getY();
			God.layeredPanel.paint(this.getGraphics());
		}
		 */
	}

	@Override
	public void mouseMoved(MouseEvent e) 
	{
		/*
		Vertex mouse = new Vertex(e.getX(), e.getY());
		ArrayList<Shape> shapes = God.shapeData.getShapes();
		for (int shapeIndex = 0; shapeIndex < shapes.size(); shapeIndex++)
		{
			Shape shape = shapes.get(shapeIndex);
			if(shape.contains(mouse))
			{
				//Polygon poly = new Polygon(xpoints, ypoints, npoints)
			}
		}
		*/
	}

}