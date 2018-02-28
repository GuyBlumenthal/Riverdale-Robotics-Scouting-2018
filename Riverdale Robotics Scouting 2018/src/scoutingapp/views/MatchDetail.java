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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.team.Team;
import javax.swing.SwingConstants;

public class MatchDetail extends JFrame {

	private JPanel contentPane;
	private JLabel lblMatchNumber;
	private JTable tblSwitch;
	private JTable tblScale;
	private JTable tblVault;
	private JTextField txtForce;
	private JTextField txtBoost;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MatchDetail frame = new MatchDetail();
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
	public MatchDetail() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 650, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchDetail = new JLabel("Match Detail");
		lblMatchDetail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchDetail.setBounds(258, 11, 97, 24);
		contentPane.add(lblMatchDetail);

		JLabel lblTeam = new JLabel("Team Number: ");
		lblTeam.setHorizontalAlignment(SwingConstants.CENTER);
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeam.setBounds(34, 44, 158, 24);
		contentPane.add(lblTeam);

		lblMatchNumber = new JLabel("Match Number: ");
		lblMatchNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatchNumber.setBounds(326, 46, 95, 24);
		contentPane.add(lblMatchNumber);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 586, 2);
		contentPane.add(separator);

		JCheckBox chkBasline = new JCheckBox("(Auto) Crossed Baseline");
		chkBasline.setBounds(20, 86, 189, 23);
		contentPane.add(chkBasline);

		JCheckBox chkClimb = new JCheckBox("(Teleop) Climb");
		chkClimb.setBounds(231, 86, 110, 23);
		contentPane.add(chkClimb);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 116, 584, 2);
		contentPane.add(separator_1);

		// headers for the table
		String[] columns = new String[] { "Auto", "Time" };
		// actual data for the table in a 2d array
		Object[][] data = new Object[][] { { false, "0:00" } };

		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(10, 116, 47, 24);
		contentPane.add(lblSwitch);

		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 140, 199, 147);
		contentPane.add(scrollPaneSwitch);

		tblSwitch = new JTable(data, columns);

		tblSwitch.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, "0:00"},
				{null, null, "0:01"},
				{null, null, "0:02"},
			},
			new String[] {
				"In Auto", "Defense", "Time"
			}
		) {
			Class[] columnTypes = new Class[] {
				Boolean.class, Boolean.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneSwitch.setViewportView(tblSwitch);

		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.setBounds(10, 298, 150, 23);
		contentPane.add(btnSaveData);

		JLabel label = new JLabel("Scale");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(231, 116, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(231, 140, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();
		DefaultTableModel mdlScale = new DefaultTableModel(
				new Object[][] {
					{new Boolean(false), "0:00"},
				},
				new String[] {
					"In Auto", "Time"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		tblScale.setModel(mdlScale);
		scrollPaneScale.setViewportView(tblScale);

		JScrollPane scrollPaneVault = new JScrollPane();
		scrollPaneVault.setBounds(431, 140, 163, 147);
		contentPane.add(scrollPaneVault);

		tblVault = new JTable();
		DefaultTableModel mdlVault = new DefaultTableModel(
				new Object[][] {
					{new Boolean(false), "0:00"},
				},
				new String[] {
					"In Auto", "Time"
				}
			) {
				Class[] columnTypes = new Class[] {
					Boolean.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		tblVault.setModel(mdlVault);

		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(431, 116, 129, 24);
		contentPane.add(label_1);

		JLabel lblPower = new JLabel("Power Ups Played");
		lblPower.setBounds(109, 299, 169, 20);
		contentPane.add(lblPower);

		JLabel lblForce = new JLabel("Force played at: ");
		lblForce.setBounds(57, 337, 105, 14);
		contentPane.add(lblForce);

		txtForce = new JTextField();
		txtForce.setBounds(162, 334, 86, 20);
		contentPane.add(txtForce);
		txtForce.setColumns(10);

		txtBoost = new JTextField();
		txtBoost.setColumns(10);
		txtBoost.setBounds(375, 334, 86, 20);
		contentPane.add(txtBoost);

		JLabel label_2 = new JLabel("Boost played at: ");
		label_2.setBounds(258, 337, 113, 14);
		contentPane.add(label_2);

		JCheckBox chkLevitate = new JCheckBox("Levitate");
		chkLevitate.setBounds(497, 333, 97, 23);
		contentPane.add(chkLevitate);

		JCheckBox chkAlliance = new JCheckBox("Check if on Red Alliance");
		chkAlliance.setBounds(394, 86, 166, 23);
		contentPane.add(chkAlliance);

		btnSaveData.setBounds(10, 298, 89, 23);
		contentPane.add(btnSaveData);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 140, 147, 147);
		contentPane.add(scrollPane);
		
		JButton btnAddCubeOnSwitch = new JButton("Add");
		btnAddCubeOnSwitch.setBounds(57, 118, 75, 20);
		contentPane.add(btnAddCubeOnSwitch);
		
		JButton btnAddCubeOnScale = new JButton("Add");
		btnAddCubeOnScale.setBounds(266, 118, 75, 20);
		contentPane.add(btnAddCubeOnScale);
		
		JButton btnAddCubeInVault = new JButton("Add");
		btnAddCubeInVault.setBounds(562, 119, 62, 20);
		contentPane.add(btnAddCubeInVault);
		
		
		btnAddCubeOnSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlSwitch.addRow(new Object[] { null, null, "0:00" });
				scrollPaneSwitch.setViewportView(tblSwitch);
			}
		});
		
		btnAddCubeOnScale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlScale.addRow(new Object[] { null, "0:00" });
				scrollPaneScale.setViewportView(tblScale);
			}
		});
		
		btnAddCubeInVault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlVault.addRow(new Object[] { null, "0:00" });
				scrollPaneVault.setViewportView(tblVault);
			}
		});
		
		
		btnSaveData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Team team = new Team(Integer.parseInt(txtTeamNumber.getText()));
				try {
					team = setTableData(team, tblSwitch);
					setTableData(team, tblScale); /*TODO: joining objects together */
					setTableData(team, tblVault);
					
					MatchHub.regionalCollection.createTeam(team);

				} catch (ExistingException e) {
					// TODO Auto-generated catch block
					System.out.println("Team already exists");
				}
			}
		});
	}

	public Team setTableData(Team team, JTable tblTable) {

		int nCubesInAuto = 0, isDefense = 0, isOffense = 0;
		String[] timesAuto = new String[tblTable.getRowCount() - 1];
		String[] timesTeleop = new String[tblTable.getRowCount() - 1];

		// initialize arrays
		for (int i = 0; i < timesAuto.length; i++) {
			timesAuto[i] = "";
			timesTeleop[i] = "";
		}

		for (int i = 1; i < tblTable.getRowCount() - 1; i++) {

			// checking if cube was placed in auto or not
			if ((Boolean) tblTable.getModel().getValueAt(i, 0)) {

				nCubesInAuto++;
				timesAuto[i - 1] = (String) tblTable.getModel().getValueAt(i, 2);

			} else {

				if (tblTable.getName().equals("tblSwitch")) {
					// played defensively or offensively in teleop
					if ((Boolean) tblTable.getModel().getValueAt(i, 1)) {
						isDefense++;
					} else {
						isOffense++;
					}

					timesTeleop[i - 1] = (String) tblTable.getModel().getValueAt(i, 2);
				} else {
					timesTeleop[i - 1] = (String) tblTable.getModel().getValueAt(i, 1);
				}
			}

		}

		team.numCubesOnSwitchAuto.add(nCubesInAuto);
		team.numCubesOnAllianceSwitch.add(isDefense);
		team.numCubesOnOpponentsSwitch.add(isOffense);

		int nAuto = 0, nTeleop = 0;

		switch (tblTable.getName()) {
		case "tblSwitch":
			nAuto = 0;
			nTeleop = 1;
			break;
		case "tblScale":
			nAuto = 2;
			nTeleop = 3;
			break;
		case "tblVault":
			nAuto = 4;
			nTeleop = 4;
			break;
		}

		team.convertTimes(timesAuto, nAuto);
		team.convertTimes(timesTeleop, nTeleop);

		return team;
	}
}
