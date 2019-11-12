package app_structure;

public class ADCI extends Counter{

	private int defectNr;
	
	public ADCI(FileReader fr) {
		super(fr);
	}
	
	public void increment(boolean tool) {
		this.defectNr = super.increments(false, false);
	}
	
	public int ADCIdefect() {
		return this.defectNr;
	}
	
}
