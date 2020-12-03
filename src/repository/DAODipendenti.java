package repository;

import java.util.HashSet;

import personale.model.Dipendente;

public interface DAODipendenti {
	
	public HashSet<Dipendente> doRetrieveAll();
	public Dipendente doRetrieveByCf(String cf);
	public int delete(String cf);
	public int update(Dipendente dip);
}
