package repository;


import contabilit�.ContoTotale;


public interface DAOContoTotale {
	public double doRetrieveContoAbitazioneByCf(String cf);
	public double doRetrieveContoStrutturaCf(String cf);
	public double doRetrieveContoRistoranteCf(String cf);
	public double doRetrieveContoEventoByCf(String cf);
	public int updateContiTotali(ContoTotale conto);
}
