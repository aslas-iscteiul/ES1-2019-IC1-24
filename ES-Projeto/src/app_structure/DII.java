package app_structure;

public class DII extends Counter {

	private int defectNr;
	
	public DII(FileReader fr) {
		super(fr);
	}
 
	public void increment() {
		this.defectNr = super.increments(false, true);	
	}
	
	public int DIIdefects() {
		return this.defectNr;
	}
}
