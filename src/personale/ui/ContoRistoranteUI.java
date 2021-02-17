package personale.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import repository.DAOFactory;
import struttureEventi.classes.ContoRistorante;
import struttureEventi.classes.PrenotazioneRistorante;
import util.GenerateRandom;

import javax.swing.JButton;

public class ContoRistoranteUI extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField idconto;
	private JTextField importo;
	private JTable table;
	private ArrayList<PrenotazioneRistorante> prenotazioni;
	private PrenotazioneRistorante p;

	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContoRistoranteUI window = new ContoRistoranteUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ContoRistoranteUI() {

		frame = new JFrame();
		frame.setName("Registrazione conto ristorante");
		frame.setBounds(100, 100, 552, 348);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		idconto = new JTextField();
		idconto.setBounds(38, 184, 86, 20);
		idconto.setText(this.getFreeId());
		frame.getContentPane().add(idconto);
		idconto.setColumns(10);

		JLabel lblNewLabel = new JLabel("Numero Ricevuta");
		lblNewLabel.setBounds(38, 165, 126, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Importo");
		lblNewLabel_1.setBounds(38, 212, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);

		importo = new JTextField();
		importo.setBounds(38, 231, 86, 20);
		frame.getContentPane().add(importo);
		importo.setColumns(10);

		JLabel lblNewLabel_2 = new JLabel("Prenotazioni");
		lblNewLabel_2.setBounds(226, 165, 137, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Registra conto");
		btnNewButton.setBounds(38, 262, 121, 23);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("registra");
		frame.getContentPane().add(btnNewButton);

		prenotazioni = new ArrayList<PrenotazioneRistorante>(); 
		for (PrenotazioneRistorante p : DAOFactory.getDAOPrenotazioneRistorante().doRetrievePrenotazioniNonRegistrate()) { 
			prenotazioni.add(p); 
		} 
		DefaultTableModel dtm = new DefaultTableModel() { 
			@Override 
			public boolean isCellEditable(int row, int column) { 
				return false; 
			} 
		}; 
		dtm.setColumnIdentifiers(new String[] { "id", "Cliente", "Tavolo" }); 
 
		for (PrenotazioneRistorante p : prenotazioni) { 
			dtm.addRow(new Object[] { p.getIdPrenotazione(), p.getCliente().getCognome()+" "+p.getCliente().getNome(), p.getnTavolo() }); 
		} 
 
		table = new JTable(); 
		table.setBounds(344, 322, 314, -206); 
		table.setModel(dtm); 
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
		// table.getSelectionModel().addListSelectionListener(this); 
		JScrollPane scrollPane_table = new JScrollPane(table); 
		scrollPane_table.setBounds(226, 186, 300, 99); 
		frame.getContentPane().add(scrollPane_table); 
 
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() { 
 
			@Override 
			public void valueChanged(ListSelectionEvent arg0) { 
				int i = table.getSelectedRow(); 
				if (i != -1) { 
					p = prenotazioni.get(i); 
				} 
			} 
		}); 
		
		JLabel lblposate1 = new JLabel("");
		lblposate1.setBounds(38, 21, 96, 96);
		lblposate1.setIcon(new ImageIcon("images/posateBianche.png"));
		frame.getContentPane().add(lblposate1);

		JLabel lblcameriere = new JLabel("");
		lblcameriere.setBounds(226, 21, 96, 96);
		lblcameriere.setIcon(new ImageIcon("images/cameriere.png"));
		frame.getContentPane().add(lblcameriere);

		JLabel lblposate2 = new JLabel("");
		lblposate2.setBounds(379, 21, 96, 96);
		lblposate2.setIcon(new ImageIcon("images/posateBianche.png"));
		frame.getContentPane().add(lblposate2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "registra":
			String idcontoristorante = idconto.getText();
			if (idcontoristorante == null) {
				JOptionPane.showMessageDialog(null, "Inserisci il numero del conto.");
				break;
			} else if (idcontoristorante.length() < 5 || idcontoristorante.length() > 5) {
				JOptionPane.showMessageDialog(null, "Il numero del conto deve contenere 5 caratteri.");
				break;
			} else if (DAOFactory.getDAOContoRistorante().doRetrieveById(idcontoristorante) != null) {
				String newId = this.getFreeId();
				JOptionPane.showMessageDialog(null, "Il conto numero '" + idcontoristorante + "' e' gia' stato registrato.\n"
						+ "Ecco un id libero: "+ newId);
				idconto.setText(newId);
				break;
			}

			String costoS = importo.getText();
			if(costoS.isEmpty()) {
				JOptionPane.showMessageDialog(null, "Il campo importo non puo' essere vuoto");
				break;
			}
			
			float costo = Float.parseFloat(costoS);

			if (costo <= 0) {
				JOptionPane.showMessageDialog(null, "L'importo deve avere un valore positivo.");
				break;
			}
			if (costo >= 1000) {
				JOptionPane.showMessageDialog(null, "Importo non consentito");
				break;
			}
			
			int i = table.getSelectedRow();
			System.out.println(i);
			if (i != -1) {
				p = prenotazioni.get(i);
			}
			else {
				JOptionPane.showMessageDialog(null, "Seleziona un cliente");
				break;
			}

			LocalDateTime ldt_prenotazione = LocalDateTime.of(p.getData(), p.getOra());
			if(!LocalDateTime.now().isAfter(ldt_prenotazione)) {
				JOptionPane.showMessageDialog(null, "La registrazione del conto ristorante potra'  essere effettuata solo dopo il: " + ldt_prenotazione);
				return;
			}

			ContoRistorante cr = new ContoRistorante(idcontoristorante, costo, p);
			System.out.println(cr.toString());
			int check = DAOFactory.getDAOContoRistorante().updateContoRistorante(cr);
			if (check != 0) {
				JOptionPane.showMessageDialog(null, "Registrazione effettuata");
				this.dispose();
			}
			else {
				JOptionPane.showMessageDialog(null, "Errore durante la registrazione del conto.");
				return;
			}
		}
	}
	
	private String getFreeId() {
		GenerateRandom g = new GenerateRandom();
		String id = g.generateRandomWithInitials("CR");	
		while(DAOFactory.getDAOContoRistorante().doRetrieveById(id) != null) {
			id = g.generateRandomWithInitials("CR");
		}
		return id;
	}
}