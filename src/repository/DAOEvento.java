package repository;

import java.util.HashMap;

import contabilita.Cliente;
import struttureEventi.classes.Evento;

public interface DAOEvento {
	public HashMap<String, Evento> doRetrieveAll();
	public HashMap<String, Evento> doRetrieveByCliente(Cliente cl);
	public Evento doRetrieveById(String evento);
	public Evento doRetrieveByNome(String evento);
	public void delete(String evento);
	public int updateEvento(Evento c);
}
