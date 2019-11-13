package gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.sl.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import app_structure.FileReader;

public class GUI {
	private JFrame frame;
	private JPanel contentPane;
	private JPanel import_panel;

	private JButton searchButton;
	private JButton openButton;
	
	private JLabel file_lbl;

	private JTable table_file;
	private File selectedFile;

	private JScrollPane scrollPane;
	private JPanel filePanel;


	public GUI() {
		init();
		actions();
	}
	public void init() {
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

		file_lbl = new JLabel("New label");
		file_lbl.setBounds(200, 5, 220, 20);
		import_panel.add(file_lbl);

		openButton = new JButton("Open File");
		openButton.setBounds(395, 0, 100, 30);
		import_panel.add(openButton);

		filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File View", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		filePanel.setBounds(15, 61, 544, 517);
		contentPane.add(filePanel);
		filePanel.setLayout(null);

		JScrollPane file_scrollPane = new JScrollPane();
		file_scrollPane.setBounds(15, 28, 514, 473);
		filePanel.add(file_scrollPane);

		table_file = new JTable();
		file_scrollPane.setViewportView(table_file);

	}
	public void actions() {
		searchButton.addActionListener(new ActionListener() { 
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				File file = new File(System.getProperty("user.home"));
				fileChooser.setCurrentDirectory(file);
				int result = fileChooser.showOpenDialog(null);
				if (result == JFileChooser.APPROVE_OPTION) {
					selectedFile = fileChooser.getSelectedFile();
					file_lbl.setText(selectedFile.getName().toString());
					try {
						FileReader fr= new FileReader();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					table_file.setModel(new DefaultTableModel(
							new Object[][] {
								{"", ""},
							},
							new String[] {

							}
							) {
						boolean[] columnEditables = new boolean[] {
								false, false
						};
						public boolean isCellEditable(int row, int column) {
							return columnEditables[column];
						}
					});


				} 

			} });
		openButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table_file.setModel(new DefaultTableModel(
						new Object[][] {
							{"", ""},
						},
						new String[] {

						}
						) {
					boolean[] columnEditables = new boolean[] {
							false, false
					};
					public boolean isCellEditable(int row, int column) {
						return columnEditables[column];
					}
				});
			}
		});
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
