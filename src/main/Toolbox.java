package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import data.Shape;
import fileio.FileIOPanel;

public class Toolbox extends JPanel 
{

	private static final long serialVersionUID = 1L;
	ShapeList shapeList = null;
	
	/**
	 * Create the panel.
	 */
	public Toolbox(ActionListener changeColor) 
	{		
		this.setBorder(new EmptyBorder(5,5,5,5));
		this.setPreferredSize(new Dimension(200,600));
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		// Set up thumbnail shape list
		shapeList = new ShapeList(God.shapeData);
		JScrollPane scrollPane = new JScrollPane(shapeList);
		scrollPane.setPreferredSize(new Dimension(200, 300));
		God.shapeList = shapeList;
		God.scrollPane = scrollPane;
		
		ColorPalette colourPalette = new ColorPalette(changeColor);
		add(colourPalette);
		add(scrollPane);
		
		JPanel buttons = new JPanel();
		JButton editLabel = new JButton("edit");
		editLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) JOptionPane.showInputDialog("Please enter new label");
				if (s != null) {
					God.shapeData.getShape(God.shapeData.listSelection).setLabel(s);
					God.shapeList.repaint();
				}
			}
		});
		
		JButton delLabel = new JButton("del");
		delLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = God.shapeData.listSelection;
				God.shapeData.removeShape(index);
				God.shapeList.removeShape(index);								
				God.layeredPanel.paint(God.layeredPanel.getGraphics());
				God.scrollPane.repaint();
			}
		});
		buttons.add(editLabel);
		buttons.add(delLabel);
		add(buttons);

		FileIOPanel fileIO = new FileIOPanel();
		add(fileIO);
		God.fileIOPanel = fileIO;
	}
}
