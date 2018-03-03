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
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.Match;
import scoutingapp.commons.ScoutingApp;

@SuppressWarnings("serial")
public class MatchOverview extends JFrame {

	private JPanel contentPane;

	private int matchID;
	
	JLabel blueBoost = new JLabel("0:00"), redBoost = new JLabel("0:00"), forceBlue = new JLabel("0:00"), redForce = new JLabel("0:00");
	JCheckBox blueLev = new JCheckBox("Levitated"), redLev = new JCheckBox();
	
	MatchOverview me = this;

	/**
	 * Create the frame.
	 */
	
	private int standardToSeconds(String time) {
		return Integer.parseInt(time.split(":")[0]) * 60 + Integer.parseInt(time.split(":")[1]);
	}
	
	private String secondsToStandard(int time) {
		return Math.floorDiv(time, 60) + ":" + time % 60;
	}
	
	public void updatePowers() {
		Match k = ScoutingApp.regionalCollection().getMatch(matchID);
		
		blueBoost.setText(secondsToStandard(k.getBluePowerups()[0]));
		forceBlue.setText(secondsToStandard(k.getBluePowerups()[1]));
		blueLev.setSelected(k.getBluePowerups()[2] == 1);
		
		redBoost.setText(secondsToStandard(k.getRedPowerups()[0]));
		redForce.setText(secondsToStandard(k.getRedPowerups()[1]));
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
		

		setBounds(100, 100, 654, 412);

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

		JMenuItem mntmSetPowerUp = new JMenuItem("Set Power Up Values");
		mntmSetPowerUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			
				SetPowerUps spu = new SetPowerUps(matchID, me);
				spu.setVisible(true);
				
			}
		});
		mnEdit.add(mntmSetPowerUp);
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
		panel_1.setBounds(324, 54, 318, 306);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		JLabel lblRedTeam1 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[0].getTeamName() + " : ");
		lblRedTeam1.setBounds(10, 168, 137, 14);
		panel_1.add(lblRedTeam1);

		JLabel lblRedTeam2 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[1].getTeamName() + " : ");
		lblRedTeam2.setBounds(10, 223, 137, 14);
		panel_1.add(lblRedTeam2);

		JLabel lblRedTeam3 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getRedTeams()[2].getTeamName() + " : ");
		lblRedTeam3.setBounds(10, 269, 137, 14);
		panel_1.add(lblRedTeam3);

		JButton btnRedMatchTeam1 = new JButton("...");
		btnRedMatchTeam1.setBounds(163, 163, 68, 23);
		panel_1.add(btnRedMatchTeam1);

		JButton btnRedMatchTeam2 = new JButton("...");
		btnRedMatchTeam2.setBounds(163, 219, 68, 23);
		panel_1.add(btnRedMatchTeam2);

		JButton btnRedMatchTeam3 = new JButton("...");
		btnRedMatchTeam3.setBounds(163, 265, 68, 23);
		panel_1.add(btnRedMatchTeam3);

		JButton btnRedTeamDetail1 = new JButton("...");
		btnRedTeamDetail1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(0, true);
			}
		});
		btnRedTeamDetail1.setBounds(241, 164, 68, 23);
		panel_1.add(btnRedTeamDetail1);

		JButton btnRedTeamDetail2 = new JButton("...");
		btnRedTeamDetail2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(1, true);
			}
		});
		btnRedTeamDetail2.setBounds(241, 219, 68, 23);
		panel_1.add(btnRedTeamDetail2);

		JButton btnRedTeamDetail3 = new JButton("...");
		btnRedTeamDetail3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				openTeamDetail(2, true);
			}
		});
		btnRedTeamDetail3.setBounds(241, 265, 68, 23);
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
		panel.setBounds(10, 54, 304, 306);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblBlueTeam1 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[0].getTeamName() + " : ");
		lblBlueTeam1.setBounds(10, 167, 143, 14);
		panel.add(lblBlueTeam1);

		JLabel lblBlueTeam2 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[1].getTeamName() + " : ");
		lblBlueTeam2.setBounds(10, 221, 155, 14);
		panel.add(lblBlueTeam2);

		JLabel lblBlueTeam3 = new JLabel(
				ScoutingApp.regionalCollection().getMatch(matchID).getBlueTeams()[2].getTeamName() + " : ");
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
