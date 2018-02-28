package scoutingapp.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.team.TeamPerformance;

@SuppressWarnings("serial")
public class TeamPerformanceWindow extends JFrame {

	private JPanel contentPane;
	public JLabel lblMatchNumber;
	public JTable tblSwitch;
	public JTable tblScale;
	public JTable tblVault;
	private JTextField txtForce;
	private JTextField txtBoost;
	public JCheckBox chkBaseline;

	private int teamNumber, matchID;
	public JTextField txtClimb;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamPerformanceWindow frame = new TeamPerformanceWindow(5834, 10);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void saveData() {

		try {

			if (!TeamHub.regionalCollection.teamExists(teamNumber)) {
				TeamHub.regionalCollection.createTeam(teamNumber);
			}

			TeamHub.regionalCollection.addTeamPerformance(teamNumber, matchID, this);

			TeamHub.regionalCollection.showTeamPerformance(matchID, teamNumber);

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Please fill in the match detail page properly.",
					"Incomplete Match Detail", JOptionPane.OK_OPTION);

		} catch (ExistingException e) {

		}

	}

	/**
	 * Create a window from a team performance file
	 */
	public TeamPerformanceWindow(int teamNumber, int matchID, TeamPerformance teamPerformance) {

		this.teamNumber = teamNumber;
		this.matchID = matchID;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 427);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmTeams = new JMenuItem("Teams");
		mnView.add(mntmTeams);

		JMenuItem mntmMatches = new JMenuItem("Matches");
		mnView.add(mntmMatches);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchDetail = new JLabel("Match Detail");
		lblMatchDetail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchDetail.setBounds(222, 2, 97, 36);
		contentPane.add(lblMatchDetail);

		JLabel lblTeam = new JLabel("Team " + teamNumber);
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblTeam.setBounds(134, 42, 95, 24);
		contentPane.add(lblTeam);

		lblMatchNumber = new JLabel("Match " + matchID);
		lblMatchNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatchNumber.setBounds(331, 42, 95, 24);
		contentPane.add(lblMatchNumber);

		JSeparator separator = new JSeparator();
		separator.setBounds(109, 77, 394, 2);
		contentPane.add(separator);

		chkBaseline = new JCheckBox("(Auto) Crossed Baseline");
		chkBaseline.setBounds(138, 84, 154, 23);
		chkBaseline.setSelected(teamPerformance.crossedBaseLine);
		chkBaseline.setEnabled(false);
		contentPane.add(chkBaseline);

		txtClimb = new JTextField();
		txtClimb.setBounds(435, 85, 68, 20);
		txtClimb.setText(Integer.toString(teamPerformance.climb));
		txtClimb.setEnabled(false);
		contentPane.add(txtClimb);
		txtClimb.setColumns(10);
		
		JLabel lblStartClimbTime = new JLabel("Start Climb Time");
		lblStartClimbTime.setBounds(343, 88, 82, 14);
		contentPane.add(lblStartClimbTime);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(109, 114, 394, 2);
		contentPane.add(separator_1);

		// headers for the table
		String[] columns = new String[] { "Auto", "Time" };
		// actual data for the table in a 2d array
		Object[][] data = new Object[][] { { false, "0:00" } };

		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(86, 116, 47, 24);
		contentPane.add(lblSwitch);

		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 140, 199, 147);
		contentPane.add(scrollPaneSwitch);

		tblSwitch = new JTable(data, columns);
		tblSwitch.setModel(teamPerformance.rawSwitchTable);
		tblSwitch.setEnabled(false);
		scrollPaneSwitch.setViewportView(tblSwitch);

		JLabel label = new JLabel("Scale");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(294, 116, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(231, 140, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();
		tblScale.setModel(teamPerformance.rawScaleTable);
		tblScale.setEnabled(false);
		scrollPaneScale.setViewportView(tblScale);

		JScrollPane scrollPaneVault = new JScrollPane();
		scrollPaneVault.setBounds(431, 140, 163, 147);
		contentPane.add(scrollPaneVault);

		tblVault = new JTable();
		tblVault.setModel(teamPerformance.rawVaultTable);
		tblVault.setEnabled(false);
		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(447, 116, 129, 24);
		contentPane.add(label_1);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 140, 147, 147);
		contentPane.add(scrollPane);

	}

	/**
	 * Create a window from a blank template
	 * 
	 * @wbp.parser.constructor
	 */
	public TeamPerformanceWindow(int teamNumber, int matchID) {

		this.teamNumber = teamNumber;
		this.matchID = matchID;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 360);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmTeams = new JMenuItem("Teams");
		mnView.add(mntmTeams);

		JMenuItem mntmMatches = new JMenuItem("Matches");
		mnView.add(mntmMatches);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchDetail = new JLabel("Match Detail");
		lblMatchDetail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchDetail.setBounds(263, 11, 97, 36);
		contentPane.add(lblMatchDetail);

		JLabel lblTeam = new JLabel("Team " + teamNumber);
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));

		lblTeam.setBounds(134, 42, 95, 24);
		contentPane.add(lblTeam);

		lblMatchNumber = new JLabel("Match " + matchID);
		lblMatchNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatchNumber.setBounds(395, 42, 95, 24);
		contentPane.add(lblMatchNumber);

		JSeparator separator = new JSeparator();
		separator.setBounds(109, 77, 394, 2);
		contentPane.add(separator);

		chkBaseline = new JCheckBox("(Auto) Crossed Baseline");
		chkBaseline.setBounds(138, 84, 154, 23);
		contentPane.add(chkBaseline);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(109, 114, 394, 2);
		contentPane.add(separator_1);

		// headers for the table
		String[] columns = new String[] { "Auto", "Time" };
		// actual data for the table in a 2d array
		Object[][] data = new Object[][] { { false, "0:00" } };

		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(86, 116, 47, 24);
		contentPane.add(lblSwitch);

		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 140, 199, 147);
		contentPane.add(scrollPaneSwitch);

		tblSwitch = new JTable(data, columns);
		tblSwitch.setModel(new DefaultTableModel(
				new Object[][] { { false, false, "0:00" }, { false, false, "0:00" }, { false, false, "0:00" }, },
				new String[] { "Auto", "Offense", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneSwitch.setViewportView(tblSwitch);

		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.setBounds(250, 44, 110, 23);
		contentPane.add(btnSaveData);

		JLabel label = new JLabel("Scale");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(294, 116, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(231, 140, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();
		tblScale.setModel(new DefaultTableModel(
				new Object[][] { { false, false, "0:00" }, { false, false, "0:00" }, { false, false, "0:00" }, },
				new String[] { "In Auto", "Defense", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneScale.setViewportView(tblScale);

		JScrollPane scrollPaneVault = new JScrollPane();
		scrollPaneVault.setBounds(431, 140, 163, 147);
		contentPane.add(scrollPaneVault);

		tblVault = new JTable();
		tblVault.setModel(
				new DefaultTableModel(new Object[][] { { false, "0:00" }, { false, "0:00" }, { false, "0:00" }, },
						new String[] { "In Auto", "Time" }) {
					Class[] columnTypes = new Class[] { Boolean.class, String.class };

					public Class getColumnClass(int columnIndex) {
						return columnTypes[columnIndex];
					}
				});
		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(447, 116, 129, 24);
		contentPane.add(label_1);

		btnSaveData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				saveData();

			}
		});
		contentPane.add(btnSaveData);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 140, 147, 147);
		contentPane.add(scrollPane);
		
		txtClimb = new JTextField();
		txtClimb.setBounds(435, 85, 68, 20);
		contentPane.add(txtClimb);
		txtClimb.setColumns(10);
		
		JLabel lblStartClimbTime = new JLabel("Start Climb Time");
		lblStartClimbTime.setBounds(343, 88, 82, 14);
		contentPane.add(lblStartClimbTime);
	}
}
