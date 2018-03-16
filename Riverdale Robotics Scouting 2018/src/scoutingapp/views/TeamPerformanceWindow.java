package scoutingapp.views;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.existing.ExistingException;
import scoutingapp.commons.team.TeamPerformance;

@SuppressWarnings("serial")
public class TeamPerformanceWindow extends JDialog {

	private JPanel contentPane;
	public JLabel lblMatchNumber;
	public JTable tblSwitch;
	public JTable tblScale;
	public JTable tblVault;
	private JTextField txtForce;
	private JTextField txtBoost;
	public JCheckBox chkBaseline;
	public JCheckBox chkParked;
	public JTextArea txtaComments;

	private int teamNumber, matchID;
	public JTextField txtClimb;
	public JTextField txtScouter;

	TeamPerformance teamPerformance;
	private JMenuBar menuBar;

	public void saveData() {

		try {

			if (!ScoutingApp.regionalCollection().teamExists(teamNumber)) {
				ScoutingApp.regionalCollection().createTeam(teamNumber);
			}
			ScoutingApp.regionalCollection().addTeamPerformance(teamNumber, matchID, this);

			JOptionPane.showMessageDialog(null, "Saved Changes.");

			ScoutingApp.updateMatchOverview();

			dispose();

			ScoutingApp.setUnsaved();

		} catch (NumberFormatException e) {

			System.out.println(e);

			JOptionPane.showMessageDialog(null, "Please fill in the match detail page properly.",
					"Incomplete Match Detail", JOptionPane.OK_OPTION);

		} catch (ExistingException e) {
			e.printStackTrace();
		}

	}

	public void replaceData() {

		try {

			if (!ScoutingApp.regionalCollection().teamExists(teamNumber)) {
				ScoutingApp.regionalCollection().createTeam(teamNumber);
			}
			ScoutingApp.regionalCollection().removeTeamPerformance(teamNumber, matchID);
			ScoutingApp.regionalCollection().addTeamPerformance(teamNumber, matchID, this);

			JOptionPane.showMessageDialog(null, "Saved Changes.");

			ScoutingApp.updateMatchOverview();

			dispose();

			ScoutingApp.setUnsaved();

		} catch (NumberFormatException e) {

			JOptionPane.showMessageDialog(null, "Please fill in the match detail page properly.",
					"Incomplete Match Detail", JOptionPane.OK_OPTION);

		} catch (ExistingException e) {

		}

	}

	/**
	 * Create a window from a team performance file
	 */
	public TeamPerformanceWindow(int teamNumber, int matchID, TeamPerformance teamPerformance, boolean editable) {

		this.teamNumber = teamNumber;
		this.matchID = matchID;

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 495);
		setResizable(false);
		setModal(true);

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

		if (editable) {
			JButton btnSaveData = new JButton("Save Data");
			btnSaveData.setBounds(250, 44, 110, 23);
			contentPane.add(btnSaveData);

			btnSaveData.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {

					replaceData();

				}
			});
		}

		chkBaseline = new JCheckBox("(Auto) Crossed Baseline");
		chkBaseline.setBounds(97, 84, 154, 23);
		chkBaseline.setSelected(teamPerformance.crossedBaseLine);
		chkBaseline.setEnabled(editable);
		contentPane.add(chkBaseline);

		txtClimb = new JTextField();
		txtClimb.setBounds(349, 87, 68, 20);
		txtClimb.setText(teamPerformance.climb > 0 ? Integer.toString(teamPerformance.climb) : "");
		txtClimb.setEnabled(editable);
		contentPane.add(txtClimb);
		txtClimb.setColumns(10);

		JLabel lblStartClimbTime = new JLabel("Start Climb Time");
		lblStartClimbTime.setBounds(257, 90, 82, 14);
		contentPane.add(lblStartClimbTime);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(109, 114, 394, 2);
		contentPane.add(separator_1);

		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(29, 140, 47, 24);
		contentPane.add(lblSwitch);

		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 175, 199, 147);
		contentPane.add(scrollPaneSwitch);

		tblSwitch = new JTable();
		tblSwitch.setModel(teamPerformance.constructSwitchTableModel());
		tblSwitch.setEnabled(editable);
		scrollPaneSwitch.setViewportView(tblSwitch);

		JLabel label = new JLabel("Scale");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(230, 140, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(230, 175, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();
		tblScale.setModel(teamPerformance.constructScaleTableModel());
		tblScale.setEnabled(editable);
		scrollPaneScale.setViewportView(tblScale);

		if (!editable) {

			menuBar = new JMenuBar();
			setJMenuBar(menuBar);
			JMenu mnEdit = new JMenu("Edit");
			menuBar.add(mnEdit);

			if (!editable) {

				JMenuItem mntmEditPerformanceWindow = new JMenuItem("Edit Performance Window");
				mntmEditPerformanceWindow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {
									UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
									TeamPerformanceWindow frame = new TeamPerformanceWindow(teamNumber, matchID,
											teamPerformance, true);
									frame.setVisible(true);
									dispose();
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				});

				mnEdit.add(mntmEditPerformanceWindow);

			}

		}

		JScrollPane scrollPaneVault = new JScrollPane();
		scrollPaneVault.setBounds(438, 175, 163, 147);
		contentPane.add(scrollPaneVault);

		tblVault = new JTable();
		tblVault.setModel(teamPerformance.constructVaultTableModel());
		tblVault.setEnabled(editable);
		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(445, 114, 129, 24);
		contentPane.add(label_1);

		// JScrollPane scrollPane = new JScrollPane();
		// scrollPane.setBounds(231, 140, 147, 147);
		// contentPane.add(scrollPane);

		JLabel lblScouterName = new JLabel("Scouter Name:");
		lblScouterName.setBounds(450, 17, 77, 14);
		contentPane.add(lblScouterName);

		txtScouter = new JTextField();
		txtScouter.setText(teamPerformance.scouterName);
		txtScouter.setEditable(editable);
		txtScouter.setBounds(525, 14, 86, 20);
		contentPane.add(txtScouter);
		txtScouter.setColumns(10);

		txtaComments = new JTextArea();
		txtaComments.setLineWrap(true);
		txtaComments.setBounds(10, 358, 591, 66);
		txtaComments.setText(teamPerformance.comments);
		txtaComments.setEnabled(editable);
		txtaComments.setColumns(10);
		contentPane.add(txtaComments);

		chkParked = new JCheckBox("Parked");
		chkParked.setBounds(436, 84, 97, 23);
		contentPane.add(chkParked);

		JButton btnAddSwitch = new JButton("Add");
		btnAddSwitch.setBounds(86, 142, 57, 22);
		contentPane.add(btnAddSwitch);
		btnAddSwitch.setEnabled(editable);

		JButton btnAddScale = new JButton("Add");
		btnAddScale.setBounds(287, 142, 57, 22);
		contentPane.add(btnAddScale);
		btnAddScale.setEnabled(editable);

		JButton btnAddVault = new JButton("Add");
		btnAddVault.setBounds(446, 142, 57, 22);
		contentPane.add(btnAddVault);
		btnAddVault.setEnabled(editable);

		JButton btnDeleteSwitch = new JButton("Del");
		btnDeleteSwitch.setBounds(152, 142, 57, 22);
		contentPane.add(btnDeleteSwitch);
		btnDeleteSwitch.setEnabled(editable);

		JButton btnDeleteScale = new JButton("Del");
		btnDeleteScale.setBounds(354, 142, 57, 22);
		contentPane.add(btnDeleteScale);
		btnDeleteScale.setEnabled(editable);

		JButton btnDeleteVault = new JButton("Del");
		btnDeleteVault.setBounds(517, 142, 57, 22);
		contentPane.add(btnDeleteVault);
		btnDeleteVault.setEnabled(editable);

		DefaultTableModel mdlSwitch = (DefaultTableModel) tblSwitch.getModel();
		DefaultTableModel mdlScale = (DefaultTableModel) tblScale.getModel();
		DefaultTableModel mdlVault = (DefaultTableModel) tblVault.getModel();

		btnAddSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlSwitch.addRow(new Object[] { false, false, 0 });
			}
		});

		btnAddScale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlScale.addRow(new Object[] { false, 0 });
			}
		});

		btnAddVault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlVault.addRow(new Object[] { false, 0 });
			}
		});

		btnDeleteSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlSwitch.removeRow(tblSwitch.getSelectedRow());
			}
		});

		btnDeleteScale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlScale.removeRow(tblScale.getSelectedRow());
			}
		});

		btnDeleteVault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlVault.removeRow(tblVault.getSelectedRow());
			}
		});

	}

	/**
	 * Create a window from a blank template
	 * 
	 * @wbp.parser.constructor
	 */
	public TeamPerformanceWindow(int teamNumber, int matchID) {

		this.teamNumber = teamNumber;
		this.matchID = matchID;

		// setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 627, 495);
		setResizable(false);
		setModal(true);

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
		chkBaseline.setBounds(97, 84, 154, 23);
		contentPane.add(chkBaseline);

		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(109, 114, 394, 2);
		contentPane.add(separator_1);

		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setHorizontalAlignment(SwingConstants.CENTER);
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(29, 140, 47, 24);
		contentPane.add(lblSwitch);

		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 175, 199, 147);
		contentPane.add(scrollPaneSwitch);

		tblSwitch = new JTable();
		DefaultTableModel mdlSwitch = new DefaultTableModel(new Object[][] {},
				new String[] { "Auto", "Offense", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		tblSwitch.setModel(mdlSwitch);
		scrollPaneSwitch.setViewportView(tblSwitch);

		JButton btnSaveData = new JButton("Save Data");
		btnSaveData.setBounds(250, 44, 110, 23);
		contentPane.add(btnSaveData);

		JLabel label = new JLabel("Scale");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label.setBounds(230, 140, 47, 24);
		contentPane.add(label);

		JScrollPane scrollPaneScale = new JScrollPane();
		scrollPaneScale.setBounds(230, 175, 179, 147);
		contentPane.add(scrollPaneScale);

		tblScale = new JTable();

		DefaultTableModel mdlScale = new DefaultTableModel(new Object[][] {}, new String[] { "In Auto", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};
		tblScale.setModel(mdlScale);
		
		scrollPaneScale.setViewportView(tblScale);

		JScrollPane scrollPaneVault = new JScrollPane();
		scrollPaneVault.setBounds(438, 175, 163, 147);
		contentPane.add(scrollPaneVault);

		tblVault = new JTable();
		DefaultTableModel mdlVault = new DefaultTableModel(new Object[][] {}, new String[] { "In Auto", "Time" }) {
			Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		};

		tblVault.setModel(mdlVault);
		scrollPaneVault.setViewportView(tblVault);

		JLabel label_1 = new JLabel("Power Cubes In Vault");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.PLAIN, 12));
		label_1.setBounds(445, 114, 129, 24);
		contentPane.add(label_1);

		btnSaveData.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {

				saveData();

			}
		});
		contentPane.add(btnSaveData);

		txtClimb = new JTextField();
		txtClimb.setBounds(349, 87, 68, 20);
		contentPane.add(txtClimb);
		txtClimb.setColumns(10);

		JLabel lblStartClimbTime = new JLabel("Start Climb Time");
		lblStartClimbTime.setBounds(257, 90, 82, 14);
		contentPane.add(lblStartClimbTime);

		JButton btnAddSwitch = new JButton("Add");
		btnAddSwitch.setBounds(86, 142, 57, 22);
		contentPane.add(btnAddSwitch);

		JButton btnAddScale = new JButton("Add");
		btnAddScale.setBounds(287, 142, 57, 22);
		contentPane.add(btnAddScale);

		JButton btnAddVault = new JButton("Add");
		btnAddVault.setBounds(446, 142, 57, 22);
		contentPane.add(btnAddVault);

		JButton btnDeleteSwitch = new JButton("Del");
		btnDeleteSwitch.setBounds(152, 142, 57, 22);
		contentPane.add(btnDeleteSwitch);

		JButton btnDeleteScale = new JButton("Del");
		btnDeleteScale.setBounds(354, 142, 57, 22);
		contentPane.add(btnDeleteScale);

		JButton btnDeleteVault = new JButton("Del");
		btnDeleteVault.setBounds(517, 142, 57, 22);
		contentPane.add(btnDeleteVault);

		txtaComments = new JTextArea();
		txtaComments.setLineWrap(true);
		txtaComments.setBounds(10, 358, 591, 66);
		contentPane.add(txtaComments);
		txtaComments.setColumns(10);

		JLabel lblComments = new JLabel("Comments");
		lblComments.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblComments.setBounds(10, 333, 95, 24);
		contentPane.add(lblComments);

		txtScouter = new JTextField();
		txtScouter.setBounds(525, 14, 86, 20);
		contentPane.add(txtScouter);
		txtScouter.setColumns(10);

		JLabel lblScouterName = new JLabel("Scouter Name:");
		lblScouterName.setBounds(450, 17, 77, 14);
		contentPane.add(lblScouterName);

		chkParked = new JCheckBox("Parked");
		chkParked.setBounds(436, 84, 97, 23);
		contentPane.add(chkParked);

		btnAddSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlSwitch.addRow(new Object[] { false, false, 0 });
			}
		});

		btnAddScale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlScale.addRow(new Object[] { false, 0 });
			}
		});

		btnAddVault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlVault.addRow(new Object[] { false, 0 });
			}
		});

		btnDeleteSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlSwitch.removeRow(tblSwitch.getSelectedRow());
			}
		});

		btnDeleteScale.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlScale.removeRow(tblScale.getSelectedRow());
			}
		});

		btnDeleteVault.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				mdlVault.removeRow(tblVault.getSelectedRow());
			}
		});
	}
}