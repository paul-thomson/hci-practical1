package fileio;

import java.awt.Dimension;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class FileIOPanel extends JPanel
{

	private static final long serialVersionUID = 1L;
	JButton openButton, saveButton;
	JTextArea log;
	JFileChooser fc;

	public FileIOPanel(ActionListener newFile, ActionListener save, ActionListener load)
	{
		this.setPreferredSize(new Dimension(200,200));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

		JButton btnNew = new JButton("New Image");
		btnNew.addActionListener(newFile);
		add(btnNew);

		JButton btnSave = new JButton("Save Session");	
		btnSave.addActionListener(save);
		add(btnSave);

		JButton btnLoad = new JButton("Load Session");
		btnLoad.addActionListener(load);
		add(btnLoad);
	}
}
