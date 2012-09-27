package data;

/**
 * Stores the 2D coordinates of a point on a shape
 */
public class Vertex 
{
	
	private int x;
	private int y;
	private int radius = 5;
	
	public Vertex(int x, int y) 
	{
		this.x = x;
		this.y = y;
	}

	public void setX(int x) 
	{
		this.x = x;
	}

	public void setY(int y) 
	{
		this.y = y;
	}

	public int getX() 
	{
		return x;
	}

	public int getY() 
	{
		return y;
	}

	public int getRadius() 
	{
		return radius;
	}
	
}
