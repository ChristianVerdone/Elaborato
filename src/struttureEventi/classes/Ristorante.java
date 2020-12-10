package struttureEventi.classes;

import java.util.HashMap;

public class Ristorante {

	public Ristorante(HashMap<Integer, Tavolo> tavoli) {
		super();
		this.tavoli = tavoli;
	}

	public HashMap<Integer, Tavolo> getTavoli() {
		return tavoli;
	}

	public void setTavoli(HashMap<Integer, Tavolo> tavoli) {
		this.tavoli = tavoli;
	}

	private HashMap<Integer,Tavolo> tavoli;
}
