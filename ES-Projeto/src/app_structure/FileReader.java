package app_structure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
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
	
	/**
	 * Prints all file.
	 */
	public void printAllFile() {
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			printRowValues(row);
		}
	}
	
	/**
	 * Prints the specified Row.
	 * @param row - The Row to be printed.
	 */
	public void printRowValues(Row row) {
		Iterator<Cell> cellIterator = row.cellIterator();
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			printCellValue(cell);
		}
		System.out.println("\n");
	}
	
	/**
	 * Prints the specified Cell.
	 * @param cell - The Cell to be printed.
	 */
	public void printCellValue(Cell cell) {
		switch (cell.getCellType()) {
		case NUMERIC: 
			System.out.print(cell.getNumericCellValue() + "\t");
			break;
		case STRING:
			System.out.print(cell.getStringCellValue() + "\t");
			break;
		case BOOLEAN:
			System.out.print(cell.getBooleanCellValue() + "\t");
			break;
		default:
			break;
		}
	}
	
	//Antes eram dois métodos 
	/**
	 * Reads all file and returns the number of occurrences that checks the specified 
	 * values with values of 9th and 10th/11th columns respectively 
	 * @param longMethod - Value to compare with 9th column (Long_Method)
	 * @param tool - Value to compare with 10th or 11th column (PMI/iPlasma)
	 * @return number of occurrences that checks the specified 
	 * values with values of 9th and 10th/11th columns respectively 
	 */
	
	/**
	 * Reads the specified row and compare the boolean value of the 9th column with the 
	 * specified value of obj_long_method; and compare the boolean value of 10th or 11th 
	 * column with the specified value of tool. If the all boolean values are equals 
	 * returns 1, otherwise returns 0.
	 * @param row - The Row to be read.
	 * @param longMethod - Value to compare with 9th column (Long_Method)
	 * @param tool - Value to compare with 10th or 11th column (PMI/iPlasma)
	 * @return 1 checks the condition, otherwise 0.
	 */
	public int getLongMethod(boolean longMethod, boolean tool) {				
		boolean long_method = false;
		boolean iPlasma = false;
		boolean pmd = false;
		
		int num = 0;
		
		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext()) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == 9 && cell.getCellType() == CellType.BOOLEAN)
					long_method = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == 10 && cell.getCellType() == CellType.BOOLEAN)
					iPlasma = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == 11 && cell.getCellType() == CellType.BOOLEAN)
					pmd = cell.getBooleanCellValue();
			}
			if(long_method == longMethod && (iPlasma == tool || pmd == tool))
				num++;
		}
		return num;	
	}
	
	//FOR TEST
	public static void main(String[] args) throws IOException {
		FileReader e = new FileReader();
		//Prints all file test
		//e.printAllFile();
		
		//Read file and get occurrences test
		//boolean a = true;
		//boolean b = true;
		//e.getLongMethod(a, b);
		// Row r = null;
		//e.printRowValues(r);   
		
	//FOR TEST ANTÓNIO
		DCI dci = new DCI(e);
		dci.DCIdefects();
		System.out.println(dci);
	}
}
