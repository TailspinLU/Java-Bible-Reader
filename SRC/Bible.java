
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.DefaultHighlighter.DefaultHighlightPainter;
import javax.swing.text.Highlighter;
import java.io.IOException;
import java.io.*;
class Bible extends JFrame implements ItemListener {
	// frame
	static JFrame f;
	// label
	static JLabel l, l1, l2;
	// Combo box
	static JComboBox c1;
	static JComboBox c2;
	// Buttons
	static JButton b;
	static JButton b2;
	// Text field
	static JTextField c3;
	// Text box
	static JTextArea textArea;

	// main class
	public static void main(String[] args) {
		// create a new frame
		f = new JFrame("Java Bible");
		// create a object
		Bible s = new Bible();
		// set layout of frame
		f.getContentPane().setLayout(new FlowLayout());
		// array of string containing cities
		String s1[] = {"Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy", "Joshua", "Judges", "Ruth", "1 Samuel", "2 Samuel", "1 Kings", "2 Kings", "1 Chronicles", "2 Chronicles", "Ezra", "Nehemiah", "Esther", "Job", "Psalms", "Proverbs", "Ecclesiastes", "SongofSolomon", "Isaiah", "Jeremiah", "Lamentations", "Ezekiel", "Daniel", "Hosea", "Joel", "Amos", "Obadiah", "Jonah", "Micah", "Nahum", "Habakkuk", "Zephaniah", "Haggai", "Zechariah", "Malachi"};
		// create checkbox
		c1 = new JComboBox(s1);
		// add ItemListener
		c1.addItemListener(s);
		// create labels
		l = new JLabel("Old Testament");
		l1 = new JLabel("New Testament");
		l2 = new JLabel("Term to Highlight");
		String s2[] = {"Matthew", "Mark", "Luke", "John", "Acts", "Romans", "1 Corinthians", "2 Corinthians", "Galatians", "Ephesians", "Philippians", "Colossians", "1 Thessalonians", "2 Thessalonians", "1 Timothy", "2 Timothy", "Titus", "Philemon", "Hebrews", "James", "1 Peter", "2 Peter", "1 John", "2 John", "3 John", "Jude", "Revelation"};
		c3 = new JTextField();
		// Initialize Buttons
		b = new JButton("alter");
		b2 = new JButton("Back to Top");
		// create checkbox
		c2 = new JComboBox(s2);
		c2.setSelectedItem(null);
		// add ItemListener
		c2.addItemListener(s);
		// create a new panel
		JPanel p = new JPanel();
		p.setLayout(new GridLayout(4, 2, 3, 3));
		p.setBackground(new Color(181, 141, 90));
		p.add(l);
		// add combobox to panel
		p.add(c1);
		p.add(l1);
		p.add(c2);
		// add panel to frame
		f.getContentPane().add(p, BorderLayout.WEST);
		// text
		textArea = new JTextArea(35, 50);
		textArea.setLineWrap(true);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setBackground(new Color(181, 141, 90));
		JScrollPane scrollPane = new JScrollPane(textArea);
		f.getContentPane().add(scrollPane, BorderLayout.CENTER);
		f.getContentPane().setBackground(new Color(110, 81, 43));
		textArea.setText(ReadFileToString("Genesis.txt"));
		textArea.setCaretPosition(0);

		//Added functions to Search for a term to alter and return the scroll bar to the top
		p.add(l2);
		p.add(c3);
		p.add(b);
		p.add(b2);
		//Alter Button used to alter the String patterns in the text area that match the pattern found in the text field above:
		//>Currently IS case-sensitive
		//>Intention was to highlight but highlighter currently does not work, so it only upper cases the target
		b.addActionListener(e -> {

			String x= ReadFileToString(c1.getSelectedItem() + ".txt");
			String y= c3.getText();
			x=x.replaceAll(y,y.toUpperCase());
			textArea.setText(x);
			c3.setText("");
			textArea.setCaretPosition(0);
			//String text = textArea.getText();
			//int pos = text.indexOf(y);
			//int length= y.length();
			Highlighter hl = textArea.getHighlighter();
			hl.removeAllHighlights();

			//FIXME:
			//Highlighter itself works but can only highlight the whole text area,
			// the while loop was meant to search through and
			// find the targets, but alas it fails

			//||||||||||||||||||||||||||||||||||||
			//VVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVVV
			//DefaultHighlightPainter paint = new DefaultHighlightPainter(Color.YELLOW);
			//while(pos != -1 ) {
			//try {
			//hl.addHighlight(pos, length, paint);
			//pos=text.indexOf(y, pos+1);
			//} catch (BadLocationException ex) {
			//throw new RuntimeException(ex);}}
		});
		//Button to return the user view back to the top of the scroll bar
		b2.addActionListener(e -> {
			String text = textArea.getText();
			textArea.setText(text);
			textArea.setCaretPosition(0);
		});

		// frame code
		f.pack();
		// set the size of frame
		f.setSize(600, 800);
		f.show();
	}

	public void itemStateChanged(ItemEvent e) {
		// if the state combobox is changed
		if (e.getSource() == c1) {
			textArea.setText(ReadFileToString(c1.getSelectedItem() + ".txt"));
			textArea.setCaretPosition(0);
		}
		if (e.getSource() == c2) {
			textArea.setText(ReadFileToString(c2.getSelectedItem() + ".txt"));
			textArea.setCaretPosition(0);
		}
	}

	private static String ReadFileToString(String filePath) {
		String bbtext = "";

		try {
			InputStream inn = Bible.class.getResourceAsStream(filePath);
			BufferedReader in = new BufferedReader(new InputStreamReader(inn));

			// FileReader filer = new FileReader(filePath);
			// BufferedReader in = new BufferedReader(filer);
			while (true) {
				String line = in.readLine();
				bbtext = bbtext + line + "\n\n";
				if (line == null) {
					break;
				}
			}
			in.close();
		} catch (IOException except) {

		}

		return bbtext;
	}


}