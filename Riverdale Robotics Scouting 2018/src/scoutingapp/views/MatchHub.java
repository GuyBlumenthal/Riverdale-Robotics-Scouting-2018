package scoutingapp.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.ScoutingApp;
import scoutingapp.commons.team.Team;
import scoutingapp.views.creation.CreateMatch;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class MatchHub extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6184145860176117808L;
	private JPanel contentPane;
	private JTable tblMatches;

	private JScrollPane scrollPane;
	private JMenuItem mntmAddMatch;
	private JMenuItem mntmRemoveMatch;

	/**
	 * Create the frame.
	 */
	public MatchHub() {

		try {
			ScoutingApp.regionalCollection().createTeam(new Team(1001, "The Testers"));
			ScoutingApp.regionalCollection().createTeam(new Team(1002, "The Ranoutofideas"));
			ScoutingApp.regionalCollection().createTeam(new Team(1003, "The Slumdogmillionaires"));
			ScoutingApp.regionalCollection().createTeam(new Team(1004, "Bang blasters"));
			ScoutingApp.regionalCollection().createTeam(new Team(1005, "Spencini81"));
			ScoutingApp.regionalCollection().createTeam(new Team(1006, "Spoincer"));
		} catch (Exception e) {

		}

		initInterface();

		createMatchTable();

		scrollPane.setViewportView(tblMatches);

		updateMatchTable();

	}

	public void updateMatchTable() {

		Object[][] arrayValues = new Object[ScoutingApp.regionalCollection().getMatchIDList().length][8];

		int[] matchIDList = ScoutingApp.regionalCollection().getMatchIDList();
		
		Arrays.sort(matchIDList);

		for (int i = 0; i < arrayValues.length; i++) {

			arrayValues[i][0] = ScoutingApp.regionalCollection().getMatch(matchIDList[i]).getMatchID();

			for (int j = 0; j < 3; j++) {
				arrayValues[i][j + 1] = ScoutingApp.regionalCollection().getMatch(matchIDList[i]).getBlueTeams()[j]
						.getTeamNumber();
			}

			for (int j = 0; j < 3; j++) {
				arrayValues[i][j + 4] = ScoutingApp.regionalCollection().getMatch(matchIDList[i]).getRedTeams()[j]
						.getTeamNumber();
			}
			
			int blueScore = ScoutingApp.regionalCollection().getMatch(matchIDList[i]).getBlueScore();
			int redScore = ScoutingApp.regionalCollection().getMatch(matchIDList[i]).getRedScore();
			
			if (redScore == -1 || blueScore == -1) {
				arrayValues[i][7] = "Indeterminate";
			} else if (blueScore > redScore) {
				arrayValues[i][7] = "Blue Alliance";
			} else if (redScore > blueScore) {
				arrayValues[i][7] = "Red Alliance";
			} else {
				arrayValues[i][7] = "Tie";
			}

		}

		DefaultTableModel tableModel = new DefaultTableModel(arrayValues,
				new String[] { "Match Number", "Blue 1", "Blue 2", "Blue 3", "Red 1", "Red 2", "Red 3", "Winner" }) {
			/**
			 * 
			 */
			private static final long serialVersionUID = -6261637160294735163L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblMatches.setModel(tableModel);
	}

	void initInterface() {

		setIconImage(Toolkit.getDefaultToolkit()
				.getImage(MatchHub.class.getResource("/scoutingapp/resources/MagnifyingGlass.png")));
		setTitle("Scouting");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 721, 485);

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
		
		JMenuItem mntmSaveAs = new JMenuItem("Save As");
		mntmSaveAs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ScoutingApp.regionalCollection().fileName = "";
				ScoutingApp.saveCollection();
				
			}
		});
		mnFile.add(mntmSaveAs);
		mnFile.add(mntmOpen);

		JMenuItem mntmNew = new JMenuItem("New");
		mntmNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.newCollection();

			}
		});
		mnFile.add(mntmNew);
		
		JMenuItem mntmMerge = new JMenuItem("Merge");
		mntmMerge.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ScoutingApp.mergeCollection();
				
			}
		});
		mnFile.add(mntmMerge);

		JMenu mnEdit = new JMenu("Edit");
		menuBar.add(mnEdit);

		mntmAddMatch = new JMenuItem("Add Match");
		mntmAddMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CreateMatch window = new CreateMatch();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}
		});
		mnEdit.add(mntmAddMatch);

		mntmRemoveMatch = new JMenuItem("Remove Match");
		mntmRemoveMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (tblMatches.getSelectedRowCount() > 1) {
					int[] selectedMatchIDs = tblMatches.getSelectedRows();

					for (int i : selectedMatchIDs) {
						ScoutingApp.regionalCollection().removeMatch((int) tblMatches.getValueAt(i, 0));
					}
				} else if (tblMatches.getSelectedRowCount() == 1) {

					ScoutingApp.regionalCollection()
							.removeMatch((int) tblMatches.getValueAt(tblMatches.getSelectedRow(), 0));

				}

				updateMatchTable();

			}
		});

		JMenuItem mntmViewMatch = new JMenuItem("View Match");
		mntmViewMatch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (tblMatches.getSelectedRowCount() == 1) {
					ScoutingApp.showMatch((int) tblMatches.getValueAt(tblMatches.getSelectedRow(), 0));
				}
			}
		});
		mnEdit.add(mntmViewMatch);
		mnEdit.add(mntmRemoveMatch);

		JMenuItem mntmRefreshMatchTable = new JMenuItem("Refresh Match Table");
		mntmRefreshMatchTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				updateMatchTable();
			}
		});
		mnEdit.add(mntmRefreshMatchTable);

		JMenu mnView = new JMenu("View");
		menuBar.add(mnView);

		JMenuItem mntmTeams = new JMenuItem("Teams");
		mntmTeams.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				ScoutingApp.showTeamHub();

			}
		});
		mnView.add(mntmTeams);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1024, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(ScoutingApp.BACKGROUND_COLOR);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 22, 673, 388);
		contentPane.add(scrollPane);
		
	}

	void createMatchTable() {

		tblMatches = new JTable();
		tblMatches.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				if (e.getClickCount() == 2 && e.getButton() == MouseEvent.BUTTON1) {
					if (tblMatches.getSelectedRowCount() == 1) {
						EventQueue.invokeLater(new Runnable() {
							public void run() {
								try {

									ScoutingApp.showMatch((int) tblMatches.getValueAt(tblMatches.getSelectedRow(), 0));

								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
					}
				}
			}
		});

		tblMatches.setModel(new DefaultTableModel(
				new Object[][] {
						{ new Integer(0), new Integer(0), new Integer(0), new Integer(1), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0) },
						{ new Integer(0), new Integer(0), new Integer(2), new Integer(0), new Integer(0),
								new Integer(3), new Integer(0), new Integer(0) },
						{ new Integer(0), new Integer(40), new Integer(0), new Integer(0), new Integer(0),
								new Integer(0), new Integer(0), new Integer(0) }, },
				new String[] { "Match Number", "Blue 1", "Blue 2", "Blue 3", "Red 1", "Red 2", "Red 3", "Winner" }) {

			/**
			 * 
			 */
			private static final long serialVersionUID = -6261637160294735163L;
			boolean[] columnEditables = new boolean[] { false, false, false, false, false, false, false, false };

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		});

		tblMatches.getTableHeader().setReorderingAllowed(false);

		tblMatches.getColumnModel().getColumn(0).setResizable(false);
		tblMatches.getColumnModel().getColumn(0).setPreferredWidth(100);
		tblMatches.getColumnModel().getColumn(1).setResizable(false);
		tblMatches.getColumnModel().getColumn(2).setResizable(false);
		tblMatches.getColumnModel().getColumn(3).setResizable(false);
		tblMatches.getColumnModel().getColumn(4).setResizable(false);
		tblMatches.getColumnModel().getColumn(5).setResizable(false);
		tblMatches.getColumnModel().getColumn(6).setResizable(false);
		tblMatches.getColumnModel().getColumn(7).setResizable(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.RIGHT);

	}
}
