package main;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;

import data.Shape;
import data.ShapeData;


public class ShapeList extends JPanel {

	DefaultListModel listModel = new DefaultListModel();
	
	/**
	 * Create the panel.
	 */
	public ShapeList(ShapeData shapeData) {

	    ArrayList<Shape> shapes = shapeData.getShapes();
	    
	    for (Shape shape : shapes) {
			listModel.addElement(shape);
	    }

		JList list = new JList(listModel);
		
		list.setCellRenderer(new MyCellRenderer());

		add(list);
	}
	
	public void removeShape(int index) {
		listModel.remove(index);
	}
	
	public void addShape(Shape shape) {
		listModel.addElement(shape);
	}

}
