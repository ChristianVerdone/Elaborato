package struttureEventi.ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import contabilit�.Cliente;
import repository.DAOFactory;
import struttureEventi.classes.Biglietto;
import struttureEventi.classes.Evento;
import struttureEventi.classes.PrenotazioneEvento;
import util.GenerateRandom;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class EventoUI extends JFrame implements ActionListener{
	
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
		frame.setBounds(100, 100, 450, 481);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Prenotazione Evento");
		
		JLabel lblNewLabel = new JLabel("Seleziona l'evento in programma:");
		lblNewLabel.setBounds(10, 11, 165, 14);
		frame.getContentPane().add(lblNewLabel);
		
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona il biglietto:");
		lblNewLabel_1.setBounds(10, 155, 165, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		DefaultListModel<String> listmodelB = new DefaultListModel<String>();
	
		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente:");
		lblNewLabel_2.setBounds(10, 284, 165, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.setBounds(0, 419, 434, 23);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
		
		
		/************CLIENTI****************/
		
	
		clienti= new ArrayList<Cliente>();
		for (Cliente cl : DAOFactory.getDAOCliente().doRetrieveAll().values()) {
			clienti.add(cl);
		}
		DefaultTableModel dtm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		dtm.setColumnIdentifiers(new String[]{"Nome","Cognome","CF"});
		
		//Da spostare in un controller
		Set<Cliente> items = new HashSet<Cliente>(); 
		items.addAll(clienti);
		
		for(Cliente cl : clienti) {
			dtm.addRow(new Object[]{cl.getNome(), cl.getCognome(),  cl.getCf()});
		}
		
		table = new JTable();
		table.setBounds(344, 322, 314, -206);
		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(45, 309, 319, 99);
		frame.getContentPane().add(scrollPane_table);
		
		/************EVENTI****************/
		
		eventi= new ArrayList<Evento>();
		for (Evento e : DAOFactory.getDAOEvento().doRetrieveAll().values()) {
			eventi.add(e);
		}
		DefaultTableModel dtmE = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		dtmE.setColumnIdentifiers(new String[]{"ID" ,"Nome","Tipo","Descrizione"});
		
		//Da spostare in un controller
		Set<Evento> itemsEventi = new HashSet<Evento>(); 
		itemsEventi.addAll(eventi);
		
		for(Evento e : eventi) {
			dtmE.addRow(new Object[]{e.getIdEvento(), e.getNome(), e.getTipo(), e.getDescrizione()});
		}
		
		tableE = new JTable();
		tableE.setBounds(344, 322, 314, -206);
		tableE.setModel(dtmE);
		tableE.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table_E = new JScrollPane((tableE));
		scrollPane_table_E.setBounds(45, 36, 319, 93);
		frame.getContentPane().add(scrollPane_table_E);
		
		/************BIGLIETTI****************/
		biglietti= new ArrayList<Biglietto>();
		for (Biglietto b : DAOFactory.getDAOBiglietto().doRetrieveAll().values()) {
			biglietti.add(b);
		}
		DefaultTableModel dtmB = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		dtmB.setColumnIdentifiers(new String[]{"ID" ,"Costo","Disponibilit�","Nome Evento"});
		
		//Da spostare in un controller
		Set<Biglietto> itemsBiglietto = new HashSet<Biglietto>(); 
		itemsBiglietto.addAll(biglietti);
		
		for(Biglietto b : biglietti) {
			dtmB.addRow(new Object[]{b.getIdBiglietto(), b.getCosto(), b.isDisponibilit�(), b.getNomeEvento()});
		}
		
		tableB = new JTable();
		tableB.setBounds(344, 322, 314, -206);
		tableB.setModel(dtmB);
		tableB.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		JScrollPane scrollPane_table_B = new JScrollPane((tableB));
		scrollPane_table_B.setBounds(45, 180, 319, 93);
		frame.getContentPane().add(scrollPane_table_B);
		
		
	}
		
	
	public  void actionPerformed(ActionEvent e) {
			
			int i =table.getSelectedRow();
			cliente= clienti.get(i);
			System.out.println(cliente.toString());
			int b =tableB.getSelectedRow();
			biglietto=biglietti.get(b);
			int ev =tableE.getSelectedRow();
			evento=eventi.get(i);
			
			GenerateRandom g = new GenerateRandom();
			String idPrenotazione = "PE" + g.GenerateRandom();
			PrenotazioneEvento pe= new PrenotazioneEvento(idPrenotazione, cliente, evento, biglietto);
			DAOFactory.getDAOPrenotazioneEvento().updatePrenotazioneEvento(pe);
		this.dispose();
		frame.dispose();
	}
}
		
