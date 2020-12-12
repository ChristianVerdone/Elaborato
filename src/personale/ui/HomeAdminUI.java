package personale.ui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;

import personale.model.Account;

public class HomeAdminUI extends HomeAddettoUI {

	private JButton btn_work_shift;
	private JButton btn_new_dip;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeAdminUI window = new HomeAdminUI(new Account("matteog40", "matt40", Account.Permessi.ALL));
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
	public HomeAdminUI(Account a) {
		super(a);
		btn_work_shift = new JButton("Assegna turno di lavoro");
		btn_work_shift.setBounds(10, 350, 205, 21);
		btn_work_shift.setActionCommand("work");
		btn_work_shift.addActionListener(this);
		getContentPane().add(btn_work_shift);

		btn_new_dip = new JButton("Registra nuovo dipendente");
		btn_new_dip.setBounds(10, 375, 205, 21);
		btn_new_dip.setActionCommand("dip");
		btn_new_dip.addActionListener(this);
		getContentPane().add(btn_new_dip);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		super.actionPerformed(e);
		String command = e.getActionCommand();
		if(command.equals("work")) {
			btn_work_shift.setEnabled(false);
			new TurnoLavoroUI().addWindowListener(new WindowListener() {	
				@Override public void windowOpened(WindowEvent e) { }
				@Override public void windowIconified(WindowEvent e) { }
				@Override public void windowDeiconified(WindowEvent e) { }
				@Override public void windowDeactivated(WindowEvent e) { }
				@Override public void windowClosing(WindowEvent e) { }					
				@Override public void windowClosed(WindowEvent e) { btn_work_shift.setEnabled(true); }
				@Override public void windowActivated(WindowEvent e) { }
			});
		}
		else if (command.equals("dip")) {
			btn_new_dip.setEnabled(false);
			new RegistrazioneUI().addWindowListener(new WindowListener() {	
				@Override public void windowOpened(WindowEvent e) { }
				@Override public void windowIconified(WindowEvent e) { }
				@Override public void windowDeiconified(WindowEvent e) { }
				@Override public void windowDeactivated(WindowEvent e) { }
				@Override public void windowClosing(WindowEvent e) { }					
				@Override public void windowClosed(WindowEvent e) { btn_new_dip.setEnabled(true); }
				@Override public void windowActivated(WindowEvent e) { }
			});
		}
	}
}