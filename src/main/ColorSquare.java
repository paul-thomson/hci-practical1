package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ColorSquare extends JButton {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Dimension preferredSize = new Dimension(20,20);

	public ColorSquare(Color color) {

		super();
		setBackground(color);
		setBorderPainted(false);
		setPreferredSize(preferredSize);
		//		setActionCommand(color);
		addActionListener(new ActionListener(){

			@Override
			public void actionPerformed(ActionEvent arg0) {

			}

		});

	}
	

}

