package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.BevelBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;

import app_structure.Application;
import app_structure.FileReader;
import net.miginfocom.swing.MigLayout;

public class GUI extends Observable {

	private static GUI INSTANCE;

	private JFrame frame;
	private JPanel contentPane;
	private JPanel import_panel;
	private JButton viewButton;

	private JComboBox tooOrRuleComboBox;
	private JButton okButton;

	private JTable table_file;

	private JScrollPane scrollPane;
	private JPanel filePanel;
	private static Application app;

	private JLabel currentRuleLabelDisplay;

	private String currentTool;

	private String currentRuleOption;
	private String currentRule;
	
	JLabel dciValue;
	JLabel diiValue;
	JLabel adciValue;
	JLabel adiiValue;

	

	/**
	 * @wbp.parser.entryPoint
	 */
	public GUI(Application app) {
		INSTANCE = this;
		this.app = app;
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void init() {
		frame = new JFrame("BugBuster");
		frame.setBackground(new Color(240, 240, 240));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/gui/web-crawler.png")));
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 964, 639);

		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);

		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);

		JCheckBoxMenuItem chckbxmntmUserGuide = new JCheckBoxMenuItem("User Guide");
		mnHelp.add(chckbxmntmUserGuide);

		JCheckBoxMenuItem chckbxmntmAboutBugbuster = new JCheckBoxMenuItem("About BugBuster");
		mnHelp.add(chckbxmntmAboutBugbuster);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[544.00px][414.00,grow]", "[29px][grow][404px,grow]"));

		import_panel = new JPanel();
		contentPane.add(import_panel, "cell 0 0 1 2,grow");
		import_panel.setLayout(null);

		JLabel fileName_lbl = new JLabel("File Name:");
		fileName_lbl.setBounds(125, 5, 100, 20);
		import_panel.add(fileName_lbl);

		viewButton = new JButton("View File");
		viewButton.setBounds(0, 0, 100, 30);
		import_panel.add(viewButton);

		JLabel lblNameimg = new JLabel("Long-Method.xml");
		lblNameimg.setBounds(215, 5, 155, 20);
		import_panel.add(lblNameimg);

		JPanel rulesPanel = new JPanel();
		contentPane.add(rulesPanel, "cell 1 0 1 3,grow");
		rulesPanel.setLayout(new MigLayout("", "[grow]", "[][][][][][][][][][][][grow]"));

		JLabel defectOptionsLabel = new JLabel("Defect Counter Options:");
		rulesPanel.add(defectOptionsLabel, "cell 0 0");

		tooOrRuleComboBox = new JComboBox();
		tooOrRuleComboBox.setModel(new DefaultComboBoxModel(new String[] { "Tool", "Rule" }));
		rulesPanel.add(tooOrRuleComboBox, "flowx,cell 0 1,growx");

		okButton = new JButton("Ok");

		rulesPanel.add(okButton, "cell 0 1,growx");

		JLabel currentRuleLabel = new JLabel("Current Rule:");
		rulesPanel.add(currentRuleLabel, "cell 0 2");

		JPanel currentRulePanel = new JPanel();
		currentRulePanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		currentRulePanel.setBackground(Color.WHITE);
		rulesPanel.add(currentRulePanel, "cell 0 3,grow");
		currentRulePanel.setLayout(new MigLayout("", "[grow]", "[]"));

		currentRuleLabelDisplay = new JLabel(" ");
		currentRulePanel.add(currentRuleLabelDisplay, "cell 0 0");

		JLabel defectCountersLabel = new JLabel("Defect Counters:");
		rulesPanel.add(defectCountersLabel, "cell 0 4");

		JLabel dciLabel = new JLabel("DCI");
		rulesPanel.add(dciLabel, "flowx,cell 0 5,growx,aligny center");

		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel.setForeground(new Color(0, 0, 0));
		panel.setBackground(Color.WHITE);
		rulesPanel.add(panel, "flowx,cell 0 6,grow");
		panel.setLayout(new MigLayout("", "[grow]", "[]"));

		dciValue = new JLabel(" ");
		panel.add(dciValue, "cell 0 0");

		JLabel adiiLabel_1 = new JLabel("ADII");
		rulesPanel.add(adiiLabel_1, "flowx,cell 0 7,growx");

		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_2.setBackground(Color.WHITE);
		rulesPanel.add(panel_2, "flowx,cell 0 8,grow");
		panel_2.setLayout(new MigLayout("", "[grow]", "[]"));

		adiiValue = new JLabel(" ");
		panel_2.add(adiiValue, "cell 0 0");

		JLabel methodIdsLabel = new JLabel("Method IDs:");
		rulesPanel.add(methodIdsLabel, "cell 0 10");

		JList idsList = new JList();
		idsList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		rulesPanel.add(idsList, "cell 0 11,grow");

		JLabel diiLabel = new JLabel("DII");
		rulesPanel.add(diiLabel, "cell 0 5,growx");

		JLabel adiiLabel = new JLabel("ADCI");
		rulesPanel.add(adiiLabel, "cell 0 7,growx");

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_1.setBackground(Color.WHITE);
		rulesPanel.add(panel_1, "cell 0 6,grow");
		panel_1.setLayout(new MigLayout("", "[grow]", "[]"));

		diiValue = new JLabel(" ");
		panel_1.add(diiValue, "cell 0 0");

		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		panel_3.setBackground(Color.WHITE);
		rulesPanel.add(panel_3, "cell 0 8,grow");
		panel_3.setLayout(new MigLayout("", "[grow]", "[]"));

		JLabel adciValue = new JLabel(" ");
		panel_3.add(adciValue, "cell 0 0");

		filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File View",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(filePanel, "cell 0 2,grow");

		JScrollPane file_scrollPane = new JScrollPane();

		file_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		file_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		file_scrollPane.setBounds(15, 28, 514, 365);
		filePanel.add(file_scrollPane);

		table_file = new JTable();
		table_file.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		file_scrollPane.setViewportView(table_file);

//		GroupLayout gl_filePanel = new GroupLayout(filePanel);
//		gl_filePanel.setHorizontalGroup(
//			gl_filePanel.createParallelGroup(Alignment.LEADING)
//				.addComponent(file_scrollPane, GroupLayout.PREFERRED_SIZE, 530, GroupLayout.PREFERRED_SIZE)
//		);
//		gl_filePanel.setVerticalGroup(
//			gl_filePanel.createParallelGroup(Alignment.LEADING)
//				.addComponent(file_scrollPane, GroupLayout.PREFERRED_SIZE, 380, GroupLayout.PREFERRED_SIZE)
//		);
//		filePanel.setLayout(gl_filePanel);

	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void actions() {
		viewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {

					Sheet sheet = INSTANCE.app.getFileReader().getSheet();
					DefaultTableModel dtm = new DefaultTableModel() {

						@Override
						public boolean isCellEditable(int row, int column) {

							return false;
						}
					};
					/*
					 * copy paste of the file
					 */
					for (int j = 0; j < sheet.getLastRowNum(); j++) {
						for (int i = 0; i < sheet.getRow(j).getLastCellNum(); i++) {
							if (j == 0) {
								dtm.addColumn(sheet.getRow(j).getCell(i));
							} else {
								Row row = sheet.getRow(j);
								dtm.addRow(INSTANCE.app.getFileReader().printRowValue(row));
							}
						}
					}
					table_file.setModel(dtm);

				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2.getMessage());
				}
			}
		});

		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (INSTANCE.tooOrRuleComboBox.getSelectedItem().toString() == "Tool") {
					INSTANCE.createToolFrame();
				} else {
					INSTANCE.createRuleFrame();
				}
			}
		});

	}

	public void createToolFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame toolFrame = new JFrame("Choose a tool");
				toolFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JPanel panel = new JPanel();
				panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
				panel.setOpaque(true);

				String[] toolOptions = { "PMD", "iPlasma" };
				JComboBox<String> toolCombo = new JComboBox(toolOptions);

				JButton applyButton = new JButton("Apply");

				panel.add(toolCombo);
				panel.add(applyButton);

				toolFrame.getContentPane().add(BorderLayout.CENTER, panel);
				toolFrame.pack();
				toolFrame.setLocationByPlatform(true);
				toolFrame.setVisible(true);
				toolFrame.setResizable(false);

				/* ******** Listener ****** */

				applyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						INSTANCE.currentTool = toolCombo.getSelectedItem().toString();
						// MÉTODO PARA MOSTRAR OS DEFEITOS COM A AJUDA DA SOFIA/TONI
						if (INSTANCE.currentTool.equals("iPlasma")) {
							currentRuleLabelDisplay.setText("iPlasma");
							INSTANCE.currentTool = "iPlasma";
						} else {
							currentRuleLabelDisplay.setText("PMD");
							INSTANCE.currentTool = "PMD";
							INSTANCE.app.getFileReader().pmdLongMethodDefects();
							INSTANCE.diiValue.setText(""+INSTANCE.app.getFileReader().getCounterSystem().getDII());
							INSTANCE.dciValue.setText(""+INSTANCE.app.getFileReader().getCounterSystem().getDCI());
							INSTANCE.adiiValue.setText(""+INSTANCE.app.getFileReader().getCounterSystem().getADII());
							INSTANCE.adciValue.setText(""+INSTANCE.app.getFileReader().getCounterSystem().getADCI());
						}

						toolFrame.dispose();
					}
				});

			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public void createRuleFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JFrame ruleFrame = new JFrame("Choose a rule");
				ruleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ruleFrame.setBounds(100, 100, 408, 136);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}
				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new FormLayout(
						new ColumnSpec[] { FormSpecs.RELATED_GAP_COLSPEC, ColumnSpec.decode("default:grow"),
								FormSpecs.RELATED_GAP_COLSPEC, FormSpecs.DEFAULT_COLSPEC, },
						new RowSpec[] { FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC,
								FormSpecs.RELATED_GAP_ROWSPEC, FormSpecs.DEFAULT_ROWSPEC, }));

				JLabel lblChooseOneOf = new JLabel("Choose one of the following rules:");
				contentPane.add(lblChooseOneOf, "2, 2, left, center");

				JComboBox comboBox = new JComboBox();
				comboBox.setBackground(Color.WHITE);
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "Long Method", "Feature Envy" }));
				contentPane.add(comboBox, "2, 4, fill, default");

				JButton btnChoose = new JButton("Choose");
				contentPane.add(btnChoose, "4, 4");

				ruleFrame.getContentPane().add(BorderLayout.CENTER, contentPane);
				ruleFrame.pack();
				ruleFrame.setLocationByPlatform(true);
				ruleFrame.setVisible(true);
				ruleFrame.setResizable(false);

				btnChoose.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						INSTANCE.currentRuleOption = comboBox.getSelectedItem().toString();
						// MÉTODO PARA MOSTRAR OS DEFEITOS COM A AJUDA DA SOFIA/TONI
						if (INSTANCE.currentRuleOption.equals("Feature Envy")) {
							INSTANCE.createFeatEnvyFrame();
						} else {
							INSTANCE.createLongMethodFrame();
						}

						ruleFrame.dispose();
					}
				});

			}
		});
	}

	public void createFeatEnvyFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				JFrame featEnvyFrame = new JFrame("Feauture Envy Rule");
				featEnvyFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				featEnvyFrame.setBounds(100, 100, 570, 150);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new MigLayout("", "[][]", "[][]"));

				JLabel lblFillInAll = new JLabel("Fill in all the blanks:");
				contentPane.add(lblFillInAll, "cell 0 0");

				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				contentPane.add(panel, "cell 0 1,grow");
				panel.setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow][]", "[]"));

				JLabel lblIfAtfd = new JLabel("IF ( ATFD ");
				panel.add(lblIfAtfd, "cell 0 0,alignx trailing");

				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox, "cell 1 0,growx");

				JTextField textField = new JTextField();
				panel.add(textField, "cell 2 0,growx");
				textField.setColumns(10);

				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
				panel.add(comboBox_1, "cell 3 0,growx");

				JLabel lblLaa = new JLabel("LAA");
				panel.add(lblLaa, "cell 4 0,alignx trailing");

				JComboBox comboBox_2 = new JComboBox();
				comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox_2, "cell 5 0,growx");

				JTextField textField_1 = new JTextField();
				panel.add(textField_1, "cell 6 0,growx");
				textField_1.setColumns(10);

				JLabel label = new JLabel(")");
				panel.add(label, "cell 7 0");

				JButton btnApply = new JButton("Apply");
				contentPane.add(btnApply, "cell 1 1");

				btnApply.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try {
							int i_txtfield = Integer.parseInt(textField.getText());
							int i_txtfield1 = Integer.parseInt(textField_1.getText());
							currentRule = "ATFD;" + comboBox.getSelectedItem().toString() + ";" + textField.getText()
									+ ";" + comboBox_1.getSelectedItem().toString() + ";LAA"
									+ comboBox_2.getSelectedItem().toString() + ";" + textField_1.getText();
							System.out.println(currentRule);

							currentRuleLabelDisplay.setText("is_feature_envy = IF ( ATFD "
									+ comboBox.getSelectedItem().toString() + " " + textField.getText() + " "
									+ comboBox_1.getSelectedItem().toString() + " LAA "
									+ comboBox_2.getSelectedItem().toString() + " " + textField_1.getText() + " )");

							featEnvyFrame.dispose();
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Only numberss!");
							textField.setText("");
							textField_1.setText("");
						}
					}
				});

				featEnvyFrame.getContentPane().add(BorderLayout.CENTER, contentPane);
				featEnvyFrame.pack();
				featEnvyFrame.setLocationByPlatform(true);
				featEnvyFrame.setVisible(true);
				featEnvyFrame.setResizable(false);

			}
		});

	}

	public void createLongMethodFrame() {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				JTextField textField;
				JTextField textField_1;

				JFrame longMethodFrame = new JFrame("Long Method Rule");
				longMethodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				longMethodFrame.setBounds(100, 100, 570, 150);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new MigLayout("", "[][]", "[][]"));

				JLabel lblFillInAll = new JLabel("Fill in all the blanks:");
				contentPane.add(lblFillInAll, "cell 0 0");

				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				contentPane.add(panel, "cell 0 1,grow");
				panel.setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow][]", "[]"));

				JLabel lblIfAtfd = new JLabel("IF ( LOC ");
				panel.add(lblIfAtfd, "cell 0 0,alignx trailing");

				JComboBox comboBox = new JComboBox();
				comboBox.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox, "cell 1 0,growx");

				textField = new JTextField();
				panel.add(textField, "cell 2 0,growx");
				textField.setColumns(10);

				JComboBox comboBox_1 = new JComboBox();
				comboBox_1.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
				panel.add(comboBox_1, "cell 3 0,growx");

				JLabel lblLaa = new JLabel("CYCLO");
				panel.add(lblLaa, "cell 4 0,alignx trailing");

				JComboBox comboBox_2 = new JComboBox();
				comboBox_2.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox_2, "cell 5 0,growx");

				textField_1 = new JTextField();
				panel.add(textField_1, "cell 6 0,growx");
				textField_1.setColumns(10);

				JLabel label = new JLabel(")");
				panel.add(label, "cell 7 0");

				JButton btnApply = new JButton("Apply");
				contentPane.add(btnApply, "cell 1 1");

				btnApply.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						try {
							int i_txtfield = Integer.parseInt(textField.getText());
							int i_txtfield1 = Integer.parseInt(textField_1.getText());
							currentRule = "LOC;" + comboBox.getSelectedItem().toString() + ";" + textField.getText()
									+ ";" + comboBox_1.getSelectedItem().toString() + ";CYCLO"
									+ comboBox_2.getSelectedItem().toString() + ";" + textField_1.getText();
							System.out.println(currentRule);

							currentRuleLabelDisplay.setText("is_long_method = IF ( LOC "
									+ comboBox.getSelectedItem().toString() + " " + textField.getText() + " "
									+ comboBox_1.getSelectedItem().toString() + " CYCLO "
									+ comboBox_2.getSelectedItem().toString() + " " + textField_1.getText() + " )");

							longMethodFrame.dispose();
						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Only numberss!");
							textField.setText("");
							textField_1.setText("");
						}
					}

				});

				longMethodFrame.getContentPane().add(BorderLayout.CENTER, contentPane);
				longMethodFrame.pack();
				longMethodFrame.setLocationByPlatform(true);
				longMethodFrame.setVisible(true);
				longMethodFrame.setResizable(false);

			}
		});
	}

	/**
	 * @wbp.parser.entryPoint
	 */
	public static void createAndShowGUI() {
		INSTANCE.init();
		INSTANCE.actions();

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}

}