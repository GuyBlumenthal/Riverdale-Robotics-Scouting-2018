package scoutingapp.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.Match;
import scoutingapp.commons.team.Team;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MatchOverview extends JFrame {

	private JPanel contentPane;

	private int match;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Team[] testBlue = { new Team(1001, "The Testers"), new Team(1002, "The Ranoutofideas"),
				new Team(1003, "The Slumdogmillionaires") };
		Team[] testRed = { new Team(1004, "Bang blasters"), new Team(1005, "Spencini81"), new Team(1006, "Spoincer") };
		try {
			TeamHub.regionalCollection.createTeam(testBlue[0]);
			TeamHub.regionalCollection.createTeam(testBlue[1]);
			TeamHub.regionalCollection.createTeam(testBlue[2]);

			TeamHub.regionalCollection.createTeam(testRed[0]);
			TeamHub.regionalCollection.createTeam(testRed[1]);
			TeamHub.regionalCollection.createTeam(testRed[2]);

			TeamHub.regionalCollection.createMatch(1, testBlue, testRed);

		} catch (Exception e) {

		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchOverview frame = new MatchOverview(1);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MatchOverview(int match) {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 672, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchID = new JLabel("Match NUMBER");
		lblMatchID.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchID.setBounds(269, 11, 105, 24);
		contentPane.add(lblMatchID);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Red Alliance",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(324, 54, 332, 306);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRedTeam1 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getRedTeams()[0].getTeamName() + " : ");
		lblRedTeam1.setBounds(10, 168, 137, 14);
		panel_1.add(lblRedTeam1);

		JLabel lblRedTeam2 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getRedTeams()[1].getTeamName() + " : ");
		lblRedTeam2.setBounds(10, 223, 137, 14);
		panel_1.add(lblRedTeam2);

		JLabel lblRedTeam3 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getRedTeams()[2].getTeamName() + " : ");
		lblRedTeam3.setBounds(10, 269, 137, 14);
		panel_1.add(lblRedTeam3);

		JButton btnRedMatchTeam1 = new JButton("...");
		btnRedMatchTeam1.setBounds(167, 164, 68, 23);
		panel_1.add(btnRedMatchTeam1);

		JButton btnRedMatchTeam2 = new JButton("...");
		btnRedMatchTeam2.setBounds(167, 219, 68, 23);
		panel_1.add(btnRedMatchTeam2);

		JButton btnRedMatchTeam3 = new JButton("...");
		btnRedMatchTeam3.setBounds(167, 265, 68, 23);
		panel_1.add(btnRedMatchTeam3);

		JButton btnRedTeamDetail1 = new JButton("...");
		btnRedTeamDetail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(0, true);
			}
		});
		btnRedTeamDetail1.setBounds(254, 164, 68, 23);
		panel_1.add(btnRedTeamDetail1);

		JButton btnRedTeamDetail2 = new JButton("...");
		btnRedTeamDetail2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(1, true);
			}
		});
		btnRedTeamDetail2.setBounds(254, 219, 68, 23);
		panel_1.add(btnRedTeamDetail2);

		JButton btnRedTeamDetail3 = new JButton("...");
		btnRedTeamDetail3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(2, true);
			}
		});
		btnRedTeamDetail3.setBounds(254, 265, 68, 23);
		panel_1.add(btnRedTeamDetail3);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Blue Alliance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 54, 304, 306);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBlueTeam1 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getBlueTeams()[0].getTeamName() + " : ");
		lblBlueTeam1.setBounds(10, 167, 143, 14);
		panel.add(lblBlueTeam1);

		JLabel lblBlueTeam2 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getBlueTeams()[1].getTeamName() + " : ");
		lblBlueTeam2.setBounds(10, 221, 155, 14);
		panel.add(lblBlueTeam2);

		JLabel lblBlueTeam3 = new JLabel(
				TeamHub.regionalCollection.getMatch(match).getBlueTeams()[2].getTeamName() + " : ");
		lblBlueTeam3.setBounds(10, 270, 155, 14);
		panel.add(lblBlueTeam3);

		JButton btnBlueMatchTeam1 = new JButton("...");
		btnBlueMatchTeam1.setBounds(163, 163, 61, 23);
		panel.add(btnBlueMatchTeam1);

		JButton btnBlueMatchTeam2 = new JButton("...");
		btnBlueMatchTeam2.setBounds(163, 217, 61, 23);
		panel.add(btnBlueMatchTeam2);

		JButton btnBlueMatchTeam3 = new JButton("...");
		btnBlueMatchTeam3.setBounds(163, 266, 61, 23);
		panel.add(btnBlueMatchTeam3);

		JButton btnBlueTeamDetail1 = new JButton("...");
		btnBlueTeamDetail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(0, false);
			}
		});
		btnBlueTeamDetail1.setBounds(234, 163, 60, 23);
		panel.add(btnBlueTeamDetail1);

		JButton btnBlueTeamDetail2 = new JButton("...");
		btnBlueTeamDetail2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(1, false);
			}
		});
		btnBlueTeamDetail2.setBounds(234, 217, 60, 23);
		panel.add(btnBlueTeamDetail2);

		JButton btnBlueTeamDetail3 = new JButton("...");
		btnBlueTeamDetail3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(2, false);
			}
		});
		btnBlueTeamDetail3.setBounds(234, 266, 60, 23);
		panel.add(btnBlueTeamDetail3);
	}

	public void openTeamDetail(int team, boolean isRed) {
		try {

			int teamNumber = -1;

			if (isRed) {

				teamNumber = TeamHub.regionalCollection.getMatch(match).getRedTeams()[team].getTeamNumber();

			} else {

				teamNumber = TeamHub.regionalCollection.getMatch(match).getBlueTeams()[team].getTeamNumber();

			}

			if (TeamHub.regionalCollection.hasTeamPerformance(match, teamNumber)) {

				TeamHub.regionalCollection.showTeamPerformance(match, teamNumber);

			} else {

				TeamHub.regionalCollection.createTeamPerformanceWindow(match, teamNumber);

			}

		} catch (ExistingException e) {
			System.out.println("nothing is real");
		}

	}
}
