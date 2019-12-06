package tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app_structure.Application;
import app_structure.FileReader;
import gui.GUI;

class GUITest {

	GUI g;
	Application a;
	FileReader f;

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
		f = new FileReader();
		a = new Application(f);
		g = new GUI(a);
		g.createAndShowGUI();
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testTool() {
		JComboBox<String> cb1 = new JComboBox();
		cb1.setModel(new DefaultComboBoxModel(new String[] { "Tool", "Rule" }));
		cb1.setSelectedItem("Tool");
		g.setToolOrRuleComboBox(cb1);
		g.setAnotherWindowOpen(true);
		g.createToolFrame();

		f.pmdLongMethodDefects();

		assertEquals(140, f.getCounterSystem().getDCI());
		assertEquals(18, f.getCounterSystem().getDII());
		assertEquals(0, f.getCounterSystem().getADII());
		assertEquals(262, f.getCounterSystem().getADCI());
	}

	@Test
	void testRule1() {
		JComboBox<String> cb1 = new JComboBox();
		cb1.setModel(new DefaultComboBoxModel(new String[] { "Tool", "Rule" }));
		cb1.setSelectedItem("Rule");
		g.setToolOrRuleComboBox(cb1);
		g.setAnotherWindowOpen(true);
		g.createRuleFrame();

		g.createSpecificRuleFrame("Long Method");

		g.setCurrentRule("LOC;<;1;AND;CYCLO;<;1");

		f.ruleLongMethodDefects(g.getCurrentRule());

		assertEquals(0, f.getCounterSystem().getDCI());
		assertEquals(0, f.getCounterSystem().getDII());
		assertEquals(140, f.getCounterSystem().getADII());
		assertEquals(280, f.getCounterSystem().getADCI());
	}

	@Test
	void testRule2() {
		JComboBox<String> cb1 = new JComboBox();
		cb1.setModel(new DefaultComboBoxModel(new String[] { "Tool", "Rule" }));
		cb1.setSelectedItem("Rule");
		g.setToolOrRuleComboBox(cb1);
		g.setAnotherWindowOpen(true);
		g.createRuleFrame();

		g.createSpecificRuleFrame("Long Method");

		g.setCurrentRule("LOC;>;70;AND;CYCLO;>;50");

		List<Integer> list = f.ruleLongMethodDefects(g.getCurrentRule());

		assertEquals(24, f.getCounterSystem().getDCI());
		assertEquals(0, f.getCounterSystem().getDII());
		assertEquals(116, f.getCounterSystem().getADII());
		assertEquals(280, f.getCounterSystem().getADCI());

		List<Integer> myArray = Arrays.asList(15, 16, 30, 79, 113, 115, 122, 128, 198, 231, 282, 309, 316, 318, 324,
				325, 336, 339, 343, 349, 377, 391, 393, 409);

		assertEquals(myArray, list);
	}
}
