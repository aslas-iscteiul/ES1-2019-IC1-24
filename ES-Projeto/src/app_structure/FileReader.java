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

/**
 * The FileReader class is used by the Application class to access the excel file "Long-Method.xlsx", 
 * allowing it to be read and evaluated by all methods described in the file, according to specific 
 * tools and/or rules defined. This valuation is accounted for as a system of counters.
 * 
 * @author ES1-2019-IC1-24
 */
public class FileReader {

	private static final String PATH = "Long-Method.xlsx";
	private static final String TITLE = "Long-Method";

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
	 * Returns the high level representation of the Excel "Long-Method.xlsx" worksheet. 
	 * @return the representation of the Excel "Long-Method.xlsx" worksheet.
	 */
	public Sheet getSheet() {
		return sheet;
	}

	/**
	 * Returns the file reader counters system.
	 * @return the file reader counters system.
	 */
	public CountersSystem getCounterSystem() {
		return this.counters;
	}

	// Initial tests for reading and obtaining excel file values.
	
	//	/**
	//	 * Prints all file.
	//	 */
	//	public void printAllFile() {
	//		Iterator<Row> rowIterator = sheet.iterator();
	//		while (rowIterator.hasNext()) {
	//			Row row = rowIterator.next();
	//			printRowValues(row);
	//		}
	//	}
	//
	//	/**
	//	 * Prints the specified Row.
	//	 * @param row - The Row to be printed.
	//	 */
	//	public void printRowValues(Row row) {
	//		Iterator<Cell> cellIterator = row.cellIterator();
	//		while (cellIterator.hasNext()) {
	//			Cell cell = cellIterator.next();
	//			printCellValue(cell);
	//		}
	//		System.out.println("\n");
	//	}
	//
	//	/**
	//	 * Prints the specified Cell based on cell type.
	//	 * @param cell - The Cell to be printed.
	//	 */
	//	public void printCellValue(Cell cell) {
	//		switch (cell.getCellType()) {
	//		case NUMERIC: 
	//			System.out.print(cell.getNumericCellValue() + "\t");
	//			break;
	//		case STRING:
	//			System.out.print(cell.getStringCellValue() + "\t");
	//			break;
	//		case BOOLEAN:
	//			System.out.print(cell.getBooleanCellValue() + "\t");
	//			break;
	//		default:
	//			break;
	//		}
	//	}

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
	 * Returns true if the specified values (value1 and value2) indicate a defect according to the specified rule.
	 * @param rule - String rule with values and operators to compare.
	 * @param value1 - First value to compare (can be LOC or AFTD metric).
	 * @param value2 - Second value to compare (can be CYCLO or LAA metric).
	 * @return true if the specified values indicate a defect according to the rule.
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
			boolean ruleResult =  isDefect(rule, loc, cyclo);
			if(row.getRowNum() != 0)
				this.counters.increment(long_method, ruleResult);
			if(ruleResult && row.getRowNum() != 0) 						
				ids.add(row.getRowNum()+1);							//+1 because getRowNum is 0 based
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
		double laa = 0;
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
				if(cell.getColumnIndex() == LAA && cell.getCellType() == CellType.STRING){
					laa = (double) Double.parseDouble(cell.getStringCellValue());
				}
			}
			boolean ruleResult = isDefect(rule, atfd, laa);
			if(row.getRowNum() != 0)
				this.counters.increment(feature_envy, ruleResult);
			if(ruleResult && row.getRowNum() != 0) 						
				ids.add(row.getRowNum()+1);							//+1 because getRowNum is 0 based
		}
		return ids;
	}

	/**
	 * 	Creates an object array with the number of cells of the given row, proceeding to printing all the values of that row
	 * 	into the array as a String in the original order. This is done while there are more cells on the row. The return is the 
	 * 	array created with the row values in their original order.	
	 * 
	 * 	@param row - row that is to be converted into an object array 
	 */
	public Object[] printRowValue(Row row) {
		Object[] rowValues = new String[row.getLastCellNum()];
		Iterator<Cell> cellIterator = row.cellIterator();
		int i=0;
		while (cellIterator.hasNext()) {
			Cell cell = cellIterator.next();
			rowValues[i] = cell.toString();
			i++;
		}
		return rowValues;
	}
}