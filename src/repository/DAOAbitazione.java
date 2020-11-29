package repository;

import java.util.HashMap;

import struttureEventi.classes.Abitazione;

public interface DAOAbitazione {
	public HashMap<String, Abitazione> doRetrieveAll();
	public Abitazione doRetrieveById(String id);
	public void delete(String id);
}
