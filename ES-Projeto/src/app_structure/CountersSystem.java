
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
	
	/**
	 * Create a CountersSistem where counters start with a value for 0.
	 */
	public CountersSystem() {
		this.dci = 0;
		this.dii = 0;
		this.adci = 0;
		this.adii = 0;
	}
	
	/**
	 * Is a getter.
	 * @return dci - value of "Defeitos Corrretamente Identificados"(DCI).
	 */
	public int getDCI() {
		return dci;
	}
	
	/**
	 * Is a getter.
	 * @return dii - value of "Defeitos Incorretamente identificados"(DII).
	 */
	public int getDII() {
		return dii;
	}
	
	/**Is a getter.
	 * @return adci - value of "Ausência de Defeitos Corretamente Identificados"(ADCI).
	 */
	public int getADCI() {
		return this.adci;
	}
	
	/**
	 * Is a getter.
	 * @return adii - value of "Aunsência de Defeitos Incorretamente Identificados"(ADII).
	 */
	public int getADII() {
		return adii;
	}
	
	/**
	 * Through the various characteristics of each indicator, we account for the existence of each indicator and count the number of times that they occur.
	 * @param long_method - boolean value taken the excel archive of the is_long_method column (8th) or the is_feature_envy column (11st).  
	 * @param value - boolean value taken the excel archive of the ClientRule, PMD, IPlasma. 
	 */
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
	
	/**
	 * set the counters to 0.
	 */
	public void restart() {
		this.dci = 0;
		this.dii = 0;
		this.adci = 0;
		this.adii = 0;
	}

	/**
	 * Print counters values - (DCI), (DII), (ADCI) e (ADII).
	 */
	@Override
	public String toString() {
		return "DCI = " + this.dci + "\nDII = " + this.dii + "\nADCI = " + this.adci + "\nADII = " + this.adii;
	}
}
