package personale.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JSpinner;

import personale.model.Account;
import personale.model.Account.Permessi;
import personale.model.Dipendente;
import repository.DAOAccount;
import repository.DAODipendenti;
import repository.DAOFactory;

public class RegistrazioneUI extends JFrame implements ActionListener{

	private JTextField tf_cf;
	private JLabel lbl_error_cf;
	private JPasswordField pf;
	private JTextField tf_name;
	private JTextField tf_surname;
	private JTextField tf_username;
	private DefaultComboBoxModel<String> cbm_description;
	private JSpinner spinner;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrazioneUI window = new RegistrazioneUI();
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
	public RegistrazioneUI() {
		this.setResizable(false);
		this.setBounds(100, 100, 450, 480);
		this.setTitle("Registra un nuovo dipendente");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.getContentPane().setLayout(null);

		/* Logo */
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(100, 10);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		this.getContentPane().add(lbl_logo);

		/* CF */
		JLabel lbl_cf = new JLabel("Codice fiscale:");
		lbl_cf.setBounds(110, 110, 200, 13);
		lbl_cf.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		this.getContentPane().add(lbl_cf);

		tf_cf = new JTextField();
		tf_cf.setBounds(110, 125, 200, 30);
		tf_cf.setColumns(32);
		this.getContentPane().add(tf_cf);

		lbl_error_cf = new JLabel();
		lbl_error_cf.setBounds(320, 125, 25, 25);
		lbl_error_cf.setIcon(new ImageIcon("res/dialog-error.png"));
		lbl_error_cf.setVisible(false);
		this.getContentPane().add(lbl_error_cf);

		/* Name */
		JLabel lbl_name = new JLabel("Nome:");
		lbl_name.setBounds(110, 160, 90, 13);
		lbl_name.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(lbl_name);

		tf_name = new JTextField();
		tf_name.setColumns(32);
		tf_name.setBounds(110, 175, 90, 30);
		getContentPane().add(tf_name);

		/* Surname */
		JLabel lbl_surname = new JLabel("Cognome:");
		lbl_surname.setBounds(220, 160, 90, 13);
		lbl_surname.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(lbl_surname);

		tf_surname = new JTextField();
		tf_surname.setColumns(32);
		tf_surname.setBounds(220, 175, 90, 30);
		getContentPane().add(tf_surname);

		/* Task */
		JLabel lbl_task = new JLabel("Mansione:");
		lbl_task.setBounds(110, 213, 137, 13);
		lbl_task.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(lbl_task);

		String[] choices = {"Addetto reception", "Pulizia camere", "Cuoco", "Cameriere", "Guida escursione", "Responsabile evento", "Amministratore"};

		cbm_description = new DefaultComboBoxModel<String>(choices);
		JComboBox<String> cb_task = new JComboBox<String>();
		cb_task.setModel(cbm_description);
		cb_task.setBounds(110, 230, 128, 21);
		this.getContentPane().add(cb_task);	

		/* Salary */
		JLabel lbl_salary = new JLabel("Stipendio:");
		lbl_salary.setBounds(227, 213, 83, 13);
		lbl_salary.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(lbl_salary);

		spinner = new JSpinner();
		spinner.setBounds(248, 231, 62, 20);
		spinner.setValue(1400);
		getContentPane().add(spinner);

		/* Username */
		JLabel lbl_username = new JLabel("Username:");
		lbl_username.setBounds(110, 262, 200, 13);
		lbl_username.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		getContentPane().add(lbl_username);

		tf_username = new JTextField();
		tf_username.setColumns(32);
		tf_username.setBounds(110, 277, 200, 30);
		getContentPane().add(tf_username);

		/* Password */
		JLabel lbl_password = new JLabel("Password:");
		lbl_password.setBounds(110, 314, 200, 13);
		lbl_password.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		this.getContentPane().add(lbl_password);

		pf = new JPasswordField();
		pf.setBounds(110, 329, 200, 30);
		this.getContentPane().add(pf);

		/* Sign up button */
		JSeparator separator = new JSeparator();
		separator.setBounds(110, 369, 200, 2);
		this.getContentPane().add(separator);

		JButton btn_signup = new JButton("Registra");
		btn_signup.setBounds(167, 390, 85, 25);
		btn_signup.addActionListener(this);
		this.getContentPane().add(btn_signup);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String msg = "";

		String cf = tf_cf.getText().toString();
		if(cf.length() != 16) msg = "Il codice fiscale deve avere 16 caratteri\n";

		String name = tf_name.getText().toString();
		if(name.length() == 0) msg += "Inserisci un nome\n";
		else if(name.length() > 30) msg += "Dimensione massima del nome: 30 caratteri\n";

		String surname = tf_surname.getText().toString();
		if(surname.length() == 0) msg += "Inserisci un cognome\n";
		else if (surname.length() > 30) msg += "Dimensione massima del cognome: 30 caratteri\n";

		String task = cbm_description.getSelectedItem().toString();
		Permessi prm = Permessi.NONE;
		if(task == "Addetto reception") prm = Permessi.REDUCED;
		else if(task == "Amministratore") prm = Permessi.ALL;

		Integer salary = (Integer) spinner.getValue();
		if(salary < 400) msg += "Minimo salariale: 400\n";
		else if(salary > 3500) msg += "Massimo salariale: 3500\n";

		String username = tf_username.getText().toString();
		if(username.length() == 0) msg += "Inserisci uno username\n";
		else if(username.length() > 30) msg += "Dimensione massima username: 16 caratteri\n";

		String password = String.valueOf(pf.getPassword());
		if(password.length() < 6) msg += "Dimensione minima password: 6 caratteri";
		else if(username.length() > 30) msg += "Dimensione massima password: 30 caratteri";

		if(!msg.equals("")) {
			JOptionPane.showMessageDialog(this, msg);
			return;
		}

		DAOAccount dao_account = DAOFactory.getDAOAccount();
		Account curr_user = dao_account.doRetrieveByUsername(username);

		if(curr_user != null) {
			JOptionPane.showMessageDialog(this, "Username non più disponibile");
			return;
		}

		DAODipendenti dao_dipendente = DAOFactory.getDAODipendenti();
		if(dao_dipendente.update(new Dipendente(cf, name, surname, task, salary)) == 0){
			System.out.println("Errore nella creazione del dipendente");
		}
		else {
			if(dao_account.update(cf, new Account(username, password, prm)) == 0) {
				System.out.println("Errore nella creazione dell'account");
			}
			else { 
				JOptionPane.showMessageDialog(this, "Registrazione avvenuta con successo");
				this.dispose();
			}
		}
	}
}