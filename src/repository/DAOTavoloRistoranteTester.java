package repository;

import java.util.HashMap;

import struttureEventi.classes.Tavolo;

public class DAOTavoloRistoranteTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<Integer, Tavolo> tavoli = df.getDAOTavoloRistorante().doRetrieveAll();

		if (!tavoli.isEmpty())
			for (Tavolo  t : tavoli.values())
				System.out.println(t.toString());
	}
}