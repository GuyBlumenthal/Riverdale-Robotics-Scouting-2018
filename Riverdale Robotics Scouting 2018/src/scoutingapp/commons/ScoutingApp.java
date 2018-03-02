package scoutingapp.commons;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.UIManager;

import scoutingapp.commons.team.Team;
import scoutingapp.views.MatchHub;
import scoutingapp.views.TeamHub;

public class ScoutingApp {

	public static MatchHub matchHub;
	public static TeamHub teamHub;

	public static RegionalCollection regionalCollection = new RegionalCollection();

	// TODO: Background Colour
	public final static Color BACKGROUND_COLOR = new Color(224, 255, 255);
	public final static Color RED_ALLIANCE_COLOR = new Color(255, 109, 81);
	public final static Color BLUE_ALLIANCE_COLOR = new Color(135, 206, 250);

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		matchHub = new MatchHub();
		teamHub = new TeamHub();

		createTestTeams();

		try {
			matchHub.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void createTestTeams() {

		try {
			regionalCollection.createTeam(new Team(1001, "The Testers"));
			regionalCollection.createTeam(new Team(1002, "The Ranoutofideas"));
			regionalCollection.createTeam(new Team(1003, "The Slumdogmillionaires"));
			regionalCollection.createTeam(new Team(1004, "Bang blasters"));
			regionalCollection.createTeam(new Team(1005, "Spencini81"));
			regionalCollection.createTeam(new Team(1006, "Spoincer"));
		} catch (Exception e) {

		}

	}

	public static void showMatchHub() {

		if (matchHub.isVisible() == false) {
			if (teamHub.isVisible()) {
				teamHub.setVisible(false);
			}
			matchHub.setVisible(true);
		}

	}

	public static void showTeamHub() {

		if (teamHub.isVisible() == false) {
			if (matchHub.isVisible()) {
				matchHub.setVisible(false);
			}
			teamHub.setVisible(true);
		}

	}

	public static void updateMatchHubTable() {

		matchHub.updateMatchTable();

	}

	public static void updateTeamHubTable() {

		teamHub.updateTeamTable();

	}

}
