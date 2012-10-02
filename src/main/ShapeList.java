package main;
import java.util.ArrayList;

import javax.swing.JList;
import javax.swing.JPanel;

import data.Shape;
import data.ShapeData;


public class ShapeList extends JPanel{

	/**
	 * Create the panel.
	 */
	public ShapeList(ShapeData shapeData) {
		
//		ArrayList<ListItem> listItems = new ArrayList<ListItem>();
//		ArrayList<Shape> shapes = shapeData.getShapes();
//		for (Shape shape : shapes) {
//			ListItem listItem = new ListItem();
//			listItems.add(listItem);
//		}
		
		ArrayList<Shape> listItems = new ArrayList<Shape>();
		
		Shape listItem = new Shape();
		listItems.add(listItem);
		listItems.add(listItem);
		listItems.add(listItem);
		listItems.add(listItem);
		JList list = new JList(listItems.toArray());
		
		list.setCellRenderer(new MyCellRenderer());
		
		add(list);
		
	}

}
