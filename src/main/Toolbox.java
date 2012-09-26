package main;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import data.Shape;

public class Toolbox extends JPanel {

	/**
	 * Create the panel.
	 */
	public Toolbox() {
		this.setPreferredSize(new Dimension(200,600));
		
		setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JButton btnNewObject = new JButton("New Object");
		btnNewObject.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
			    	ImagePanel.addShape(new Shape());
			}
		});
		add(btnNewObject);
		
		JButton btnSelectObject = new JButton("Move Object");
		add(btnSelectObject);
		
		JButton btnChooseColour = new JButton("Choose Colour");
		add(btnChooseColour);

	}

}
