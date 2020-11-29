package struttureEventi.classes;

public class Tavolo {

	public Tavolo(int nTavolo, int posti) {

		this.nTavolo = nTavolo;
		this.posti = posti;
	}
	
	public int getnTavolo() {
		return nTavolo;
	}
	public void setnTavolo(int nTavolo) {
		this.nTavolo = nTavolo;
	}
	public int getPosti() {
		return posti;
	}
	public void setPosti(int posti) {
		this.posti = posti;
	}

	private int nTavolo;
	private int posti;
}
