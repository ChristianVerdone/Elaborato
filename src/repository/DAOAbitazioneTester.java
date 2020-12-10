package repository;

import java.util.HashMap;
import struttureEventi.classes.Abitazione;

public class DAOAbitazioneTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,Abitazione> abitazioni = df.getDAOAbitazione().doRetrieveAll();

		if (!abitazioni.isEmpty())
			for (Abitazione a : abitazioni.values())
				System.out.println(a.toString());
	}
}