package fileio;

import java.io.File;
import javax.swing.filechooser.*;

public class TypeFilter extends FileFilter {

	private int type;

	public TypeFilter(int type)
	{
		this.type = type;
	}

	public boolean accept(File f) {
		if (f.isDirectory()) {
			return true;
		}

		String extension = Utils.getExtension(f);

		// Images
		if (type == 1)
		{
			if (extension != null) {
				if (extension.equals(Utils.tiff) ||
						extension.equals(Utils.tif) ||
						extension.equals(Utils.gif) ||
						extension.equals(Utils.jpeg) ||
						extension.equals(Utils.jpg) ||
						extension.equals(Utils.png)) 
				{
					return true;
				} else {
					return false;
				}
			}
		} 
		else
		{	
			// Vertice
			if (extension != null) {
				if (extension.equals(Utils.csv)) 
				{
					return true;
				} else {
					return false;
				}
			}
		}
		return false;
	}

	//The description of this filter
	public String getDescription() {
		if(type == 1)
		{
			return "Images";
		} 
		else
		{
			return "Annotation (CSV) Format";
		}

	}
}