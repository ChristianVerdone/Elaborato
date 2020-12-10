package struttureEventi.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import repository.DAOFactory;
import struttureEventi.classes.Evento;
import util.GenerateRandom;

import javax.swing.JButton;

public class RegistrazioneEvento extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField tfdescrizione;
	private JTextField tfnome;
	private JTextField tftipo;
	private String nome;
	private String tipo;
	private String descrizione;

	/**
	 * Launch the application.
	 */
	public  void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegistrazioneEvento window = new RegistrazioneEvento();
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
	public RegistrazioneEvento() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblNewLabel = new JLabel("Descrizione");
		lblNewLabel.setBounds(189, 111, 114, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome ");
		lblNewLabel_1.setBounds(80, 39, 140, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tipo");
		lblNewLabel_2.setBounds(305, 39, 46, 14);
		frame.getContentPane().add(lblNewLabel_2);

		tfdescrizione = new JTextField();
		tfdescrizione.setBounds(80, 136, 309, 20);
		frame.getContentPane().add(tfdescrizione);
		tfdescrizione.setColumns(10);

		tfnome = new JTextField();
		tfnome.setBounds(83, 61, 86, 20);
		frame.getContentPane().add(tfnome);
		tfnome.setColumns(10);

		tftipo = new JTextField();
		tftipo.setBounds(303, 61, 86, 20);
		frame.getContentPane().add(tftipo);
		tftipo.setColumns(10);

		JButton btnNewButton = new JButton("Registra");
		btnNewButton.setActionCommand("registra");
		btnNewButton.setBounds(177, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command=e.getActionCommand();
		switch (command) {
		case "registra":

			GenerateRandom g = new GenerateRandom();
			String id="EV" + g.GenerateRandom();
			nome=tfnome.getText();
			if(nome.length()>30) {
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
				break;}
			if(nome==null) {
				JOptionPane.showMessageDialog(this, "Inserire il nome.");
				break;}

			tipo=tftipo.getText();
			if(tipo.length()>20) {
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
				break;}
			if(tipo==null){
				JOptionPane.showMessageDialog(this, "Inserire il tipo.");
				break;}
			descrizione=tfdescrizione.getText();
			if(descrizione==null){
				JOptionPane.showMessageDialog(this, "Inserire la descrizione.");
				break;}
			if(descrizione.length()>200)
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
			if(DAOFactory.getDAOEvento().doRetrieveById(id)!=null) {
				JOptionPane.showMessageDialog(this, "Evento con identificativo " + id + "già registrato");
				break;}

			Evento ev = new Evento(id, nome, tipo, descrizione);
			int check=DAOFactory.getDAOEvento().updateEvento(ev);
			if(check!=0) {
				JOptionPane.showMessageDialog(this, "Evento registrato!");
			}
			this.dispose();
			frame.dispose();
		}
	}
}	