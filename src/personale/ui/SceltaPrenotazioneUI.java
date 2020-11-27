package personale.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.JButton;

public class SceltaPrenotazioneUI extends JFrame implements ActionListener{

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public  void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaPrenotazioneUI window = new SceltaPrenotazioneUI();
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
	public SceltaPrenotazioneUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnAbitazione = new JButton("Prenotazione Abitazione");
		btnAbitazione.setBounds(109, 58, 202, 23);
		btnAbitazione.setActionCommand("abitazione");
		btnAbitazione.addActionListener(this);
		frame.getContentPane().add(btnAbitazione);
		
		JButton btnStruttura = new JButton("Prenotazione Struttura");
		btnStruttura.setBounds(109, 92, 202, 23);
		btnStruttura.setActionCommand("struttura");
		btnStruttura.addActionListener(this);
		frame.getContentPane().add(btnStruttura);
		
		JButton btnRistorante = new JButton("Prenotazione Ristorante");
		btnRistorante.setBounds(109, 126, 202, 23);
		btnRistorante.setActionCommand("ristorante");
		btnRistorante.addActionListener(this);
		frame.getContentPane().add(btnRistorante);
	}
	
	public void actionPerformed(ActionEvent e) {
		
		switch(e.getActionCommand()) {
		case "abitazione":
			AbitazioneUI a=new AbitazioneUI();
			a.start();
			this.dispose();
			break;
		case "struttura":
			/* To do */
			break;	
		case "ristorante":
			/* To do */
			break;
		}
	}
}