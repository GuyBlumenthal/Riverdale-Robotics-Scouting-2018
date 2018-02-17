package scoutingapp.views;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

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

public class MatchHub extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6184145860176117808L;
	private JPanel contentPane;
	private JButton btnAddMatch, buttonRemoveMatch;
	private JTable table;

	public final static Color BACKGROUND_COLOR = new Color(224, 255, 255);
	public final static Color RED_ALLIANCE_COLOR = new Color(255, 109, 81);
	public final static Color BLUE_ALLIANCE_COLOR = new Color(135, 206, 250);

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

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(57, 71, 673, 388);
		contentPane.add(scrollPane);

		createMatchTable();

		scrollPane.setViewportView(table);

		System.out.println(scrollPane.getY() * 2 + scrollPane.getHeight());

	}

	void createMatchTable() {

		table = new JTable() {
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
		table.setModel(new DefaultTableModel(
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

		table.getTableHeader().setReorderingAllowed(false);

		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(3).setResizable(false);
		table.getColumnModel().getColumn(4).setResizable(false);
		table.getColumnModel().getColumn(5).setResizable(false);
		table.getColumnModel().getColumn(6).setResizable(false);
		table.getColumnModel().getColumn(7).setResizable(false);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.RIGHT);

	}
}
