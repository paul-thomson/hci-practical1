package fileio;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.God;
import main.ShapeList;
import main.Toolbox;
import data.AutoFileChooser;
import data.Shape;
import data.ShapeData;
import data.Vertex;

public class FileIOPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	JButton newButton, saveButton, loadButton, exitButton;
	JTextArea log;
	JFileChooser fc;

	public FileIOPanel()
	{
//		this.setPreferredSize(new Dimension(200,200));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

//		newButton = new JButton("New Image");
		newButton = new JButton();
		newButton.setToolTipText("Load a new image");
		newButton.setPreferredSize(Toolbox.buttonSize);
		newButton.setIcon(new ImageIcon("res/new_image.png"));
		newButton.addActionListener(newImage());
		add(newButton);

//		saveButton = new JButton("Save Labels");
		saveButton = new JButton();
		saveButton.setToolTipText("Save labels to disc");
		saveButton.setPreferredSize(Toolbox.buttonSize);
		saveButton.setIcon(new ImageIcon("res/save_labels.png"));
		saveButton.addActionListener(saveSession());
		add(saveButton);

//		loadButton = new JButton("Load Labels");
		loadButton = new JButton();
		loadButton.setToolTipText("Load labels from disc");
		loadButton.setPreferredSize(Toolbox.buttonSize);
		loadButton.setIcon(new ImageIcon("res/load_labels.png"));
		loadButton.addActionListener(loadSession());
		add(loadButton);


//		exitButton = new JButton("Exit");
		exitButton = new JButton();
		exitButton.setToolTipText("Exit the application");
		exitButton.setPreferredSize(Toolbox.buttonSize);
		exitButton.addActionListener(exitApplication());
		exitButton.setIcon(new ImageIcon("res/exit.png"));
		add(exitButton);
	}

	/***
	 * ACTIONLISTENER DEFINITIONS FOR BUTTONS
	 */

	/***
	 * Method definition for loading a new image into the system.
	 * @return ActionListener for newButton button
	 */
	ActionListener newImage()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{		
				if(God.dirtyFlag)
				{
					int response = JOptionPane.showConfirmDialog(null,"You have not saved your labels. \nWould you like to continue loading a new image?"
							,"New Image",JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.NO_OPTION)
						return;
				}
				if (fc == null) 
				{
					fc = new JFileChooser();
					//Add a custom file filter for images
					fc.addChoosableFileFilter(new TypeFilter(1));
					fc.setAcceptAllFileFilterUsed(true);
					//Add custom icons for file types.
					fc.setFileView(new ViewFile());
					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				// Show window dialog
				int returnVal = fc.showDialog(FileIOPanel.this, "Opening New Image");

				// Process the results
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					String file = fc.getSelectedFile().getPath();
					God.autoFileChooser = new AutoFileChooser(file);

					try 
					{
						// TODO SAVE BEFORE NEW PROJECT
						/*** Reset properties for new image ***/
						ShapeData shapeData = new ShapeData();
						God.shapeData = shapeData;
						God.imagePanel.newImage(file);
						// Empty thumbnail list
						God.shapeList.emptyList();
						God.dirtyFlag = false;
						God.vertexPanel.repaint();
					} 
					catch (IOException e) 
					{
						e.printStackTrace();
					}
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
				fc = null;
			}};
	}

	/***
	 *  Method definition for saving an image and polygon labels.
	 * @return ActionListener for saveButton button
	 */
	ActionListener saveSession()
	{
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{
				if (fc == null) 
				{
					fc = new JFileChooser();
					fc.setAcceptAllFileFilterUsed(true);
					//Add custom icons for file types.
					fc.setFileView(new ViewFile());
					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				int returnVal = fc.showDialog(FileIOPanel.this, "Save Annotations");

				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					FileWriter fw = null;
					BufferedWriter bw = null;
					try {
						String filePath = fc.getSelectedFile().getPath();
						int pathLength = filePath.length() - 1;
						for (int i = pathLength; i >= 0; i--) {
							if (filePath.charAt(i) == File.separatorChar) {
								break;
							} 
							if (filePath.charAt(i) == '.') {
								filePath = filePath.substring(0, i);
							}

						}
						AutoFileChooser afc = new AutoFileChooser(filePath);
						File file = new File(filePath + ".csv");
						if (!file.exists()) {
							file.createNewFile();
						}
						fw = new FileWriter(file.getAbsoluteFile());
						bw = new BufferedWriter(fw);
						// Write the image dimension this label is for 
						// Avoids drawing vertices in non image space (illegal!)
						bw.write(String.valueOf(God.imageDimension[0]) + ',' + String.valueOf(God.imageDimension[1]));
						bw.newLine();

						// Write shapes and labels to file
						for (Shape shape : God.shapeData.getShapes()) {
							// Only write shapes that are complete (head == tail and at least 3 vertices)!
							if(shape.size() > 2)
								if(shape.complete())
								{
									bw.write(shape.getLabel());
									//System.out.println(shape.getLabel());
									bw.write(',' + String.valueOf(shape.getColor().getRed()));
									bw.write(',' + String.valueOf(shape.getColor().getGreen()));
									bw.write(',' + String.valueOf(shape.getColor().getBlue()));
									for (Vertex v : shape.getVertices()) {
										bw.write(',' + String.valueOf(v.getX()));
										bw.write(',' + String.valueOf(v.getY()));
									}
									bw.newLine();
								}
						}
						bw.close();
						God.dirtyFlag = false;
						//System.out.println("Done");

					} catch (IOException e) {
						//System.out.println("Error when writing to file");
						e.printStackTrace();
						//System.out.println(e.getMessage());
					} 
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
				fc = null;
			}};
	}

	/***
	 * Method definition for loading an image and polygon labels.
	 * @return ActionListener for loadButton button
	 */
	ActionListener loadSession()
	{
		// LOAD SESSION
		return new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent arg0) 
			{	
				if(God.dirtyFlag)
				{
					int response = JOptionPane.showConfirmDialog(null,"You have not saved your labels. \nWould you like to continue loading an annotation file?"
							,"Load Session",JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.NO_OPTION)
						return;
				}
				if (fc == null) {
					fc = new JFileChooser();

					//Add a custom file filter for annotation csv
					fc.addChoosableFileFilter(new TypeFilter(0));

					//Add custom icons for file types.
					fc.setFileView(new ViewFile());

					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				//Show it.
				int returnVal = fc.showDialog(FileIOPanel.this,
						"Loading Annotations");

				//Process the results.
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					try {

						// TODO FUNCTIONALITY
						File file = fc.getSelectedFile();
						FileInputStream fis = new FileInputStream(file);
						DataInputStream in = new DataInputStream(fis);
						BufferedReader br = new BufferedReader(new InputStreamReader(in));
						ArrayList<String> shapeInfo = new ArrayList<String>();
						String strLine;

						/***
						 * Checks if the annotation file was for an larger image than the current one on display
						 * (draws vertices out of bounds of the current image)
						 * If so, show error message to user
						 */
						if((strLine = br.readLine()) != null)
						{
							String[] infoArray = strLine.split(",");
							int[] fileDimension = new int[2];
							fileDimension[0] = Integer.parseInt(infoArray[0]);
							fileDimension[1] = Integer.parseInt(infoArray[1]);
							if (fileDimension[0] > God.imageDimension[0] || fileDimension[1] > God.imageDimension[1])
							{
								JOptionPane.showMessageDialog(null, "Annotation dimensions are too large for the current image!");		
								return;
							}
						}

						while ((strLine = br.readLine()) != null) {
							shapeInfo.add(strLine);
						}
						God.shapeList.emptyList();
						ShapeData shapeData = new ShapeData();

						// Loading shape function
						for (String info : shapeInfo) {
							if (!info.contains(",")) {
								//PROBLEM, continue for now
								continue;
							}
							String[] infoArray = info.split(",");
							//create shape object from data
							Shape shape = new Shape();
							shape.setLabel(infoArray[0]);
							Color color = new Color(Integer.parseInt(infoArray[1]),
									Integer.parseInt(infoArray[2]),
									Integer.parseInt(infoArray[3])
									);
							shape.setColor(color);
							for (int i = 4; i < infoArray.length; i+=2) {
								Vertex v = new Vertex(	Integer.parseInt(infoArray[i]),
										Integer.parseInt(infoArray[i+1])
										);
								shape.add(v);
							}
							BufferedImage screenshot = God.imagePanel.getScreenshot();
							Rectangle r = shape.getBoundingBox();
							shape.setThumbnail(screenshot.getSubimage(r.x,r.y,r.width,r.height));
							shapeData.addShape(shape);
							ShapeList.addShape(shape);

						}
						shapeData.addShape(new Shape());
						God.shapeData = shapeData;
						God.dirtyFlag = false;					
						God.layeredPanel.paint(God.layeredPanel.getGraphics());
					} 
					catch(FileNotFoundException e) 
					{
						e.printStackTrace();
					} 
					catch(IOException e) 
					{	
						e.printStackTrace();
					} 
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "This file is not a valid annotation file!\n Please try another file.");
					}
				} 
				else 
				{
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
				fc = null;
			}};
	}
	
	ActionListener exitApplication()
	{
		return new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent arg0) {				
				if(God.dirtyFlag)
				{
					int response = JOptionPane.showConfirmDialog(null,"You have not saved your labels. \nWould you still like to exit this application?"
							,"Load Session",JOptionPane.YES_NO_OPTION);
					if(response == JOptionPane.NO_OPTION)
						return;
				}
				System.exit(0);

			}

		};
	}
}
