package scoutingapp.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.team.Team;
import scoutingapp.commons.team.TeamPerformance;
import scoutingapp.views.creation.CreateTeam;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;

public class TeamDetail extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4828606662028786474L;
	private JTable tblMatches;
	private JTable tblOverview;
	
	private Team currentTeam;
	
	public TeamDetail(int team) {
		initInterface(team);
		createMatchTable();
	}
	
	private void initInterface(int team) {
		currentTeam = ScoutingApp.regionalCollection().getTeam(team);
		setResizable(false);

		setSize(new Dimension(635, 489));
		setTitle("Scouting - Team Detail");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);
		
		JMenuItem mntmPitScouting = new JMenuItem("Pit Scouting");
		mntmPitScouting.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if (currentTeam.pitScouting.equals("") == false) {
					
					PitScouting pit = new PitScouting (currentTeam.getTeamNumber(), false);
					
				} else {
					
					PitScouting pit = new PitScouting (currentTeam.getTeamNumber(), true);
					
				}
				
			}
		});
		mnView.add(mntmPitScouting);
		getContentPane().setLayout(null);

		JLabel lblTeamNumber = new JLabel(Integer.toString(team) + " - " + currentTeam.getTeamName());
		lblTeamNumber.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTeamNumber.setHorizontalAlignment(SwingConstants.LEFT);
		lblTeamNumber.setBounds(28, 11, 270, 51);
		getContentPane().add(lblTeamNumber);

		JScrollPane scrollPaneMatches = new JScrollPane();
		scrollPaneMatches.setBounds(10, 73, 604, 176);
		getContentPane().add(scrollPaneMatches);

		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							int match = (Integer)tblMatches.getValueAt(tblMatches.getSelectedRow(), 0);
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							TeamPerformanceWindow teamPerformance = new TeamPerformanceWindow(
									currentTeam.getTeamNumber(), match, currentTeam.getTeamPerformance(match),false);
							teamPerformance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							teamPerformance.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		tblMatches.getTableHeader().setReorderingAllowed(false);
		scrollPaneMatches.setViewportView(tblMatches);

		JLabel lblOverview = new JLabel("Overview");
		lblOverview.setHorizontalAlignment(SwingConstants.LEFT);
		lblOverview.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblOverview.setBounds(28, 260, 140, 31);
		getContentPane().add(lblOverview);

		JScrollPane scrollPaneOverview = new JScrollPane();
		scrollPaneOverview.setBounds(10, 302, 604, 125);
		getContentPane().add(scrollPaneOverview);

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
						currentTeam.calcClimbAverage(),
						currentTeam.calcClimbConsistency(),
						currentTeam.calcAverageClimbTime() },
				
				{ "Parking", null, null, null, 
						currentTeam.calcBooleanAverage(false),
						currentTeam.calcBooleanConsistency(false),
						null }
				},
				new String[] { "Robot Abilities", "Auto Average", "Consistency", "Average Time",
						"Teleop Average", "Consistency", "Average Time" }));

		
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
		scrollPaneOverview.setViewportView(tblOverview);
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
	}

	public void createMatchTable(){
		Object[][] arrayValues = new Object[currentTeam.teamPerformances.size()][3];
		
		int x = 0;
		for(Integer matchID : currentTeam.teamPerformances.keySet()){
			arrayValues[x][0] = matchID;
			x++;
		}
		
		x = 0;
		for (TeamPerformance performances : currentTeam.teamPerformances.values()){
			arrayValues[x][1] = performances.comments;
			arrayValues[x][2] = performances.scouterName;
			x++;
		}

		
		DefaultTableModel tableModel = new DefaultTableModel(arrayValues,
				new String[] { "Match", "Scouter", "Comments"}) {
			private static final long serialVersionUID = -6261637160294735163L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		
		tblMatches.setModel(new DefaultTableModel(
			arrayValues,
			new String[] {
				"Match", "Scouter", "Comments"
			}
		));
		tblMatches.getColumnModel().getColumn(0).setMinWidth(75);
		tblMatches.getColumnModel().getColumn(0).setMaxWidth(75);
		tblMatches.getColumnModel().getColumn(1).setPreferredWidth(150);
		tblMatches.getColumnModel().getColumn(1).setMinWidth(150);
		tblMatches.getColumnModel().getColumn(1).setMaxWidth(150);
	}
}
