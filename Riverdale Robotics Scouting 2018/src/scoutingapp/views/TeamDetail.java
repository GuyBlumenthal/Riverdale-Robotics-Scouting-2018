package scoutingapp.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;

import scoutingapp.commons.Team;

import java.awt.Font;
import javax.swing.JList;

public class TeamDetail {

	private JFrame frame;
	
	private Team team;
	
	//TODO: Fix

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		Team q = new Team(5834, "R3P2");
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamDetail window = new TeamDetail(q);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TeamDetail(Team team) {
		this.team = team;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblTeamName = new JLabel(team.getName());
		lblTeamName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTeamName.setBounds(161, 11, 101, 24);
		frame.getContentPane().add(lblTeamName);
		
		JLabel lblTeamNumber = new JLabel(String.valueOf(team.getNumber()));
		lblTeamNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTeamNumber.setBounds(161, 46, 73, 24);
		frame.getContentPane().add(lblTeamNumber);
		
		JList lstMatches = new JList(team.matches().keySet().toArray());
		lstMatches.setBounds(30, 124, 358, 108);
		frame.getContentPane().add(lstMatches);
		
		
	}
}
