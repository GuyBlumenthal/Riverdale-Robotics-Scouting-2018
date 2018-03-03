package scoutingapp.views;

import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
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

public class TeamDetail extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4828606662028786474L;
	private JTable tblMatches;
	private JTable tblOverview;

	public TeamDetail(int team) {

		setResizable(false);

		setSize(new Dimension(1024, 600));

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

		JLabel lblTeamNumber = new JLabel(Integer.toString(team));
		lblTeamNumber.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblTeamNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamNumber.setBounds(10, 11, 155, 51);
		getContentPane().add(lblTeamNumber);

		JButton btnNewButton = new JButton("Edit");
		btnNewButton.setBounds(175, 39, 89, 23);
		getContentPane().add(btnNewButton);

		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(293, 39, 89, 23);
		getContentPane().add(btnAdd);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(47, 73, 903, 176);
		getContentPane().add(scrollPane);

		tblMatches = new JTable();
		tblMatches.setEnabled(false);
		tblMatches.setModel(new DefaultTableModel(new String[] { "Match Number", "Performance", "Comments" },
				ScoutingApp.regionalCollection().getTeam(team).getMatchesPlayed()) {
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

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

				{ "Cube on Switch", ScoutingApp.regionalCollection().getTeam(team).calcAverage(0),
						ScoutingApp.regionalCollection().getTeam(team).calcConsistency(0),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageTime(0),
						ScoutingApp.regionalCollection().getTeam(team).calcTeleopNumCubesOnSwitchAverage(),
						ScoutingApp.regionalCollection().getTeam(team).calcTeleopNumCubesOnSwitchConsistency(),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageSwitchTimeTeleop() },

				{ "Cube on Scale", ScoutingApp.regionalCollection().getTeam(team).calcAverage(1),
						ScoutingApp.regionalCollection().getTeam(team).calcConsistency(1),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageTime(1),
						ScoutingApp.regionalCollection().getTeam(team).calcAverage(2),
						ScoutingApp.regionalCollection().getTeam(team).calcConsistency(2),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageTime(2) },

				{ "Cube in Vault", ScoutingApp.regionalCollection().getTeam(team).calcAverage(3),
						ScoutingApp.regionalCollection().getTeam(team).calcConsistency(3),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageTime(3),
						ScoutingApp.regionalCollection().getTeam(team).calcAverage(4),
						ScoutingApp.regionalCollection().getTeam(team).calcConsistency(4),
						ScoutingApp.regionalCollection().getTeam(team).calcAverageTime(4) },

				{ "Baseline", ScoutingApp.regionalCollection().getTeam(team).calcBooleanAverage(true),
						ScoutingApp.regionalCollection().getTeam(team).calcBooleanConsistency(true), null, null, null,
						null },

				{ "Climb", ScoutingApp.regionalCollection().getTeam(team).calcBooleanAverage(false),
						ScoutingApp.regionalCollection().getTeam(team).calcBooleanConsistency(false), null, null, null,
						null } },
				new String[] { "Robot Abilities", "Auto Average", "Auto Consistency", "Auto Average Time",
						"Teleop Average", "Teleop Consistency", "Teleop Average Time" }));

		
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
		scrollPane_1.setViewportView(tblOverview);

		JButton btnTeamDetail = new JButton("Team Detail");
		btnTeamDetail.setBounds(392, 39, 89, 23);
		getContentPane().add(btnTeamDetail);
		tblOverview.getTableHeader().setReorderingAllowed(false);

	}
}
