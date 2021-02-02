package repository;

import java.time.LocalDate;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneAbitazione;

public interface DAOPrenotazioneAbitazione {
	public HashSet<PrenotazioneAbitazione> doRetrieveAll();
	public PrenotazioneAbitazione doRetrieveById(String id);
	public void delete(String id);
	public void deleteByCliente(String cf);
	public int updatePrenotazioneAbitazione(PrenotazioneAbitazione pa);
	PrenotazioneAbitazione doRetrivePrenotazioneValidaCliente(String cf);
	boolean isPrenotazioneGenericaPossibile(String cf, LocalDate dataEvento);
}
