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

	public int increments(boolean long_method, boolean tool) {
		//return this.fileReader.getLongMethod(long_method, tool);
		return 0;
	}
}
