package main;

import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.AutoFileChooser;
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
	public static ShapeList shapeList;
	public static JScrollPane scrollPane;
	public static Color color = Color.red;
	public static AutoFileChooser autoFileChooser;
	public static JLabel fileName = new JLabel("No file chosen yet");

	// Data structure stuff
	public static ShapeData shapeData;
	public static MoveVertex moveVertex;
	public static int lastVertex;
	public static boolean dirtyFlag;
	
	public static int[] imageDimension = new int[2];

	public static boolean requestLabel() {
		String s = (String) JOptionPane.showInputDialog("Please enter label");
		// If they press cancel
		if(s == null)
		{
			return false;
		}
		// If they enter an invalid label (no characters, only white space)
		if (s.trim().length() < 1)
		{
			return false;
		}
		ArrayList<Shape> shapes = God.shapeData.getShapes();
		if (s != null) {
			shapes.get(shapes.size()-1).setLabel(s);
		}
		return true;
	}
}
