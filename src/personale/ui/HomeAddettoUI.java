package personale.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import contabilita.PagamentoUI;
import personale.model.Account;
import struttureEventi.ui.RegistrazioneEvento;

public class HomeAddettoUI extends HomeUI {

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAddettoUI window = new HomeAddettoUI(new Account("userAddetto", "psw02", Account.Permessi.REDUCED));
					window.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public HomeAddettoUI(Account a) {
		super(a);

		JButton btn_events = new JButton("Registra evento");
		btn_events.setBounds(10, 275, 205, 21);
		btn_events.setActionCommand("event");
		btn_events.addActionListener(this);
		getContentPane().add(btn_events);

		JButton btn_prenotations = new JButton("Registra prenotazione");
		btn_prenotations.setBounds(10, 300, 205, 21);
		btn_prenotations.setActionCommand("pren");
		btn_prenotations.addActionListener(this);
		getContentPane().add(btn_prenotations);

		JButton btn_amount = new JButton("Registra pagamento");
		btn_amount.setBounds(10, 325, 205, 21);
		btn_amount.setActionCommand("amount");
		btn_amount.addActionListener(this);
		getContentPane().add(btn_amount);
		
		JButton btn_restaurant = new JButton("Registra conto ristorante");
		btn_restaurant.setBounds(10, 350, 205, 21);
		btn_restaurant.setActionCommand("risto");
		btn_restaurant.addActionListener(this);
		getContentPane().add(btn_restaurant);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		switch(e.getActionCommand()) {
		case "event":
			new RegistrazioneEvento().start();
			break;
		case "pren":
			new SceltaPrenotazioneUI().start();
			break;
		case "amount":
			new PagamentoUI().start();
			break;
			
		case "risto":
			new ContoRistoranteUI().start();
		}
	}
}