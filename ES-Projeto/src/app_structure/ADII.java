package app_structure;

public class ADII extends Counter{
 
	private int defectNr;
	
	public ADII() {
		this.defectNr = 0;
	}
	
	public int getDefectNr() {
		return defectNr;
	}
		
	//SEPARAR AS FERRAMENTAS DA REGRA DO UTILIZADOR
	public void increment (boolean long_method, boolean tool) {
		if(long_method == false && tool == true)
				this.defectNr++;
	}
	
	//COMPARAR OS 3 EM SIMULTANEO
	public void increment (boolean long_method, boolean iPlasma, boolean pmd, boolean user_rule) {
		if(long_method == true && (iPlasma == false || pmd == false || user_rule == false ))
			this.defectNr++;
	}
	
	
}
