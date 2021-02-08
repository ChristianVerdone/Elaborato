package repository;

import java.util.ArrayList;

import struttureEventi.classes.PrenotazioneRistorante;

public class DAOPrenotazioneRistoranteTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		ArrayList<PrenotazioneRistorante> prenotazioneRistorante = df.getDAOPrenotazioneRistorante().doRetrieveAll();

		/**
		 * if (!prenotazioneRistorante.isEmpty()) for (PrenotazioneRistorante pr :
		 * prenotazioneRistorante) System.out.println(pr.toString());
		 * 
		 */
		PrenotazioneRistorante pr = df.getDAOPrenotazioneRistorante().doRetrieveById("PR002");
		System.out.println(pr.toString());
	}
}