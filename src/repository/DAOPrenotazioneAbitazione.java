package repository;

import java.util.HashMap;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneAbitazione;

public interface DAOPrenotazioneAbitazione {
	public HashSet<PrenotazioneAbitazione> doRetrieveAll();
	public PrenotazioneAbitazione doRetrieveById(String id);
	public void delete(String id);
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa);
}
