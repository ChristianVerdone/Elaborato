package repository;

import java.util.HashMap;

import struttureEventi.classes.StrutturaVillaggio;

public class DAOStrutturaVillaggioTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,StrutturaVillaggio> strutture = df.getDAOStrutturaVillaggio().doRetrieveAll();

		if (!strutture.isEmpty())
			for (StrutturaVillaggio s : strutture.values())
				System.out.println(s.toString());
	}
}