package scoutingapp.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import scoutingapp.commons.team.Team;

public class MatchHub extends JFrame {

	/**
	 * 
	 */

	private static final long serialVersionUID = 6184145860176117808L;
	private JPanel contentPane;
	private JButton btnAddMatch, buttonRemoveMatch;
	private JTable tblMatches;

	public final static Color BACKGROUND_COLOR = new Color(224, 255, 255);
	public final static Color RED_ALLIANCE_COLOR = new Color(255, 109, 81);
	public final static Color BLUE_ALLIANCE_COLOR = new Color(135, 206, 250);
	private JScrollPane scrollPane;

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
					MatchHub frame = new MatchHub();
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
	public MatchHub() {

		try {
			TeamHub.regionalCollection.createTeam(new Team(1001, "The Testers"));
			TeamHub.regionalCollection.createTeam(new Team(1002, "The Ranoutofideas"));
			TeamHub.regionalCollection.createTeam(new Team(1003, "The Slumdogmillionaires"));
			TeamHub.regionalCollection.createTeam(new Team(1004, "Bang blasters"));
			TeamHub.regionalCollection.createTeam(new Team(1005, "Spencini81"));
			TeamHub.regionalCollection.createTeam(new Team(1006, "Spoincer"));
		} catch (Exception e) {

		}

		try {

			int[] blueTeams = { 1001, 1002, 1003 };
			int[] redTeams = { 1004, 1005, 1006 };

			TeamHub.regionalCollection.createMatch(1, blueTeams, redTeams);

		} catch (

		Exception e) {

		}

		initInterface();

		createMatchTable();

		scrollPane.setViewportView(tblMatches);

		updateMatchTable();

	}

	public void updateMatchTable() {

		Object[][] arrayValues = new Object[TeamHub.regionalCollection.getMatchIDList().length][8];

		int[] matchIDList = TeamHub.regionalCollection.getMatchIDList();

		for (int i = 0; i < arrayValues.length; i++) {

			arrayValues[i][0] = TeamHub.regionalCollection.getMatch(matchIDList[i]).getMatchID();

			for (int j = 0; j < 3; j++) {
				arrayValues[i][j + 1] = TeamHub.regionalCollection.getMatch(matchIDList[i]).getBlueTeams()[j]
						.getTeamNumber();
			}

			for (int j = 0; j < 3; j++) {
				arrayValues[i][j + 4] = TeamHub.regionalCollection.getMatch(matchIDList[i]).getRedTeams()[j]
						.getTeamNumber();
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
		setSize(new Dimension(1024, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 530);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1024, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(BACKGROUND_COLOR);
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnAddMatch = new JButton();
		btnAddMatch.setText("<html><center>" + "Add" + "<br>" + "Match" + "</center></html>");
		btnAddMatch.setBounds(57, 13, 80, 45);
		contentPane.add(btnAddMatch);

		buttonRemoveMatch = new JButton();
		buttonRemoveMatch.setText("<html><center>" + "Remove" + "<br>" + "Match" + "</center></html>");
		buttonRemoveMatch.setBounds(149, 13, 80, 45);
		contentPane.add(buttonRemoveMatch);

		JButton btnopenmatch = new JButton();
		btnopenmatch.setText("<html><center>Open<br>Match</center></html>");
		btnopenmatch.setBounds(239, 13, 80, 45);
		contentPane.add(btnopenmatch);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 71, 673, 388);
		contentPane.add(scrollPane);
	}

	void createMatchTable() {

		tblMatches = new JTable() {
			/**
			 * 
			 */
			private static final long serialVersionUID = -7944290291409346981L;

			public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
				Component comp = super.prepareRenderer(renderer, row, column);

				if (column > 0 && column < 4) {
					comp.setBackground(BLUE_ALLIANCE_COLOR);
				} else if (column >= 4 && column < 7) {
					comp.setBackground(RED_ALLIANCE_COLOR);
				} else {
					comp.setBackground(BACKGROUND_COLOR);
				}
				return comp;
			}
		};
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

		/* functions */
		btnAddMatch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

	}

}
