package fileio;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import main.God;
import data.Shape;
import data.ShapeData;
import data.Vertex;

public class FileIOPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	JButton newButton, saveButton, loadButton;
	JTextArea log;
	JFileChooser fc;

	public FileIOPanel()
	{
		this.setPreferredSize(new Dimension(200,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		newButton = new JButton("New Image");
		newButton.addActionListener(newImage());
		add(newButton);

		saveButton = new JButton("Save Session");	
		saveButton.addActionListener(saveSession());
		add(saveButton);

		loadButton = new JButton("Load Session");
		loadButton.addActionListener(loadSession());
		add(loadButton);
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
				if (fc == null) 
				{
					fc = new JFileChooser();

					//Add a custom file filter
					fc.addChoosableFileFilter(new TypeFilter(1));
					fc.addChoosableFileFilter(new TypeFilter(0));
					fc.setAcceptAllFileFilterUsed(true);

					//Add custom icons for file types.
					fc.setFileView(new ViewFile());

					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				// Show it
				int returnVal = fc.showDialog(FileIOPanel.this, "Opening New Image");

				// Process the results
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					String file = fc.getSelectedFile().getPath();

					try 
					{
						// TODO SAVE BEFORE NEW PROJECT
						ShapeData shapeData = new ShapeData();
						God.shapeData = shapeData;
						God.imagePanel.newImage(file);
					} 
					catch (IOException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
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

					//Add a custom file filter
					fc.addChoosableFileFilter(new TypeFilter(1));
					fc.addChoosableFileFilter(new TypeFilter(0));
					fc.setAcceptAllFileFilterUsed(true);

					//Add custom icons for file types.
					fc.setFileView(new ViewFile());

					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				int returnVal = fc.showDialog(FileIOPanel.this, "Save Session");

				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					FileWriter fw = null;
					BufferedWriter bw = null;
					try {
						String filePath = fc.getSelectedFile().getPath();
						String[] fileArray = filePath.split("."); // want to make sure we only take name, not extension
						if (fileArray.length > 0) {
							filePath = fileArray[0];
						}
						File file = new File(fileArray + ".csv");
						if (!file.exists()) {
							file.createNewFile();
						}

						fw = new FileWriter(file.getAbsoluteFile());
						bw = new BufferedWriter(fw);
						for (Shape shape : God.shapeData.getShapes()) {
							bw.write(shape.getLabel());
							bw.write(',' + String.valueOf(shape.getColor().getRed()));
							bw.write(',' + String.valueOf(shape.getColor().getGreen()));
							bw.write(',' + String.valueOf(shape.getColor().getBlue()));
							for (Vertex v : shape.getVertices()) {
								bw.write(',' + String.valueOf(v.getX()));
								bw.write(',' + String.valueOf(v.getY()));

							}
							bw.newLine();

						}
						bw.close();
						System.out.println("Done");



					} catch (IOException e) {
						System.out.println("Error when writing to file");
						e.printStackTrace();
						System.out.println(e.getMessage());
					} 
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
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
				if (fc == null) {
					fc = new JFileChooser();

					//Add a custom file filter
					fc.addChoosableFileFilter(new TypeFilter(1));
					fc.addChoosableFileFilter(new TypeFilter(0));
					fc.setAcceptAllFileFilterUsed(false);

					//Add custom icons for file types.
					fc.setFileView(new ViewFile());

					//Add the preview pane.
					fc.setAccessory(new ImagePreview(fc));
				}

				//Show it.
				int returnVal = fc.showDialog(FileIOPanel.this,
						"Loading Session");

				//Process the results.
				if (returnVal == JFileChooser.APPROVE_OPTION) 
				{
					// TODO FUNCTIONALITY
					File file = fc.getSelectedFile();
				} 
				else 
				{
				}
				//Reset the file chooser for the next time it's shown.
				fc.setSelectedFile(null);
			}};
	}


}
