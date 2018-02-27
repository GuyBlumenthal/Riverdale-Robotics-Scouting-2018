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
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ExistingException;
import scoutingapp.commons.Match;
import scoutingapp.commons.RegionalCollection;
import scoutingapp.commons.Team;

public class MatchDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtTeamNumber;
	private JLabel lblMatchNumber;
	private JTextField txtMatchNumber;
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
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 675, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new LineBorder(new Color(0, 0, 0)));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblMatchDetail = new JLabel("Match Detail");
		lblMatchDetail.setFont(new Font("Trebuchet MS", Font.PLAIN, 16));
		lblMatchDetail.setBounds(168, 11, 97, 24);
		contentPane.add(lblMatchDetail);

		JLabel lblTeam = new JLabel("Team Number: ");
		lblTeam.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblTeam.setBounds(34, 44, 95, 24);
		contentPane.add(lblTeam);

		txtTeamNumber = new JTextField();
		txtTeamNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtTeamNumber.setBounds(130, 46, 62, 20);
		contentPane.add(txtTeamNumber);
		txtTeamNumber.setColumns(10);

		lblMatchNumber = new JLabel("Match Number: ");
		lblMatchNumber.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblMatchNumber.setBounds(231, 44, 95, 24);
		contentPane.add(lblMatchNumber);

		txtMatchNumber = new JTextField();
		txtMatchNumber.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtMatchNumber.setColumns(10);
		txtMatchNumber.setBounds(328, 46, 62, 20);
		contentPane.add(txtMatchNumber);

		JSeparator separator = new JSeparator();
		separator.setBounds(10, 79, 438, 2);
		contentPane.add(separator);

		JCheckBox chkBasline = new JCheckBox("(Auto) Crossed Baseline");
		chkBasline.setBounds(20, 86, 154, 23);
		contentPane.add(chkBasline);

		JCheckBox chkClimb = new JCheckBox("(Teleop) Climb");
		chkClimb.setBounds(231, 86, 110, 23);
		contentPane.add(chkClimb);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 116, 438, 2);
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
				new Object[][] { { null, null, "0:00" }, { null, null, null }, { null, null, null }, },
				new String[] { "In Auto", "Defense", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneSwitch.setViewportView(tblSwitch);

		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.setBounds(10, 333, 89, 23);
		contentPane.add(btnSaveData);

		JLabel label = new JLabel("Scale");
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(231, 116, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(231, 140, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();
		tblScale.setModel(new DefaultTableModel(
				new Object[][] { { null, null, "0:00" }, { null, null, null }, { null, null, null }, },
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
		tblVault.setModel(new DefaultTableModel(new Object[][] { { null, "0:00" }, { null, null }, { null, null }, },
				new String[] { "In Auto", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, String.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(431, 116, 129, 24);
		contentPane.add(label_1);

		JLabel lblPower = new JLabel("Power Ups Played");
		lblPower.setBounds(109, 298, 97, 20);
		contentPane.add(lblPower);

		JLabel lblForce = new JLabel("Force played at: ");
		lblForce.setBounds(109, 337, 83, 14);
		contentPane.add(lblForce);

		txtForce = new JTextField();
		txtForce.setBounds(192, 334, 86, 20);
		contentPane.add(txtForce);
		txtForce.setColumns(10);

		txtBoost = new JTextField();
		txtBoost.setColumns(10);
		txtBoost.setBounds(375, 334, 86, 20);
		contentPane.add(txtBoost);

		JLabel label_2 = new JLabel("Boost played at: ");
		label_2.setBounds(288, 337, 83, 14);
		contentPane.add(label_2);

		JCheckBox chkLevitate = new JCheckBox("Levitate");
		chkLevitate.setBounds(497, 333, 97, 23);
		contentPane.add(chkLevitate);

		JCheckBox chkAlliance = new JCheckBox("Check if on Red Alliance");
		chkAlliance.setBounds(394, 86, 166, 23);
		contentPane.add(chkAlliance);

		btnSaveData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				Team team = new Team(Integer.parseInt(txtTeamNumber.getText()), chkAlliance.isSelected());
				try {
					MatchHub.regionalCollection.createTeam(team);
					team.climb.add(chkClimb.isSelected() ? 1 : 0);
					team.crossedBaseLine.add(chkBasline.isSelected() ? 1 : 0);

					for (int i = 0; i < tblSwitch.getRowCount() - 2; i++) {
					}

				} catch (ExistingException e) {
					// TODO Auto-generated catch block
					System.out.println("Team already exists");
				}
			}
		});
		btnSaveData.setBounds(10, 298, 89, 23);
		contentPane.add(btnSaveData);

		JLabel label2 = new JLabel("Switch");
		label2.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label2.setBounds(231, 116, 47, 24);
		contentPane.add(label2);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(231, 140, 147, 147);
		contentPane.add(scrollPane);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(485, 140, 147, 147);
		contentPane.add(scrollPane_1);
	}
}
