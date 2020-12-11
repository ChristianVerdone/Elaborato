package struttureEventi.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SceltaCliente extends JFrame implements ActionListener{

	private JFrame frame;
	private String operazione;

	/**
	 * Launch the application.
	 */
	public static void Scelta(String op) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaCliente window = new SceltaCliente(op);
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
	public SceltaCliente(String op) {
		operazione=op;
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Nuovo Cliente");
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("nuovocliente");
		btnNewButton.setBounds(119, 69, 163, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cliente gia' registrato");
		btnNewButton_1.addActionListener(this);
		btnNewButton_1.setActionCommand("registrato");
		btnNewButton_1.setBounds(119, 144, 163, 23);
		frame.getContentPane().add(btnNewButton_1);
	}
	
	public  void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
			case "nuovocliente":
					RegistrazioneClienteUI reg = new RegistrazioneClienteUI();
					reg.Registrazione();
					break;
		
			case "registrato": 
				RicercaClienteUI rc= new RicercaClienteUI(operazione);
				rc.Ricerca(operazione);
				break;
	}
		this.dispose();
		frame.dispose();
	}}