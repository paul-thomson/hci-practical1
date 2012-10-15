package data;

public class MoveVertex 
{
	// stores closest vertex
	private int vertexIndex;
	private int shapeIndex;
	
	public MoveVertex(int vertexIndex, int shapeIndex)
	{
		this.vertexIndex = vertexIndex;
		this.shapeIndex = shapeIndex;
	}
	
	public MoveVertex(int[] index)
	{
		this.vertexIndex = index[0];
		this.shapeIndex = index[1];
	}
	
	public int getVertex()
	{
		return vertexIndex;
	}
	
	public int getShape()
	{
		return shapeIndex;
	}
}