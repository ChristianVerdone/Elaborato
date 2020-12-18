package struttureEventi.ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;

import com.toedter.calendar.JCalendar;

import contabilita.Cliente;
import repository.DAOFactory;
import struttureEventi.classes.PrenotazioneAbitazione;
import util.GenerateRandom;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.time.LocalDate;
import java.time.ZoneId;

public class AbitazioneUI   extends JFrame implements ActionListener {
	private Cliente cliente;
	private JFrame frmPrenotazioneAbitazione;
	private JComboBox cb_abitazione;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String abitazione;
	private PrenotazioneAbitazione pa;

	/**
	 * Launch the application.
	 */


	public void start(Cliente c) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					//	Cliente cliente= setCliente(c);
					AbitazioneUI window = new AbitazioneUI(c);
					window.frmPrenotazioneAbitazione.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public AbitazioneUI(Cliente c) {
		cliente=c;	
		/**
		 * Initialize the contents of the frame.
		 */

		frmPrenotazioneAbitazione = new JFrame();
		frmPrenotazioneAbitazione.setTitle("Prenotazione abitazione");
		frmPrenotazioneAbitazione.setBounds(100, 100, 780, 424);
		frmPrenotazioneAbitazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmPrenotazioneAbitazione.getContentPane().setLayout(null);



		cb_abitazione = new JComboBox();
		cb_abitazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abitazione=(String) cb_abitazione.getSelectedItem();
			}
		});
		cb_abitazione.setModel(new DefaultComboBoxModel(new String[] {"Camera doppia", "Appartamento", "Suite", "Camera singola", "Standard", "Deluxe"}));
		cb_abitazione.setBounds(23, 301, 140, 22);
		frmPrenotazioneAbitazione.getContentPane().add(cb_abitazione);

		JLabel lblSeleziona = new JLabel("Seleziona l'abitazione");
		lblSeleziona.setBounds(23, 277, 140, 14);
		frmPrenotazioneAbitazione.getContentPane().add(lblSeleziona);

		JLabel lblDataInizio = new JLabel("Data di inizio");
		lblDataInizio.setBounds(329, 145, 97, 14);
		frmPrenotazioneAbitazione.getContentPane().add(lblDataInizio);

		JCalendar inizio = new JCalendar();
		inizio.setBounds(329, 170, 184, 153);
		frmPrenotazioneAbitazione.getContentPane().add(inizio);
		inizio.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				dataInizio = inizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				//dataInizio=LocalDate.parse(inizio.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));    
			}
		}); 
		JLabel lblDataFine = new JLabel("Data di fine");
		lblDataFine.setBounds(523, 145, 71, 14);
		frmPrenotazioneAbitazione.getContentPane().add(lblDataFine);

		JCalendar fine = new JCalendar();
		fine.setBounds(523, 170, 184, 153);
		frmPrenotazioneAbitazione.getContentPane().add(fine);

		fine.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {

				//dataFine=LocalDate.parse(fine.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
				dataFine = fine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			}
		});   


		JButton btn_Prenota = new JButton("Prenota");
		btn_Prenota.addActionListener(this);
		btn_Prenota.setBounds(337, 351, 89, 23);
		frmPrenotazioneAbitazione.getContentPane().add(btn_Prenota);

		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(266, 34);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frmPrenotazioneAbitazione.getContentPane().add(lbl_logo);

		JLabel lbl_info = new JLabel("Informazioni cliente");
		lbl_info.setBounds(23, 146, 123, 13);
		frmPrenotazioneAbitazione.getContentPane().add(lbl_info);

		JLabel lbl_cliente = new JLabel("Codice Fiscale:");
		lbl_cliente.setFont(new Font("Verdana", Font.PLAIN, 10));
		lbl_cliente.setBounds(23, 170, 108, 20);
		frmPrenotazioneAbitazione.getContentPane().add(lbl_cliente);

		JTextPane tp_cf = new JTextPane();
		tp_cf.setBounds(143, 170, 140, 20);
		tp_cf.setText(cliente.getCf());
		tp_cf.setEditable(false);
		frmPrenotazioneAbitazione.getContentPane().add(tp_cf);

		JLabel lbl_nome = new JLabel("Nome:");
		lbl_nome.setFont(new Font("Verdana", Font.PLAIN, 10));
		lbl_nome.setBounds(23, 195, 56, 20);
		frmPrenotazioneAbitazione.getContentPane().add(lbl_nome);

		JTextPane tp_nome = new JTextPane();
		tp_nome.setBounds(143, 195, 140, 20);
		tp_nome.setText(cliente.getNome());
		tp_nome.setEditable(false);
		frmPrenotazioneAbitazione.getContentPane().add(tp_nome);

		JLabel lbl_cognome = new JLabel("Cognome:");
		lbl_cognome.setFont(new Font("Verdana", Font.PLAIN, 10));
		lbl_cognome.setBounds(23, 220, 89, 20);
		frmPrenotazioneAbitazione.getContentPane().add(lbl_cognome);

		JTextPane tp_cognome = new JTextPane();
		tp_cognome.setBounds(143, 220, 140, 20);
		tp_cognome.setText(cliente.getCognome());
		tp_cognome.setEditable(false);
		frmPrenotazioneAbitazione.getContentPane().add(tp_cognome);
	}


	public  void actionPerformed(ActionEvent e) {
		GenerateRandom g= new GenerateRandom();
		String id= "PA" + g.GenerateRandom();
		if (abitazione==null) {
			JOptionPane.showMessageDialog(this, "Scegliere il tipo di abitazione.");

		}
		if (dataInizio==null || dataFine==null) 
			JOptionPane.showMessageDialog(this, "Scegliere il periodo della prenotazione.");

		int count=0;
		for(PrenotazioneAbitazione prenotazione: DAOFactory.getDAOPrenotazioneAbitazione().doRetrieveAll()) {
			if(prenotazione.getAbitazione().getIdAbitazione().equals(abitazione)) {
				if( !dataInizio.isAfter(prenotazione.getDataFine()) || !dataFine.isBefore(prenotazione.getDataInizio())) {
					count=count+1;
				}

			}
		}
		
		if((DAOFactory.getDAOAbitazione().doRetrieveById(abitazione).getAbitazioniDisponibili()-count)!=0) {
			pa= new PrenotazioneAbitazione( id, cliente, DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), dataInizio, dataFine);
		}
		else {
			JOptionPane.showMessageDialog(null, "Abitazione non disponibile.");
			
		}

		System.out.println(pa.toString());	
		int check = DAOFactory.getDAOPrenotazioneAbitazione().updatePrenotazioneAbitazione(pa);
		if(check==0) 
			JOptionPane.showMessageDialog(this, "Errore nella registrazione della prenotazione!");
		else if(check!=0)
			JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");
		this.dispose();
		frmPrenotazioneAbitazione.dispose();
	}
}


