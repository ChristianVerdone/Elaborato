package struttureEventi.ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import java.util.HashSet;
import java.util.Set;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import javax.swing.JList;
import javax.swing.JOptionPane;
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
	private JLabel lblNewLabel;
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
		
	/**
		
	 * Initialize the contents of the frame.
	 */
		frame = new JFrame();
		frame.setBounds(100, 100, 383, 417);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.getContentPane().setLayout(null);
		frame.setTitle("Ricerca cliente");
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(68, 31);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lbl_logo);
		
		listmodel = new DefaultListModel<Cliente>();

		//HashMap<String, Cliente> clienti = new HashMap<String, Cliente>();
		clienti= new ArrayList<Cliente>();
		for (Cliente c : DAOFactory.getDAOCliente().doRetrieveAll().values()) {
			clienti.add(c);
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
		scrollPane_table.setBounds(24, 157, 319, 176);
		frame.getContentPane().add(scrollPane_table);
		
		btnNewButton = new JButton("Conferma");
		btnNewButton.setActionCommand("seleziona");
		btnNewButton.addActionListener(this);
		btnNewButton.setBounds(109, 344, 148, 23);
		frame.getContentPane().add(btnNewButton);
		
		lblNewLabel = new JLabel("Scegli il cliente");
		lblNewLabel.setBounds(24, 136, 153, 14);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		frame.getContentPane().add(lblNewLabel);
	}
	
	public  void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		switch(command) {
		case "seleziona":
			
			int i =table.getSelectedRow();
			if(i==-1) {
				JOptionPane.showMessageDialog(null, "Seleziona un cliente.");
				break;
			}
			cliente= clienti.get(i);
			
			AbitazioneUI a= new AbitazioneUI(cliente);
			a.start(cliente);
		}
		this.dispose();
		frame.dispose();
	}
}