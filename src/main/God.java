package main;

import javax.swing.JPanel;

import data.MoveVertex;
import data.ShapeData;
import fileio.FileIOPanel;

public class God {
	
	public static MainWindow mainWindow;
	static ImagePanel imagePanel; 
	static Toolbox toolBox;
	static FileIOPanel fileIOPanel;
	static ShapeData shapeData;
	static boolean moveMode = false;
	static MoveVertex moveVertex;
	static boolean release;
	static VertexPanel vertexPanel;
	static JPanel layeredPanel;
	
	public void reset()
	{
		mainWindow = null;
		imagePanel = null;
		toolBox = null;
		fileIOPanel = null;
		shapeData = null;
		moveVertex = null;
		vertexPanel = null;
	}
	
	public void resetMoveVertex()
	{
		moveVertex = null;
	}


}