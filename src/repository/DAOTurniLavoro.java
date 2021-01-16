package repository;

import java.util.HashMap;
import java.util.HashSet;

import personale.model.Servizio;
import personale.model.TurnoLavoro;

public interface DAOTurniLavoro {
	public HashSet<TurnoLavoro> doRetrieveAll();
	public HashMap<String, Servizio> doRetrieveAllServizi();
	public HashSet<TurnoLavoro> doRetrieveByCf(String cf);
	public HashSet<TurnoLavoro> doRetrieveByUsername(String cf);
	public int update(TurnoLavoro tl);
	public void delete(String id);
	boolean checkConflicts(TurnoLavoro tl);
}
