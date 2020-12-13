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

import repository.DAOFactory;
import struttureEventi.classes.ContoRistorante;
import struttureEventi.classes.PrenotazioneRistorante;

import javax.swing.JButton;

public class ContoRistoranteUI extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField idconto;
	private JTextField importo;
	private JTextField idprenotazione;

	/**
	 * Launch the application.
	 */
	public  void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContoRistoranteUI window = new ContoRistoranteUI();
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
	public ContoRistoranteUI() {
		
		frame = new JFrame();
		frame.setName("Registrazione conto ristorante");
		frame.setBounds(100, 100, 490, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		idconto = new JTextField();
		idconto.setBounds(38, 184, 86, 20);
		frame.getContentPane().add(idconto);
		idconto.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Numero Conto");
		lblNewLabel.setBounds(38, 165, 86, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Importo");
		lblNewLabel_1.setBounds(172, 165, 46, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		importo = new JTextField();
		importo.setBounds(172, 184, 86, 20);
		frame.getContentPane().add(importo);
		importo.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Numero prenotazione");
		lblNewLabel_2.setBounds(300, 165, 137, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		idprenotazione = new JTextField();
		idprenotazione.setBounds(300, 184, 86, 20);
		frame.getContentPane().add(idprenotazione);
		idprenotazione.setColumns(10);
		
		JButton btnNewButton = new JButton("Registra conto");
		btnNewButton.setBounds(176, 227, 121, 23);
		btnNewButton.addActionListener(this);
		btnNewButton.setActionCommand("registra");
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblposate1 = new JLabel("");
		lblposate1.setBounds(80, 21, 96, 96);
		lblposate1.setIcon(new ImageIcon("images/posateBianche.png"));
		frame.getContentPane().add(lblposate1);
		
		JLabel lblcameriere = new JLabel("");
		lblcameriere.setBounds(189, 21, 96, 96);
		lblcameriere.setIcon(new ImageIcon("images/cameriere.png"));
		frame.getContentPane().add(lblcameriere);
		
		JLabel lblposate2 = new JLabel("");
		lblposate2.setBounds(298, 21, 96, 96);
		lblposate2.setIcon(new ImageIcon("images/posateBianche.png"));
		frame.getContentPane().add(lblposate2);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();
		switch (command) {
		case "registra":
			String idcontoristorante=idconto.getText();
			if(idcontoristorante==null) {
				JOptionPane.showMessageDialog(null, "Inserisci il numero del conto.");
				break;
			}
			else if(idcontoristorante.length()<5 || idcontoristorante.length()>5) {
				JOptionPane.showMessageDialog(null, "Il numero del conto deve contenere 5 caratteri.");
				break;
			}
			else if(DAOFactory.getDAOContoRistorante().doRetrieveById(idcontoristorante)== null) {
				JOptionPane.showMessageDialog(null, "Il conto numero " + idcontoristorante + "è già stato registrato.");
				break;
			}
			String costoS=importo.getText();
			float costo=Float.parseFloat(costoS);
			
			if(costo==0) {
				JOptionPane.showMessageDialog(null, "Inserisci l'importo.");
				break;
			}
			
			String idprenotazioneRistorante= idprenotazione.getText().toString();
			System.out.println(idprenotazioneRistorante);
			if(idprenotazioneRistorante == null) {
				JOptionPane.showMessageDialog(null, "Inserisci il numero della prenotazione.");
				break;
			}
			else if(idprenotazioneRistorante.length()<5 || idprenotazioneRistorante.length()>5 ) {
				JOptionPane.showMessageDialog(null, "Il numero della prenotazione deve contenere 5 caratteri.");
				break;
			}
			PrenotazioneRistorante pr= DAOFactory.getDAOPrenotazioneRistorante().doRetrieveById(idprenotazioneRistorante);
			if(pr==null) {
				JOptionPane.showMessageDialog(null, "La prenotazione numero " + idprenotazioneRistorante + " non esiste.");
				break;
			}
			ContoRistorante cr = new ContoRistorante(idcontoristorante, costo, pr);
			System.out.println(cr.toString());
			int check= DAOFactory.getDAOContoRistorante().updateContoRistorante(cr);
			if(check!=0) {
				JOptionPane.showMessageDialog(null, "Registrazione effettuata");
				System.out.println(cr.toString());
			}
			else {
				JOptionPane.showMessageDialog(null, "Errore durante la registrazione del conto.");
				break;
			}
			
		}
		this.dispose();
	}
}
