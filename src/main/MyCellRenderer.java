package main;

import java.awt.Component;

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
		JTextArea textArea = new JTextArea("lolololololol");
		add(textArea);

		setOpaque(true);
		return this;
	}

}
