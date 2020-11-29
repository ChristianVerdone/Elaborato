package struttureEventi.ui;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import repository.DAOClienteImpl;
import repository.DAOFactory;
import contabilit�.Cliente;
import personale.model.Account;
import personale.ui.HomeAdminUI;
import personale.ui.HomeUI;

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class RegistrazioneClienteUI  extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField textField_nome;
	private JTextField textField_cognome;
	private JTextField textField_cf;
	private Cliente cliente;
	private ArrayList<Cliente> cl;
	private String nome, cognome,cf;
	/**
	 * Launch the application.
	 */
	public void Registrazione() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					RegistrazioneClienteUI window = new RegistrazioneClienteUI();
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
	public   RegistrazioneClienteUI() {


		/**
		 * Initialize the contents of the frame.
		 */
		cl=new ArrayList<Cliente>();
		frame = new JFrame();
		frame.setBounds(100, 100, 292, 252);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Registrazione di un nuovo cliente");
		lblNewLabel.setBounds(42, 11, 165, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 30, 46, 14);
		frame.getContentPane().add(lblNome);

		textField_nome = new JTextField();
		textField_nome.setBounds(10, 46, 151, 20);
		frame.getContentPane().add(textField_nome);
		textField_nome.setColumns(10);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(10, 84, 85, 14);
		frame.getContentPane().add(lblCognome);

		textField_cognome = new JTextField();
		textField_cognome.setColumns(10);
		textField_cognome.setBounds(10, 98, 151, 20);
		frame.getContentPane().add(textField_cognome);

		JLabel lblCF = new JLabel("Codice Fiscale");
		lblCF.setBounds(10, 129, 106, 14);
		frame.getContentPane().add(lblCF);

		textField_cf = new JTextField();
		textField_cf.setColumns(10);
		textField_cf.setBounds(10, 148, 151, 20);
		frame.getContentPane().add(textField_cf);

		JButton aggiungi = new JButton("Aggiungi");
		aggiungi.addActionListener(this); 
		aggiungi.setActionCommand("agg");
		aggiungi.setBounds(96, 179, 89, 23);

		frame.getContentPane().add(aggiungi);

	}


	public Cliente getCliente() {

		return cliente;
	}

	public void actionPerformed(ActionEvent e) {
		String msg= null;
		switch(e.getActionCommand()) {
		case "agg":

			String n = textField_nome.getText().toString();
			if(n.length()==0) msg ="Inserisci un nome";
			else if(n.length() > 30) msg = "Dimensione massima del nome: 30 caratteri\n";
			
			String c = textField_cognome.getText().toString();
			if(c.length()==0) msg ="Inserisci un cognome";
			else if(c.length() > 30) msg = "Dimensione massima del cognome: 30 caratteri\n";
			
			String cf = textField_cf.getText().toString();
			if(cf.length() != 16) msg = "Il codice fiscale deve avere 16 caratteri\n";
			else if(DAOFactory.getDAOCliente().doRetrieveByCf(cf)!= null) {
				
				JOptionPane.showMessageDialog(this, "Cliente con codice fiscale " + cf + " gi� registrato.");
				break;
			}
			if(msg!=null) {
				JOptionPane.showMessageDialog(this, msg);
				return;
			}
			
			
			cliente = new Cliente(cf,n,c); 
			
			int check = DAOFactory.getDAOCliente().updateCliente(cliente);
			if(check==0) 
				JOptionPane.showMessageDialog(this, "Errore nella registrazione del cliente!");
			
			else if(check!=0)
				JOptionPane.showMessageDialog(this, "Cliente aggiunto!");
			
			AbitazioneUI a= new AbitazioneUI(cliente);
			a.start(cliente);
			this.dispose();
			frame.dispose();

			break;
		}
		
	}

	
}
