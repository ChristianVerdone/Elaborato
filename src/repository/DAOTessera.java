package repository;

import java.util.HashMap;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneEvento;
import struttureEventi.classes.Tessera;

public interface DAOTessera {
	public HashMap<String, Tessera> doRetrieveAll();
	public Tessera doRetrieveById(String id);
	public void delete(String id);
	public int updateTessera(Tessera t);
}