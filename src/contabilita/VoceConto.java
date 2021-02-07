package contabilita;

public class VoceConto {
	
	public VoceConto(String voce, double importo) {
		this.voce = voce;
		this.importo = importo;
	}
	
	@Override
	public String toString() {
		return  voce + ", " + importo ;
	}
	public String getVoce() {
		return voce;
	}
	public double getImporto() {
		return importo;
	}
	private String voce;
	private double importo;
}
