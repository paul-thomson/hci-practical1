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
import javax.swing.JTextArea;
import javax.swing.ListCellRenderer;

import data.Shape;

public class MyCellRenderer extends JPanel implements ListCellRenderer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Shape shape;

	@Override
	public Component getListCellRendererComponent(JList list, Object shape,
			int index, boolean isSelected, boolean cellHasFocus) {

		this.shape = (Shape)shape;
		
		JLabel label = new JLabel();
		label.setText(this.shape.getLabel());
		label.setOpaque(true);
				
		label.setIcon(new ImageIcon(this.shape.getThumbnail()));
		
		// had weird errors if this.add(label) was used, so just created panel instead
		JPanel jp = new JPanel();
		jp.add(label);
		return jp;
	}

}
