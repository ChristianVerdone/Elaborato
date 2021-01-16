package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneAbitazione;

public interface DAOPrenotazioneAbitazione {
	public HashSet<PrenotazioneAbitazione> doRetrieveAll();
	public PrenotazioneAbitazione doRetrieveById(String id);
	public void delete(String id);
	public void deleteByCliente(String cf);
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa);
}
