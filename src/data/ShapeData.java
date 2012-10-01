package data;

import java.awt.Color;
import java.util.ArrayList;

public class ShapeData {
	
	public static int index = -1;
	public static ArrayList<Shape> shapes = new ArrayList<Shape>();
	public static Color shapeColor = new Color(0,0,0);
	
	/**
	 * Add a shape to the list of shapes to be drawn
	 * @param shape
	 */
	public static void addShape(Shape shape) 
	{
		shape.setColor(shapeColor);
		shapes.add(shape);
		selectShape(shapes.size() - 1);
	}
	
	public static void selectShape(int newIndex) {
		index = newIndex;
	}
	
	public static void setColor(Color newColor) {
		shapeColor = newColor;
	}

	/**
	 * Ends the current shape (which is last in the list of shapes) by 
	 * adding the first vertex to the end
	 */
	public static Shape endShape(int index) 
	{
		if (shapes.size() > 0) {
			if (shapes.get(index).size() > 0) {
				Shape lastShape = shapes.get(index);
				lastShape.add(lastShape.get(0));
				return lastShape;
			}
		}
		return null;
	}
	
	public static Shape getShape(int index) {
		return shapes.get(index);
	}
}
