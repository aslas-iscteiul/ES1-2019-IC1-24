package tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import app_structure.Application;
import app_structure.FileReader;

class ApplicationTest {

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
		a = new Application(f);
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testGetFileReader() {
		assertEquals(f,a.getFileReader());
	}
}
