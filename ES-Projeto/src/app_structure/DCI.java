package app_structure;

public class DCI extends Counter{

	private int defectNr;
	
	public DCI() {
		this.defectNr = 0;
	}
	
	public int getDefectNr() {
		return defectNr;
	}
	
	//SEPARAR AS FERRAMENTAS DA REGRA DO UTILIZADOR
	public void increment (boolean long_method, boolean tool) {
		if(long_method == true && tool == true)
				this.defectNr++;
	}
		
	//COMPARAR OS 3 EM SIMULTANEO
	public void increment (boolean long_method, boolean iPlasma, boolean pmd, boolean user_rule) {
		if(long_method == true && (iPlasma == true || pmd == true || user_rule == true ))
			this.defectNr++;
		}
		
	//Futuro
	//public void increment(boolean feature_envy, boolean user_rule) {
	//	if(feature_envy == true && user_rule == true)
	//		this.defectNr++;
	//}

	
}
