package contabilita;

import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import repository.DAOClienteImpl;
import repository.DAOContoTotale;
import repository.DAOContoTotaleImpl;
import repository.DAOFactory;
import util.GenerateRandom;

import java.awt.Font;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.util.HashMap;
import java.awt.event.ActionEvent;

public class PagamentoUI implements ListSelectionListener {


	private JFrame frame;
	private JLabel lblPagamentoConContanti;
	private JLabel lblPagamentoConCarta;
	private JTextField fieldContanti;
	private JTextField fieldCodiceCarta;
	private JTextField fieldConto;
	private DefaultTableModel dtm;
	private JTable table;
	private JTextField textCodiceFiscale;
	/**
	 * Launch the application.
	 */
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PagamentoUI window = new PagamentoUI();
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
	public PagamentoUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {


		frame = new JFrame();
		frame.setBounds(100, 100, 700, 350);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		lblPagamentoConContanti = new JLabel("Pagamento con contanti");
		lblPagamentoConContanti.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPagamentoConContanti.setBounds(458, 11, 184, 23);
		frame.getContentPane().add(lblPagamentoConContanti);

		lblPagamentoConCarta = new JLabel("Pagamento con carta");
		lblPagamentoConCarta.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblPagamentoConCarta.setBounds(458, 182, 169, 14);
		frame.getContentPane().add(lblPagamentoConCarta);


		JButton btnPage = new JButton("Pagamento Carta");

		btnPage.setBounds(464, 262, 148, 23);
		frame.getContentPane().add(btnPage);

		JButton btnNewButton = new JButton("Pagamento Contanti");
		btnNewButton.setBounds(464, 98, 163, 23);
		frame.getContentPane().add(btnNewButton);

		JLabel lblRichiediConto = new JLabel("Richiedi conto totale di un cliente");
		lblRichiediConto.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		lblRichiediConto.setBounds(23, 15, 241, 14);
		frame.getContentPane().add(lblRichiediConto);


		//bottone per la richiesta del conto
		JButton btnRichiedi = new JButton("Richiedi conto");
		btnRichiedi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				String codCliente=textCodiceFiscale.getText();

				DAOContoTotaleImpl contoEvento=new DAOContoTotaleImpl();
				double contoTotaleEvento=contoEvento.doRetrieveContoEventoByCf(codCliente);
				DAOContoTotaleImpl contoAbitazione=new DAOContoTotaleImpl();
				double contoTotaleAbitazione=contoAbitazione.doRetrieveContoAbitazioneByCf(codCliente);
				DAOContoTotaleImpl contoStruttura=new DAOContoTotaleImpl();
				double contoTotaleStruttura=contoStruttura.doRetrieveContoStrutturaCf(codCliente);
				DAOContoTotaleImpl contoRistorante=new DAOContoTotaleImpl();
				double contoTotaleRistorante=contoRistorante.doRetrieveContoRistoranteCf(codCliente);

				double contoTotale=contoTotaleEvento+contoTotaleAbitazione+contoTotaleStruttura+contoTotaleRistorante;
				String contoStringa=String.valueOf(contoTotale).toString();
				fieldConto.setText(contoStringa);

			}
		});

		btnRichiedi.setBounds(23, 279, 124, 23);
		frame.getContentPane().add(btnRichiedi);

		fieldContanti = new JTextField();
		fieldContanti.setBounds(500, 55, 86, 20);
		frame.getContentPane().add(fieldContanti);
		fieldContanti.setColumns(10);


		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				double contanti=Double.parseDouble(fieldContanti.getText());	//contanti che ha inserito il cliente
				double contoTotale=Double.parseDouble(fieldConto.getText());

				GenerateRandom g= new GenerateRandom();
				String id= "CT" + g.GenerateRandom();

				if(contanti<contoTotale) {
					JOptionPane.showMessageDialog(null, "L'importo inserito non è valido, riprova", "Errore",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Il pagamento è andato a buon fine");
					ContoTotale ct= new ContoTotale(id,(Double.parseDouble(fieldConto.getText())),LocalDate.now(),textCodiceFiscale.getText()); 
					System.out.println(ct.toString());	
					int check = DAOFactory.getDAOContoTotale().updateContiTotali(ct);
					if(check==0) 
						JOptionPane.showMessageDialog(null, "Errore nella registrazione del pagamento!");
					else if(check!=0) {
						JOptionPane.showMessageDialog(null, "Pagamento effettuato!");
					DAOFactory.getDAOCliente().delete(textCodiceFiscale.getText());
				}
				}
				frame.dispose();
				
			}


		});


		//Pagamento con la carta di credito passando il codice della carta
		//Se il codice è presente in lista il pagamento va a buon fine altrimenti messaggio di errore

		fieldCodiceCarta = new JTextField();
		fieldCodiceCarta.setBounds(500, 220, 86, 20);
		frame.getContentPane().add(fieldCodiceCarta);
		fieldCodiceCarta.setColumns(10);



		fieldConto = new JTextField();
		fieldConto.setBounds(297, 280, 86, 20);
		frame.getContentPane().add(fieldConto);
		fieldConto.setColumns(10);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 51, 372, 220);
		frame.getContentPane().add(scrollPane);

		table = new JTable();

		dtm=new DefaultTableModel() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};
		dtm.setColumnIdentifiers(new String[]{"Codice Fiscale","Nome","Cognome"});
		HashMap<String,Cliente> clienti = DAOFactory.getDAOCliente().doRetrieveAll();
		//Da spostare in un controller
		for(Cliente cli : clienti.values()) {
			dtm.addRow(new Object[]{cli.getCf(), cli.getNome() , cli.getCognome()});
		}
		table.setModel(dtm);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.getSelectionModel().addListSelectionListener(this);
		scrollPane.setViewportView(table);

		textCodiceFiscale = new JTextField();
		textCodiceFiscale.setBounds(180, 280, 86, 20);
		frame.getContentPane().add(textCodiceFiscale);
		textCodiceFiscale.setColumns(10);


		btnPage.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String codiceCarta=fieldCodiceCarta.getText();	//ottengo il codice della carta
				GenerateRandom g= new GenerateRandom();
				String id= "CT" + g.GenerateRandom();
				if(codiceCarta.length()!=16) {
					JOptionPane.showMessageDialog(null, "Il numero della carta è sbagliato riprova", "Errore",JOptionPane.ERROR_MESSAGE);
				}else {
					JOptionPane.showMessageDialog(null, "Il pagamento è andato a buon fine");
					ContoTotale ct= new ContoTotale(id,(Double.parseDouble(fieldConto.getText())),LocalDate.now(),textCodiceFiscale.getText()); 
					System.out.println(ct.toString());	
					int check = DAOFactory.getDAOContoTotale().updateContiTotali(ct);
					if(check==0) 
						JOptionPane.showMessageDialog(null, "Errore nella registrazione del pagamento!");
					else if(check!=0) {
						JOptionPane.showMessageDialog(null, "Pagamento effettuato!");
						DAOFactory.getDAOCliente().delete(textCodiceFiscale.getText());
					}	
				}

			}
		});
	}


	@Override
	public void valueChanged(ListSelectionEvent arg0) {
		textCodiceFiscale.setText(table.getValueAt(table.getSelectedRow(), 0).toString()); 
	}
}
