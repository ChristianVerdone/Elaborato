package personale.model;

import java.time.LocalTime;

public class Servizio {
	
	public Servizio(String id, String descrizione, LocalTime inizio, LocalTime fine) {
		this.id = id;
		this.descrizione = descrizione;
		this.inizio = inizio;
		this.fine = fine;
	}
	
	/* Getters and Setters*/
	public String getId() { return id; }
	public String getDescrizione() { return descrizione; }
	public LocalTime getInizio() { return inizio; }
	public LocalTime getFine() { return fine; }
	
	public void setId(String id) { this.id = id; }	
	public void setDescrizione(String descrizione) { this.descrizione = descrizione; }	
	public void setInizio(LocalTime inizio) { this.inizio = inizio; }
	public void setFine(LocalTime fine) { this.fine = fine; }
	
	private String id;
	private String descrizione;
	private LocalTime inizio;
	private LocalTime fine;
}
