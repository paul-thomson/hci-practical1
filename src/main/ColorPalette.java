package main;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.ShapeData;

public class ColorPalette extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dimension preferredSize = new Dimension(20,20);

	/**
	 * Create the panel.
	 */
	public ColorPalette() {
		
		setLayout(new GridLayout(2,6));		

		ColorEnum[] colors = ColorEnum.values();
		for (ColorEnum color : colors) {
			JButton button = new JButton();
			button.setBackground(color.getColor());
			button.setBorderPainted(false);
			button.setPreferredSize(preferredSize);
			button.setActionCommand(color.getName());
			button.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					ShapeData.setColor(ColorEnum.getColor(arg0.getActionCommand()));
					
				}
				
			});
			add(button);
		}
		
	}

}
