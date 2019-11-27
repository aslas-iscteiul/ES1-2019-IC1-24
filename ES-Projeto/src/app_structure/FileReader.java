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
	
	private static final int ID = 0;
	private static final int LOC = 4;
	private static final int CYCLO = 5;
	private static final int ATFD = 6;
	private static final int LAA = 7;
	private static final int IS_LONG_METHOD = 8;
	private static final int IPLASMA = 9;
	private static final int PMD = 10;
	private static final int IS_FEATURE_ENVY = 11;

	private FileInputStream file;
	private Workbook workbook;
	private Sheet sheet;
	
	private DCI dci;
	private DII dii;
	private ADCI adci;
	private ADII adii;
	
	private CountersSystem counters;

	/**
	 * Creates a FileReader instance for the file 'Long-Method.xlsx'.
	 * @throws IOException if an I/O error occurs. 
	 */
	public FileReader() throws IOException {
		this.file = new FileInputStream(PATH); 
		this.workbook = new XSSFWorkbook(file);
		this.sheet = workbook.getSheet(TITLE);
		
		this.dci = new DCI();
		this.dii = new DII();
		this.adci = new ADCI();
		this.adii = new ADII();
		
		this.counters = new CountersSystem();
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
	
	/**
	 * Reads all 'Long-Method.xlsx' file and increment each of counters based on 
	 * 
	 */
	public void iPlasmaLongMethodDefects() {	
		this.counters.restart();
		boolean long_method = false;
		boolean iPlasma = false;															
		Iterator<Row> rowIterator = sheet.iterator();
	
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext() && row.getRowNum() != 0) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == IS_LONG_METHOD && cell.getCellType() == CellType.BOOLEAN)
					long_method = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == IPLASMA && cell.getCellType() == CellType.BOOLEAN)
					iPlasma = cell.getBooleanCellValue();
			}
			if(row.getRowNum() != 0 ) {
				this.counters.increment(long_method, iPlasma);
			}
		}
	}
	
	public void pmdLongMethodDefects() {		
		this.counters.restart();
		boolean long_method = false;
		boolean pmd = false;															
		Iterator<Row> rowIterator = sheet.iterator();
	
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext() && row.getRowNum() != 0) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == IS_LONG_METHOD && cell.getCellType() == CellType.BOOLEAN)
					long_method = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == PMD && cell.getCellType() == CellType.BOOLEAN)
					pmd = cell.getBooleanCellValue();
			}
			if(row.getRowNum() != 0 ) {
				this.counters.increment(long_method, pmd);
			}
		}
	}
	
	//FOR TEST
	public static void main(String[] args) throws IOException {
		FileReader e = new FileReader();
		
		//Prints all file test
		//e.printAllFile();
		
		// Test for verifyLongMethodDefects
//		e.iPlasmaLongMethodDefects();
//		System.out.println("Total dci= " + e.dci.getDefectNr() );		
//		System.out.println("Total dii= " + e.dii.getDefectNr() );
//		System.out.println("Total adci= " + e.adci.getDefectNr() );
//		System.out.println("Total adii= " + e.adii.getDefectNr() );
		
		// test iPlasma
		e.iPlasmaLongMethodDefects();
		System.out.println(e.counters.toString());
	}
}

