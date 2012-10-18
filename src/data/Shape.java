package data;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Rectangle;
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
	private int THUMB_SIZE = 50;
	private Image thumbnail = new BufferedImage(THUMB_SIZE,THUMB_SIZE,BufferedImage.TYPE_INT_ARGB);

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

	public Image getThumbnail()
	{
		return thumbnail;
	}

	public void setThumbnail(BufferedImage thumbnail) 
	{

		float max = Math.max(thumbnail.getHeight(), thumbnail.getWidth());
		Image scaledImage = thumbnail.getScaledInstance((int)((thumbnail.getWidth()/max) * THUMB_SIZE), 
				(int)((thumbnail.getHeight()/max) * THUMB_SIZE), 
				Image.SCALE_FAST);

		BufferedImage newImage = new BufferedImage(THUMB_SIZE,THUMB_SIZE,BufferedImage.TYPE_INT_ARGB);

		Graphics g = newImage.getGraphics();

		g.setColor(new Color(0, 0, 0, 0));
		int startX = (int) (THUMB_SIZE - ((thumbnail.getWidth()/max) * THUMB_SIZE))/2;
		int startY = (int) (THUMB_SIZE - ((thumbnail.getHeight()/max) * THUMB_SIZE))/2;
		g.drawImage(scaledImage, startX, startY, null);
		g.dispose();

		this.thumbnail = newImage;
	}

	public Vertex get(int index) 
	{
		return vertices.get(index);
	}

	public void add(Vertex vertex) 
	{
		vertices.add(vertex);

	}

	public int size() 
	{
		return vertices.size();
	}

	public void remove(int index)
	{
		vertices.remove(index);
	}

	public void addAt(int index, Vertex vertex)
	{
		vertices.add(index, vertex);
	}
	
	public boolean contains(Vertex vertex) {
		Polygon p = new Polygon();
		for (Vertex v : vertices) { 
			p.addPoint(v.getX(),v.getY());
		}
		return p.contains(vertex.getX(), vertex.getY());
	}
	
	public Vertex getHead()
	{
		return vertices.get(0);
	}
	
	public Vertex getTail()
	{
		return vertices.get(vertices.size() - 1);
	}

}
