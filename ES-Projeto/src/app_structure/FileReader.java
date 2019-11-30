package app_structure;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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

	private CountersSystem counters;

	/**
	 * Creates a FileReader instance for the file 'Long-Method.xlsx'.
	 * @throws IOException if an I/O error occurs. 
	 */
	public FileReader() throws IOException {
		this.file = new FileInputStream(PATH); 
		this.workbook = new XSSFWorkbook(file);
		this.sheet = workbook.getSheet(TITLE);

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
	 * Prints the specified Cell based on cell type.
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
	 * Reads all 'Long-Method.xlsx' file by each row and each cell, and increment the counters based 
	 * on the values of iPlasma tool column (9th) and is_long_method column (8th).
	 * Counters are reset to eliminate previously obtained results.
	 * The first line of the excel file is ignored as it represents the column headings.
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

	/**
	 * Reads all 'Long-Method.xlsx' file by each row and cell, and increment the counters based 
	 * on the values of PMD tool column (10th) and is_long_method column (8th).
	 * Counters are reset to eliminate previously obtained results.
	 * The first line of the excel file is ignored as it represents the column headings.
	 */
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

	/***
	 * Returns true if the result condition is true. In other words, returns true if ???? 
	 * @param rule - String rule with values and operators to compare
	 * @param value1 - First value to compare (LOC or AFTD).
	 * @param value2 - Second value to compare (CYCLO or LAA).
	 * @return true if ????? não sei como explicar bahhh
	 */
	private boolean isDefect(String rule, int value1, double value2) {
		String[] args = rule.split(";");

		boolean left_condition = RelationalOperator.parseOperator(args[1]).apply(value1, Integer.parseInt(args[2]));
		boolean right_condition = RelationalOperator.parseOperator(args[5]).apply(value2, Double.parseDouble(args[6]));
		boolean result = LogicOperator.parseOperator(args[3]).apply(left_condition, right_condition);

		return result;
	}

	/**
	 * Reads all 'Long-Method.xlsx' file for each row and cell, and get the LOC (Number of Code Lines) and CYCLO (Cyclomatic Complexity) 
	 * metric values from each method (from each row). It is evaluated for each method if it has long method defect. Subsequently, the values 
	 * obtained are compared with the values of the is_long_method column (8th) and the counters are incremented according to the values obtained. 
	 * Counters are reset to eliminate previously obtained results.
	 * The first line of the excel file is ignored as it represents the column headings.
	 * Method IDs that have the long method defect are stored in an integer list and then returned.
	 * @param rule - String rule that specifies which methods have the long method defect, using the LOC and CYCLO metrics.
	 * @return the list of integers that represent the method IDs with the long method defect, according to the specific rule.
	 */
	public List<Integer> ruleLongMethodDefects(String rule) {	
		this.counters.restart();
		boolean long_method = false;
		int loc = 0;
		int cyclo = 0;
		List<Integer> ids = new ArrayList<Integer>();

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext() && row.getRowNum() != 0) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == IS_LONG_METHOD && cell.getCellType() == CellType.BOOLEAN)
					long_method = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == LOC && cell.getCellType() == CellType.NUMERIC)
					loc = (int) cell.getNumericCellValue();
				if(cell.getColumnIndex() == CYCLO && cell.getCellType() == CellType.NUMERIC)
					cyclo = (int) cell.getNumericCellValue();
			}
			boolean ruleResult = isDefect(rule, loc, cyclo);
			if(row.getRowNum() != 0)
				this.counters.increment(long_method, ruleResult);
			if(ruleResult) 						
				ids.add(row.getRowNum());							//Lista de ids para enviar para a GUI
		}
		return ids;
	}

	
	
	/**
	 * Reads all 'Long-Method.xlsx' file for each row and cell, and get the ATFD (method accesses to methods of other classes) and LAA (method accesses to attributes of the class itself) 
	 * metric values from each method (from each row). It is evaluated for each method if it has long method defect. Subsequently, the values 
	 * obtained are compared with the values of the is_feature_envy column (11st) and the counters are incremented according to the values obtained. 
	 * Counters are reset to eliminate previously obtained results.
	 * The first line of the excel file is ignored as it represents the column headings.
	 * Method IDs that have the long method defect are stored in an integer list and then returned.
	 * @param rule - String rule that specifies which methods have the long method defect, using the ATFD and LAA metrics.
	 * @return the list of integers that represent the method IDs with the long method defect, according to the specific rule.
	 */
	public List<Integer> ruleFeatureEnvyDefects(String rule) {	
		this.counters.restart();
		boolean feature_envy = false;
		int atfd = 0;
		int laa = 0;
		List<Integer> ids = new ArrayList<Integer>();

		Iterator<Row> rowIterator = sheet.iterator();
		while (rowIterator.hasNext()) {
			Row row = rowIterator.next();
			Iterator<Cell> cellIterator = row.cellIterator();
			while (cellIterator.hasNext() && row.getRowNum() != 0) {
				Cell cell = cellIterator.next();
				if(cell.getColumnIndex() == IS_FEATURE_ENVY && cell.getCellType() == CellType.BOOLEAN)
					feature_envy = cell.getBooleanCellValue();
				if(cell.getColumnIndex() == ATFD && cell.getCellType() == CellType.NUMERIC)
					atfd = (int) cell.getNumericCellValue();
				if(cell.getColumnIndex() == LAA && cell.getCellType() == CellType.NUMERIC)
					laa = (int) cell.getNumericCellValue();
			}
			boolean ruleResult = isDefect(rule, atfd, laa);
			if(row.getRowNum() != 0)
				this.counters.increment(feature_envy, ruleResult);
			if(ruleResult) 						
				ids.add(row.getRowNum());							//Lista de ids para enviar para a GUI
		}
		return ids;
	}
	
	
	
	
	//FOR TEST
	public static void main(String[] args) throws IOException {
		FileReader e = new FileReader();

		//Prints all file test
		//e.printAllFile();

		// Test for iPlasma tool
		//Correct answer: DCI = 140; DII = 0; ADCI = 280; ADII = 0;  
		e.iPlasmaLongMethodDefects();
		System.out.println(e.counters.toString());
				
		//Incomplete
		e.pmdLongMethodDefects();
		System.out.println(e.counters.toString());

		//Incomplete
		String test = "LOC;>;200;AND;CYCLO;>;100";
		e.ruleLongMethodDefects(test);
		System.out.println(e.counters.toString());
		
		//Incomplete
		String test2 = "ATFD;>;50;AND;LAA;>;0";
		e.ruleFeatureEnvyDefects(test2);
		System.out.println(e.counters.toString());

	}
}

