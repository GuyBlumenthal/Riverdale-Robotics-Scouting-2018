package scoutingapp.views;

import java.awt.Dimension;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.team.Team;
import scoutingapp.commons.team.TeamPerformance;

public class TeamDetail extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4828606662028786474L;
	private JTable tblMatches;
	private JTable tblOverview;
	
	private Team currentTeam;
	
	public TeamDetail(int team) {
		currentTeam = ScoutingApp.regionalCollection().getTeam(team);
		setResizable(false);

		setSize(new Dimension(1024, 600));
		setTitle("Scouting - Team Detail");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmShowTeamHub = new JMenuItem("Show Team Hub ...");
		mnView.add(mntmShowTeamHub);

		JMenuItem mntmShowMatchHub = new JMenuItem("Show Match Hub ...");
		mnView.add(mntmShowMatchHub);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnView.add(mntmExit);
		getContentPane().setLayout(null);

		JLabel lblTeamNumber = new JLabel(Integer.toString(team) + " - " + currentTeam.getTeamName());
		lblTeamNumber.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblTeamNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamNumber.setBounds(47, 11, 346, 51);
		getContentPane().add(lblTeamNumber);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 73, 903, 176);
		getContentPane().add(scrollPane);

		tblMatches = new JTable();
		tblMatches.setEnabled(false);
		tblMatches.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Match Number", "Performance", "Comments"
			}
		));

		tblMatches.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(tblMatches);

		JLabel lblOverview = new JLabel("Overview");
		lblOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverview.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblOverview.setBounds(10, 313, 140, 31);
		getContentPane().add(lblOverview);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(47, 353, 903, 110);
		getContentPane().add(scrollPane_1);

		tblOverview = new JTable();
		tblOverview.setEnabled(false);

		tblOverview.setModel(new DefaultTableModel(new Object[][] {

				{ "Cube on Switch", currentTeam.calcAverage(0),
						currentTeam.calcConsistency(0),
						currentTeam.calcAverageTime(0),
						currentTeam.calcTeleopNumCubesOnSwitchAverage(),
						currentTeam.calcTeleopNumCubesOnSwitchConsistency(),
						currentTeam.calcAverageSwitchTimeTeleop() },

				{ "Cube on Scale", currentTeam.calcAverage(1),
						currentTeam.calcConsistency(1),
						currentTeam.calcAverageTime(1),
						currentTeam.calcAverage(2),
						currentTeam.calcConsistency(2),
						currentTeam.calcAverageTime(2) },

				{ "Cube in Vault", currentTeam.calcAverage(3),
						currentTeam.calcConsistency(3),
						currentTeam.calcAverageTime(3),
						currentTeam.calcAverage(4),
						currentTeam.calcConsistency(4),
						currentTeam.calcAverageTime(4) },

				{ "Baseline", currentTeam.calcBooleanAverage(true),
						currentTeam.calcBooleanConsistency(true), null, null, null,
						null },

				{ "Climb", null, null, null, 	
						currentTeam.calcBooleanAverage(false),
						currentTeam.calcBooleanConsistency(false),
						currentTeam.calcAverageClimbTime()} },
				new String[] { "Robot Abilities", "Auto Average", "Auto Consistency", "Auto Average Time",
						"Teleop Average", "Teleop Consistency", "Teleop Average Time" }));

		
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
		scrollPane_1.setViewportView(tblOverview);
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
		for (TeamPerformance performance : currentTeam.teamPerformances.values()) {
			for(int i = 0; i <= 6; i++){	
				ArrayList<Integer> data = currentTeam.getData(i, performance);
				System.out.println(i);
				System.out.println(data.size());
				for(int j = 0; j < data.size(); j++){
					System.out.println(data.get(j));
				}
			}
		}

	}
}
