package repository;

import java.util.HashMap;

import struttureEventi.classes.Biglietto;


public interface DAOBiglietto {
	public HashMap<String, Biglietto> doRetrieveAll();
	public Biglietto doRetrieveById(String id);
	public void delete(String id);
	public int updateBiglietto(Biglietto b);
	
}
