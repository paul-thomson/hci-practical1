package main;

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

	// Data structure stuff
	public static ShapeData shapeData;
	public static boolean moveMode = false;
	public static MoveVertex moveVertex;
	public static int[] image_dimension = new int[2];

	public static void resetMoveVertex()
	{
		moveVertex = null;
	}

	public static void requestLabel() {
		String s = (String) JOptionPane.showInputDialog("Please enter label");
		ArrayList<Shape> shapes = God.shapeData.getShapes();
		if (s != null) {
			shapes.get(shapes.size()-1).setLabel(s);
		}
	}
}