package repository;

import java.util.HashMap;

import struttureEventi.classes.StrutturaVillaggio;


public interface DAOStrutturraVillaggio {
	public HashMap<String, StrutturaVillaggio> doRetrieveAll();
	public StrutturaVillaggio doRetrieveById(String id);
	public void delete(String id);
	public int updateStruttura(StrutturaVillaggio s);
}
