package main;

import java.awt.Component;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import data.Shape;

public class MyCellRenderer extends JPanel implements ListCellRenderer {
	
	private Shape shape;
	
	@Override
	public Component getListCellRendererComponent(JList list, Object shape,
			int index, boolean isSelected, boolean cellHasFocus) {
		
		this.shape = (Shape)shape;
		
		JLabel label = new JLabel();
		label.setText(this.shape.getLabel());
		label.setOpaque(true);
		
		
		BufferedImage thumbnail = this.shape.getThumbnail();
		if (thumbnail != null) {
			float max = Math.max(thumbnail.getHeight(), thumbnail.getWidth());
			int largestDimension = 50;
			Image scaledImage = thumbnail.getScaledInstance((int)((thumbnail.getWidth()/max) * largestDimension), 
					(int)((thumbnail.getHeight()/max) * largestDimension), 
					Image.SCALE_FAST);
			label.setIcon(new ImageIcon(scaledImage));
		}
		
		// had weird errors if this.add(label) was used, so just created panel instead
		JPanel jp = new JPanel();
		jp.add(label);
		return jp;
	}

}
