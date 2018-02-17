package scoutingapp.views;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;

public class MatchDetail extends JFrame {

	private JPanel contentPane;
	private JTextField txtTeamNumber;
	private JLabel lblMatchNumber;
	private JTextField txtMatchNumber;
	private JTable tblSwitch;

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
		setBounds(100, 100, 474, 337);
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
		chkClimb.setBounds(269, 88, 110, 23);
		contentPane.add(chkClimb);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setBounds(10, 116, 438, 2);
		contentPane.add(separator_1);
		
        //headers for the table
        String[] columns = new String[] {
            "Auto", "Time"
        };
        //actual data for the table in a 2d array
        Object[][] data = new Object[][] {
            {false, "0:00" }
        };
		
		JLabel lblSwitch = new JLabel("Switch");
		lblSwitch.setFont(new Font("Tahoma", Font.PLAIN, 12));
		lblSwitch.setBounds(10, 116, 47, 24);
		contentPane.add(lblSwitch);
		
		JScrollPane scrollPaneSwitch = new JScrollPane();
		scrollPaneSwitch.setBounds(10, 140, 147, 147);
		contentPane.add(scrollPaneSwitch);
		
		tblSwitch = new JTable(data, columns);
		scrollPaneSwitch.setViewportView(tblSwitch);
		tblSwitch.getColumnModel().getColumn(0).setPreferredWidth(49);
	}
}
