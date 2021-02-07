package repository;

import java.util.HashMap;

import contabilita.ContoTotale;
import struttureEventi.classes.ContoRistorante;

public interface DAOContoRistorante {
	public HashMap<String, ContoRistorante> doRetrieveAll();
	public ContoRistorante doRetrieveById(String id);
	public void delete(String id);
	public int updateContoRistorante(ContoRistorante cr);
}
