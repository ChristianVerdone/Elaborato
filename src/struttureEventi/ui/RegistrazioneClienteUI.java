package struttureEventi.ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import javax.swing.JTextField;

import contabilita.Cliente;
import repository.DAOFactory;

import javax.swing.ImageIcon;
import javax.swing.JButton;


public class RegistrazioneClienteUI  extends JFrame implements ActionListener{

	private JFrame frmRegistrazioneCliente;
	private JTextField textField_nome;
	private JTextField textField_cognome;
	private JTextField textField_cf;
	private Cliente cliente;
	private ArrayList<Cliente> cl;
	/**
	 * Launch the application.
	 */
	public void Registrazione() {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {

					RegistrazioneClienteUI window = new RegistrazioneClienteUI();
					window.frmRegistrazioneCliente.setVisible(true);

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
		frmRegistrazioneCliente = new JFrame();
		frmRegistrazioneCliente.setTitle("Registrazione Cliente");
		frmRegistrazioneCliente.setBounds(100, 100, 583, 296);
		frmRegistrazioneCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmRegistrazioneCliente.getContentPane().setLayout(null);
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(168, 34);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frmRegistrazioneCliente.getContentPane().add(lbl_logo);

		JLabel lblNome = new JLabel("Nome");
		lblNome.setBounds(10, 160, 46, 14);
		frmRegistrazioneCliente.getContentPane().add(lblNome);

		textField_nome = new JTextField();
		textField_nome.setBounds(10, 176, 151, 20);
		frmRegistrazioneCliente.getContentPane().add(textField_nome);
		textField_nome.setColumns(10);

		JLabel lblCognome = new JLabel("Cognome");
		lblCognome.setBounds(201, 160, 85, 14);
		frmRegistrazioneCliente.getContentPane().add(lblCognome);

		textField_cognome = new JTextField();
		textField_cognome.setColumns(10);
		textField_cognome.setBounds(201, 176, 151, 20);
		frmRegistrazioneCliente.getContentPane().add(textField_cognome);

		JLabel lblCF = new JLabel("Codice Fiscale");
		lblCF.setBounds(390, 160, 106, 14);
		frmRegistrazioneCliente.getContentPane().add(lblCF);

		textField_cf = new JTextField();
		textField_cf.setColumns(10);
		textField_cf.setBounds(389, 176, 151, 20);
		frmRegistrazioneCliente.getContentPane().add(textField_cf);

		JButton aggiungi = new JButton("Aggiungi cliente");
		aggiungi.addActionListener(this); 
		aggiungi.setActionCommand("agg");
		aggiungi.setBounds(220, 224, 126, 23);

		frmRegistrazioneCliente.getContentPane().add(aggiungi);

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
				
				JOptionPane.showMessageDialog(this, "Cliente con codice fiscale " + cf + " già registrato.");
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
			frmRegistrazioneCliente.dispose();

			break;
		}
		
	}

	
}
