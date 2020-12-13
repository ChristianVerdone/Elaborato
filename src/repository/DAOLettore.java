package repository;

import java.util.HashMap;

import struttureEventi.classes.Lettore;

public interface DAOLettore {
	public HashMap<String, Lettore> doRetrieveAll();
	public Lettore doRetrieveById(String lettore);
}