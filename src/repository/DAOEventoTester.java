package repository;

import java.util.HashMap;

import struttureEventi.classes.Evento;

public class DAOEventoTester {

	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,Evento> eventi = df.getDAOEvento().doRetrieveAll();

		if (!eventi.isEmpty())
			for (Evento e : eventi.values())
				System.out.println(e.toString());
	}
}