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

		setSize(new Dimension(1024, 600));
		setTitle("Scouting - Team Detail");
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		//JMenuItem mntmShowTeamHub = new JMenuItem("Show Team Hub ...");
		//mnView.add(mntmShowTeamHub);

		//JMenuItem mntmShowMatchHub = new JMenuItem("Show Match Hub ...");
		//mnView.add(mntmShowMatchHub);

		JMenuItem mntmExit = new JMenuItem("Exit");
		mnView.add(mntmExit);
		getContentPane().setLayout(null);

		JLabel lblTeamNumber = new JLabel(Integer.toString(team) + " - " + currentTeam.getTeamName());
		lblTeamNumber.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblTeamNumber.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeamNumber.setBounds(47, 11, 346, 51);
		getContentPane().add(lblTeamNumber);

		JScrollPane scrollPaneMatches = new JScrollPane();
		scrollPaneMatches.setBounds(47, 73, 903, 176);
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
		lblOverview.setHorizontalAlignment(SwingConstants.CENTER);
		lblOverview.setFont(new Font("Courier New", Font.PLAIN, 15));
		lblOverview.setBounds(10, 313, 140, 31);
		getContentPane().add(lblOverview);

		JScrollPane scrollPaneOverview = new JScrollPane();
		scrollPaneOverview.setBounds(47, 353, 903, 125);
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
				new String[] { "Robot Abilities", "Auto Average", "Auto Consistency", "Auto Average Time",
						"Teleop Average", "Teleop Consistency", "Teleop Average Time" }));

		
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
		scrollPaneOverview.setViewportView(tblOverview);
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
//		for (TeamPerformance performance : currentTeam.teamPerformances.values()) {
//			for(int i = 0; i <= 6; i++){	
//				ArrayList<Integer> data = currentTeam.getData(i, performance);
//				System.out.println(i);
//				System.out.println(data.size());
//				for(int j = 0; j < data.size(); j++){
//					System.out.println(data.get(j));
//				}
//			}
//		}
		
		/*mntmShowTeamHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							TeamHub frame = new TeamHub();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});*/
		
		/*mntmShowMatchHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							MatchHub frame = new MatchHub();
							frame.setVisible(true);
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});*/
		
		mntmExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				dispose();
			}
		});
		
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
				new String[] { "Match Number", "Comments", "Scouter"}) {
			private static final long serialVersionUID = -6261637160294735163L;

			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
		
		tblMatches.setModel(tableModel);
	}
}
