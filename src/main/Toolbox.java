package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

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

		/*
		newLabelButton = new JButton("New Label");
		newLabelButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// Reset move modes
				if (God.shapeData.getShapes().size() != 0) 
				{
					Shape lastShape = God.shapeData.getLastShape();
					// If the current shape has at least 3 vertices, possible to complete
					if(lastShape.size() > 2)
					{
						// Ask for a label before completing the shape (they can cancel)
						if(God.requestLabel())
						{
							lastShape = God.shapeData.endShape();
							if (lastShape != null) {
								BufferedImage screenshot = God.imagePanel.getScreenshot();
								Rectangle r = lastShape.getBoundingBox();
								lastShape.setThumbnail(screenshot.getSubimage(r.x,r.y,r.width,r.height));

								God.vertexPanel.drawLine(lastShape.get(lastShape.size() - 2), lastShape.get(0), lastShape.getColor());
								God.layeredPanel.paint(God.layeredPanel.getGraphics());
							}
						}
						else
						{
							// User cancelled label, exit
							return;
						}
					}
					else
					{			
						if(God.moveMode == 1)
						{
							God.moveMode = 0; 
							God.moveVertex = null;
						}
						else
						{
							// User tried to end an illegal shape, show warning
							JOptionPane.showMessageDialog(null, "Polygon requires at least 3 vertices!");	
							God.moveVertex = null;
							God.moveMode = 0;	
							return;
						}
					}

				}
				God.moveVertex = null;
				God.moveMode = 0;
				God.shapeData.addShape(new Shape());
			}
		});
		add(newLabelButton);


		moveButton = new JButton("Move Vertice TODO?");
		moveButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				God.moveMode = 1;
			}
		});
		add(moveButton);
		 */


		ColorPalette colourPalette = new ColorPalette(changeColor);
		add(colourPalette);
		add(scrollPane);

		JPanel buttons = new JPanel();
		JButton editLabel = new JButton("Edit");
		editLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				String s = (String) JOptionPane.showInputDialog("Please enter new label", 
						God.shapeData.getShape(God.shapeData.listSelection).getLabel());
				if (s != null) {
					God.shapeData.getShape(God.shapeData.listSelection).setLabel(s);
					God.shapeList.repaint();
				}
			}
		});

		JButton delLabel = new JButton("Del");
		delLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				int index = God.shapeData.listSelection;
				if (index >= 0)
				{
					God.shapeData.removeShape(index);
					God.shapeList.removeShape(index);								
					God.layeredPanel.paint(God.layeredPanel.getGraphics());
					God.scrollPane.repaint();
				}
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
