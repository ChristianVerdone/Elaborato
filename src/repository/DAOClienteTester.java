package repository;

import java.util.HashMap;
import java.util.HashSet;

import contabilità.Cliente;


public class DAOClienteTester {
	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String,Cliente> clienti = df.getDAOCliente().doRetrieveAll();

		if (!clienti.isEmpty())
			for (Cliente c : clienti.values())
				System.out.println(c.toString());

		//df.getDAOCliente().delete("cnljsc98p60c525t");

	}
}
