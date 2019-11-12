package app_structure;

public class ADII extends Counter{
 
	private int defectNr;
	
	public ADII(FileReader fr) {
		super(fr);
	}

	public void increment(boolean tool) {
		this.defectNr = super.getFileReader().getLongMethod(true, false);	
	}
	
	public int ADIIdefects() {
		return this.defectNr;
	}
	
}
