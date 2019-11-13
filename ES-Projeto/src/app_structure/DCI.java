package app_structure;

public class DCI extends Counter{

	private int defectNr;
	
	public DCI(FileReader fr) {
		super(fr);
	}
	
	public void increment() {
		this.defectNr = super.increments(true, true);	
	}
	
	public int DCIdefects() {
		return this.defectNr;
	}
 
}
