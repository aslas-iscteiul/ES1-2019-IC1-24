package app_structure;

public class ADCI extends Counter{

	private int defectNr;
	
	public ADCI() {
		this.defectNr = 0;
	}
	
	public int getDefectNr() {
		return defectNr;
	}
	
	//SEPARAR AS FERRAMENTAS DA REGRA DO UTILIZADOR
	public void increment (boolean long_method, boolean tool) {
		if(long_method == false && tool == false)
				this.defectNr++;
	}
	
	//COMPARAR OS 3 EM SIMULTANEO
	public void increment (boolean long_method, boolean iPlasma, boolean pmd, boolean user_rule) {
		if(long_method == false && (iPlasma == false || pmd == false || user_rule == false ))
			this.defectNr++;
	}
	
	
	
}
