package scoutingapp.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.Team;

public class TeamHub extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4828606662028786474L;
	private JTable tblMatches;
	private JTable tblOverview;
	private Team team;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamHub frame = new TeamHub(5834);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public TeamHub(int teamNumber) {
		setResizable(false);
		this.team = new Team(teamNumber);
			
		
		setSize(new Dimension(1024, 600));

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		mnFile.setPreferredSize(new Dimension(80, 22));
		menuBar.add(mnFile);
		getContentPane().setLayout(null);

		JLabel lblTeamNumber = new JLabel(Integer.toString(teamNumber));
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
		tblMatches.setModel(new DefaultTableModel(
			new String[] {
				"Match Number", "Performance", "Comments"
			}, team.getMatchesPlayed()
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			boolean[] columnEditables = new boolean[] {
				false, false, false
			};
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
		scrollPane_1.setBounds(47, 353, 903, 91);
		getContentPane().add(scrollPane_1);
		
		tblOverview = new JTable();
		tblOverview.setEnabled(false);
		tblOverview.setModel(new DefaultTableModel(
			new Object[][] {
				{"Cube on Switch", team.calcAverage(team.numCubesOnSwitchAuto), team.calcConsistency(team.numCubesOnSwitchAuto), 
								   team.calcAverage(team.numCubesOnSwitchTeleop), team.calcConsistency(team.numCubesOnSwitchTeleop)},
				
				{"Cube on Scale", team.calcAverage(team.numCubesOnScaleAuto), team.calcConsistency(team.numCubesOnScaleAuto), 
								  team.calcAverage(team.numCubesOnSwitchTeleop), team.calcConsistency(team.numCubesOnSwitchTeleop)},
				
				{"Baseline", team.calcAverage(team.crossedBaseLine), team.calcConsistency(team.crossedBaseLine), null, null},
				
				{"Climb", team.calcAverage(team.climb), team.calcConsistency(team.climb), null, null},
			},
			new String[] {
				"Robot Abilities", "Auto Average", "Auto Consistency", "Teleop Average", "Teleop Consistency"
			}
		));
		scrollPane_1.setViewportView(tblOverview);
		
		JButton btnTeamDetail = new JButton("Team Detail");
		btnTeamDetail.setBounds(392, 39, 89, 23);
		getContentPane().add(btnTeamDetail);
		tblOverview.getTableHeader().setReorderingAllowed(false);
		
	}
}
