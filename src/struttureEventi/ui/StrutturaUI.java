package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JComboBox;
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
import struttureEventi.classes.PrenotazioneSv;
import struttureEventi.classes.Tessera;
import util.GenerateRandom;


public class StrutturaUI extends JFrame implements ActionListener {
	
	private ArrayList<Tessera> tessere;
	private ArrayList<Cliente> clienti;
	private JFrame frame;
	private JTable table;
	private Cliente cliente;
	private Tessera tessera;
	private String struttura;
	private ArrayList<Evento> eventi;
	private Evento evento;
	private int c, t;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StrutturaUI window = new StrutturaUI();
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
					StrutturaUI window = new StrutturaUI();
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
	public StrutturaUI() {
		
		
		frame = new JFrame();
		frame.setBounds(100, 100, 488, 345);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setTitle("Prenotazione Struttura");
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(119, 22);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lbl_logo);
		
		String[] strutture = {"Campo da tennis", "Campo da calcio"};
		
		JComboBox struttbox = new JComboBox(strutture);
		
		struttbox.setBounds(150, 122, 131, 22);
		frame.getContentPane().add(struttbox);
		
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona la struttura");
		lblNewLabel_1.setBounds(9, 122, 131, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		
		
		
		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente");
		lblNewLabel_2.setBounds(9, 166, 112, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.setBounds(186, 279, 99, 23);
		btnNewButton.setActionCommand("prenota");
		btnNewButton.addActionListener(this);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
		/**CLIENTI*/
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
	
		JScrollPane scrollPane_table_1 = new JScrollPane(table);
		scrollPane_table_1.setBounds(150, 165, 309, 89);
		frame.getContentPane().add(scrollPane_table_1);

		/**TESSERE*/
		
		DefaultTableModel dtmT = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		dtmT.setColumnIdentifiers(new String[]{"Tessera","Descrizione"});
	
		struttbox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				struttura=(String) struttbox.getSelectedItem();
				
			
			}}
		);
		
	}
		
			
	
	public  void actionPerformed(ActionEvent e) {

		
	
	String command= e.getActionCommand();
	
	switch (command) {
	case "prenota":
		 c =table.getSelectedRow();
		 
		
		if (struttura==null) { 
			JOptionPane.showMessageDialog(this, "Selezionare la struttura.");
			break;}
	
		if(c==-1) {
			JOptionPane.showMessageDialog(this, "Selezionare un cliente.");
			break;}
		
		cliente=clienti.get(c);
		
		GenerateRandom g = new GenerateRandom();
		String idPrenotazione = "PE" + g.GenerateRandom();
		String idTessera= "TS" + g.GenerateRandom();
		Tessera t= new Tessera(idTessera, "Tessera per il " + struttura.toLowerCase());
		System.out.println(t.toString());
		PrenotazioneSv p= new PrenotazioneSv(idPrenotazione, cliente, DAOFactory.getDAOStrutturaVillaggio().doRetrieveById(struttura), t);
		System.out.println(DAOFactory.getDAOStrutturaVillaggio().doRetrieveById(struttura));
		int prenotazione=DAOFactory.getDAOPrenotazioneStruttura().updatePrenotazioneStruttura(p);
		this.dispose();
		frame.dispose();
		if(prenotazione!= 0) 
			JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");
		else if(prenotazione==0)
			JOptionPane.showMessageDialog(this, "Errore durante la registrazione della prenotazione!");
			
		else if(DAOFactory.getDAOPrenotazioneStruttura().doRetrieveById(idPrenotazione)!= null) {
			JOptionPane.showMessageDialog(this, "Non è possibile registrare la prenotazione.\nE' già presente una prenotazione con identificativo " + idPrenotazione);
		}
		this.dispose();
		frame.dispose();
		break;
	
	}
	}}
