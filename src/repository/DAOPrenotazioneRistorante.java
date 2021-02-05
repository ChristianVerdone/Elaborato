package repository;

import java.util.ArrayList;

import struttureEventi.classes.PrenotazioneRistorante;

public interface DAOPrenotazioneRistorante {
	public ArrayList<PrenotazioneRistorante> doRetrieveAll();

	public PrenotazioneRistorante doRetrieveById(String id);

	public void delete(String id);

	public void deleteByCliente(String cf);

	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr);
}