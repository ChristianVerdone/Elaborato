package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;

import struttureEventi.ui.EventoUI;
import struttureEventi.ui.RistoranteUI;
import struttureEventi.ui.SceltaCliente;
import struttureEventi.ui.StrutturaUI;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class SceltaPrenotazioneUI implements ActionListener{

	private JFrame frmSceltaTipologiaPrenotazione;

	/**
	 * Launch the application.
	 */
	public  void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SceltaPrenotazioneUI window = new SceltaPrenotazioneUI();
					window.frmSceltaTipologiaPrenotazione.setVisible(true);
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
		frmSceltaTipologiaPrenotazione = new JFrame();
		frmSceltaTipologiaPrenotazione.setTitle("Scelta tipologia prenotazione");
		frmSceltaTipologiaPrenotazione.setBounds(100, 100, 536, 275);
		frmSceltaTipologiaPrenotazione.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmSceltaTipologiaPrenotazione.getContentPane().setLayout(null);
		
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(144, 28);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frmSceltaTipologiaPrenotazione.getContentPane().add(lbl_logo);
		
		JButton btnAbitazione = new JButton("Prenotazione Abitazione");
		btnAbitazione.setBounds(34, 159, 202, 23);
		btnAbitazione.setActionCommand("abitazione");
		btnAbitazione.addActionListener(this);
		frmSceltaTipologiaPrenotazione.getContentPane().add(btnAbitazione);

		JButton btnStruttura = new JButton("Prenotazione Struttura");
		btnStruttura.setBounds(283, 159, 202, 23);
		btnStruttura.setActionCommand("struttura");
		btnStruttura.addActionListener(this);
		frmSceltaTipologiaPrenotazione.getContentPane().add(btnStruttura);

		JButton btnRistorante = new JButton("Prenotazione Ristorante");
		btnRistorante.setBounds(34, 204, 202, 23);
		btnRistorante.setActionCommand("ristorante");
		btnRistorante.addActionListener(this);
		frmSceltaTipologiaPrenotazione.getContentPane().add(btnRistorante);

		JButton btnEvento = new JButton("Prenotazione Evento");
		btnEvento.setActionCommand("evento");
		btnEvento.setBounds(283, 204, 202, 23);
		btnEvento.addActionListener(this);
		frmSceltaTipologiaPrenotazione.getContentPane().add(btnEvento);

		JLabel lblNewLabel = new JLabel("Scegliere la prenotazione che si desidera effettuare");
		frmSceltaTipologiaPrenotazione.getContentPane().add(lblNewLabel);
		lblNewLabel.setBounds(33, 11, 375, 36);
		lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 18));
	}

	public void actionPerformed(ActionEvent e) {

		switch(e.getActionCommand()) {
		case "abitazione":
			SceltaCliente sc= new SceltaCliente();
			sc.Scelta();
			break;
		case "struttura":
			new StrutturaUI().start();
			break;	
		case "ristorante":
			new RistoranteUI().start();
			break;
		case "evento":
			new EventoUI().start();
			break;	  
		}
		frmSceltaTipologiaPrenotazione.dispose();
	}
}