package app_structure;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class FileReader {
 
	private static final String PATH = "Long-Method.xlsx";
	private static final String TITLE = "Long-Method";

	private FileInputStream file;
	private Workbook workbook;
	private Sheet sheet;

	/**
	 * Creates a FileReader instance for the file 'Long-Method.xlsx'.
	 * @throws IOException if an I/O error occurs.
	 */
	public FileReader() throws IOException {
		this.file = new FileInputStream(PATH);
		this.workbook = new XSSFWorkbook(file);
		this.sheet = workbook.getSheet(TITLE);
	}
}
