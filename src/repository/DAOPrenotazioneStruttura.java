package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneRistorante;
import struttureEventi.classes.PrenotazioneSv;

public interface DAOPrenotazioneStruttura {
	public HashSet<PrenotazioneSv> doRetrieveAll();
	public PrenotazioneSv doRetrieveById(String id);
	public void delete(String id);
	public int updatePrenotazioneStruttura(PrenotazioneSv pr);
}
