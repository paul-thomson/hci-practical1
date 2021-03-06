package main;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import data.AutoFileChooser;
import fileio.FileIOPanel;

public class Toolbox extends JPanel 
{

	private static final long serialVersionUID = 1L;
	public static Dimension buttonSize = new Dimension(40,40);
	ShapeList shapeList = null;

	/**
	 * Create the panel.
	 */
	public Toolbox(ActionListener changeColor) 
	{		
		this.setBorder(new EmptyBorder(5,5,5,5));
		this.setPreferredSize(new Dimension(200,600));
		setLayout(new BorderLayout());

		// Set up thumbnail shape list
		shapeList = new ShapeList(God.shapeData);
		JScrollPane scrollPane = new JScrollPane(shapeList);
		scrollPane.setPreferredSize(new Dimension(190, 300));
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

		FileIOPanel fileIO = new FileIOPanel();
		God.fileIOPanel = fileIO;
		add(fileIO,BorderLayout.NORTH);
		
		JPanel afc = new JPanel(new BorderLayout());
		JButton previous = new JButton();
		
		ImageIcon pre = new ImageIcon("res/left.png");
		Image preImg = pre.getImage();  
		Image newPreImg = preImg.getScaledInstance(ColorPalette.preferredSize.width-5, ColorPalette.preferredSize.height-5,  java.awt.Image.SCALE_SMOOTH);  

		previous.setIcon(new ImageIcon(newPreImg));
		
		previous.setPreferredSize(ColorPalette.preferredSize);
		previous.setToolTipText("Load the previous image in the directory");
		previous.setAlignmentX(Component.LEFT_ALIGNMENT);
		previous.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				God.autoFileChooser.loadPreviousFile();
			}
		});
		
		JButton next = new JButton();
		
		ImageIcon nex = new ImageIcon("res/right.png");
		Image nexImg = nex.getImage();  
		Image newNexImg = nexImg.getScaledInstance(ColorPalette.preferredSize.width-5, ColorPalette.preferredSize.height-5,  java.awt.Image.SCALE_SMOOTH);  
		next.setIcon(new ImageIcon(newNexImg));
		
		next.setPreferredSize(ColorPalette.preferredSize);
		next.setToolTipText("Load the next image in the directory");
		next.setAlignmentX(Component.RIGHT_ALIGNMENT);
		next.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				God.autoFileChooser.loadNextFile();
			}
		});
		
		afc.add(previous,BorderLayout.WEST);
		God.autoFileChooser = new AutoFileChooser((new File(MainWindow.startingImage).getAbsolutePath()));
		God.fileName.setPreferredSize(new Dimension(100,30));
		afc.add(God.fileName,BorderLayout.CENTER);
		afc.add(next,BorderLayout.EAST);
		
		//		JSeparator separator = new JSeparator(JSeparator.HORIZONTAL);
		//		separator.setPreferredSize(new Dimension(100,10));
		//	    ////System.out.println(separator.getPreferredSize().width);
		//	    ////System.out.println(separator.getMaximumSize().height);
		//		add(separator);

		JPanel bottomHalf = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
		bottomHalf.add(afc);
		bottomHalf.setPreferredSize(new Dimension(200,460));
		bottomHalf.add(scrollPane);

		JButton editLabel = new JButton();

		ImageIcon edit = new ImageIcon("res/edit.png");
		Image editImg = edit.getImage();  
		Image newEditImg = editImg.getScaledInstance(ColorPalette.preferredSize.width-5, ColorPalette.preferredSize.height-5,  java.awt.Image.SCALE_SMOOTH); 

		editLabel.setIcon(new ImageIcon(newEditImg));
		editLabel.setPreferredSize(ColorPalette.preferredSize);
		editLabel.setToolTipText("Edit selected label");
		editLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				if (ShapeList.list.getSelectedIndex() >= 0)
				{
					God.shapeData.listSelection = ShapeList.list.getSelectedIndex();
					String s = (String) JOptionPane.showInputDialog("Please enter new label", 
							God.shapeData.getShape(God.shapeData.listSelection).getLabel());
					if (s != null) {
						God.shapeData.getShape(God.shapeData.listSelection).setLabel(s);
						God.dirtyFlag = true;
						God.shapeList.repaint();
					}
				}
			}
		});
		JButton delLabel = new JButton();

		ImageIcon del = new ImageIcon("res/delete.png");
		Image delImg = del.getImage();  
		Image newDelImg = delImg.getScaledInstance(ColorPalette.preferredSize.width-5, ColorPalette.preferredSize.height-5,  java.awt.Image.SCALE_SMOOTH);  

		delLabel.setIcon(new ImageIcon(newDelImg));
		delLabel.setPreferredSize(ColorPalette.preferredSize);
		delLabel.setToolTipText("Delete selected label");
		delLabel.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int response = JOptionPane.showConfirmDialog(null,"Are you sure you want to delete this label?"
						,"Deleting Label",JOptionPane.YES_NO_OPTION);
				if(response == JOptionPane.NO_OPTION)
					return;
				if (ShapeList.list.getSelectedIndex() >= 0)
				{
					God.shapeData.listSelection = ShapeList.list.getSelectedIndex();
					God.shapeData.removeShape();
					God.shapeList.removeShape();				
					God.shapeData.listSelection = -1;
					God.dirtyFlag = true;
					God.layeredPanel.paint(God.layeredPanel.getGraphics());
					God.scrollPane.repaint();
				}
			}
		});
		JPanel buttons = new JPanel();
		buttons.setLayout(new BoxLayout(buttons,BoxLayout.Y_AXIS));
		buttons.add(editLabel);
		buttons.add(delLabel);
		ColorPalette colourPalette = new ColorPalette(changeColor);
		JPanel listTools = new JPanel();
		listTools.add(buttons);
		listTools.add(colourPalette);

		bottomHalf.add(listTools);
		
		JButton helpButton = new JButton();
		ImageIcon hel = new ImageIcon("res/help.png");
		Image helImg = hel.getImage();  
		Image newHelImg = helImg.getScaledInstance(ColorPalette.preferredSize.width-5, ColorPalette.preferredSize.height-5,  java.awt.Image.SCALE_SMOOTH);  

		helpButton.setIcon(new ImageIcon(newHelImg));
		helpButton.setPreferredSize(ColorPalette.preferredSize);
		helpButton.setToolTipText("Get some help");
		helpButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(God.mainWindow,
						"- Click and drag vertices to move them\n" +
						"- 'Enter' will finish the current shape\n" +
						"- 'Backspace' or 'delete' will delete the vertex\n " +
						"you are currently moving",
                        "Shortcuts",
                        JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		
		bottomHalf.add(helpButton);

		add(bottomHalf,BorderLayout.SOUTH);
	}
}
