package tests;

import org.junit.platform.runner.JUnitPlatform;
import org.junit.platform.suite.api.SelectClasses;
import org.junit.runner.RunWith;

@RunWith(JUnitPlatform.class)
@SelectClasses({CountersSystemTest.class, FileReaderTest.class, GUITest.class, LogicOperatorTest.class, RelationalOperatorTest.class,
	ApplicationTest.class})
public class AllTests {

}
