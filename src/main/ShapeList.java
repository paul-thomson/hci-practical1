package main;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

import data.Shape;
import data.ShapeData;


public class ShapeList extends JPanel {

	static DefaultListModel listModel = new DefaultListModel();
	JList list;
	
	public ShapeList(ShapeData shapeData) {
		
	    ArrayList<Shape> shapes = shapeData.getShapes();
	    for (Shape shape : shapes) {
			listModel.addElement(shape);
	    }
	    this.setLayout(new GridLayout(1,1));
		list = new JList(listModel);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		list.setCellRenderer(new MyCellRenderer());
		list.setBorder(new EmptyBorder(5,5,5,5));
		add(list);
	}
	
	public static void removeShape(int index) {
		listModel.remove(index);
	}
	
	public static void addShape(Shape shape) {
		listModel.addElement(shape);
	}

}