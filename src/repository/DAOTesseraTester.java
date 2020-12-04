package repository;

import java.util.HashMap;

import struttureEventi.classes.Tessera;

public class DAOTesseraTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,Tessera> tessere = df.getDAOTessera().doRetrieveAll();

		if (!tessere.isEmpty())
			for (Tessera t : tessere.values())
				System.out.println(t.toString());


	}
}
