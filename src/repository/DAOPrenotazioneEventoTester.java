package repository;

import java.util.HashMap;
import java.util.HashSet;

import struttureEventi.classes.Evento;
import struttureEventi.classes.PrenotazioneEvento;

public class DAOPrenotazioneEventoTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashSet<PrenotazioneEvento> prenotazioneEventi = df.getDAOPrenotazioneEvento().doRetrieveAll();

		if (!prenotazioneEventi.isEmpty())
			for (PrenotazioneEvento pe : prenotazioneEventi)
				System.out.println(pe.toString());


	}
}
