package main;

import data.ShapeData;
import fileio.FileIOPanel;

public class God {
	
	static MainWindow mainWindow;
	static ImagePanel imagePanel; 
	static Toolbox toolBox;
	static FileIOPanel fileIOPanel;
	static ShapeData shapeData;
	
	public static void setMainWindow(MainWindow mWindow)
	{
		mainWindow = mWindow;
	}
	
	public static void setImagePanel(ImagePanel iPanel)
	{
		imagePanel = iPanel;
	}
	
	public static void setToolBox(Toolbox tBox)
	{
		toolBox = tBox;
	}

	public static void setFileIOPanel(FileIOPanel fIOPanel)
	{
		fileIOPanel = fIOPanel;
	}
	
	public static void setShapeData(ShapeData sData)
	{
		shapeData = sData;
	}
	
	public void reset()
	{
		mainWindow = null;
		imagePanel = null;
		toolBox = null;
		fileIOPanel = null;
		shapeData = null;
	}


}
