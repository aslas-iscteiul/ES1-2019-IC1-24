package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Observable;

import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
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
import javax.swing.SwingUtilities;
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
import net.miginfocom.swing.MigLayout;

public class GUI extends Observable {

	private static GUI INSTANCE;

	private JFrame frame;
	private JPanel contentPane;
	private JPanel view_panel;
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

	private JLabel dciValue;
	private JLabel diiValue;
	private JLabel adciValue;
	private JLabel adiiValue;

	private DefaultListModel<Integer> idsListModel;
	private JList<Integer> idsList;

	public GUI(Application app) {
		INSTANCE = this;
		this.app = app;
	}

	public void init() {
		// Frame setup
		frame = new JFrame("Quick");
		frame.setBackground(new Color(240, 240, 240));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(GUI.class.getResource("/gui/clock.png")));
		frame.setVisible(true);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(50, 50, 1008, 643);

		// Menu bar
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		JCheckBoxMenuItem chckbxmntmUserGuide = new JCheckBoxMenuItem("User Guide");
		mnHelp.add(chckbxmntmUserGuide);
		JCheckBoxMenuItem chckbxmntmAboutBugbuster = new JCheckBoxMenuItem("About Quick");
		mnHelp.add(chckbxmntmAboutBugbuster);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		frame.setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[544.00px][414.00,grow]", "[29px][grow][][404px,grow]"));

		view_panel = new JPanel();
		contentPane.add(view_panel, "cell 0 0 1 2,grow");
		view_panel.setLayout(null);

		JLabel fileLabel = new JLabel("File Name:");
		fileLabel.setBounds(125, 5, 100, 20);
		view_panel.add(fileLabel);

		viewButton = new JButton("View File");
		viewButton.setBounds(0, 0, 100, 30);

		view_panel.add(viewButton);

		JLabel fileName = new JLabel("Long-Method.xml");
		fileName.setBounds(215, 5, 155, 20);
		view_panel.add(fileName);

		JPanel rulesPanel = new JPanel();
		contentPane.add(rulesPanel, "cell 1 0 1 4,grow");
		rulesPanel.setLayout(new MigLayout("", "[grow][]", "[][][][][][][][][][][][][grow]"));

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

		JPanel dciPanel = new JPanel();
		dciPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		dciPanel.setForeground(new Color(0, 0, 0));
		dciPanel.setBackground(Color.WHITE);
		rulesPanel.add(dciPanel, "flowx,cell 0 6,grow");
		dciPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		dciValue = new JLabel(" ");
		dciPanel.add(dciValue, "cell 0 0");

		JLabel adiiLabel_1 = new JLabel("ADII");
		rulesPanel.add(adiiLabel_1, "flowx,cell 0 7,growx");

		JPanel adiiPanel = new JPanel();
		adiiPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		adiiPanel.setBackground(Color.WHITE);
		rulesPanel.add(adiiPanel, "flowx,cell 0 8,grow");
		adiiPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		adiiValue = new JLabel(" ");
		adiiPanel.add(adiiValue, "cell 0 0");

		JLabel methodIdsLabel = new JLabel("Method IDs:");
		rulesPanel.add(methodIdsLabel, "cell 0 10");

		idsListModel = new DefaultListModel<Integer>();

		JLabel diiLabel = new JLabel("DII");
		rulesPanel.add(diiLabel, "cell 0 5,growx");

		JLabel adiiLabel = new JLabel("ADCI");
		rulesPanel.add(adiiLabel, "cell 0 7,growx");

		JPanel diiPanel = new JPanel();
		diiPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		diiPanel.setBackground(Color.WHITE);
		rulesPanel.add(diiPanel, "cell 0 6,grow");
		diiPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		diiValue = new JLabel(" ");
		diiPanel.add(diiValue, "cell 0 0");

		JPanel adciPanel = new JPanel();
		adciPanel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		adciPanel.setBackground(Color.WHITE);
		rulesPanel.add(adciPanel, "cell 0 8,grow");
		adciPanel.setLayout(new MigLayout("", "[grow]", "[]"));

		adciValue = new JLabel(" ");
		adciPanel.add(adciValue, "cell 0 0");
		idsList = new JList<>();
		idsList.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		idsList.setModel(idsListModel);

		JScrollPane idsListPanel = new JScrollPane(idsList, ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS,
				ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

		idsListPanel.setBounds(15, 28, 514, 365);

		rulesPanel.add(idsListPanel, "cell 0 11,grow");

		filePanel = new JPanel();
		filePanel.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "File View",
				TitledBorder.CENTER, TitledBorder.TOP, null, null));
		contentPane.add(filePanel, "cell 0 2 1 2,grow");

		JScrollPane file_scrollPane = new JScrollPane();

		file_scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		file_scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		file_scrollPane.setBounds(15, 28, 514, 365);
		filePanel.add(file_scrollPane);

		table_file = new JTable();
		table_file.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		file_scrollPane.setViewportView(table_file);

	}

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
				} else if (INSTANCE.tooOrRuleComboBox.getSelectedItem().toString() == "Rule") {
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

				applyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						INSTANCE.currentTool = toolCombo.getSelectedItem().toString();

						if (INSTANCE.currentTool.equals("iPlasma")) {
							currentRuleLabelDisplay.setText("iPlasma");
							INSTANCE.app.getFileReader().iPlasmaLongMethodDefects();
						} else {
							currentRuleLabelDisplay.setText("PMD");
							INSTANCE.app.getFileReader().pmdLongMethodDefects();
						}

						INSTANCE.diiValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getDII());
						INSTANCE.dciValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getDCI());
						INSTANCE.adiiValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getADII());
						INSTANCE.adciValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getADCI());

						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								idsListModel.clear();
							}
						});

						toolFrame.dispose();
					}
				});
			}
		});
	}

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

				JLabel explanationLabel = new JLabel("Choose one of the following rules:");
				contentPane.add(explanationLabel, "2, 2, left, center");

				JComboBox rulesComboBox = new JComboBox();
				rulesComboBox.setBackground(Color.WHITE);
				rulesComboBox.setModel(new DefaultComboBoxModel(new String[] { "Long Method", "Feature Envy" }));
				contentPane.add(rulesComboBox, "2, 4, fill, default");

				JButton chooseButton = new JButton("Choose");
				contentPane.add(chooseButton, "4, 4");

				ruleFrame.getContentPane().add(BorderLayout.CENTER, contentPane);
				ruleFrame.pack();
				ruleFrame.setLocationByPlatform(true);
				ruleFrame.setVisible(true);
				ruleFrame.setResizable(false);

				chooseButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						INSTANCE.currentRuleOption = rulesComboBox.getSelectedItem().toString();

						INSTANCE.createSpecificRuleFrame(INSTANCE.currentRuleOption);

						ruleFrame.dispose();
					}
				});

			}
		});
	}

	public void createSpecificRuleFrame(String nameRule) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {

				JFrame ruleFrame = new JFrame(nameRule + " Rule");
				ruleFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				ruleFrame.setBounds(100, 100, 570, 150);
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
				} catch (Exception e) {
					e.printStackTrace();
				}

				JPanel contentPane = new JPanel();
				contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
				contentPane.setLayout(new MigLayout("", "[][]", "[][]"));

				JLabel explanationLabel = new JLabel("Fill in all the blanks:");
				contentPane.add(explanationLabel, "cell 0 0");

				JPanel panel = new JPanel();
				panel.setBackground(Color.WHITE);
				panel.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
				contentPane.add(panel, "cell 0 1,grow");
				panel.setLayout(new MigLayout("", "[][grow][grow][grow][][grow][grow][]", "[]"));

				//which columns to consider
				JLabel firstColumn;
				JLabel secondColumn;

				if (nameRule.equals("Feature Envy")) {
					firstColumn = new JLabel("IF ( ATFD ");
					panel.add(firstColumn, "cell 0 0,alignx trailing");
					secondColumn = new JLabel("LAA");
					panel.add(secondColumn, "cell 4 0,alignx trailing");
				} else {
					firstColumn = new JLabel("IF ( LOC ");
					panel.add(firstColumn, "cell 0 0,alignx trailing");
					secondColumn = new JLabel("CYCLO");
					panel.add(secondColumn, "cell 4 0,alignx trailing");
				}

				JComboBox comboBox1 = new JComboBox();
				comboBox1.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox1, "cell 1 0,growx");

				JTextField firstNumber = new JTextField();
				panel.add(firstNumber, "cell 2 0,growx");
				firstNumber.setColumns(10);

				JComboBox comboBox2 = new JComboBox();
				comboBox2.setModel(new DefaultComboBoxModel(new String[] { "AND", "OR" }));
				panel.add(comboBox2, "cell 3 0,growx");

				JComboBox comboBox3 = new JComboBox();
				comboBox3.setModel(new DefaultComboBoxModel(new String[] { "<", ">" }));
				panel.add(comboBox3, "cell 5 0,growx");

				JTextField secondNumber = new JTextField();
				panel.add(secondNumber, "cell 6 0,growx");
				secondNumber.setColumns(10);

				JLabel endLabel = new JLabel(")");
				panel.add(endLabel, "cell 7 0");

				JButton applyButton = new JButton("Apply");
				contentPane.add(applyButton, "cell 1 1");

				applyButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {

						List<Integer> auxList;

						try {
							int i_txtfield = Integer.parseInt(firstNumber.getText());
							int i_txtfield1 = Integer.parseInt(secondNumber.getText());

							if (nameRule.equals("Feature Envy")) {
								currentRule = "ATFD;" + comboBox1.getSelectedItem().toString() + ";"
										+ firstNumber.getText() + ";" + comboBox2.getSelectedItem().toString() + ";LAA;"
										+ comboBox3.getSelectedItem().toString() + ";" + secondNumber.getText();

								auxList = INSTANCE.app.getFileReader().ruleFeatureEnvyDefects(INSTANCE.currentRule);

								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										idsListModel.clear();
										for (Integer i : auxList) {
											idsListModel.addElement(i);
										}
									}
								});

								currentRuleLabelDisplay.setText("is_feature_envy = IF ( ATFD "
										+ comboBox1.getSelectedItem().toString() + " " + firstNumber.getText() + " "
										+ comboBox2.getSelectedItem().toString() + " LAA "
										+ comboBox3.getSelectedItem().toString() + " " + secondNumber.getText() + " )");

							} else {
								currentRule = "LOC;" + comboBox1.getSelectedItem().toString() + ";" + firstNumber.getText()
										+ ";" + comboBox2.getSelectedItem().toString() + ";CYCLO;"
										+ comboBox3.getSelectedItem().toString() + ";" + secondNumber.getText();

								auxList = INSTANCE.app.getFileReader().ruleLongMethodDefects(INSTANCE.currentRule);

								SwingUtilities.invokeLater(new Runnable() {
									@Override
									public void run() {
										idsListModel.clear();
										for (Integer i : auxList) {
											idsListModel.addElement(i);
										}
									}
								});

								currentRuleLabelDisplay.setText("is_long_method = IF ( LOC "
										+ comboBox1.getSelectedItem().toString() + " " + firstNumber.getText() + " "
										+ comboBox2.getSelectedItem().toString() + " CYCLO "
										+ comboBox3.getSelectedItem().toString() + " " + secondNumber.getText() + " )");

							}

							System.out.println(currentRule);

							INSTANCE.diiValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getDII());
							INSTANCE.dciValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getDCI());
							INSTANCE.adiiValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getADII());
							INSTANCE.adciValue.setText("" + INSTANCE.app.getFileReader().getCounterSystem().getADCI());

							ruleFrame.dispose();

						} catch (NumberFormatException nfe) {
							JOptionPane.showMessageDialog(null, "Only numbers!");
							firstNumber.setText("");
							secondNumber.setText("");
						}
					}
				});

				ruleFrame.getContentPane().add(BorderLayout.CENTER, contentPane);
				ruleFrame.pack();
				ruleFrame.setLocationByPlatform(true);
				ruleFrame.setVisible(true);
				ruleFrame.setResizable(false);

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