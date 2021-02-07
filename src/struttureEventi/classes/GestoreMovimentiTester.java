package struttureEventi.classes;

import java.util.HashMap;
import java.util.HashSet;

import repository.DAOFactory;

public class GestoreMovimentiTester {

	public static void main(String[] args) {
		DAOFactory df = new DAOFactory();
		HashMap<String, Tessera> tessere = df.getDAOTessera().doRetrieveAll();
		HashMap<String, Lettore> lettori = df.getDAOLettore().doRetrieveAll();
		HashSet<PrenotazioneSv> prenotazioni = df.getDAOPrenotazioneStruttura().doRetrieveAll();
		
		if(!prenotazioni.isEmpty()) {
			for(PrenotazioneSv p : prenotazioni) {
				Tessera t = p.getTessera();
				StrutturaVillaggio s = p.getStruttura();
				for(Lettore l : lettori.values()) {
					if(l.getStruttura().equals(s.getIdStruttura())) {
						Movimento m = new Movimento(t.getId(), l.getIdLettore(), true);
						df.getDAOMovimento().updateMovimento(m);
						m = new Movimento(t.getId(), l.getIdLettore(), false);
						df.getDAOMovimento().updateMovimento(m);
						df.getDAOTessera().delete(t.getId());
					}
				}
			}
		}
	}
}