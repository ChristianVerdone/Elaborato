package struttureEventi.ui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import javax.swing.JComboBox;
import javax.swing.JList;
import javax.swing.JScrollPane;

import contabilità.Cliente;
import struttureEventi.classes.PrenotazioneSv;
import struttureEventi.classes.Tessera;

public class StrutturaUI {
	
	private HashMap<String, Tessera> tessere;
	private HashMap<String, Cliente> clienti;
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StrutturaUI window = new StrutturaUI();
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
					StrutturaUI window = new StrutturaUI();
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
	public StrutturaUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Prenotazione Struttura");
		
		String[] strutture = {"Campo da tennis", "Campo calcetto"};
		
		JComboBox struttbox = new JComboBox(strutture);
		struttbox.setBounds(141, 7, 131, 22);
		frame.getContentPane().add(struttbox);
		
		
		JLabel lblNewLabel_1 = new JLabel("Seleziona la struttura");
		lblNewLabel_1.setBounds(10, 11, 131, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		Cliente c = new Cliente("aaaa", "Mario", "Bianco");
		clienti = new HashMap<String, Cliente>();
		clienti.put(c.getNome(), c);
		
		DefaultListModel<String> listmodel = new DefaultListModel<>();
		listmodel.addAll(clienti.keySet());
		
		JLabel lblNewLabel_2 = new JLabel("Seleziona il cliente");
		lblNewLabel_2.setBounds(10, 50, 112, 14);
		frame.getContentPane().add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(141, 48, 219, 55);
		frame.getContentPane().add(scrollPane);
		
		JList listC = new JList(listmodel);
		scrollPane.setViewportView(listC);
		
		JLabel lblNewLabel = new JLabel("Seleziona la tessera");
		lblNewLabel.setBounds(12, 130, 121, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(141, 128, 220, 55);
		frame.getContentPane().add(scrollPane_1);
		
		Tessera t = new Tessera("01", "tessera 1");
		tessere = new HashMap<String, Tessera>();
		tessere.put(t.getId(), t);
		//ci vuole un metodo per inserire solo le tessere disponibili
		
		DefaultListModel<String> listmodel2 = new DefaultListModel<>();
		listmodel2.addAll(tessere.keySet());
		
		JList listT = new JList(listmodel2);
		scrollPane_1.setViewportView(listT);
		
		JButton btnNewButton = new JButton("Prenota");
		btnNewButton.setBounds(0, 238, 434, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Random r = new Random();
				int i = r.nextInt();
				String idT = listT.getSelectedValue().toString();
				Tessera t = tessere.get(idT);
				String idC = listC.getSelectedValue().toString();
				Cliente c = clienti.get(idC);
				PrenotazioneSv p=new PrenotazioneSv(Integer.toString(i),c,t , struttbox.getSelectedItem().toString());
				System.out.println(p.toString());
			}
		});
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(btnNewButton);
	}
}
