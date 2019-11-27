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
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextPane;

public class GUI {
	private JFrame frame;
	private JPanel contentPane;
	private JPanel import_panel;
	private JButton openButton;

	private JTable table_file;
	private File selectedFile;

	private JScrollPane scrollPane;
	private JPanel filePanel;
	private JTextField locNumber;
	private JTextField cycloLab;
	private JTextField textField;
	private JTextField textField_1;


	public GUI() {
		init();
		actions();
	}
	public void init() {
		frame= new JFrame("BugBuster");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 892, 650);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(null);

		import_panel = new JPanel();
		import_panel.setBounds(25, 15, 500, 30);
		contentPane.add(import_panel);
		import_panel.setLayout(null);

		JLabel fileName_lbl = new JLabel("File Name:");
		fileName_lbl.setBounds(125, 5, 100, 20);
		import_panel.add(fileName_lbl);

		openButton = new JButton("Open File");
		openButton.setBounds(0, 0, 100, 30);
		import_panel.add(openButton);
		
		JLabel lblNomeimg = new JLabel("nome.img");
		lblNomeimg.setBounds(256, 5, 69, 20);
		import_panel.add(lblNomeimg);

		filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File View", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		filePanel.setBounds(15, 61, 544, 405);
		contentPane.add(filePanel);
		filePanel.setLayout(null);

		JScrollPane file_scrollPane = new JScrollPane();
		file_scrollPane.setBounds(15, 28, 514, 365);
		filePanel.add(file_scrollPane);

		table_file = new JTable();
		file_scrollPane.setViewportView(table_file);
		
		JLabel lblRuleForIslongmethod = new JLabel("Rule for is_long_method =");
		lblRuleForIslongmethod.setBounds(25, 482, 206, 20);
		contentPane.add(lblRuleForIslongmethod);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(229, 476, 625, 41);
		contentPane.add(panel);
		
		JLabel lblIf = new JLabel("IF ( LOC");
		panel.add(lblIf);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"<", ">"}));
		panel.add(comboBox);
		
		locNumber = new JTextField();
		panel.add(locNumber);
		locNumber.setColumns(10);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		panel.add(comboBox_1);
		
		JLabel lblCyclo = new JLabel("CYCLO");
		panel.add(lblCyclo);
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"<", ">"}));
		panel.add(comboBox_2);
		
		cycloLab = new JTextField();
		panel.add(cycloLab);
		cycloLab.setColumns(10);
		
		JLabel label = new JLabel(")");
		panel.add(label);
		
		JLabel lblRuleForIsenvyfeature = new JLabel("Rule for is_envy_feature=");
		lblRuleForIsenvyfeature.setBounds(25, 546, 206, 20);
		contentPane.add(lblRuleForIsenvyfeature);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(229, 537, 625, 41);
		contentPane.add(panel_1);
		
		JLabel lblIfAtfd = new JLabel("IF ( ATFD");
		panel_1.add(lblIfAtfd);
		
		JComboBox comboBox_3 = new JComboBox();
		comboBox_3.setModel(new DefaultComboBoxModel(new String[] {"<", ">"}));
		panel_1.add(comboBox_3);
		
		textField = new JTextField();
		panel_1.add(textField);
		textField.setColumns(10);
		
		JComboBox comboBox_4 = new JComboBox();
		comboBox_4.setModel(new DefaultComboBoxModel(new String[] {"AND", "OR"}));
		panel_1.add(comboBox_4);
		
		JLabel lblLaa = new JLabel("LAA");
		panel_1.add(lblLaa);
		
		JComboBox comboBox_5 = new JComboBox();
		comboBox_5.setModel(new DefaultComboBoxModel(new String[] {"<", ">"}));
		panel_1.add(comboBox_5);
		
		textField_1 = new JTextField();
		panel_1.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel(")");
		panel_1.add(label_1);
		
		JLabel lblDci = new JLabel("DCI");
		lblDci.setBounds(615, 25, 69, 20);
		contentPane.add(lblDci);
		
		JTextPane textPane = new JTextPane();
		textPane.setBounds(615, 61, 59, 26);
		contentPane.add(textPane);
		
		JTextPane textPane_1 = new JTextPane();
		textPane_1.setBounds(689, 61, 59, 26);
		contentPane.add(textPane_1);
		
		JTextPane textPane_2 = new JTextPane();
		textPane_2.setBounds(763, 61, 59, 26);
		contentPane.add(textPane_2);
		
		JLabel lblDii = new JLabel("DII");
		lblDii.setBounds(615, 117, 69, 20);
		contentPane.add(lblDii);
		
		JTextPane textPane_3 = new JTextPane();
		textPane_3.setBounds(615, 155, 59, 26);
		contentPane.add(textPane_3);
		
		JTextPane textPane_4 = new JTextPane();
		textPane_4.setBounds(689, 155, 59, 26);
		contentPane.add(textPane_4);
		
		JTextPane textPane_5 = new JTextPane();
		textPane_5.setBounds(763, 155, 59, 26);
		contentPane.add(textPane_5);
		
		JLabel lblAdci = new JLabel("ADCI");
		lblAdci.setBounds(615, 225, 69, 20);
		contentPane.add(lblAdci);
		
		JTextPane textPane_6 = new JTextPane();
		textPane_6.setBounds(615, 261, 59, 26);
		contentPane.add(textPane_6);
		
		JTextPane textPane_7 = new JTextPane();
		textPane_7.setBounds(689, 261, 59, 26);
		contentPane.add(textPane_7);
		
		JTextPane textPane_8 = new JTextPane();
		textPane_8.setBounds(763, 261, 59, 26);
		contentPane.add(textPane_8);
		
		JLabel lblAdii = new JLabel("ADII");
		lblAdii.setBounds(615, 323, 69, 20);
		contentPane.add(lblAdii);
		
		JTextPane textPane_9 = new JTextPane();
		textPane_9.setBounds(615, 359, 59, 26);
		contentPane.add(textPane_9);
		
		JTextPane textPane_10 = new JTextPane();
		textPane_10.setBounds(689, 359, 59, 26);
		contentPane.add(textPane_10);
		
		JTextPane textPane_11 = new JTextPane();
		textPane_11.setBounds(763, 359, 59, 26);
		contentPane.add(textPane_11);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.LIGHT_GRAY);
		panel_2.setBounds(599, 15, 243, 445);
		contentPane.add(panel_2);

	}
	public void actions() {
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
