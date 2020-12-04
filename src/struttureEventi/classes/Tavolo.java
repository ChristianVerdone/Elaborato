package struttureEventi.classes;

public class Tavolo {

	public Tavolo(int nTavolo, int posti) {

		this.nTavolo = nTavolo;
		this.posti = posti;
	}
	
	public int getnTavolo() {
		return nTavolo;
	}
	
	public int getPosti() {
		return posti;
	}
	
	
	@Override
	public String toString() {
		return "Tavolo [nTavolo=" + nTavolo + ", posti=" + posti + "]";
	}


	private int nTavolo;
	private int posti;
}
