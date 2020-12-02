package repository;

import java.util.HashMap;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneEvento;
import struttureEventi.classes.Tavolo;

public interface DAOTavoloRistorante {
	public HashMap<Integer, Tavolo> doRetrieveAll();
	public Tavolo doRetrieveByNTavolo(int n);
	public void delete(int n);
	public int updateTavolo(Tavolo t);
}
