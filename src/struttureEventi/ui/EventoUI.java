package struttureEventi.ui;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import contabilità.Cliente;
import struttureEventi.classes.Biglietto;
import struttureEventi.classes.Evento;
import struttureEventi.classes.PrenotazioneEvento;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class EventoUI {
	
	private HashMap<String, Cliente> clienti;
	private HashMap<String, Evento> eventi;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventoUI window = new EventoUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void start() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EventoUI window = new EventoUI();
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
	public EventoUI() {
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
		frame.setTitle("Prenotazione Evento");
		
		JLabel lblNewLabel = new JLabel("Seleziona l'evento in programma:");
		lblNewLabel.setBounds(10, 11, 165, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(195, 10, 229, 50);
		frame.getContentPane().add(scrollPane);
		
		HashMap<String, Biglietto> biglietti = new HashMap<String, Biglietto>();
		Biglietto b = new Biglietto("1", 12, true);
		Biglietto b2 = new Biglietto("2", 13, true);
		biglietti.put(b.getIdBiglietto(), b);
		biglietti.put(b2.getIdBiglietto(), b2);
		
		Evento event =new Evento("001", "Escursione in montagna", "escursione", "montagna", biglietti);
		eventi = new HashMap<String, Evento>();
		eventi.put(event.getIdEvento(), event);
		
		DefaultListModel<String> listmodel = new DefaultListModel<>();
		listmodel.addAll(eventi.keySet());
		
		JList listE = new JList(listmodel);
		scrollPane.setViewportView(listE);
		listE.setSelectedIndex(0);
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona il biglietto:");
		lblNewLabel_1.setBounds(10, 76, 165, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(195, 76, 229, 50);
		frame.getContentPane().add(scrollPane_1);
		
		String s = listE.getSelectedValue().toString();
		Evento e = eventi.get(s);
		
		DefaultListModel<String> listmodelB = new DefaultListModel<String>();
		listmodelB.addAll(e.getBiglietti().keySet()); //metodo per prendere solo i biglietti disponibili
		
		JList listB = new JList(listmodelB);
		scrollPane_1.setViewportView(listB);
		
		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente:");
		lblNewLabel_2.setBounds(10, 144, 165, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(195, 144, 229, 50);
		frame.getContentPane().add(scrollPane_2);
		
		Cliente c = new Cliente("aaaa", "Mario", "Bianco");
		clienti = new HashMap<String, Cliente>();
		clienti.put(c.getNome(), c);
		
		DefaultListModel<String> listmodelC = new DefaultListModel<String>();
		listmodelC.addAll(clienti.keySet());
		
		JList listC = new JList(listmodelC);
		scrollPane_2.setViewportView(listC);
		
		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int i = r.nextInt();
				String s = listE.getSelectedValue().toString();
				Evento ev = eventi.get(s);
				s = listB.getSelectedValue().toString();
				Biglietto b = ev.getBiglietti().get(s);
				s = listC.getSelectedValue().toString();
				Cliente c = clienti.get(s);
				PrenotazioneEvento p = new PrenotazioneEvento(Integer.toString(i), c, ev.getIdEvento(), b.getIdBiglietto());
				System.out.println(p.toString());
			}
		});
		btnNewButton.setBounds(0, 238, 434, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
