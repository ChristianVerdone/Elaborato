package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SceltaCliente extends JFrame implements ActionListener{

	private JFrame frmSceltaTipologiaCliente;
	private String operazione;

	/**
	 * Launch the application.
	 */
	public static void Scelta() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaCliente window = new SceltaCliente();
					window.frmSceltaTipologiaCliente.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SceltaCliente() {
		
		frmSceltaTipologiaCliente = new JFrame();
		frmSceltaTipologiaCliente.setTitle("Scelta tipologia cliente");
		frmSceltaTipologiaCliente.setBounds(100, 100, 450, 225);
		frmSceltaTipologiaCliente.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSceltaTipologiaCliente.getContentPane().setLayout(null);
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(101, 28);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frmSceltaTipologiaCliente.getContentPane().add(lbl_logo);
		
		JButton btnNewButton = new JButton("Nuovo Cliente");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("nuovocliente");
		btnNewButton.setBounds(26, 138, 163, 23);
		frmSceltaTipologiaCliente.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cliente gia' registrato");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("registrato");
		btnNewButton_1.setBounds(237, 138, 163, 23);
		frmSceltaTipologiaCliente.getContentPane().add(btnNewButton_1);
	}
	public  void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()) {
		case "nuovocliente":
			RegistrazioneClienteUI reg = new RegistrazioneClienteUI();
			reg.Registrazione();
			break;

		case "registrato": 
			RicercaClienteUI rc= new RicercaClienteUI();
			rc.Ricerca();
			break;
		}
		this.dispose();
		frmSceltaTipologiaCliente.dispose();
	}
}