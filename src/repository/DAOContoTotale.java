package repository;

import java.util.HashMap;

import contabilità.Cliente;
import contabilità.ContoTotale;
import struttureEventi.classes.Abitazione;

public interface DAOContoTotale {
	public double doRetrieveContoAbitazioneByCf(String cf);
	public double doRetrieveContoStrutturaCf(String cf);
	public double doRetrieveContoRistoranteCf(String cf);
	public double doRetrieveContoEventoByCf(String cf);
	public int updateContiTotali(ContoTotale conto);
}
