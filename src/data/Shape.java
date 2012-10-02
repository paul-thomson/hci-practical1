package data;

import java.awt.Color;
import java.util.ArrayList;

/**
 * Stores the colour, label and  a list of vertices which make up the shape.
 */
public class Shape 
{

	private ArrayList<Vertex> vertices;
	private Color color;
	private String label;

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

}
