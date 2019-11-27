/**
 * 
 */
package app_structure;

/**
 * @author ana0s
 *
 */
public class CountersSystem {
	
	int dci;
	int dii;
	int adci;
	int adii;
	
	public CountersSystem() {
		this.dci = 0;
		this.dii = 0;
		this.adci = 0;
		this.adii = 0;
	}
	
	public int getDCI() {
		return dci;
	}
	
	public int getDII() {
		return dii;
	}
		
	public int getADCI() {
		return this.adci;
	}
	
	public int getADII() {
		return adii;
	}
	
	public void increment(boolean long_method, boolean value) {
		if(long_method == true && value == true)
			this.dci++;
		
		if(long_method == false && value == true)
			this.dii++;
		
		if(long_method == false && value == false)
			this.adci++;
		
		if(long_method == true && value == false)
			this.adii++;
	}
	
	public void restart() {
		this.dci = 0;
		this.dii = 0;
		this.adci = 0;
		this.adii = 0;
	}

	@Override
	public String toString() {
		return "DCI = " + this.dci + "\nDII = " + this.dii + "\nADCI = " + this.adci + "\nADII = " + this.adii;
	}
}
