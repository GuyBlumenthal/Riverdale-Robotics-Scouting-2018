package scoutingapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.UIManager;

import scoutingapp.commons.RegionalCollection;

public class TeamHub extends JFrame {

	static public RegionalCollection regionalCollection = new RegionalCollection();

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamHub frame = new TeamHub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TeamHub() {

	}

}
