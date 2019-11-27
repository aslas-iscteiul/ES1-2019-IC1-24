package app_structure;

public class DII extends Counter {

	private int defectNr;
	
	public DII() {
		this.defectNr = 0;
	}
 
	public int getDefectNr() {
		return defectNr;
	}
	
	//SEPARAR AS FERRAMENTAS DA REGRA DO UTILIZADOR
			public void increment (boolean long_method, boolean iPlasma, boolean pmd) {
				if(long_method == false && (iPlasma == true || pmd == true))
					this.defectNr++;
			}
			
			//COMPARAR OS 3 EM SIMULTANEO
			public void increment (boolean long_method, boolean iPlasma, boolean pmd, boolean user_rule) {
				if(long_method == false && (iPlasma == true || pmd == true || user_rule == true ))
					this.defectNr++;
			}
			
			//Futuro
			public void increment(boolean feature_envy, boolean user_rule) {
				if(feature_envy == false && user_rule == true)
					this.defectNr++;
			}
	
}
