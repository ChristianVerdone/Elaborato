package struttureEventi.ui;

import java.awt.EventQueue;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;

import contabilità.Cliente;
import struttureEventi.classes.PrenotazioneRistorante;
import struttureEventi.classes.Ristorante;
import struttureEventi.classes.Tavolo;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RistoranteUI {

	private Ristorante r;
	private HashMap<String, Cliente> clienti;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RistoranteUI window = new RistoranteUI();
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
					RistoranteUI window = new RistoranteUI();
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
	public RistoranteUI() {
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
		frame.setTitle("Prenotazione Ristorante");
		
		JLabel lblNewLabel = new JLabel("Selezionare il tavolo:");
		lblNewLabel.setBounds(10, 12, 118, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(176, 11, 248, 59);
		frame.getContentPane().add(scrollPane);
		
		HashMap<Integer, Tavolo> tavoli = new HashMap<Integer, Tavolo>();
		Tavolo t = new Tavolo(1, 6);
		tavoli.put(t.getnTavolo(), t);
		t = new Tavolo(2, 8);
		tavoli.put(t.getnTavolo(), t);
		
		r = new Ristorante(tavoli);
		
		DefaultListModel<Integer> listModel = new DefaultListModel<Integer>();
		listModel.addAll(r.getTavoli().keySet());
		
		JList listT = new JList(listModel);
		scrollPane.setViewportView(listT);
		
		JLabel lblNewLabel_1 = new JLabel("Selezionare il cliente:");
		lblNewLabel_1.setBounds(10, 88, 118, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(175, 88, 249, 59);
		frame.getContentPane().add(scrollPane_1);
		
		Cliente c = new Cliente("aaaa", "Mario", "Bianco");
		clienti = new HashMap<String, Cliente>();
		clienti.put(c.getNome(), c);
		
		DefaultListModel<String> listmodelC = new DefaultListModel<String>();
		listmodelC.addAll(clienti.keySet());
		
		JList listC = new JList(listmodelC);
		scrollPane_1.setViewportView(listC);
		
		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random n = new Random();
				int i = n.nextInt();
				String s = listT.getSelectedValue().toString();
				System.out.println(s);
				int pos = Integer.parseInt(s);
				Tavolo t = r.getTavoli().get(pos);
				System.out.println(t.toString());
				s = listC.getSelectedValue().toString();
				System.out.println(s);
				Cliente c = clienti.get(s);
				System.out.println(c.toString());
				PrenotazioneRistorante p = new PrenotazioneRistorante(Integer.toString(i), c, t.getnTavolo());
				System.out.println(p.toString());
			}
		});
		btnNewButton.setBounds(0, 238, 434, 23);
		frame.getContentPane().add(btnNewButton);
	}
}
