package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
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
		lblNewLabel.setBounds(25, 321, 165, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Seleziona il biglietto:");
		lblNewLabel_1.setBounds(425, 321, 165, 14);
		frame.getContentPane().add(lblNewLabel_1);

		DefaultListModel<String> listmodelB = new DefaultListModel<String>();

		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente:");
		lblNewLabel_2.setBounds(203, 175, 165, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.setBounds(346, 468, 97, 23);
		btnNewButton.setActionCommand("prenota");
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);

		/************ CLIENTI ****************/

		clienti = new ArrayList<Cliente>();
		for (Cliente cl : DAOFactory.getDAOCliente().doRetrieveAll().values()) {
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
		scrollPane_table.setBounds(234, 200, 319, 99);
		frame.getContentPane().add(scrollPane_table);

		/************ EVENTI ****************/

		DefaultTableModel dtmE = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtmE.setColumnIdentifiers(new String[] { "ID", "Nome", "Tipo", "Descrizione" });

		tableE = new JTable();
		tableE.setBounds(344, 322, 314, -206);
		tableE.setModel(dtmE);
		tableE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_table_E = new JScrollPane((tableE));

		scrollPane_table_E.setBounds(60, 346, 319, 93);
		frame.getContentPane().add(scrollPane_table_E);

		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				dtmE.setRowCount(0);
				int i = table.getSelectedRow();
				eventi = new ArrayList<Evento>();
				if (i != -1) {
					cliente = clienti.get(i);
					for (PrenotazioneAbitazione pa : DAOFactory.getDAOPrenotazioneAbitazione().doRetrieveAll()) {

						if (pa.getCliente().getCf().equalsIgnoreCase(cliente.getCf())) {

							for (Evento e : DAOFactory.getDAOEvento().doRetrieveAll().values()) {
								if (e.getDataEvento().isAfter(pa.getDataInizio())
										&& e.getDataEvento().isBefore(pa.getDataFine())) {
									eventi.add(e);
								}
							}
						}
					}

				}
				for (Evento ev : eventi) {
					dtmE.addRow(new Object[] { ev.getIdEvento(), ev.getNome(), ev.getTipo(), ev.getDescrizione() });
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

		dtmB.setColumnIdentifiers(new String[] { "ID", "Costo", "Disponibilità", "Nome Evento" });

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
		scrollPane_table_B.setBounds(459, 346, 319, 93);
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
						dtmB.addRow(new Object[] { b.getIdBiglietto(), b.getCosto(), b.isDisponibilità(),
								b.getNomeEvento() });
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

			int b = tableB.getSelectedRow();
			if (b == -1) {
				JOptionPane.showMessageDialog(null, "Seleziona un biglietto.");
				break;
			}

			biglietto = biglietti.get(b);

			int ev = tableE.getSelectedRow();
			if (ev == -1) {
				JOptionPane.showMessageDialog(null, "Seleziona un evento.");
				break;
			}
			evento = eventi.get(ev);

			GenerateRandom g = new GenerateRandom();
			String idPrenotazione = "PE" + g.GenerateRandom();
			PrenotazioneEvento pe = new PrenotazioneEvento(idPrenotazione, cliente, evento, biglietto);
			int check = DAOFactory.getDAOPrenotazioneEvento().updatePrenotazioneEvento(pe);
			if (check != 0) {
				JOptionPane.showMessageDialog(null, "Prenotazione effettuata.");
				DAOFactory.getDAOBiglietto().updateDisponibilita(biglietto.getIdBiglietto());
				System.out.println(DAOFactory.getDAOBiglietto().doRetrieveById(biglietto.getIdBiglietto()));

			} else {
				JOptionPane.showMessageDialog(null, "Errore durante la registrazionee della prenotazione.");
			}
			this.dispose();
			frame.dispose();
		}
	}
}