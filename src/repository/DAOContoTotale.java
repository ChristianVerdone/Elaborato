package repository;


import contabilita.ContoTotale;


public interface DAOContoTotale {
	public double doRetrieveContoAbitazioneByCf(String cf);
	public double doRetrieveContoStrutturaCf(String cf);
	public double doRetrieveContoRistoranteCf(String cf);
	public double doRetrieveContoEventoByCf(String cf);
	public ContoTotale doRetrieveContoTotaleById(String id);
	public int updateContiTotali(ContoTotale conto);
	public boolean isCalcoloContoPossibile(String cf);
	public double getContoTotale(String codCliente);
}
