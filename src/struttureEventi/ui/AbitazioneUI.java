package struttureEventi.ui;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import com.toedter.calendar.JCalendar;

import contabilita.Cliente;
import repository.DAOFactory;
import struttureEventi.classes.PrenotazioneAbitazione;
import util.GenerateRandom;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import java.time.LocalDate;
import java.time.ZoneId;

public class AbitazioneUI   extends JFrame implements ActionListener {
	private Cliente cliente;
	private JFrame frame;
	private JComboBox cb_abitazione;
	private LocalDate dataInizio;
	private LocalDate dataFine;
	private String abitazione;
	
	/**
	 * Launch the application.
	 */
	

		public void start(Cliente c) {
			
			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
					//	Cliente cliente= setCliente(c);
						AbitazioneUI window = new AbitazioneUI(c);
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
		public AbitazioneUI(Cliente c) {
		cliente=c;	
	/**
	 * Initialize the contents of the frame.
	 */
		
		frame = new JFrame();
		frame.setBounds(100, 100, 653, 325);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		cb_abitazione = new JComboBox();
		cb_abitazione.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				abitazione=(String) cb_abitazione.getSelectedItem();
			}
		});
		cb_abitazione.setModel(new DefaultComboBoxModel(new String[] {"Camera doppia", "Appartamento", "Suite", "Camera singola", "Standard", "Deluxe"}));
		cb_abitazione.setBounds(10, 71, 108, 22);
		frame.getContentPane().add(cb_abitazione);
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
		inizio.addPropertyChangeListener("calendar", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    	dataInizio = inizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		        //dataInizio=LocalDate.parse(inizio.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));    
		    }
		}); 
		JLabel lblDataFine = new JLabel("Data di fine");
		lblDataFine.setBounds(429, 50, 71, 14);
		frame.getContentPane().add(lblDataFine);
		
		JCalendar fine = new JCalendar();
		fine.setBounds(429, 71, 184, 153);
		frame.getContentPane().add(fine);
		
		fine.addPropertyChangeListener("calendar", new PropertyChangeListener() {
		    @Override
		    public void propertyChange(PropertyChangeEvent e) {
		    	
		    	//dataFine=LocalDate.parse(fine.getDate().toString(), DateTimeFormatter.ofPattern("yyyy-MM-dd")); 
		    	dataFine = fine.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		    	
		    }
		});   

		
		JButton btn_Prenota = new JButton("Prenota");
		btn_Prenota.addActionListener(this);
		btn_Prenota.setBounds(253, 250, 89, 23);
		frame.getContentPane().add(btn_Prenota);
	
	

	}
	
	
	public  void actionPerformed(ActionEvent e) {
		GenerateRandom g= new GenerateRandom();
		String id= "PA" + g.GenerateRandom();
		if (abitazione==null) 
			JOptionPane.showMessageDialog(this, "Scegliere il tipo di abitazione.");
		if (dataInizio==null || dataFine==null) 
			JOptionPane.showMessageDialog(this, "Scegliere il periodo della prenotazione.");
		
	 PrenotazioneAbitazione pa= new PrenotazioneAbitazione( id, cliente, DAOFactory.getDAOAbitazione().doRetrieveById(abitazione), dataInizio, dataFine); 
	System.out.println(pa.toString());	
			int check = DAOFactory.getDAOPrenotazioneAbitazione().updatePrenotazioneAbitazione(pa);
		if(check==0) 
			JOptionPane.showMessageDialog(this, "Errore nella registrazione della prenotazione!");
		else if(check!=0)
			JOptionPane.showMessageDialog(this, "Prenotazione effettuata!");
		this.dispose();
		frame.dispose();
		}
	
	
    
}


