package app_structure;

public abstract class Counter {
 
	private FileReader fileReader;
	
	public Counter(FileReader fr) {
		this.fileReader = fr;
	}

	public void setFileReader(FileReader fr) {
		this.fileReader = fr;
	}
	
	public FileReader getFileReader() {
		return this.fileReader;
	}
	
}
