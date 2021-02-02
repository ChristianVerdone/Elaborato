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
import javax.swing.SpinnerDateModel;

import repository.DAOFactory;
import struttureEventi.classes.Biglietto;
import struttureEventi.classes.Evento;

import util.GenerateRandom;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.beans.PropertyChangeListener;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.beans.PropertyChangeEvent;
import java.awt.event.InputMethodListener;
import java.awt.event.InputMethodEvent;
import com.toedter.calendar.JCalendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;

public class RegistrazioneEvento extends JFrame implements ActionListener {

	private JFrame frame;
	private JTextField tfdescrizione;
	private JTextField tfnome;
	private JTextField tftipo;
	private String nome;
	private String tipo;
	private String descrizione;
	private LocalDate data;
	private LocalTime oraEvento;
	private JSpinner spr_startTime;
	private JTextField tfCosto;

	/**
	 * Launch the application.
	 */
	public void start() {
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
		frame.setBounds(100, 100, 795, 442);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setTitle("Registrazione evento");
		JLabel lbl_logo = new JLabel();
		lbl_logo.setLocation(274, 39);
		lbl_logo.setSize(231, 80);
		lbl_logo.setIcon(new ImageIcon("res/logo.png"));
		lbl_logo.setBackground(Color.DARK_GRAY);
		frame.getContentPane().add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Descrizione");
		lblNewLabel.setBounds(36, 216, 114, 14);
		frame.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Nome evento");
		lblNewLabel_1.setBounds(36, 150, 140, 14);
		frame.getContentPane().add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Tipologia evento");
		lblNewLabel_2.setBounds(247, 150, 95, 14);
		frame.getContentPane().add(lblNewLabel_2);

		tfdescrizione = new JTextField();
		tfdescrizione.setBounds(36, 231, 351, 56);
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
		btnNewButton.setBounds(345, 369, 89, 23);
		frame.getContentPane().add(btnNewButton);

		JCalendar inizio = new JCalendar();
		inizio.setBounds(573, 158, 184, 153);
		frame.getContentPane().add(inizio);
		inizio.addPropertyChangeListener("calendar", new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent e) {
				data = inizio.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
				// dataInizio=LocalDate.parse(inizio.getDate().toString(),
				// DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		});

		JLabel lblNewLabel_3 = new JLabel("Selezionare l'ora");
		lblNewLabel_3.setBounds(425, 145, 105, 14);
		frame.getContentPane().add(lblNewLabel_3);

		spr_startTime = new JSpinner(new SpinnerDateModel());
		spr_startTime.setEditor(new JSpinner.DateEditor(spr_startTime, "HH:mm"));
		spr_startTime.setBounds(425, 160, 74, 35);
		frame.getContentPane().add(spr_startTime);

		JLabel lblNewLabel_2_1 = new JLabel("Selezionare la data");
		lblNewLabel_2_1.setBounds(573, 139, 128, 14);
		frame.getContentPane().add(lblNewLabel_2_1);

		tfCosto = new JTextField();
		tfCosto.setBounds(425, 231, 74, 20);
		frame.getContentPane().add(tfCosto);
		tfCosto.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Costo biglietto");
		lblNewLabel_4.setBounds(424, 216, 106, 14);
		frame.getContentPane().add(lblNewLabel_4);
		btnNewButton.addActionListener(this);

		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 24); // 24 == 12 PM == 00:00:00
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);

		SpinnerDateModel model = new SpinnerDateModel();
		model.setValue(calendar.getTime());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		GenerateRandom g = new GenerateRandom();
		String command = e.getActionCommand();
		switch (command) {
		case "registra":
			Date d = (Date) spr_startTime.getValue();
			System.out.println(spr_startTime.getValue());
			oraEvento = LocalTime.of(d.getHours(), d.getMinutes());
			System.out.println(oraEvento);
			if (data.isBefore(LocalDate.now())) {
				JOptionPane.showMessageDialog(this, "Selezionare una data successiva alla data odierna.");
				break;
			}
			if (data == null) {
				JOptionPane.showMessageDialog(this, "Selezionare una data.");
				break;
			}
			String id = "EV" + g.GenerateRandom();
			nome = tfnome.getText();
			if (nome.length() > 30) {
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
				break;
			}
			if (nome == null) {
				JOptionPane.showMessageDialog(this, "Inserire il nome.");
				break;
			}

			if (DAOFactory.getDAOEvento().doRetrieveByNome(nome) != null) {
				JOptionPane.showMessageDialog(this, "Evento con nome " + nome + " già registrato");
				break;
			}

			tipo = tftipo.getText();
			if (tipo.length() > 20) {
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
				break;
			}
			if (tipo == null) {
				JOptionPane.showMessageDialog(this, "Inserire il tipo.");
				break;
			}
			descrizione = tfdescrizione.getText();
			if (descrizione == null) {
				JOptionPane.showMessageDialog(this, "Inserire la descrizione.");
				break;
			}
			if (descrizione.length() > 200) {
				JOptionPane.showMessageDialog(this, "Dimensione massima superata.");
				break;
			}
			float costoBiglietto = Float.parseFloat(tfCosto.getText());
			if (costoBiglietto < 0) {
				JOptionPane.showMessageDialog(this, "Costo biglietto non valido.");
				break;
			}
			if (DAOFactory.getDAOEvento().doRetrieveById(id) != null) {
				JOptionPane.showMessageDialog(this, "Evento con identificativo " + id + "già registrato");
				break;
			}
			Evento ev = new Evento(id, nome, tipo, descrizione, data, oraEvento);
			System.out.println(ev.getOraEvento());
			int check = DAOFactory.getDAOEvento().updateEvento(ev);
			if (check != 0) {
				JOptionPane.showMessageDialog(this, "Evento registrato!");
			}
			int b = 0;
			while (b < 15) {
				String cod = "BI" + g.GenerateRandom();
				Biglietto bi = new Biglietto(cod, costoBiglietto, true, nome);
				if (DAOFactory.getDAOBiglietto().doRetrieveById(cod) == null) {
					DAOFactory.getDAOBiglietto().updateBiglietto(bi);
				}
				b++;
			}
			this.dispose();
			frame.dispose();
		}
	}
}