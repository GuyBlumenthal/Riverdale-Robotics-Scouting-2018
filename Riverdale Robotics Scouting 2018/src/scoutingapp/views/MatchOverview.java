package scoutingapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.Match;
import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.existing.ExistingException;
import scoutingapp.commons.team.Team;
import javax.swing.JScrollPane;

@SuppressWarnings("serial")
public class MatchOverview extends JFrame {

	private JPanel contentPane;

	private int matchID;

	JLabel blueBoost = new JLabel("0:00"), redBoost = new JLabel("0:00"), forceBlue = new JLabel("0:00"),
			redForce = new JLabel("0:00");
	JCheckBox blueLev = new JCheckBox("Levitated"), redLev = new JCheckBox();

	MatchOverview me = this;
	private JTextArea txtaBlueSummary;
	private JTextArea txtaRedSummary;
	private JLabel lblRedScore;
	private JLabel lblBlueScore;

	/**
	 * Create the frame.
	 */

	public void updatePowers() {
		Match k = ScoutingApp.regionalCollection().getMatch(matchID);

		blueBoost.setText((k.getBluePowerups()[0] == -1) ? "0:00"
				: ScoutingApp.regionalCollection().secondsToStandard(k.getBluePowerups()[0]));
		forceBlue.setText((k.getBluePowerups()[1] == -1) ? "0:00"
				: ScoutingApp.regionalCollection().secondsToStandard(k.getBluePowerups()[1]));
		blueLev.setSelected(k.getBluePowerups()[2] == 1);

		redBoost.setText((k.getRedPowerups()[0] == -1) ? "0:00"
				: ScoutingApp.regionalCollection().secondsToStandard(k.getRedPowerups()[0]));
		redForce.setText((k.getRedPowerups()[1] == -1) ? "0:00"
				: ScoutingApp.regionalCollection().secondsToStandard(k.getRedPowerups()[1]));
		redLev.setSelected(k.getRedPowerups()[2] == 1);

	}

	public MatchOverview(int matchID) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				ScoutingApp.unshowMatch(matchID);
			}
		});

		this.matchID = matchID;

		updatePowers();

		setResizable(false);

		setBounds(100, 100, 666, 420);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmDeleteMatch = new JMenuItem("Delete Match");
		mntmDeleteMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.regionalCollection().removeMatch(matchID);

			}
		});
		mnEdit.add(mntmDeleteMatch);

		JMenuItem mntmSetBluePower = new JMenuItem("Set Blue Power Up Values");
		mntmSetBluePower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				SetPowerUps spu = new SetPowerUps(matchID, false);
				spu.setVisible(true);

			}
		});
		mnEdit.add(mntmSetBluePower);

		JMenuItem mntmSetRedPower = new JMenuItem("Set Red Power Up Values");
		mntmSetRedPower.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				SetPowerUps spu = new SetPowerUps(matchID, true);
				spu.setVisible(true);

			}
		});
		mnEdit.add(mntmSetRedPower);
		
		JMenuItem mntmSetBluePoints = new JMenuItem("Set Blue Match Points");
		mntmSetBluePoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			
				SetMatchPoints smp = new SetMatchPoints(matchID, false);
				smp.setVisible(true);
				
			}
		});
		mnEdit.add(mntmSetBluePoints);

		JMenuItem mntmSetRedPoints = new JMenuItem("Set Red Match Points");
		mntmSetRedPoints.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				SetMatchPoints smp = new SetMatchPoints(matchID, true);
				smp.setVisible(true);
				
			}
		});
		mnEdit.add(mntmSetRedPoints);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmMissingInfo = new JMenuItem("Missing Info");
		mntmMissingInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				JOptionPane.showMessageDialog(null, missingInfoInMatch(), "Missing Info", JOptionPane.OK_OPTION);

			}
		});
		mnView.add(mntmMissingInfo);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchID = new JLabel("Match " + matchID);
		lblMatchID.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchID.setBounds(269, 11, 105, 24);
		contentPane.add(lblMatchID);

		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(UIManager.getBorder("TitledBorder.border"), "Red Alliance",
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(330, 54, 318, 306);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRedTeam1 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[0].getTeamName() + " : ");
		lblRedTeam1.setBounds(10, 170, 68, 14);
		panel_1.add(lblRedTeam1);

		JLabel lblRedTeam2 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[1].getTeamName() + " : ");
		lblRedTeam2.setBounds(10, 215, 68, 14);
		panel_1.add(lblRedTeam2);

		JLabel lblRedTeam3 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[2].getTeamName() + " : ");
		lblRedTeam3.setBounds(10, 261, 68, 14);
		panel_1.add(lblRedTeam3);

		JButton btnRedMatchTeam1 = new JButton("Details");
		btnRedMatchTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[0].getTeamNumber());
			}
		});
		btnRedMatchTeam1.setBounds(100, 165, 68, 23);
		panel_1.add(btnRedMatchTeam1);

		JButton btnRedMatchTeam2 = new JButton("Details");
		btnRedMatchTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[1].getTeamNumber());
			}
		});
		btnRedMatchTeam2.setBounds(100, 211, 68, 23);
		panel_1.add(btnRedMatchTeam2);

		JButton btnRedMatchTeam3 = new JButton("Details");
		btnRedMatchTeam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[2].getTeamNumber());
			}
		});
		btnRedMatchTeam3.setBounds(100, 257, 68, 23);
		panel_1.add(btnRedMatchTeam3);

		JButton btnRedTeamDetail1 = new JButton("Add Performance");
		btnRedTeamDetail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(0, true);
			}
		});
		btnRedTeamDetail1.setBounds(178, 165, 130, 23);
		panel_1.add(btnRedTeamDetail1);

		JButton btnRedTeamDetail2 = new JButton("Add Performance");
		btnRedTeamDetail2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(1, true);
			}
		});
		btnRedTeamDetail2.setBounds(178, 211, 130, 23);
		panel_1.add(btnRedTeamDetail2);

		JButton btnRedTeamDetail3 = new JButton("Add Performance");
		btnRedTeamDetail3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(2, true);
			}
		});
		btnRedTeamDetail3.setBounds(178, 257, 130, 23);
		panel_1.add(btnRedTeamDetail3);
		redLev.setEnabled(false);

		redLev.setText("Levitated");
		redLev.setBounds(215, 115, 97, 23);
		panel_1.add(redLev);

		redForce.setBounds(163, 119, 46, 14);
		panel_1.add(redForce);

		JLabel label_1 = new JLabel("Force:");
		label_1.setBounds(119, 119, 46, 14);
		panel_1.add(label_1);

		redBoost.setBounds(50, 119, 46, 14);
		panel_1.add(redBoost);

		JLabel label_5 = new JLabel("Boost:");
		label_5.setBounds(10, 119, 46, 14);
		panel_1.add(label_5);

		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Blue Alliance", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 54, 318, 306);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBlueTeam1 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[0].getTeamName() + " : ");
		lblBlueTeam1.setBounds(10, 167, 68, 14);
		panel.add(lblBlueTeam1);

		JLabel lblBlueTeam2 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[1].getTeamName() + " : ");
		lblBlueTeam2.setBounds(10, 221, 68, 14);
		panel.add(lblBlueTeam2);

		JLabel lblBlueTeam3 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[2].getTeamName() + " : ");
		lblBlueTeam3.setBounds(10, 270, 68, 14);
		panel.add(lblBlueTeam3);

		JButton btnBlueMatchTeam1 = new JButton("Details");
		btnBlueMatchTeam1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[0].getTeamNumber());
			}
		});
		btnBlueMatchTeam1.setBounds(97, 163, 68, 23);
		panel.add(btnBlueMatchTeam1);

		JButton btnBlueMatchTeam2 = new JButton("Details");
		btnBlueMatchTeam2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[1].getTeamNumber());
			}
		});
		btnBlueMatchTeam2.setBounds(97, 217, 68, 23);
		panel.add(btnBlueMatchTeam2);

		JButton btnBlueMatchTeam3 = new JButton("Details");
		btnBlueMatchTeam3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ScoutingApp
						.showTeam(ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[2].getTeamNumber());
			}
		});
		btnBlueMatchTeam3.setBounds(97, 266, 68, 23);
		panel.add(btnBlueMatchTeam3);

		JButton btnBlueTeamDetail1 = new JButton("Add Performance");
		btnBlueTeamDetail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(0, false);
			}
		});
		btnBlueTeamDetail1.setBounds(175, 163, 123, 23);
		panel.add(btnBlueTeamDetail1);

		JButton btnBlueTeamDetail2 = new JButton("Add Performance");
		btnBlueTeamDetail2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(1, false);
			}
		});
		btnBlueTeamDetail2.setBounds(175, 217, 123, 23);
		panel.add(btnBlueTeamDetail2);

		JButton btnBlueTeamDetail3 = new JButton("Add Performance");
		btnBlueTeamDetail3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(2, false);
			}
		});
		btnBlueTeamDetail3.setBounds(175, 266, 123, 23);
		panel.add(btnBlueTeamDetail3);

		JLabel label_3 = new JLabel("Boost:");
		label_3.setBounds(10, 119, 46, 14);
		panel.add(label_3);

		JLabel blueForceTitle = new JLabel("Force:");
		blueForceTitle.setBounds(119, 119, 46, 14);
		panel.add(blueForceTitle);

		blueBoost.setBounds(50, 119, 46, 14);
		panel.add(blueBoost);

		forceBlue.setBounds(163, 119, 46, 14);
		panel.add(forceBlue);
		blueLev.setEnabled(false);

		blueLev.setBounds(215, 115, 83, 23);
		panel.add(blueLev);

		JScrollPane blueScrlPane = new JScrollPane();
		blueScrlPane.setBounds(10, 21, 298, 87);
		panel.add(blueScrlPane);

		txtaBlueSummary = new JTextArea();
		blueScrlPane.setViewportView(txtaBlueSummary);
		txtaBlueSummary.setEditable(false);
		txtaBlueSummary.setText((String) null);

		JScrollPane redScrlPane = new JScrollPane();
		redScrlPane.setBounds(10, 21, 298, 87);
		panel_1.add(redScrlPane);

		txtaRedSummary = new JTextArea();
		redScrlPane.setViewportView(txtaRedSummary);
		txtaRedSummary.setEditable(false);
		txtaBlueSummary.setText((String) null);

		lblBlueScore = new JLabel("Score:");
		lblBlueScore.setBounds(134, 18, 69, 14);
		contentPane.add(lblBlueScore);

		lblRedScore = new JLabel("Score:");
		lblRedScore.setBounds(454, 18, 69, 14);
		contentPane.add(lblRedScore);

		updateMatchOverview();

	}

	public void updateMatchOverview() {

		txtaRedSummary
				.setText(generateAllianceSummary(ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()));
		txtaBlueSummary
				.setText(generateAllianceSummary(ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()));

		if (ScoutingApp.regionalCollection().getMatch(matchID).getBlueScore() != -1) {
			lblBlueScore.setText("Score: " + ScoutingApp.regionalCollection().getMatch(matchID).getBlueScore());
		} else {
			lblBlueScore.setText("Score: 0");
		}

		if (ScoutingApp.regionalCollection().getMatch(matchID).getRedScore() != -1) {
			lblRedScore.setText("Score: " + ScoutingApp.regionalCollection().getMatch(matchID).getRedScore());
		} else {
			lblRedScore.setText("Score: 0");
		}

		updatePowers();

	}

	public String generateAllianceSummary(Team[] k) {

		int[] allianceTeams = new int[k.length];

		int A_switch = 0;
		int A_scale = 0;
		int A_vault = 0;

		int T_allianceSwitchCubes = 0;
		int T_opponentSwitchCubes = 0;
		int T_scaleCubes = 0;
		int T_vault = 0;

		int climbs = 0;

		for (int i = 0; i < k.length; i++) {
			allianceTeams[i] = k[i].getTeamNumber();
		}

		for (int i = 0; i < allianceTeams.length; i++) {

			if (ScoutingApp.regionalCollection().getTeam(allianceTeams[i]).hasTeamPerformance(matchID)) {

				A_switch += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesOnSwitchAuto.size();

				A_scale += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesOnScaleAuto.size();

				A_vault += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesInVaultAuto.size();

				T_allianceSwitchCubes += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesOnAllianceSwitchTeleop.size();

				T_opponentSwitchCubes += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesOnOpponentSwitchTeleop.size();

				T_scaleCubes += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesOnScaleTeleop.size();

				T_vault += ScoutingApp.regionalCollection().getTeam(allianceTeams[i])
						.getTeamPerformance(matchID).cubesInVaultTeleop.size();

			}

		}
		StringBuilder highlight = new StringBuilder();

		highlight.append("Switch in Auto: ");
		highlight.append(A_switch);

		highlight.append("\n");

		highlight.append("Scale in Auto: ");
		highlight.append(A_scale);

		highlight.append("\n");

		highlight.append("Vault in Auto: ");
		highlight.append(A_vault);

		highlight.append("\n");

		highlight.append("Alliance Switch in Tele-Op: ");
		highlight.append(T_allianceSwitchCubes);

		highlight.append("\n");

		highlight.append("Opponnent Switch in Tele-Op: ");
		highlight.append(T_opponentSwitchCubes);

		highlight.append("\n");

		highlight.append("Scale in Tele-Op: ");
		highlight.append(T_scaleCubes);

		highlight.append("\n");

		highlight.append("Vault in Tele-Op: ");
		highlight.append(T_vault);

		return highlight.toString();

	}

	private String missingInfoInMatch() {

		StringBuilder infoMissing = new StringBuilder("");

		Team[] blueTeams = ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams();

		for (int i = 0; i < 3; i++) {

			if (blueTeams[i].hasTeamPerformance(matchID) == false) {

				infoMissing.append("Team " + blueTeams[i].getTeamNumber() + " is missing a team performance");
				infoMissing.append("\n");

			}

		}

		Team[] redTeams = ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams();

		for (int i = 0; i < 3; i++) {

			if (redTeams[i].hasTeamPerformance(matchID) == false) {

				infoMissing.append("Team " + redTeams[i].getTeamNumber() + " is missing a team performance");
				infoMissing.append("\n");

			}

		}

		for (int i = 0; i < 3; i++) {

			if (ScoutingApp.regionalCollection().getMatch(matchID).getBluePowerups()[i] != -1) {
				break;
			}

			if (i == 2) {
				infoMissing.append("The blue power ups are missing");
				infoMissing.append("\n");
			}

		}

		for (int i = 0; i < 3; i++) {

			if (ScoutingApp.regionalCollection().getMatch(matchID).getRedPowerups()[i] != -1) {
				break;
			}

			if (i == 2) {
				infoMissing.append("The red power ups are missing");
				infoMissing.append("\n");
			}

		}

		if (ScoutingApp.regionalCollection().getMatch(matchID).getBlueScore() == -1) {
			infoMissing.append("There is no score for the blue alliance!");
			infoMissing.append("\n");
		}

		if (ScoutingApp.regionalCollection().getMatch(matchID).getRedScore() == -1) {
			infoMissing.append("There is no score for the red alliance!");
		}

		return infoMissing.toString();

	}

	public void openTeamDetail(int team, boolean isRed) {
		try {

			int teamNumber = -1;

			if (isRed) {

				teamNumber = ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[team].getTeamNumber();

			} else {

				teamNumber = ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[team].getTeamNumber();

			}

			if (ScoutingApp.regionalCollection().hasTeamPerformance(matchID, teamNumber)) {

				ScoutingApp.regionalCollection().showTeamPerformance(matchID, teamNumber);

			} else {
				ScoutingApp.regionalCollection().createTeamPerformanceWindow(matchID, teamNumber);

			}

		} catch (ExistingException e) {
			System.out.println("nothing is real");
		}

	}
}
