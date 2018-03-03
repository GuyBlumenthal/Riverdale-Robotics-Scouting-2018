package scoutingapp.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.views.creation.CreateTeam;

public class TeamHub extends JFrame {

	private static final long serialVersionUID = 6184145860176117808L;
	private JTable tblTeams;
	private JPanel contentPane;

	Object[][] arrayValues = new Object[ScoutingApp.regionalCollection().getTeamList().length][3];

	public TeamHub() {
		initInterface();
		updateTeamTable();
	}

	public void initInterface() {
		setTitle("Scouting - Team Hub");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 515, 444);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1024, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		JScrollPane scrollPaneTeams = new JScrollPane();
		scrollPaneTeams.setBounds(22, 22, 461, 354);
		getContentPane().add(scrollPaneTeams);

		tblTeams = new JTable();
		tblTeams.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Rank", "Team Number", "Team Name"
			}
		));
		
		tblTeams.getColumnModel().getColumn(0).setResizable(false);
		tblTeams.getColumnModel().getColumn(0).setMinWidth(75);
		tblTeams.getColumnModel().getColumn(0).setMaxWidth(75);
		tblTeams.getColumnModel().getColumn(1).setResizable(false);
		tblTeams.getColumnModel().getColumn(1).setPreferredWidth(125);
		tblTeams.getColumnModel().getColumn(1).setMinWidth(125);
		tblTeams.getColumnModel().getColumn(1).setMaxWidth(125);
		tblTeams.getColumnModel().getColumn(2).setResizable(false);
		
		tblTeams.getTableHeader().setReorderingAllowed(false);
		scrollPaneTeams.setViewportView(tblTeams);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);

		JMenuItem mntmSave = new JMenuItem("Save");
		mntmSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				ScoutingApp.saveCollection();

			}
		});
		mntmSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
		mnFile.add(mntmSave);

		JMenuItem mntmOpen = new JMenuItem("Open");
		mntmOpen.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.openCollection();

			}
		});
		mnFile.add(mntmOpen);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.newCollection();

			}
		});
		mnFile.add(mntmNew);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		JMenuItem mntmAddNewTeam = new JMenuItem("Add New Team");
		mnEdit.add(mntmAddNewTeam);

		JMenuItem mntmRemoveTeam = new JMenuItem("Remove Team");
		mntmRemoveTeam.setEnabled(false);
		mnEdit.add(mntmRemoveTeam);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmViewMatchhub = new JMenuItem("View MatchHub ...");
		mnView.add(mntmViewMatchhub);

		TeamHub Temp = this;
		mntmAddNewTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							CreateTeam dialog = new CreateTeam();
							dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							dialog.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});

			}
		});

		mntmViewMatchhub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.showMatchHub();

			}
		});

		tblTeams.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if (tblTeams.getSelectedRowCount() > 0) {
					mntmRemoveTeam.setEnabled(true);
				} else {
					mntmRemoveTeam.setEnabled(false);
				}
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if (tblTeams.getSelectedRowCount() == 1) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {

									ScoutingApp.showTeam((int) tblTeams.getValueAt(tblTeams.getSelectedRow(), 1));

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			}

		});

	}

	public void updateTeamTable() {

		arrayValues = new Object[ScoutingApp.regionalCollection().getTeamList().length][3];
		int[] teamList = ScoutingApp.regionalCollection().getTeamList();

		for (int i = 0; i < arrayValues.length; i++) {
			arrayValues[i][0] = i + 1;
			arrayValues[i][1] = teamList[i];
			arrayValues[i][2] = ScoutingApp.regionalCollection().getTeam(teamList[i]).getTeamName();
		}

		DefaultTableModel tableModel = new DefaultTableModel(arrayValues,
				new String[] { "Rank", "Team Number", "Team Name" }) {
			private static final long serialVersionUID = -6261637160294735163L;
			boolean[] columnEditables = new boolean[] { false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblTeams.setModel(tableModel);
	}

}
