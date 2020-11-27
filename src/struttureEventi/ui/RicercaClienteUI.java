package struttureEventi.ui;
import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import contabilità.Cliente;
import personale.model.Dipendente;
import javax.swing.JTable;
import java.awt.Panel;
import javax.swing.JScrollBar;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RicercaClienteUI extends JFrame implements ActionListener {
	private static JList<Cliente> list;
	private JFrame frame;
	private JTable table;
	private static DefaultListModel<Cliente> listmodel;
	/**
	 * Launch the application.
	 */
	public static void Ricerca() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RicercaClienteUI window = new RicercaClienteUI();
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
	public RicercaClienteUI() {
		initialize();
	}

	/**
		
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 417);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		listmodel = new DefaultListModel<Cliente>();

		HashSet<Cliente> clienti = new HashSet<Cliente>();
		clienti.add(new Cliente("Jessica", "Cinelli", "cf"));
		clienti.add(new Cliente("Christian", "Verdone", "cf1"));
		listmodel.addAll(clienti);
		list = new JList<Cliente>(listmodel);
		
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(30, 24, 319, 100);
		frame.getContentPane().add(scrollPane);
		
	
		JLabel lbl_list = new JLabel("Clienti registrati:");
		lbl_list.setBounds(40, 129, 227, 20);
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
		
			items.add(new Cliente("Jessica", "Cinelli", "cf"));
			items.add(new Cliente("Christian", "Verdone", "cf1"));
			items.add(new Cliente("Paola", "De Medici", "CF0003"));
			items.add(new Cliente( "Gennaro", "Napoli", "CF0004"));
			items.add(new Cliente( "Agatha", "Christie", "CF0005"));
		
		for(Cliente cl : items) {
			dtm.addRow(new Object[]{cl.getCf(), cl.getNome(),  cl.getCognome()});
		}
		
		table = new JTable();
		table.setBounds(344, 322, 314, -206);
		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//table.getSelectionModel().addListSelectionListener(this);
		JScrollPane scrollPane_table = new JScrollPane(table);
		scrollPane_table.setBounds(27, 160, 386, 207);
		frame.getContentPane().add(scrollPane_table);
		
	}
	
	public  void actionPerformed(ActionEvent e) {
		
	}
}
