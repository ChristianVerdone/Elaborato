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
import javax.swing.JTextPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSpinner;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerDateModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import personale.model.Dipendente;
import personale.model.Servizio;
import personale.model.TurnoLavoro;
import repository.DAOFactory;

import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class TurnoLavoroUI extends JFrame implements ActionListener, ListSelectionListener{

	private JTextField tf_cf;
	private JTable table;
	private DefaultTableModel dtm;
	private DefaultComboBoxModel<String> cbm_service;
	private JTextPane tp_startTime;
	private JTextPane tp_endTime;
	private JSpinner spr_startDate;
	private Map<String, Servizio> map_ser;

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

		table = new JTable();
		table.setBounds(344, 322, 314, -206);

		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};		
		dtm.setColumnIdentifiers(new String[]{"CF","Nome e Cognome","Mansione", "Stipendio"});
		refresh();


		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(310, 105, 386, 207);
		getContentPane().add(scrollPane_table);

		JButton btn_refresh = new JButton("Ricarica dati\r\n");
		btn_refresh.setBounds(310, 318, 123, 21);
		btn_refresh.setActionCommand("refresh");
		btn_refresh.addActionListener(this);
		this.getContentPane().add(btn_refresh);

		/* Inserimento CF */
		JLabel lbl_cf = new JLabel("Codice fiscale del dipendente:");
		lbl_cf.setBounds(10, 80, 206, 20);
		this.getContentPane().add(lbl_cf);

		tf_cf = new JTextField();
		tf_cf.setBounds(10, 105, 250, 35);
		this.getContentPane().add(tf_cf);
		tf_cf.setColumns(10);

		/* Scelta descrizione */
		JLabel lbl_service = new JLabel("Servizio:");
		lbl_service.setBounds(10, 146, 206, 13);
		this.getContentPane().add(lbl_service);

		cbm_service = new DefaultComboBoxModel<String>();
		getServizi();
		JComboBox<String> cb_service = new JComboBox<String>();
		cb_service.setModel(cbm_service);
		cb_service.addActionListener(this);
		cb_service.setActionCommand("desc");
		cb_service.setBounds(10, 169, 250, 21);
		this.getContentPane().add(cb_service);

		JLabel lbl_startTime = new JLabel("Ora inizio:");
		lbl_startTime.setBounds(10, 200, 60, 20);
		getContentPane().add(lbl_startTime);

		tp_startTime = new JTextPane();
		tp_startTime.setBounds(70, 200, 60, 20);
		tp_startTime.setEditable(false);
		getContentPane().add(tp_startTime);

		JLabel lbl_endTime = new JLabel("Ora fine:");
		lbl_endTime.setBounds(150, 200, 50, 20);
		getContentPane().add(lbl_endTime);

		tp_endTime = new JTextPane();
		tp_endTime.setEditable(false);
		tp_endTime.setBounds(200, 200, 60, 20);
		getContentPane().add(tp_endTime);

		/* Inserimento data e orario di inizio */
		JLabel lbl_start = new JLabel("Data di inizio turno (gg/mm/aaaa):");
		lbl_start.setBounds(10, 230, 206, 20);
		this.getContentPane().add(lbl_start);

		spr_startDate = new JSpinner(new SpinnerDateModel());
		spr_startDate.setBounds(10, 255, 85, 35);
		spr_startDate.setEditor(new JSpinner.DateEditor(spr_startDate, "dd/MM/yyyy"));
		this.getContentPane().add(spr_startDate);

		/* Separatore tra campi e bottone invio */
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 312, 206, 2);
		this.getContentPane().add(separator);

		/* Bottone invio */
		JButton btn_send = new JButton("Invia");
		btn_send.setBounds(10, 318, 85, 21);
		btn_send.setActionCommand("send");
		btn_send.addActionListener(this);
		this.getContentPane().add(btn_send);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		String key = cbm_service.getSelectedItem().toString();
		key = key.substring(1, key.indexOf(")"));
		Servizio ser = null;

		switch(command) {
		case "refresh":
			refresh();
			break;
		case "desc":
			ser = map_ser.get(key);
			tp_startTime.setText(ser.getInizio().format(DateTimeFormatter.ofPattern("HH:mm")));
			tp_endTime.setText(ser.getFine().format(DateTimeFormatter.ofPattern("HH:mm")));
			break;
		case "send":
			String cf = tf_cf.getText();
			if(cf.length() != 16) {
				JOptionPane.showMessageDialog(this, "Il codice fiscale deve essere di 16 caratteri");
				return;
			}
			else if(DAOFactory.getDAODipendenti().doRetrieveByCf(cf) == null) {
				JOptionPane.showMessageDialog(this, "Non è presente alcun dipendente con il codice fiscale inserito");
				return;
			}

			ser = map_ser.get(key);
			Date temp_date = (Date) spr_startDate.getValue();
			LocalDate inizioTurno = temp_date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
			TurnoLavoro tl = new TurnoLavoro(cf, inizioTurno, ser);

			if(DAOFactory.getDAOTurniLavoro().update(tl) > 0) { 
				JOptionPane.showMessageDialog(this, "Registrazione turno avvenuta con successo");
				this.dispose();
			}
			else JOptionPane.showMessageDialog(this, "Errore nella registrazione del turno");

			break;
		}
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		int row = table.getSelectedRow();
		int col = table.getSelectedColumn();
		if(row == -1 || col == -1) return;
		tf_cf.setText(dtm.getValueAt(row, col).toString());
	}

	private void refresh() {
		//setRowCount scarta le righe inferiori al parametro (in seguito ad un inserimento)
		dtm.setRowCount(0);
		Set<Dipendente> dip_set = DAOFactory.getDAODipendenti().doRetrieveAll();
		if(dip_set.size() > 0) {
			if(dtm.getColumnCount() < 4) dtm.setColumnIdentifiers(new String[]{"CF","Nome e Cognome","Mansione", "Stipendio"});
			for(Dipendente dip : dip_set) {
				dtm.addRow(new Object[]{dip.getCf(), dip.getNome() + " " + dip.getCognome(), dip.getMansione(), dip.getStipendio()});
			}
		}
		else {
			dtm.setColumnIdentifiers(new String[]{"Risultato:"});
			dtm.addRow(new Object[] {"Nessun dipendente presente nel database"});
		}
	}

	private void getServizi() {
		map_ser = DAOFactory.getDAOTurniLavoro().doRetrieveAllServizi();
		for(String s : map_ser.keySet()) {
			String element = "(" + map_ser.get(s).getId() + ") " + map_ser.get(s).getDescrizione();
			cbm_service.addElement(element);
		}
	}
}