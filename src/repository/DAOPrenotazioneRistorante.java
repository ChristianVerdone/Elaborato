package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneRistorante;

public interface DAOPrenotazioneRistorante {
	public HashSet<PrenotazioneRistorante> doRetrieveAll();
	public PrenotazioneRistorante doRetrieveById(String id);
	public void delete(String id);
	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr);
}
