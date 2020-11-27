package personale.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JTree;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import contabilità.Cliente;
import struttureEventi.PrenotazioneAbitazione;

import javax.swing.JTextField;
import javax.swing.JMenu;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.Choice;
import java.awt.Dialog.ModalityType;

import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.Action;
import javax.swing.JList;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;


public class AbitazioneUI   extends JFrame implements ActionListener {
	private Cliente cliente=null;
	private JFrame frame;
	private JTextField textField_nome;
	private JTextField textField_cognome;
	private JTextField textField_cf;
	private JComboBox cb_abitazione;
	private Date dataInizio;
	private Date dataFine;
	private String abitazione;
	private RegistrazioneClienteUI reg;
	/**
	 * Launch the application.
	 */
	
		public  static void main(String [] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AbitazioneUI window = new AbitazioneUI();
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
						AbitazioneUI window = new AbitazioneUI();
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
		public AbitazioneUI() {
			initialize();
		}


	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cb_abitazione = new JComboBox();
		cb_abitazione.setModel(new DefaultComboBoxModel(new String[] {"Camera", "Appartamento"}));
		cb_abitazione.setBounds(10, 71, 108, 22);
		frame.getContentPane().add(cb_abitazione);
		abitazione=(String) cb_abitazione.getSelectedItem();
	
		JLabel lblAbitazione = new JLabel("Prenotazione di un'abitazione");
		lblAbitazione.setBounds(10, 11, 179, 14);
		frame.getContentPane().add(lblAbitazione);
		
		JLabel lblSeleziona = new JLabel("Seleziona l'abitazione");
		lblSeleziona.setBounds(10, 50, 108, 14);
		frame.getContentPane().add(lblSeleziona);
		
		JLabel lblDataInizio = new JLabel("Data di inizio");
		lblDataInizio.setBounds(235, 50, 66, 14);
		frame.getContentPane().add(lblDataInizio);
		
		JCalendar inizio = new JCalendar();
		inizio.setBounds(235, 71, 184, 153);
		frame.getContentPane().add(inizio);
		dataInizio=inizio.getDate();
		
		JLabel lblDataFine = new JLabel("Data di fine");
		lblDataFine.setBounds(429, 50, 71, 14);
		frame.getContentPane().add(lblDataFine);
		
		JCalendar fine = new JCalendar();
		fine.setBounds(429, 71, 184, 153);
		frame.getContentPane().add(fine);
		dataFine=fine.getDate();
		
		
		
		JLabel lblNewLabel_4 = new JLabel("Clienti");
		lblNewLabel_4.setBounds(10, 104, 46, 14);
		frame.getContentPane().add(lblNewLabel_4);
		
		
		JButton btn_Prenota = new JButton("Prenota");
		btn_Prenota.addActionListener(this);
		btn_Prenota.setBounds(253, 250, 89, 23);
		frame.getContentPane().add(btn_Prenota);
		
		JButton btnNewButton = new JButton("Cliente gia' registrato");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				RicercaClienteUI ricerca=new RicercaClienteUI();
				ricerca.Ricerca();
			}
		});
		btnNewButton.setBounds(10, 123, 158, 23);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("Nuovo cliente");
		btnNewButton_2.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				reg= new RegistrazioneClienteUI();
				reg.Registrazione();
				reg.addWindowListener(new java.awt.event.WindowAdapter() {
					@Override
					public void windowClosed(java.awt.event.WindowEvent windowEvent) {
					if (JOptionPane.showConfirmDialog(frame,
					"Are you sure you want to close this window?", "Close Window?",
					JOptionPane.YES_NO_OPTION,
					JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
					System.exit(0);
					}
					cliente=reg.getCliente();
					}
					});
				
				
				
				
			}
		});
		btnNewButton_2.setBounds(10, 157, 158, 23);
		frame.getContentPane().add(btnNewButton_2);
	
	

	}
	
	
	public  void actionPerformed(ActionEvent e) {

		
		String idPrenotazione="prenotazione1";
		
		cliente=reg.getCliente();
		String a = abitazione;
		PrenotazioneAbitazione pa= new PrenotazioneAbitazione(idPrenotazione, cliente, a); 
		
		System.out.println(pa.toString().toString());
		JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");;
			this.dispose();
			frame.dispose();
		}
    }


