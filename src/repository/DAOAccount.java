package repository;

import java.util.HashSet;
import personale.model.Account;

public interface DAOAccount{
	
	public HashSet<Account> doRetrieveAll();
	public Account doRetrieveByUsername(String username);
	public int delete(String tipo);
	public int update(String cf, Account account);
}
