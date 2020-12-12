package struttureEventi.ui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import repository.DAOFactory;
import struttureEventi.classes.Evento;
import util.GenerateRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;

public class RegistrazioneEvento extends JFrame implements ActionListener{

	private JFrame frame;
	private JTextField tfdescrizione;
	private JTextField tfnome;
	private JTextField tftipo;
	private String nome;
	private String tipo;
	private String descrizione;
	private JTextPane tp_caratteri;

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
		frame.setBounds(100, 100, 450, 442);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Registrazione evento");
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(101, 39);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lbl_logo);
		
		JLabel lblNewLabel = new JLabel("Descrizione");
		lblNewLabel.setBounds(36, 235, 114, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nome evento");
		lblNewLabel_1.setBounds(36, 150, 140, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Tipologia evento");
		lblNewLabel_2.setBounds(247, 150, 95, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		
		tfdescrizione = new JTextField();
		tfdescrizione.setBounds(36, 260, 351, 56);
		frame.getContentPane().add(tfdescrizione);
		tfdescrizione.setColumns(10);
		
		
		
		tfnome = new JTextField();
		tfnome.setBounds(34, 175, 142, 20);
		frame.getContentPane().add(tfnome);
		tfnome.setColumns(10);
		
		tftipo = new JTextField();
		tftipo.setBounds(247, 175, 140, 20);
		frame.getContentPane().add(tftipo);
		tftipo.setColumns(10);
		
		JButton btnNewButton = new JButton("Registra");
		btnNewButton.setActionCommand("registra");
		btnNewButton.setBounds(172, 369, 89, 23);
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
	   if(descrizione.length()>200) {
		   JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
	   		break;}
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