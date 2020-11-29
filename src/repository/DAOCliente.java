package repository;

import java.util.HashMap;


import contabilità.Cliente;


public interface DAOCliente {
	public HashMap<String, Cliente> doRetrieveAll();
	public Cliente doRetrieveByCf(String cf);
	public void delete(String cf);
	public int updateCliente(Cliente c);

}
