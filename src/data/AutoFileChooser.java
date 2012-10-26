package data;

import java.io.File;

public class AutoFileChooser {
	
	public AutoFileChooser(String path) {
		int pathLength = path.length() - 1;
		for (int i = pathLength; i >= 0; i--) {
			if (path.charAt(i) == File.separatorChar) {
				path = path.substring(0,i+1); //+ 1 means we keep separator
				break;
			}
		}
		File folder = new File(path);
		System.out.println(folder.listFiles()[0]);
	}
}