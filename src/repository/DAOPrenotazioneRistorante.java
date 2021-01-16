package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneRistorante;

public interface DAOPrenotazioneRistorante {
	public HashSet<PrenotazioneRistorante> doRetrieveAll();
	public PrenotazioneRistorante doRetrieveById(String id);
	public void delete(String id);
	public void deleteByCliente(String cf);
	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr);
}
