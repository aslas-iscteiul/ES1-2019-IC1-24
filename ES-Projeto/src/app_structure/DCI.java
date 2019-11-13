package app_structure;

public class DCI extends Counter{

	private int defectNr;
	
	public DCI(FileReader fr) {
		super(fr);
	}
	
	public int increment() {
		this.defectNr = super.increments(true, true);	
		return this.defectNr;
	}
	
}
