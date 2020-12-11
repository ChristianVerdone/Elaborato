package personale.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import personale.model.Account;
import personale.model.Servizio;
import personale.model.TurnoLavoro;
import repository.DAOFactory;
import repository.DAOTurniLavoro;

import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.util.Set;

public class HomeUI extends JFrame implements ActionListener{

	private DefaultTableModel dtm;
	private JTable table;
	private Account acc;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeUI window = new HomeUI(new Account("ryanp12", "ryan12", Account.Permessi.NONE));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public HomeUI(Account acc) {
		this.setResizable(false);
		this.setTitle("Home");
		this.setBounds(100, 100, 720, 480);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.getContentPane().setLayout(null);
		this.acc = acc;

		/* Logo */
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(203, 10);
		lbl_logo.setSize(280, 50);
		lbl_logo.setIcon(new ImageIcon("res/test-logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(lbl_logo);

		JLabel lbl_list = new JLabel("I miei turni:");
		lbl_list.setBounds(264, 70, 227, 19);
		this.getContentPane().add(lbl_list);

		/* Tabella turni */
		table = new JTable();
		table.setBounds(344, 322, 314, -206);

		dtm = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		dtm.setColumnIdentifiers(new String[]{"Servizio","Data","Inizio", "Fine"});
		refresh();

		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(264, 105, 432, 207);
		getContentPane().add(scrollPane_table);

		JButton btn_refresh = new JButton("Ricarica turni");
		btn_refresh.setBounds(406, 322, 123, 21);
		btn_refresh.setActionCommand("refresh");
		btn_refresh.addActionListener(this);
		this.getContentPane().add(btn_refresh);

		/* Separatore verticale */
		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(240, 90, 1, 250);
		getContentPane().add(separator);

		/* Info account */
		JLabel lbl_info = new JLabel("Informazioni account:");
		lbl_info.setBounds(10, 70, 123, 13);
		getContentPane().add(lbl_info);

		JLabel lbl_username = new JLabel("Utente:");
		lbl_username.setFont(new Font("Verdana", Font.PLAIN, 10));
		lbl_username.setBounds(10, 105, 56, 20);
		getContentPane().add(lbl_username);

		JTextPane tp_username = new JTextPane();
		tp_username.setBounds(75, 105, 140, 20);
		tp_username.setText(acc.getUsername());
		tp_username.setEditable(false);
		getContentPane().add(tp_username);

		JLabel lbl_permissions = new JLabel("Permessi:");
		lbl_permissions.setFont(new Font("Verdana", Font.PLAIN, 10));
		lbl_permissions.setBounds(10, 130, 56, 20);
		getContentPane().add(lbl_permissions);

		JTextPane tp_permissions = new JTextPane();
		tp_permissions.setText((String) null);
		tp_permissions.setEditable(false);
		String p = "Visualizzazione turni";
		if(acc.getTipologiaPermessi() == Account.Permessi.ALL) p = "Completi";
		else if (acc.getTipologiaPermessi() == Account.Permessi.REDUCED) p = "Ridotti";
		tp_permissions.setText(p);
		tp_permissions.setBounds(75, 130, 140, 20);
		getContentPane().add(tp_permissions);

		/* Operazioni */
		JLabel lbl_operations = new JLabel("Operazioni disponibili:");
		lbl_operations.setBounds(10, 175, 205, 25);
		getContentPane().add(lbl_operations);

		JButton btn_logout = new JButton("Logout");
		btn_logout.setBounds(10, 210, 205, 21);
		btn_logout.setActionCommand("logout");
		btn_logout.addActionListener(this);
		getContentPane().add(btn_logout);
	}

	private void refresh() {
		dtm.setRowCount(0);
		DAOTurniLavoro dao_turni = DAOFactory.getDAOTurniLavoro();
		Set<TurnoLavoro> set_turni = dao_turni.doRetrieveByUsername(acc.getUsername());
		if(set_turni.size() > 0) {
			if(dtm.getColumnCount() < 4) dtm.setColumnIdentifiers(new String[]{"Servizio","Data","Inizio", "Fine"});
			for(TurnoLavoro tl : set_turni) {
				Servizio ser = tl.getServizio();
				dtm.addRow(new Object[]{ser.getDescrizione(), tl.getInizio().format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
						ser.getInizio(), ser.getFine()});
			}
		}
		else {
			dtm.setColumnIdentifiers(new String[]{"Risultato:"});
			dtm.addRow(new Object[] {"Nessun turno di lavoro presente nel database"});
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch(e.getActionCommand()) {
		case "logout":
			new LoginUI();
			this.dispose();
			break;
		case "refresh":
			refresh();
			break;
		}
	}
}