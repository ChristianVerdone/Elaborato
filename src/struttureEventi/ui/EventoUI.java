package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import contabilita.Cliente;
import repository.DAOFactory;
import struttureEventi.classes.Biglietto;
import struttureEventi.classes.Evento;
import struttureEventi.classes.PrenotazioneAbitazione;
import struttureEventi.classes.PrenotazioneEvento;
import util.GenerateRandom;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;

public class EventoUI extends JFrame implements ActionListener {

	private ArrayList<Cliente> clienti;
	private ArrayList<Evento> eventi;
	private ArrayList<Biglietto> biglietti;
	private JFrame frame;
	private JTable tableE, table, tableB;
	private Cliente cliente;
	private Biglietto biglietto;
	private Evento evento;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventoUI window = new EventoUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventoUI window = new EventoUI();
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
	public EventoUI() {
		/**
		 * Initialize the contents of the frame.
		 */

		frame = new JFrame();
		frame.setBounds(100, 100, 804, 541);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Prenotazione Evento");

		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(278, 34);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Seleziona l'evento in programma:");
		lblNewLabel.setBounds(33, 321, 243, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Seleziona il biglietto:");
		lblNewLabel_1.setBounds(491, 321, 195, 14);
		frame.getContentPane().add(lblNewLabel_1);

		DefaultListModel<String> listmodelB = new DefaultListModel<String>();

		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente:");
		lblNewLabel_2.setBounds(189, 175, 165, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.setBounds(346, 468, 97, 23);
		btnNewButton.setActionCommand("prenota");
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);

		/************ CLIENTI ****************/

		clienti = new ArrayList<Cliente>();
		Map<String, Cliente> map = DAOFactory.getDAOCliente().doRetrieveClientiPrenotati();
		if(map == null) {
			JOptionPane.showMessageDialog(null, "Nessun cliente soggiornante");
			return;
		}
		for (Cliente cl : map.values()) {
			clienti.add(cl);
		}
		
		DefaultTableModel dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(new String[] { "Nome", "Cognome", "CF" });

		// Da spostare in un controller
		Set<Cliente> items = new HashSet<Cliente>();
		items.addAll(clienti);

		for (Cliente cl : clienti) {
			dtm.addRow(new Object[] { cl.getNome(), cl.getCognome(), cl.getCf() });
		}

		table = new JTable();
		table.setBounds(344, 322, 314, -206);
		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		// table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(189, 200, 388, 99);
		frame.getContentPane().add(scrollPane_table);

		/************ EVENTI ****************/

		DefaultTableModel dtmE = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtmE.setColumnIdentifiers(new String[] { "Nome", "Tipo", "Descrizione", "Data", "Ora" });

		tableE = new JTable();
		tableE.setBounds(344, 322, 314, -206);
		tableE.setModel(dtmE);
		tableE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_table_E = new JScrollPane((tableE));

		scrollPane_table_E.setBounds(33, 346, 366, 93);
		frame.getContentPane().add(scrollPane_table_E);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				dtmE.setRowCount(0);
				int i = table.getSelectedRow();
				eventi = new ArrayList<Evento>();
				if (i != -1) {
					cliente = clienti.get(i);
					Map<String, Evento> hs = DAOFactory.getDAOEvento().doRetrieveByCliente(cliente);
					if(hs == null) {
						JOptionPane.showMessageDialog(null, "Nessun evento disponibile nel periodo di soggiorno del cliente");
						return;
					}
					for (Evento e : DAOFactory.getDAOEvento().doRetrieveByCliente(cliente).values()) {
						eventi.add(e);
						dtmE.addRow(new Object[] { e.getNome(), e.getTipo(), e.getDescrizione(), e.getDataEvento(), e.getOraEvento() });
					}
				}
			}

		});
		// Da spostare in un controller

		/************ BIGLIETTI ****************/

		DefaultTableModel dtmB = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtmB.setColumnIdentifiers(new String[] { "ID", "Costo", "Nome Evento" });

		/**
		 * Da spostare in un controller Set<Biglietto> itemsBiglietto = new
		 * HashSet<Biglietto>(); itemsBiglietto.addAll(biglietti);
		 * 
		 * for(Biglietto b : biglietti) { dtmB.addRow(new Object[]{b.getIdBiglietto(),
		 * b.getCosto(), b.isDisponibilità(), b.getNomeEvento()}); }
		 */
		tableB = new JTable();
		tableB.setBounds(344, 322, 314, -206);
		tableB.setModel(dtmB);
		tableB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_table_B = new JScrollPane((tableB));
		scrollPane_table_B.setBounds(491, 346, 287, 93);
		frame.getContentPane().add(scrollPane_table_B);

		tableE.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				dtmB.setRowCount(0);

				biglietti = new ArrayList<Biglietto>();
				int i = tableE.getSelectedRow();

				if (i != -1) {
					evento = eventi.get(i);

					for (Biglietto b : DAOFactory.getDAOBiglietto().doRetrieveAll().values()) {
						if (b.getNomeEvento().equalsIgnoreCase(evento.getNome()) && b.isDisponibilità() == true) {
							biglietti.add(b);
						}
					}
					for (Biglietto b : biglietti) {
						dtmB.addRow(new Object[] { b.getIdBiglietto(), b.getCosto(), b.getNomeEvento() });
					}
				}
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "prenota":
			// int i =table.getSelectedRow();
			// if(i==-1) {
			// JOptionPane.showMessageDialog(null, "Seleziona un cliente.");
			// break;
			// }
			// cliente= clienti.get(i);

			int ev = tableE.getSelectedRow();
			if (ev == -1) {
				JOptionPane.showMessageDialog(null, "Seleziona un evento.");
				break;
			}
			evento = eventi.get(ev);
			
			int b = tableB.getSelectedRow();
			if (b == -1) {
				JOptionPane.showMessageDialog(null, "Seleziona un biglietto.");
				break;
			}

			biglietto = biglietti.get(b);

			GenerateRandom g = new GenerateRandom();
			String idPrenotazione = "PE" + g.GenerateRandom();
			PrenotazioneEvento pe = new PrenotazioneEvento(idPrenotazione, cliente, evento, biglietto);
			int check = DAOFactory.getDAOPrenotazioneEvento().updatePrenotazioneEvento(pe);
			if(check == 0) {
				JOptionPane.showMessageDialog(null, "Errore durante la registrazione della prenotazione.");
				return;
			}
			else if(check == -1) {
				//Questa parte non è raggiungibile, poiché prima è stato effettuato un filtraggio dei clienti
				JOptionPane.showMessageDialog(null, "Cliente non soggiornante");
				return;
			}
			else if(check == -2) {
				JOptionPane.showMessageDialog(null, "Il cliente risulta già registrato a questo evento");
				return;
			}
			else {
				JOptionPane.showMessageDialog(null, "Prenotazione effettuata.");
				DAOFactory.getDAOBiglietto().updateDisponibilita(biglietto.getIdBiglietto());
				System.out.println(DAOFactory.getDAOBiglietto().doRetrieveById(biglietto.getIdBiglietto()));
				this.dispose();
				frame.dispose();
			}
		}
	}
}