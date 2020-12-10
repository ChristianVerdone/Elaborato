package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneAbitazione;

public class DAOPrenotazioneAbitazioneTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashSet<PrenotazioneAbitazione> pa = df.getDAOPrenotazioneAbitazione().doRetrieveAll();
		if (!pa.isEmpty())
			for (PrenotazioneAbitazione p : pa)
				System.out.println(p.toString());
	}
}