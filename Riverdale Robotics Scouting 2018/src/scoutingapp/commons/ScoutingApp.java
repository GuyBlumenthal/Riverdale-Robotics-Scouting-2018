package scoutingapp.commons;

import java.awt.Color;
import java.awt.EventQueue;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import javax.swing.UIManager;

import scoutingapp.commons.existing.ExistingException;
import scoutingapp.commons.fileIO.ReadFile;
import scoutingapp.commons.fileIO.SaveFile;
import scoutingapp.commons.team.Team;
import scoutingapp.views.MatchHub;
import scoutingapp.views.MatchOverview;
import scoutingapp.views.TeamDetail;
import scoutingapp.views.TeamHub;
import scoutingapp.views.TeamPerformanceWindow;
import scoutingapp.views.fileIO.CollectionName;
import scoutingapp.views.fileIO.DirectoryAction;
import scoutingapp.views.fileIO.SaveConfirm;
import scoutingapp.views.fileIO.ViewDirectories;

public class ScoutingApp {

	public static MatchHub matchHub;
	public static TeamHub teamHub;

	private static RegionalCollection regionalCollection = new RegionalCollection();

	private static HashMap<Integer, MatchOverview> matchesShown = new HashMap<Integer, MatchOverview>();
	private static HashMap<Integer, TeamDetail> teamsShown = new HashMap<Integer, TeamDetail>();

	private static boolean saved = false;

	// TODO: Background Colours
	public final static Color BACKGROUND_COLOR = new Color(224, 255, 255);
	public final static Color RED_ALLIANCE_COLOR = new Color(255, 109, 81);
	public final static Color BLUE_ALLIANCE_COLOR = new Color(135, 206, 250);

	public final static String FILE_EXTENSION = "sct";

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}

		createTestEnvironment();

		matchHub = new MatchHub();
		teamHub = new TeamHub();

		try {
			matchHub.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public static void createTestEnvironment() {

		try {

			regionalCollection.createTeam(new Team(1001, "The Testers"));
			regionalCollection.createTeam(new Team(1002, "The Ranoutofideas"));
			regionalCollection.createTeam(new Team(1003, "The Slumdogmillionaires"));
			regionalCollection.createTeam(new Team(1004, "Bang blasters"));
			regionalCollection.createTeam(new Team(1005, "Spencini81"));
			regionalCollection.createTeam(new Team(1006, "Spoincer"));

			int[] blueTeams = { 1001, 1002, 1003 };
			int[] redTeams = { 1004, 1005, 1006 };

			regionalCollection.createMatch(1, blueTeams, redTeams);

		} catch (Exception e) {

		}

	}

	public static RegionalCollection regionalCollection() {
		saved = false;
		return regionalCollection;
	}

	public static void showMatchHub() {

		if (matchHub.isVisible() == false) {
			if (teamHub.isVisible()) {
				teamHub.setVisible(false);
			}
			matchHub.setVisible(true);
			matchHub.updateMatchTable();
		}

	}

	public static void showTeamHub() {

		if (teamHub.isVisible() == false) {
			if (matchHub.isVisible()) {
				matchHub.setVisible(false);
			}
			teamHub.setVisible(true);
			teamHub.updateTeamTable();
		}

	}

	public static void hideAllWindows() {

		unshowAllMatches();
		unshowAllTeams();

		matchHub.setVisible(false);
		teamHub.setVisible(false);

	}

	public static void showMatch(int matchID) {

		if (matchesShown.containsKey(matchID) == false) {

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						MatchOverview frame = new MatchOverview(matchID);
						frame.setVisible(true);

						matchesShown.put(matchID, frame);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}

	}

	public static void unshowMatch(int matchID) {

		if (matchesShown.containsKey(matchID)) {

			matchesShown.get(matchID).dispose();

			matchesShown.remove(matchID);

		}

	}

	public static void updateMatchView(int matchID) {

		if (matchesShown.containsKey(matchID)) {

			matchesShown.get(matchID).updateMatchOverview();

		}

	}

	public static void unshowAllMatches() {

		for (int i : matchesShown.keySet()) {

			matchesShown.get(i).dispose();
			matchesShown.remove(i);

		}

	}

	public static void showTeam(int team) {

		if (matchesShown.containsKey(team) == false) {

			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			EventQueue.invokeLater(new Runnable() {

				public void run() {
					try {
						TeamDetail frame = new TeamDetail(team);
						frame.setVisible(true);

						teamsShown.put(team, frame);

					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		}

	}

	public static void unshowTeam(int team) {

		if (teamsShown.containsKey(team)) {

			teamsShown.get(team).dispose();

			teamsShown.remove(team);

		}

	}

	public static void unshowAllTeams() {

		for (int i : teamsShown.keySet()) {

			teamsShown.get(i).dispose();
			teamsShown.remove(i);

		}

	}

	public static void updateMatchHubTable() {

		matchHub.updateMatchTable();

	}

	public static void updateTeamHubTable() {

		teamHub.updateTeamTable();

	}

	public static void updateMatchOverview() {

		for (MatchOverview matchOverview : matchesShown.values()) {

			matchOverview.updateMatchOverview();

		}

	}

	public static void setCollectionName(String name) {

		regionalCollection.fileName = name;

	}

	public static void saveCollection() {

		if (regionalCollection.fileName.equals("")) {

			try {
				CollectionName frame = new CollectionName();
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {

				File folder = new File("collections");

				folder.mkdirs();

				SaveFile save = new SaveFile();

				save.saveFile(regionalCollection, folder.getName() + "/" + regionalCollection.fileName, FILE_EXTENSION);

				saved = true;

			} catch (IOException ex) {

				ex.printStackTrace();

			}
		}

	}

	private static void changeCollection(RegionalCollection collection) {

		hideAllWindows();

		regionalCollection = collection;

		teamHub.updateTeamTable();
		matchHub.updateMatchTable();

		showMatchHub();

	}

	/**
	 * <h1><b>Danger</b></h1> <b>Warning!</b> - might erase saved data if called!
	 */
	public static void setSaved() {
		saved = true;
	}

	public static void setUnsaved() {

	}

	public static void newCollection() {

		if (saved == false) {

			try {
				SaveConfirm frame = new SaveConfirm(DirectoryAction.NEW);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			changeCollection(new RegionalCollection());

		}

	}

	public static void setPowerUps(int matchID, int[] redPowerUps, int[] bluePowerUps) {

		regionalCollection().setPowerUps(matchID, redPowerUps, true);
		regionalCollection().setPowerUps(matchID, bluePowerUps, false);

	}

	public static void openCollection(String fileName) {

		if (saved == false) {

			try {
				SaveConfirm frame = new SaveConfirm(DirectoryAction.OPEN);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			ReadFile readFile = new ReadFile();

			changeCollection((RegionalCollection) readFile.readFile("collections/" + fileName + "." + FILE_EXTENSION));

		}

	}

	public static void openCollection() {

		if (saved == false) {

			try {
				SaveConfirm frame = new SaveConfirm(DirectoryAction.OPEN);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {

				ViewDirectories frame = new ViewDirectories(DirectoryAction.OPEN);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void mergeCollection() {

		if (saved == false) {

			try {
				SaveConfirm frame = new SaveConfirm(DirectoryAction.MERGE);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			try {

				ViewDirectories frame = new ViewDirectories(DirectoryAction.MERGE);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static void mergeCollection(String fileName) {

		if (saved == false) {

			try {
				SaveConfirm frame = new SaveConfirm(DirectoryAction.MERGE);
				frame.setVisible(true);

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			ReadFile readFile = new ReadFile();
			RegionalCollection newCollection = (RegionalCollection) readFile
					.readFile("collections/" + fileName + "." + FILE_EXTENSION);

			try {
				regionalCollection().mergeCollection(newCollection);
			} catch (ExistingException e) {
				System.out.println(regionalCollection().findDuplicates(newCollection));
			}
		}

	}

}
