package struttureEventi.ui;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

import repository.DAOFactory;
import struttureEventi.classes.PrenotazioneRistorante;
import util.GenerateRandom;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JSpinner;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import com.toedter.calendar.JCalendar;

import contabilita.Cliente;

public class RistoranteUI extends JFrame implements ActionListener {
	private ArrayList<Cliente> clienti;
	private JFrame frame;
	private Cliente cliente;
	private int tavolo;
	private LocalDate data;
	private LocalDateTime dataPrenotazione;
	private JSpinner spr_startTime;
	private JTable table;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RistoranteUI window = new RistoranteUI();
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
					RistoranteUI window = new RistoranteUI();
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
	public RistoranteUI() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 526);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Prenotazione Ristorante");
		
		JLabel lblNewLabel = new JLabel("Selezionare il tavolo:");
		lblNewLabel.setBounds(10, 46, 118, 14);
		frame.getContentPane().add(lblNewLabel);

		JComboBox comboBox_tavoli = new JComboBox();

		Set<Integer> keys = (DAOFactory.getDAOTavoloRistorante().doRetrieveAll()).keySet();
		comboBox_tavoli.setModel(new DefaultComboBoxModel(keys.toArray()));
		comboBox_tavoli.setBounds(10, 71, 81, 22);
		frame.getContentPane().add(comboBox_tavoli);
		comboBox_tavoli.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				tavolo=  (int) comboBox_tavoli.getSelectedItem();
			}
		});

		JLabel lblNewLabel_1 = new JLabel("Selezionare il cliente:");
		lblNewLabel_1.setBounds(10, 248, 118, 14);
		frame.getContentPane().add(lblNewLabel_1);

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
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(44, 273, 319, 176);
		frame.getContentPane().add(scrollPane_table);

		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("prenota");
		btnNewButton.setBounds(0, 464, 434, 23);
		frame.getContentPane().add(btnNewButton);

		spr_startTime = new JSpinner(new SpinnerDateModel());
		spr_startTime.setEditor(new JSpinner.DateEditor(spr_startTime, "HH:mm"));
		spr_startTime.setBounds(10, 159, 74, 35);
		frame.getContentPane().add(spr_startTime);

		JCalendar inizio = new JCalendar();
		inizio.setBounds(235, 71, 184, 153);
		frame.getContentPane().add(inizio);

		JLabel lblNewLabel_2 = new JLabel("Seleziona la data");
		lblNewLabel_2.setBounds(235, 46, 128, 14);
		frame.getContentPane().add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Seleziona l'ora");
		lblNewLabel_3.setBounds(10, 134, 105, 14);
		frame.getContentPane().add(lblNewLabel_3);
		inizio.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				data= inizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				//dataInizio=LocalDate.parse(inizio.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));    
			}
		}); 

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		String command= e.getActionCommand();
		switch (command) {
		case "prenota":
			Date oraSpinner=  (Date) spr_startTime.getValue();
			dataPrenotazione= LocalDateTime.of(data.getYear(), data.getMonth(), data.getDayOfMonth(), oraSpinner.getHours(), oraSpinner.getMinutes());
			//String ora= oraSpinner. + ":" + oraSpinner.getMinutes() + ":" + oraSpinner.getSeconds();
			System.out.println(dataPrenotazione);
			//dataPrenotazione=LocalDateTime.parse(data.toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

			int i=table.getSelectedRow();

			if (tavolo==0) { 
				JOptionPane.showMessageDialog(this, "Selezionare il numero del tavolo.");
				break;}
			if(i==-1) {
				JOptionPane.showMessageDialog(this, "Selezionare un cliente.");
				break;}
			if (dataPrenotazione== null) { 
				JOptionPane.showMessageDialog(this, "Selezionare una data.");
				break;}
			if (oraSpinner==null) { 
				JOptionPane.showMessageDialog(this, "Selezionare l'ora.");
				break;}

			int d= disponibilita();
			if(d==-1) {
				JOptionPane.showMessageDialog(this, "Tavolo già prenotato.");
				break;
			}
			cliente=clienti.get(i);
			GenerateRandom g = new GenerateRandom();
			String idPrenotazione= "PR" + g.GenerateRandom();

			PrenotazioneRistorante pr = new PrenotazioneRistorante(idPrenotazione, cliente, tavolo, dataPrenotazione);

			int prenotazione = DAOFactory.getDAOPrenotazioneRistorante().updatePrenotazioneRistorante(pr);

			if(prenotazione!= 0) 
				JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");
			else if(prenotazione==0)
				JOptionPane.showMessageDialog(this, "Errore durante la registrazione della prenotazione!");

			else if(DAOFactory.getDAOPrenotazioneRistorante().doRetrieveById(idPrenotazione)!= null) {
				JOptionPane.showMessageDialog(this, "Non è possibile registrare la prenotazione.\nE' già presente una prenotazione con identificativo " + idPrenotazione);
			}
			this.dispose();
			frame.dispose();
			break;
		}
	}
	
	public int disponibilita () {
		for(PrenotazioneRistorante p : DAOFactory.getDAOPrenotazioneRistorante().doRetrieveAll()) {
			p.getData();
			if(p.getData().equals(dataPrenotazione) && p.getnTavolo()==(tavolo)) {
				return -1;
			}
		} return 0;}
}