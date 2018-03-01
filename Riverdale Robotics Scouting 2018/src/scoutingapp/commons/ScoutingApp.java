package scoutingapp.commons;

import java.awt.EventQueue;

import javax.swing.UIManager;

import scoutingapp.commons.team.Team;
import scoutingapp.views.MatchHub;
import scoutingapp.views.TeamHub;

public class ScoutingApp {

	public static MatchHub matchHub;
	public static TeamHub teamHub;

	public static void main(String[] args) {

		matchHub = new MatchHub();
		teamHub = new TeamHub();

		createTestTeams();

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

	public static void createTestTeams() {

		try {
			TeamHub.regionalCollection.createTeam(new Team(1001, "The Testers"));
			TeamHub.regionalCollection.createTeam(new Team(1002, "The Ranoutofideas"));
			TeamHub.regionalCollection.createTeam(new Team(1003, "The Slumdogmillionaires"));
			TeamHub.regionalCollection.createTeam(new Team(1004, "Bang blasters"));
			TeamHub.regionalCollection.createTeam(new Team(1005, "Spencini81"));
			TeamHub.regionalCollection.createTeam(new Team(1006, "Spoincer"));
		} catch (Exception e) {

		}

	}

}
