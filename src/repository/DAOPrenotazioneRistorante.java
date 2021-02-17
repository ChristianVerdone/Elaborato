package repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;

import struttureEventi.classes.PrenotazioneRistorante;

public interface DAOPrenotazioneRistorante {
	public ArrayList<PrenotazioneRistorante> doRetrieveAll();

	public PrenotazioneRistorante doRetrieveById(String id);

	public void delete(String id);

	public void deleteByCliente(String cf);

	public int updatePrenotazioneRistorante(PrenotazioneRistorante pr);

	boolean isClientePrenotatoRistorante(String cf, LocalDate date, LocalTime time);

	HashSet<PrenotazioneRistorante> doRetrieveByCliente(String cf);

	ArrayList<PrenotazioneRistorante> doRetrievePrenotazioniNonRegistrate();

	int deletePrenotazioniNonRegistrate(String cf);
}