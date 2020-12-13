package repository;

import java.util.ArrayList;

import struttureEventi.classes.Movimento;

public interface DAOMovimento {
	public int updateMovimento(Movimento m);
	public ArrayList<Movimento> doRetrieveAll();
}
