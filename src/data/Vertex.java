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
	
	public void set(Vertex v) 
	{
		this.x = v.getX();
		this.x = v.getY();
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

	public boolean equals(Object obj)
	{
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (obj.getClass() != getClass())
			return false;
		Vertex vertex = (Vertex) obj;
		if((vertex.x == this.x) && (vertex.y == this.y))
		{
			return true;
		}
		return false;
	}

}
