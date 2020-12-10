package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneSv;

public class DAOPrenotazioneStrutturaTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashSet<PrenotazioneSv> ps = df.getDAOPrenotazioneStruttura().doRetrieveAll();

		if (!ps.isEmpty())
			for (PrenotazioneSv p : ps)
				System.out.println(p.toString());
	}
}