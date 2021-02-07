package personale.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import personale.model.Account;
import repository.DAOFactory;

import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class LoginUI extends JFrame implements ActionListener {
	private JTextField tf_username;
	private JLabel lbl_error_username;
	private JPasswordField pf;
	private JLabel lbl_error_password;
	private JButton btn_login;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginUI window = new LoginUI();
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
	public LoginUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.setResizable(false);
		this.setBounds(100, 100, 450, 330);
		this.setTitle("Login");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		/* Logo */
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(97, 10);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		this.getContentPane().add(lbl_logo);

		/* Username */
		JLabel lbl_username = new JLabel("Username");
		lbl_username.setBounds(113, 110, 104, 13);
		lbl_username.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		this.getContentPane().add(lbl_username);

		tf_username = new JTextField();
		tf_username.setBounds(113, 125, 200, 30);
		tf_username.setColumns(32);
		tf_username.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void removeUpdate(DocumentEvent e) { /* Do nothing */ }
			@Override public void changedUpdate(DocumentEvent e) { /* Do nothing */}
			@Override 
			public void insertUpdate(DocumentEvent e) {
				if(lbl_error_username.isVisible()) lbl_error_username.setVisible(false);
			}	
		});
		this.getContentPane().add(tf_username);

		lbl_error_username = new JLabel("");
		lbl_error_username.setBounds(323, 125, 25, 25);
		lbl_error_username.setIcon(new ImageIcon("res/dialog-error.png"));
		lbl_error_username.setVisible(false);
		this.getContentPane().add(lbl_error_username);

		/* Password */
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setBounds(113, 165, 104, 13);
		lbl_password.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		this.getContentPane().add(lbl_password);

		pf = new JPasswordField();
		pf.setBounds(113, 180, 200, 30);
		pf.getDocument().addDocumentListener(new DocumentListener() {
			@Override public void removeUpdate(DocumentEvent e) { /* Do nothing */ }
			@Override public void changedUpdate(DocumentEvent e) { /* Do nothing */ }
			@Override
			public void insertUpdate(DocumentEvent e) {
				if(lbl_error_password.isVisible()) lbl_error_password.setVisible(false);			
			}		
		});
		this.getContentPane().add(pf);

		lbl_error_password = new JLabel();
		lbl_error_password.setBounds(323, 180, 25, 25);
		//lbl_error_password.setIcon(new ImageIcon(new ImageIcon("dialog-error.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		lbl_error_password.setIcon(new ImageIcon("res/dialog-error.png"));
		lbl_error_password.setVisible(false);
		this.getContentPane().add(lbl_error_password);

		/* Send button */
		JSeparator separator = new JSeparator();
		separator.setBounds(113, 230, 200, 2);
		this.getContentPane().add(separator);

		btn_login = new JButton("Login");
		btn_login.setBounds(165, 240, 85, 25);
		btn_login.addActionListener(this);
		this.getContentPane().add(btn_login);
	}

	private void endLoginProcessing(String msg, boolean usn, boolean pwd) {
		JOptionPane.showMessageDialog(this, msg);
		lbl_error_username.setVisible(usn);
		lbl_error_password.setVisible(pwd);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String username = tf_username.getText().toString();
		String password = String.valueOf(pf.getPassword());
		String msg = "";
		boolean error_username = true;
		boolean error_password = true;

		/* Controllo validità inserimento */
		if(username.length() < 3) msg = "Username di almeno 3 caratteri\n";
		else if(username.length() > 16) msg = "Username di massimo 16 caratteri\n";
		else error_username = false;

		if(password.length() < 6) msg += "Password di almeno 6 caratteri";
		else if(password.length() > 30) msg += "Password di massimo 30 caratteri";
		else error_password = false;	

		if(!msg.equals("")) { 
			endLoginProcessing(msg, error_username, error_password);
			return;
		}

		/* Controllo su database MySQL */	
		Account curr_user = DAOFactory.getDAOAccount().doRetrieveByUsername(username);

		if(curr_user == null) {
			endLoginProcessing("Non esiste alcun utente: " + username, true, error_password);
			return;
		} else if (curr_user.getPassword().equals(password)) {
			JOptionPane.showMessageDialog(this, "Accesso riuscito");
			switch(curr_user.getTipologiaPermessi()) {
			case ALL: 
				new HomeAdminUI(curr_user);
				break;
			case REDUCED: 
				new HomeAddettoUI(curr_user);
				break;
			default : 
				new HomeUI(curr_user);	
			}
			this.dispose();
		} else {
			endLoginProcessing("Password errata", error_username, true);
			return;
		}
	}
}