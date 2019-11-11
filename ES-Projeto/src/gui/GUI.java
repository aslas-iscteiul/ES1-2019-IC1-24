package gui;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

public class GUI {
	private JFrame frame;
	private JPanel contentPane;
	private JPanel import_panel;
	
	private JButton searchButton;
	private JButton openButton;
	
	private JTable table_file;
	
	private JScrollPane scrollPane;
	
	
public GUI() {
		frame= new JFrame("BugBuster");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 1080, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);
		
		import_panel = new JPanel();
		import_panel.setBounds(25, 15, 500, 30);
		contentPane.add(import_panel);
		import_panel.setLayout(null);
		
		searchButton = new JButton("Search File...");
		searchButton.setBounds(0, 0, 130, 30);
		import_panel.add(searchButton);
		
		JLabel fileName_lbl = new JLabel("File Name:");
		fileName_lbl.setBounds(145, 5, 75, 20);
		import_panel.add(fileName_lbl);
		
		JLabel file_lbl = new JLabel("New label");
		file_lbl.setBounds(200, 5, 220, 20);
		import_panel.add(file_lbl);
		
		openButton = new JButton("Open File");
		openButton.setBounds(395, 0, 100, 30);
		import_panel.add(openButton);
}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI gui = new GUI();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

}
