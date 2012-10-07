package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import data.Shape;
import data.ShapeData;
import fileio.FileIOPanel;

public class Toolbox extends JPanel 
{

	private static final long serialVersionUID = 1L;
	ShapeData shapeData;
	JButton btnNewObject = new JButton("New Object");
	/**
	 * Create the panel.
	 */
	public Toolbox(final ShapeData shapeData, ActionListener changeColor,
			 ActionListener newFile, ActionListener save, ActionListener load) 
	{		
		this.shapeData = shapeData;
		this.setPreferredSize(new Dimension(200,600));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		btnNewObject.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				shapeData.addShape(new Shape());
			}
		});
		add(btnNewObject);

		JButton btnSelectObject = new JButton("Move Object");
		add(btnSelectObject);

		ColorPalette colourPalette = new ColorPalette(shapeData, changeColor);
		add(colourPalette);
		
		ShapeList shapeList = new ShapeList(shapeData);
		JScrollPane scrollPane = new JScrollPane(shapeList);
		add(scrollPane);
		
		FileIOPanel fileIO = new FileIOPanel(newFile, save, load);
		add(fileIO);
	}
	
	public void changeShapeData(final ShapeData shapeData)
	{
		btnNewObject.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				shapeData.addShape(new Shape());
			}
		});
	}

}
