package main;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

public class FileIOPanel extends JPanel
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public FileIOPanel(ActionListener newFile,
			ActionListener save, ActionListener load)
	{
		this.setPreferredSize(new Dimension(200,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		JButton btnNew = new JButton("New... Session? ");
		btnNew.addActionListener(new ActionListener() 
		{
			@Override
			public void actionPerformed(ActionEvent e) 
			{
				
			}
		});
		btnNew.addActionListener(newFile);
		add(btnNew);
		
		JButton btnSave = new JButton("Save etc");	
		btnSave.addActionListener(save);
		add(btnSave);
		
		JButton btnLoad = new JButton("Load etc");
		btnLoad.addActionListener(load);
		add(btnLoad);
	}
	
}
