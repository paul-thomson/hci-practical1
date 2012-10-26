package main;

import java.awt.Cursor;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyleContext;
import javax.swing.text.StyledDocument;

public class HelpBox extends JDialog {
	public HelpBox(JFrame parent) {
		super(parent, "Help", true);

		Box b = Box.createVerticalBox();
		b.add(Box.createGlue());
		
		
		JTextArea textArea = new JTextArea(
                "This is an editable JTextArea. " +
                "A text area is a \"plain\" text component, " +
                "which means that although it can display text " +
                "in any font, all of the text is in the same font."
        );
        textArea.setFont(new Font("Serif", Font.PLAIN, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
		
		
		b.add(Box.createGlue());
		getContentPane().add(b, "Center");

		JPanel p2 = new JPanel();
		JButton ok = new JButton("Ok");
		p2.add(ok);
		getContentPane().add(p2, "South");

		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				setVisible(false);
			}
		});

		setSize(250, 150);
	}
	
	protected void addStylesToDocument(StyledDocument doc) {
        //Initialize some styles.
        Style def = StyleContext.getDefaultStyleContext().
                        getStyle(StyleContext.DEFAULT_STYLE);
 
        Style regular = doc.addStyle("regular", def);
        StyleConstants.setFontFamily(def, "SansSerif");
 
        Style s = doc.addStyle("italic", regular);
        StyleConstants.setItalic(s, true);
 
        s = doc.addStyle("bold", regular);
        StyleConstants.setBold(s, true);
 
        s = doc.addStyle("small", regular);
        StyleConstants.setFontSize(s, 10);
 
        s = doc.addStyle("large", regular);
        StyleConstants.setFontSize(s, 16);
        JButton button = new JButton();
        
        button.setCursor(Cursor.getDefaultCursor());
        button.setMargin(new Insets(0,0,0,0));
        StyleConstants.setComponent(s, button);
    }
}