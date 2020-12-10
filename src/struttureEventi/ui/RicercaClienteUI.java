package struttureEventi.ui;
import java.awt.EventQueue;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import contabilita.Cliente;
import repository.DAOFactory;

import javax.swing.JTable;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public class RicercaClienteUI extends JFrame implements ActionListener {
	private static JList<Cliente> list;
	private JFrame frame;
	private JTable table;
	private static DefaultListModel<Cliente> listmodel;
	private Cliente cliente;
	private JButton btnNewButton;
	private String op;
	private ArrayList<Cliente> clienti= new ArrayList<Cliente>();
	/**
	 * Launch the application.
	 */
	public static void Ricerca(String operazione) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaClienteUI window = new RicercaClienteUI(operazione);
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
	public RicercaClienteUI(String operazione) {
		op=operazione;
		/**

		 * Initialize the contents of the frame.
		 */
		frame = new JFrame();
		frame.setBounds(100, 100, 383, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		listmodel = new DefaultListModel<Cliente>();

		//HashMap<String, Cliente> clienti = new HashMap<String, Cliente>();
		clienti= new ArrayList<Cliente>();
		for (Cliente c : DAOFactory.getDAOCliente().doRetrieveAll().values()) {
			clienti.add(c);
		}

		listmodel.addAll(clienti);
		list = new JList<Cliente>(listmodel);

		JScrollPane elencoClienti = new JScrollPane(list);
		elencoClienti.setBounds(25, 49, 319, 100);
		frame.getContentPane().add(elencoClienti);
		cliente=list.getSelectedValue();

		JLabel lbl_list = new JLabel("Clienti registrati:");
		lbl_list.setBounds(26, 18, 227, 20);
		frame.getContentPane().add(lbl_list);

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
		scrollPane_table.setBounds(25, 157, 319, 176);
		frame.getContentPane().add(scrollPane_table);

		btnNewButton = new JButton("Seleziona cliente");

		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(105, 344, 148, 23);
		frame.getContentPane().add(btnNewButton);
	}

	public  void actionPerformed(ActionEvent e) {
		if(op=="abitazione") {
			//cliente=list.getSelectedValue();

			int i =table.getSelectedRow();
			cliente= clienti.get(i);

			AbitazioneUI a= new AbitazioneUI(cliente);
			a.start(cliente);
		}
		this.dispose();
		frame.dispose();
	}
}