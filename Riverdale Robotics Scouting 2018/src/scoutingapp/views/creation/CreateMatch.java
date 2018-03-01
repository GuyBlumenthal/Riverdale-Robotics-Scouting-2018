package scoutingapp.views.creation;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.team.Team;
import scoutingapp.views.MatchHub;
import scoutingapp.views.TeamHub;

public class CreateMatch {

	public JDialog frame;
	ArrayList<Integer> teams = new ArrayList<Integer>();
	ArrayList<Integer> usedTeams = new ArrayList<Integer>();
	private JComboBox<Integer> cmbBlue1;
	private JComboBox<Integer> cmbBlue2;
	private JComboBox<Integer> cmbBlue3;
	private JComboBox<Integer> cmbRed1;
	private JComboBox<Integer> cmbRed2;
	private JComboBox<Integer> cmbRed3;
	private JTextField txtMatchID;

	private int matchID;
	
	MatchHub matchHub;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CreateMatch window = new CreateMatch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @param matchHub 
	 */
	public CreateMatch(MatchHub matchHub) {
		
		this.matchHub = matchHub;

//		Team[] testBlue = { new Team(1001, "The Testers"), new Team(1002, "The Ranoutofideas"),
//				new Team(1003, "The Slumdogmillionaires") };
//		Team[] testRed = { new Team(1004, "Bang blasters"), new Team(1005, "Spencini81"), new Team(1006, "Spoincer") };
//		try {
//			TeamHub.regionalCollection.createTeam(testBlue[0]);
//			TeamHub.regionalCollection.createTeam(testBlue[1]);
//			TeamHub.regionalCollection.createTeam(testBlue[2]);
//
//			TeamHub.regionalCollection.createTeam(testRed[0]);
//			TeamHub.regionalCollection.createTeam(testRed[1]);
//			TeamHub.regionalCollection.createTeam(testRed[2]);
//		} catch (Exception e) {
//
//		}
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		Object[] temp = TeamHub.regionalCollection.getTeams().keySet().toArray();

		for (int i = 0; i < temp.length; i++) {
			teams.add(Integer.parseInt(String.valueOf(temp[i])));
		}

		frame = new JDialog();
		frame.setBounds(100, 100, 450, 366);
		frame.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setModal(true);

		JLabel lblMatchId = new JLabel("Match ID:");
		lblMatchId.setBounds(20, 11, 53, 14);
		frame.getContentPane().add(lblMatchId);

		JSeparator separator = new JSeparator();
		separator.setBounds(20, 36, 393, 2);
		frame.getContentPane().add(separator);

		JPanel pnlBlueTeam = new JPanel();
		pnlBlueTeam.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Blue Alliance",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		pnlBlueTeam.setBounds(20, 74, 195, 242);
		frame.getContentPane().add(pnlBlueTeam);
		pnlBlueTeam.setLayout(null);

		JPanel pnlRedTeam = new JPanel();
		pnlRedTeam
				.setBorder(new TitledBorder(null, "Red Alliance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		pnlRedTeam.setBounds(225, 74, 188, 242);
		frame.getContentPane().add(pnlRedTeam);
		pnlRedTeam.setLayout(null);

		cmbBlue1 = new JComboBox<Integer>();
		cmbBlue1.setBounds(104, 22, 81, 20);
		pnlBlueTeam.add(cmbBlue1);

		cmbBlue2 = new JComboBox<Integer>();
		cmbBlue2.setBounds(104, 115, 81, 20);
		pnlBlueTeam.add(cmbBlue2);

		cmbBlue3 = new JComboBox<Integer>();
		cmbBlue3.setBounds(104, 211, 81, 20);
		pnlBlueTeam.add(cmbBlue3);

		cmbRed1 = new JComboBox<Integer>();
		cmbRed1.setBounds(97, 21, 81, 20);
		pnlRedTeam.add(cmbRed1);

		cmbRed2 = new JComboBox<Integer>();
		cmbRed2.setBounds(97, 114, 81, 20);
		pnlRedTeam.add(cmbRed2);

		cmbRed3 = new JComboBox<Integer>();
		cmbRed3.setBounds(97, 211, 81, 20);
		pnlRedTeam.add(cmbRed3);

		JButton btnCreateMatch = new JButton("Create Match");
		btnCreateMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Team[] blueTeam = {
						TeamHub.regionalCollection
								.getTeam(Integer.parseInt(String.valueOf(cmbBlue1.getSelectedItem()))),
						TeamHub.regionalCollection
								.getTeam(Integer.parseInt(String.valueOf(cmbBlue2.getSelectedItem()))),
						TeamHub.regionalCollection
								.getTeam(Integer.parseInt(String.valueOf(cmbBlue3.getSelectedItem()))) };

				Team[] redTeam = {
						TeamHub.regionalCollection.getTeam(Integer.parseInt(String.valueOf(cmbRed1.getSelectedItem()))),
						TeamHub.regionalCollection.getTeam(Integer.parseInt(String.valueOf(cmbRed2.getSelectedItem()))),
						TeamHub.regionalCollection
								.getTeam(Integer.parseInt(String.valueOf(cmbRed3.getSelectedItem()))) };
				
				matchHub.updateMatchTable();
				frame.dispose();
				
				try {
					TeamHub.regionalCollection.createMatch(Integer.parseInt(txtMatchID.getText()), blueTeam, redTeam);
				} catch (NumberFormatException | ExistingException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnCreateMatch.setBounds(184, 7, 108, 23);
		frame.getContentPane().add(btnCreateMatch);

		JButton btnCreateTeam = new JButton("Create Team");

		// TODO: Make more efficient

		btnCreateTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CreateTeam dialog = new CreateTeam();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e3) {
					e3.printStackTrace();
				}
				cmbBlue1.removeAllItems();
				cmbBlue2.removeAllItems();
				cmbBlue3.removeAllItems();
				cmbRed1.removeAllItems();
				cmbRed2.removeAllItems();
				cmbRed3.removeAllItems();

				Object[] temp = TeamHub.regionalCollection.getTeams().keySet().toArray();

				teams.clear();

				for (int i = 0; i < temp.length; i++) {
					teams.add(Integer.parseInt(String.valueOf(temp[i])));
				}

				for (int i = 0; i < teams.size(); i++) {
					cmbBlue1.addItem(teams.get(i));
					cmbBlue2.addItem(teams.get(i));
					cmbBlue3.addItem(teams.get(i));
					cmbRed1.addItem(teams.get(i));
					cmbRed2.addItem(teams.get(i));
					cmbRed3.addItem(teams.get(i));
				}
			}
		});
		btnCreateTeam.setBounds(302, 7, 108, 23);
		frame.getContentPane().add(btnCreateTeam);

		txtMatchID = new JTextField();
		txtMatchID.setBounds(88, 8, 86, 20);
		frame.getContentPane().add(txtMatchID);
		txtMatchID.setColumns(10);

		for (int i = 0; i < teams.size(); i++) {
			cmbBlue1.addItem(teams.get(i));
			cmbBlue2.addItem(teams.get(i));
			cmbBlue3.addItem(teams.get(i));
			cmbRed1.addItem(teams.get(i));
			cmbRed2.addItem(teams.get(i));
			cmbRed3.addItem(teams.get(i));
		}

	}
}
