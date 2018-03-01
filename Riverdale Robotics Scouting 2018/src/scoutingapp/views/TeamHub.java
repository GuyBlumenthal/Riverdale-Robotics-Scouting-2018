package scoutingapp.views;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import scoutingapp.commons.RegionalCollection;
import scoutingapp.views.creation.CreateTeam;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class TeamHub extends JFrame {
	
	static public RegionalCollection regionalCollection = new RegionalCollection();
	
	private static final long serialVersionUID = 6184145860176117808L;
	private JTable tblTeams;
	private JPanel contentPane;
	
	public TeamHub() {
		initInterface();
		updateTeamTable();
	}

	public void initInterface() {
		setTitle("Scouting - Team Hub");
		setSize(new Dimension(1024, 600));
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 787, 530);
		contentPane = new JPanel();
		contentPane.setSize(new Dimension(1024, 600));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);
		
		JScrollPane scrollPaneTeams = new JScrollPane();
		scrollPaneTeams.setBounds(63, 61, 633, 354);
		getContentPane().add(scrollPaneTeams);
		
		tblTeams = new JTable();
		tblTeams.setModel(new DefaultTableModel(
			new Object[][] {
				{null, null, null},
			},
			new String[] {
				"Rank", "Team Number", "Team Name"
			}
		) {
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
				Integer.class, Integer.class, String.class
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		scrollPaneTeams.setViewportView(tblTeams);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnFile = new JMenu("File");
		menuBar.add(mnFile);
		
		JMenuItem mntmSaveData = new JMenuItem("Save Data ...");
		mnFile.add(mntmSaveData);
		
		JMenuItem mntmSave = new JMenuItem("Save");
		mnFile.add(mntmSave);
		
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
		
		TeamHub teamHub = this;
		mntmAddNewTeam.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							CreateTeam dialog = new CreateTeam(teamHub);
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
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
							MatchHub matchHub = new MatchHub();
							matchHub.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
							matchHub.setVisible(true);	
							dispose();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		
		tblTeams.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(tblTeams.isRowSelected(tblTeams.getSelectedRow())){
					mntmRemoveTeam.setEnabled(true);
				}
			}
		});
		
	}

	public void updateTeamTable() {

		Object[][] arrayValues = new Object[TeamHub.regionalCollection.getTeamList().length][3];

		int[] teamList = TeamHub.regionalCollection.getTeamList();

		for (int i = 0; i < arrayValues.length; i++) {
			arrayValues[i][0] = i + 1;
			arrayValues[i][1] = teamList[i];
			arrayValues[i][2] = TeamHub.regionalCollection.getTeam(teamList[i]).getTeamName();
		}

		DefaultTableModel tableModel = new DefaultTableModel(arrayValues,
			new String[] { "Rank", "Team Number", "Team Name"}) {
			private static final long serialVersionUID = -6261637160294735163L;
			boolean[] columnEditables = new boolean[] { false, false, false};

			public boolean isCellEditable(int row, int column) {
				return columnEditables[column];
			}
		};
		tblTeams.setModel(tableModel);
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamHub frame = new TeamHub();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
