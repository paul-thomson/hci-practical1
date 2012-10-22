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
	JButton newLabelButton = new JButton("New Label");
	JButton moveButton = new JButton("Move");
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

		/***
		 * New Label
		 */ 
		newLabelButton = new JButton("New Label");
		newLabelButton.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				// Reset move modes
				God.moveMode = 0;
				God.moveVertex = null;
				if (God.shapeData.getShapes().size() != 0) 
				{
					Shape lastShape = God.shapeData.getShape(God.shapeData.getIndex());
					// If the current shape has at least 3 vertices, possible to complete
					if(lastShape.size() > 2)
					{
						// Ask for a label before completing the shape (they can cancel)
						if(God.requestLabel())
						{
							lastShape = God.shapeData.endShape(God.shapeData.getIndex());
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
						// User tried to end an illegal shape, show warning
						JOptionPane.showMessageDialog(null, "Polygon requires at least 3 vertices!");		
						return;
					}

				}
				God.shapeData.addShape(new Shape());
			}
		});
		add(newLabelButton);

		/***
		 * Move
		 */
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

		ColorPalette colourPalette = new ColorPalette(changeColor);
		add(colourPalette);
		add(scrollPane);

		FileIOPanel fileIO = new FileIOPanel();
		add(fileIO);
		God.fileIOPanel = fileIO;
	}

}
