package repository;

import java.util.HashMap;

import struttureEventi.classes.Evento;

public interface DAOEvento {
	public HashMap<String, Evento> doRetrieveAll();
	public Evento doRetrieveById(String evento);
	public void delete(String evento);
	public int updateEvento(Evento c);
}
