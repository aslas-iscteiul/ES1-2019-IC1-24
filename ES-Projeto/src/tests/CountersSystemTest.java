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

import app_structure.CountersSystem;

class CountersSystemTest {
	
	CountersSystem c;

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
		this.c = new CountersSystem();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	void tearDown() throws Exception {
	}

	/**
	 * Test method for {@link copy.CountersSystem#toString()}.
	 */
	@Test
	void testToString() {
		assertEquals("DCI = " + this.c.getDCI() + 
				   "\nDII = " + this.c.getDII() + 
				  "\nADCI = " + this.c.getADCI() + 
				  "\nADII = " + this.c.getDII(), this.c.toString());
	}

}
