package app_structure;

public class DII extends Counter {

	private int defectNr;
	
	public DII(FileReader fr) {
		super(fr);
	}
 
	public int increment() {
		this.defectNr = super.increments(false, true);
		return this.defectNr;
	}
}
