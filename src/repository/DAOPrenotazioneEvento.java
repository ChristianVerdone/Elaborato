package repository;

import java.util.HashSet;

import struttureEventi.classes.PrenotazioneEvento;


public interface DAOPrenotazioneEvento {
	public HashSet<PrenotazioneEvento> doRetrieveAll();
	public PrenotazioneEvento doRetrieveById(String id);
	public void delete(String id);
	public void deleteByCliente(String cf);
	public int updatePrenotazioneEvento(PrenotazioneEvento pa);
}
