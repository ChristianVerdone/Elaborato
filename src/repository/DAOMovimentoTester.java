package repository;

import java.util.ArrayList;

import struttureEventi.classes.Movimento;

public class DAOMovimentoTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		ArrayList<Movimento> movimenti = df.getDAOMovimento().doRetrieveAll();

		if (!movimenti.isEmpty())
			for (Movimento m : movimenti)
				System.out.println(m.toString());
	}
}