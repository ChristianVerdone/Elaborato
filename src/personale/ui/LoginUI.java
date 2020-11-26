package personale.ui;

import java.awt.Color;
import java.awt.EventQueue;
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

import javax.swing.JPasswordField;
import javax.swing.JSeparator;
import javax.swing.JButton;

public class LoginUI extends JFrame implements ActionListener {
	private JTextField tf_username;
	private JLabel lbl_error_username;
	private JPasswordField pf;
	private JLabel lbl_error_password;

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
		this.setBounds(100, 100, 450, 300);
		this.setTitle("Login");
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);

		/* Logo */
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(68, 10);
		lbl_logo.setSize(280, 50);
		lbl_logo.setIcon(new ImageIcon("res/test-logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		this.getContentPane().add(lbl_logo);

		/* Username */
		JLabel lbl_username = new JLabel("Username");
		lbl_username.setBounds(113, 80, 60, 13);
		this.getContentPane().add(lbl_username);

		tf_username = new JTextField();
		tf_username.setBounds(113, 95, 200, 30);
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
		lbl_error_username.setBounds(323, 95, 25, 25);
		lbl_error_username.setIcon(new ImageIcon("res/dialog-error.png"));
		lbl_error_username.setVisible(false);
		this.getContentPane().add(lbl_error_username);

		/* Password */
		JLabel lbl_password = new JLabel("Password");
		lbl_password.setBounds(113, 135, 60, 13);
		this.getContentPane().add(lbl_password);

		pf = new JPasswordField();
		pf.setBounds(113, 150, 200, 30);
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
		lbl_error_password.setBounds(323, 150, 25, 25);
		//lbl_error_password.setIcon(new ImageIcon(new ImageIcon("dialog-error.png").getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT)));
		lbl_error_password.setIcon(new ImageIcon("res/dialog-error.png"));
		lbl_error_password.setVisible(false);
		this.getContentPane().add(lbl_error_password);

		/* Send button */
		JSeparator separator = new JSeparator();
		separator.setBounds(113, 200, 200, 2);
		this.getContentPane().add(separator);

		JButton btn_login = new JButton("Login");
		btn_login.setBounds(165, 210, 85, 25);
		btn_login.addActionListener(this);
		this.getContentPane().add(btn_login);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		/* Temporaneo */
		String username = tf_username.getText().toString();
		String password = String.valueOf(pf.getPassword());

		String msg = "";
		boolean error_username = true;
		boolean error_password = true;

		if(username.length() < 4) msg = "Username di almeno 3 caratteri\n";
		else if(username.length() > 32) msg = "Username di massimo 32 caratteri\n";
		else error_username = false;


		if(password.length() < 7) msg += "Password di almeno 6 caratteri";
		else if(password.length() > 32) msg += "Password di massimo 32 caratteri";
		else error_password = false;

		lbl_error_username.setVisible(error_username);
		lbl_error_password.setVisible(error_password);

		if(!msg.equals("")) JOptionPane.showMessageDialog(this, msg);
		else {
			/* Nel controller, inserire factory method*/
			JOptionPane.showMessageDialog(this, "Accesso riuscito");
			switch(username) {
			case "admin": new HomeAdminUI(new Account(username, password, Account.Permessi.ALL));
			break;
			case "reception": new HomeAdminUI(new Account(username, password, Account.Permessi.REDUCED));
			break;
			default : new HomeUI(new Account(username, password, Account.Permessi.NONE));
			}
		
			this.dispose();
		}
	}
}
