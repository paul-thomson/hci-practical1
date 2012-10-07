package data;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Stores the colour, label and  a list of vertices which make up the shape.
 */
public class Shape 
{

	private ArrayList<Vertex> vertices;
	private Color color;
	private String label = "Default Label";
	private BufferedImage thumbnail = null;

	public Shape() 
	{
		vertices = new ArrayList<Vertex>();
		color = Color.WHITE;
		label = "Default shape label";
	}

	public Shape(ArrayList<Vertex> vertices, Color color, String label) 
	{
		this.vertices = vertices;
		this.color = color;
		this.label = label;
	}

	public ArrayList<Vertex> getVertices() 
	{
		return vertices;
	}

	public void setVertices(ArrayList<Vertex> vertices) 
	{
		this.vertices = vertices;
	}
	
	public Rectangle getBoundingBox() 
	{
		int xMax = 0;
		int xMin = Integer.MAX_VALUE;
		int yMax = 0;
		int yMin = Integer.MAX_VALUE;
		for (Vertex vertex : vertices)
		{
			xMax = Math.max(xMax, vertex.getX());
			xMin = Math.min(xMin, vertex.getX());
			yMax = Math.max(yMax, vertex.getY());
			yMin = Math.min(yMin, vertex.getY());
			
		}
		System.out.println("xMin: " + xMin + "yMin: " + yMin + "xMax: " + xMax + "yMax: " + yMax);
		return  new Rectangle(xMin, yMin, xMax - xMin, yMax - yMin);
	}

	public Color getColor() 
	{
		return color;
	}

	public void setColor(Color color) 
	{
		this.color = color;
	}

	public String getLabel() 
	{
		return label;
	}

	public void setLabel(String label) 
	{
		this.label = label;
	}
	
	public BufferedImage getThumbnail()
	{
		return thumbnail;
	}
	
	public void setThumbnail(BufferedImage thumbnail) 
	{
		this.thumbnail = thumbnail;
	}

	public Vertex get(int index) 
	{
		return vertices.get(index);
	}

	public void add(Vertex vertex) 
	{
		vertices.add(vertex);
		System.out.println("x: " + vertex.getX() + " y: " + vertex.getY());

	}

	public int size() 
	{
		return vertices.size();
	}

}
