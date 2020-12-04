package personale.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;

import javax.swing.JButton;

import contabilità.PagamentoUI;
import personale.model.Account;

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
		btn_events.setBounds(10, 235, 205, 21);
		btn_events.setActionCommand("event");
		btn_events.addActionListener(this);
		getContentPane().add(btn_events);
		
		JButton btn_amount = new JButton("Registra conto");
		btn_amount.setBounds(10, 285, 205, 21);
		btn_amount.setActionCommand("amount");
		btn_amount.addActionListener(this);
		getContentPane().add(btn_amount);
		
		JButton btn_prenotations = new JButton("Registra prenotazione");
		btn_prenotations.setBounds(10, 260, 205, 21);
		btn_prenotations.setActionCommand("pren");
		btn_prenotations.addActionListener(this);
		getContentPane().add(btn_prenotations);
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		switch(e.getActionCommand()) {
		case "event":
			/* To do */
			break;
		case "pren":
			SceltaPrenotazioneUI p= new SceltaPrenotazioneUI();
			p.start();
			this.dispose();
			break;
		case "amount":
			PagamentoUI pagamento=new PagamentoUI();
			pagamento.start();
			this.dispose();
			break;
		}
	}

}
