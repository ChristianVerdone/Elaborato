package repository;

import java.util.HashMap;

import struttureEventi.classes.Biglietto;

public class DAOBigliettoTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,Biglietto> biglietti = df.getDAOBiglietto().doRetrieveAll();

		if (!biglietti.isEmpty())
			for (Biglietto b : biglietti.values())
				System.out.println(b.toString());

	
	}
}