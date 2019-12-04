/**
 * 
 */
package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app_structure.FileReader;

class FileReaderTest {

	FileReader f;

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass() throws Exception {

	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp() throws Exception {
		this.f = new FileReader();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link app_structure.FileReader#getCounterSystem()}.
	 */
	@Test
	void testGetCounterSystem() {
		assertNotNull(this.f.getCounterSystem());
	}

	/**
	 * Test method for {@link app_structure.FileReader#iPlasmaLongMethodDefects()}.
	 */
	@Test
	void testIPlasmaLongMethodDefects() {
		f.iPlasmaLongMethodDefects(); 

		assertEquals(140, f.getCounterSystem().getDCI());
		assertEquals(0, f.getCounterSystem().getDII());
		assertEquals(280, f.getCounterSystem().getADCI());
		assertEquals(0, f.getCounterSystem().getADII());
	}

	/**
	 * Test method for {@link app_structure.FileReader#pmdLongMethodDefects()}.
	 */
	@Test
	void testPmdLongMethodDefects() {
		f.pmdLongMethodDefects();
		
		assertEquals(140, f.getCounterSystem().getDCI());
		assertEquals(18, f.getCounterSystem().getDII());
		assertEquals(262, f.getCounterSystem().getADCI());
		assertEquals(0, f.getCounterSystem().getADII());
	}

	/**
	 * Test method for {@link app_structure.FileReader#ruleLongMethodDefects(java.lang.String)}.
	 */
	@Test
	void testRuleLongMethodDefects() {
		///////////////////////////1º Case/////////////////////////
		String test_rule_v1 = "LOC;>;200;AND;CYCLO;>;100";
		this.f.ruleLongMethodDefects(test_rule_v1);
		assertEquals(5, f.getCounterSystem().getDCI());
		assertEquals(0, f.getCounterSystem().getDII());
		assertEquals(280, f.getCounterSystem().getADCI());
		assertEquals(135, f.getCounterSystem().getADII());

		assertEquals(5, this.f.ruleLongMethodDefects(test_rule_v1).size());

		///////////////////////////2º Case/////////////////////////
		String test_rule_v2 = "LOC;<;5;OR;CYCLO;<;0";
		this.f.ruleLongMethodDefects(test_rule_v2);
		assertEquals(0, f.getCounterSystem().getDCI());
		assertEquals(101, f.getCounterSystem().getDII());
		assertEquals(179, f.getCounterSystem().getADCI());
		assertEquals(140, f.getCounterSystem().getADII());

		assertEquals(101, this.f.ruleLongMethodDefects(test_rule_v2).size());
	}

	/**
	 * Test method for {@link app_structure.FileReader#ruleFeatureEnvyDefects(java.lang.String)}.
	 */
	@Test
	void testRuleFeatureEnvyDefects() {
		String test_rule = "ATFD;>;50;AND;LAA;>;0";
		f.ruleFeatureEnvyDefects(test_rule);
		
		assertEquals(4, f.getCounterSystem().getDCI());
		assertEquals(0, f.getCounterSystem().getDII());
		assertEquals(306, f.getCounterSystem().getADCI());
		assertEquals(110, f.getCounterSystem().getADII());
		
	}

	/**
	 * Test method for {@link app_structure.FileReader#getSheet()}.
	 */
	@Test
	void testGetSheet() {
		assertNotNull(this.f.getSheet());
	}

}
