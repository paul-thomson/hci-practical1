package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class ColorPalette extends JPanel {
	
	private static final long serialVersionUID = 1L;
	public static Dimension preferredSize = new Dimension(32,32);

	/**
	 * Create the panel.
	 */
	public ColorPalette(ActionListener changeColor) {
		
		setLayout(new GridLayout(2,6));		

		ColorEnum[] colors = ColorEnum.values();
		for (ColorEnum color : colors) {
			JButton button = new JButton();
			button.setBackground(color.getColor());
			button.setBorderPainted(false);
			button.setPreferredSize(preferredSize);
			button.setActionCommand(color.getName());
			button.addActionListener(changeColor);
			add(button);
		}
	}
}
