package personale.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import personale.model.Dipendente;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;



public class TurnoLavoroUI extends JFrame implements ActionListener, ListSelectionListener{

	private JTextField tf_cf;
	private JTable table;
	private DefaultTableModel dtm;
	private DefaultComboBoxModel<String> cbm_description;
	private JLabel lbl_place;
	private DefaultComboBoxModel<String> cbm_place;
	private JSpinner spr_startDate;
	private JSpinner spr_startTime;
	private JSpinner spr_endDate;
	private JSpinner spr_endTime;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TurnoLavoroUI window = new TurnoLavoroUI();
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TurnoLavoroUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setResizable(false);
		this.setTitle("Assegna turno di lavoro");
		this.setBounds(100, 100, 720, 480);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(null);

		/* Logo */
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(203, 10);
		lbl_logo.setSize(280, 50);
		lbl_logo.setIcon(new ImageIcon("res/test-logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(lbl_logo);

		/* Lista dipendenti */
		JLabel lbl_list = new JLabel("Dipendenti registrati:");
		lbl_list.setBounds(310, 80, 227, 20);
		this.getContentPane().add(lbl_list);
		
		dtm = new DefaultTableModel() {
		    @Override
		    public boolean isCellEditable(int row, int column) {
		        return false;
		    }
		};
		
		dtm.setColumnIdentifiers(new String[]{"CF","Nome e Cognome","Mansione", "Stipendio"});
		
		//Da spostare in un controller
		Set<Dipendente> items = new HashSet<Dipendente>(); 
		int x = 0;
		while( x < 5) {
			items.add(new Dipendente("CF0001", "Giorgio", "Napolitano", "Responsabile evento", 1700));
			items.add(new Dipendente("CF0002", "Adamo", "Pilota", "Cuoco", 1200));
			items.add(new Dipendente("CF0003", "Paola", "De Medici", "Guida", 1300));
			items.add(new Dipendente("CF0004", "Gennaro", "Napoli", "Addetto Reception", 1700));
			items.add(new Dipendente("CF0005", "Agatha", "Christie", "Amministratore", 1300));
			x++;
		}
		for(Dipendente dip : items) {
			dtm.addRow(new Object[]{dip.getCf(), dip.getNome() + " " + dip.getCognome(), dip.getMansione(), dip.getStipendio()});
		}
		
		table = new JTable();
		table.setBounds(344, 322, 314, -206);
		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(310, 105, 386, 207);
		getContentPane().add(scrollPane_table);

		/* --> JList invece di table
		Set<Dipendente> items = new HashSet<Dipendente>();
		int x = 0;
		while( x < 5) {
			items.add(new Dipendente("CF0001", "Giorgio", "Napolitano", "Responsabile evento", 1700));
			items.add(new Dipendente("CF0002", "Adamo", "Pilota", "Cuoco", 1200));
			items.add(new Dipendente("CF0003", "Paola", "De Medici", "Guida", 1300));
			items.add(new Dipendente("CF0004", "Gennaro", "Napoli", "Addetto Reception", 1700));
			items.add(new Dipendente("CF0005", "Agatha", "Christie", "Amministratore", 1300));
			x++;
		}
		DefaultListModel<Dipendente> dlf = new DefaultListModel<Dipendente>();
		dlf.addAll(items);
		JList list = new JList(dlf);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.addListSelectionListener(this);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(334, 108, 362, 227);
		scrollPane.setViewportView(list);	
		getContentPane().add(scrollPane);*/	
		
		JButton btn_refresh = new JButton("Ricarica dati\r\n");
		btn_refresh.setBounds(310, 318, 123, 21);
		this.getContentPane().add(btn_refresh);

		/* Inserimento CF */
		JLabel lbl_cf = new JLabel("Codice fiscale del dipendente:");
		lbl_cf.setBounds(10, 80, 206, 20);
		this.getContentPane().add(lbl_cf);

		tf_cf = new JTextField();
		tf_cf.setBounds(10, 105, 206, 35);
		this.getContentPane().add(tf_cf);
		tf_cf.setColumns(10);

		/* Scelta descrizione */
		JLabel lbl_description = new JLabel("Descrizione:");
		lbl_description.setBounds(10, 146, 206, 13);
		this.getContentPane().add(lbl_description);

		String[] choices = {"Turno reception", "Pulizia camere", "Cuoco", "Cameriere", "Guida escursione", "Responsabile evento", "TEST", "TEST"};
		//JComboBox<String> cb_description = new JComboBox<String>(choices); --> Problema window builder v1.9.4

		cbm_description = new DefaultComboBoxModel<String>(choices);
		JComboBox<String> cb_description = new JComboBox<String>();
		cb_description.setModel(cbm_description);

		cb_description.addActionListener(this);
		cb_description.setActionCommand("desc");
		cb_description.setBounds(10, 169, 206, 21);
		this.getContentPane().add(cb_description);		

		/* Inserimento Struttura/Evento */
		String[] structures = {"STR001", "EVT001", "STR002", "STR003", "RIS001", "STR004"};
		cbm_place = new DefaultComboBoxModel<String>(structures);
		JComboBox<String> cb_place = new JComboBox<String>();
		cb_place.setModel(cbm_place);
		cb_place.setBounds(10, 223, 206, 21);
		this.getContentPane().add(cb_place);

		JLabel lbl_place = new JLabel("Struttura:");
		lbl_place.setBounds(10, 200, 206, 13);
		this.getContentPane().add(lbl_place);
		
		/* Inserimento data e orario di inizio */
		JLabel lbl_start = new JLabel("Data ed orario di inizio turno:");
		lbl_start.setBounds(10, 254, 206, 13);
		this.getContentPane().add(lbl_start);

		spr_startDate = new JSpinner(new SpinnerDateModel());
		spr_startDate.setBounds(10, 277, 74, 35);
		spr_startDate.setEditor(new JSpinner.DateEditor(spr_startDate, "dd/MM/yy"));
		this.getContentPane().add(spr_startDate);

		spr_startTime = new JSpinner(new SpinnerDateModel());
		spr_startTime.setEditor(new JSpinner.DateEditor(spr_startTime, "HH:mm"));
		spr_startTime.setBounds(94, 277, 74, 35);
		this.getContentPane().add(spr_startTime);

		/* Inserimento data e orario di fine */
		JLabel lbl_end = new JLabel("Data ed orario di fine turno:");
		lbl_end.setBounds(10, 322, 206, 13);
		this.getContentPane().add(lbl_end);

		spr_endDate = new JSpinner(new SpinnerDateModel());
		spr_endDate.setBounds(10, 345, 74, 35);
		spr_endDate.setEditor(new JSpinner.DateEditor(spr_endDate, "dd/MM/yy"));
		this.getContentPane().add(spr_endDate);

		spr_endTime = new JSpinner(new SpinnerDateModel());
		spr_endTime.setBounds(94, 345, 74, 35);
		spr_endTime.setEditor(new JSpinner.DateEditor(spr_endTime, "HH:mm"));

		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.HOUR_OF_DAY, 6);
		Date d = cal.getTime();
		
		spr_endDate.setValue(d);
		spr_endTime.setValue(d);
		this.getContentPane().add(spr_endTime);

		/* Separatore tra campi e bottone invio */
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 396, 206, 2);
		this.getContentPane().add(separator);

		/* Bottone invio */
		JButton btn_send = new JButton("Invia");
		btn_send.setBounds(10, 412, 85, 21);
		btn_send.setActionCommand("send");
		btn_send.addActionListener(this);
		this.getContentPane().add(btn_send);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		//Testing
		String command = e.getActionCommand();
		switch(command) {
		case "send":
			String cf = tf_cf.getText();
			String description = cbm_description.getSelectedItem().toString();
			String str_evt = cbm_place.getSelectedItem().toString();
			Date a = (Date) spr_startDate.getValue();
			Date b = (Date) spr_startTime.getValue();
			
			if(a != null && b != null) JOptionPane.showConfirmDialog(this, a.toString() + b.toString());
			break;
		case "desc":
			String choice = cbm_description.getSelectedItem().toString();
			//Chiamata al database per caricare le strutture nella combobox (controller)
			JOptionPane.showConfirmDialog(this, choice);
			break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		tf_cf.setText(table.getValueAt(table.getSelectedRow(), 0).toString());
	}
}
