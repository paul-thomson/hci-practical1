package main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;
import javax.swing.border.EmptyBorder;

import data.Shape;

public class MyCellRenderer extends JPanel implements ListCellRenderer {

	private static final long serialVersionUID = 1L;
	protected Shape shape;
	protected int desiredLength = 13;

	@Override
	public Component getListCellRendererComponent(JList list, Object shape,
			int index, boolean isSelected, boolean cellHasFocus) {

		this.shape = (Shape)shape;
		
		String text = this.shape.getLabel();
		if (text.length() > desiredLength) {
			text = text.substring(0, desiredLength-1) + "...";
		}
		JLabel label = new JLabel();
		label.setBackground(new Color(0,0,0,0));
		label.setText(text);
		label.setOpaque(true);
			
		label.setIcon(new ImageIcon(this.shape.getThumbnail()));
		
		// had weird errors if this.add(label) was used, so just created panel instead
		JPanel jp = new JPanel();
		jp.setBackground(new Color(0,0,0,0));
		jp.setLayout(new BoxLayout(jp,BoxLayout.X_AXIS));
		jp.setBorder(new EmptyBorder(5,5,5,5));
		jp.add(label);
		
		return jp;
	}
	
}
