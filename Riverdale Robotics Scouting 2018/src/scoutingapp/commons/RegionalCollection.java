package scoutingapp.commons;

import java.awt.EventQueue;
import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;

import javax.swing.JOptionPane;

import scoutingapp.commons.existing.ExistingException;
import scoutingapp.commons.existing.ExistingType;
import scoutingapp.commons.team.Team;
import scoutingapp.views.TeamPerformanceWindow;

/**
 * 
 * The class that holds all the
 * {@link scoutingapp.commons.RegionalCollection#matches matches} and
 * {@link scoutingapp.commons.RegionalCollection#teams teams} in one place to
 * ensure that no copies of the teams are created and desync the program
 * 
 * @see scoutingapp.commons.ScoutingApp ScoutingApp
 * @see scoutingapp.commons.Match Match
 * @see scoutingapp.commons.team.Team Team
 * 
 */
public class RegionalCollection implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -81214186242167416L;

	public String fileName;

	/**
	 * The hashmap that contains the teams in the regional collection with their
	 * team number as their Key
	 * 
	 * @param Key
	 *            is an Integer that refers to the team's team number
	 * @param Value
	 *            is the team
	 */
	HashMap<Integer, Team> teams;

	/**
	 * The hashmap that contains the matches in the regional collection with their
	 * match ID as their Key
	 * 
	 * @param Key
	 *            is an Integer that refers to the match ID
	 * @param Value
	 *            is the match
	 */
	HashMap<Integer, Match> matches;

	public RegionalCollection() {

		fileName = "";

		teams = new HashMap<Integer, Team>();
		matches = new HashMap<Integer, Match>();

	}

	/**
	 * @return a list of all the teams in a hashmap where the key is the team number
	 *         as an Integer
	 */
	public HashMap<Integer, Team> getTeams() {
		return teams;
	}

	/**
	 * 
	 * Use this method to create a new team from a team number and an optional team
	 * name
	 * 
	 * @param teamNumber
	 *            is the team number of the new team
	 * @param teamName
	 *            is the team name of the new team which if left empty will be the
	 *            team's number
	 * 
	 * @throws ExistingException
	 *             if the team number is an already created team in the regional
	 *             collection
	 */
	public void createTeam(int teamNumber, String teamName) throws ExistingException {

		if (teams.containsKey(teamNumber)) {
			throw new ExistingException(teamNumber, ExistingType.TEAM);
		} else {
			Team team = new Team(teamNumber, teamName);

			teams.put(teamNumber, team);
		}

	}

	/**
	 * 
	 * Use this method to create a new team from a team number and an optional team
	 * name
	 * 
	 * @param teamNumber
	 *            is the team number of the new team
	 * @param teamName
	 *            is the team name of the new team which if left empty will be the
	 *            team's number
	 * 
	 * @throws ExistingException
	 *             if the team number is an already created team in the regional
	 *             collection
	 */
	public void createTeam(int teamNumber) throws ExistingException {

		if (teams.containsKey(teamNumber)) {
			throw new ExistingException(teamNumber, ExistingType.TEAM);
		} else {
			Team team = new Team(teamNumber);

			teams.put(teamNumber, team);
		}
	}

	/**
	 * 
	 * Use this method to create a new team from a team created outside of the
	 * regional collection
	 * 
	 * @param team
	 *            is the new team as a {@link scoutingapp.commons.team.Team Team}
	 *            class
	 * 
	 * @throws ExistingException
	 *             if the team number is an already created team in the regional
	 *             collection
	 */
	public void createTeam(Team team) throws ExistingException {

		if (teams.containsKey(team.getTeamNumber())) {
			throw new ExistingException(team.getTeamNumber(), ExistingType.TEAM);
		} else {
			teams.put(team.getTeamNumber(), team);
		}
	}

	/**
	 * 
	 * Use this method to create a team performance for an already existing team and
	 * already existing match in the regional collection.
	 * 
	 * @param matchID
	 *            is the ID for the match in which the team is competiting in. This
	 *            is not check if it is a valid match as it cannot be called from a
	 *            location where the match does not exist
	 * @param teamNumber
	 *            is the unique team number of the team who is receiving the new
	 *            team performance. This is not check to be a valid team as it is
	 *            already ensured to be a valid through where it is being called
	 */
	// TODO: Check to make sure that the team and match are both legitimate just for
	// redundancy
	public void createTeamPerformanceWindow(int matchID, int teamNumber) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TeamPerformanceWindow frame = new TeamPerformanceWindow(teamNumber, matchID);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public void addTeamPerformance(int teamNumber, int matchID, TeamPerformanceWindow window) {
		teams.get(teamNumber).addTeamPerformance(matchID, window);
	}

	public boolean hasTeamPerformance(int matchID, int teamNumber) {

		if (teams.containsKey(teamNumber)) {
			return teams.get(teamNumber).hasTeamPerformance(matchID);
		} else {
			return false;
		}

	}

	public void showTeamPerformance(int matchID, int teamNumber) throws ExistingException {

		if (hasTeamPerformance(matchID, teamNumber)) {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TeamPerformanceWindow frame = teams.get(teamNumber).getTeamPerformance(matchID)
								.createWindow(teamNumber, matchID, false);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} else {
			throw new ExistingException(matchID, ExistingType.TEAM_PERFORMANCE);
		}

	}

	public void setPowerUps(int matchID, int[] powerUps, boolean isRed) {

		if (isRed) {
			getMatch(matchID).redPowerUps = powerUps;
		} else {
			getMatch(matchID).bluePowerUps = powerUps;
		}

	}

	public void setScore(int matchID, int score, boolean isRed) {

		if (isRed) {
			getMatch(matchID).redScore = score;
		} else {
			getMatch(matchID).blueScore = score;
		}

	}

	public void removeTeamPerformance(int teamNumber, int matchID) {

		teams.get(teamNumber).removeTeamPerformance(matchID);

	}

	public boolean teamExists(int teamNumber) {

		return teams.containsKey(teamNumber);

	}

	public void createMatch(int matchID, int[] blueTeams, int[] redTeams) throws ExistingException {

		if (matches.containsKey(matchID)) {
			throw new ExistingException(matchID, ExistingType.MATCH);
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			matches.put(matchID, match);
		}
	}

	public void createMatch(int matchID, Team[] blueTeams, Team[] redTeams) throws ExistingException {

		if (matches.containsKey(matchID)) {
			throw new ExistingException(matchID, ExistingType.MATCH);
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			matches.put(matchID, match);
		}
	}

	public boolean matchExists(int matchID) {

		return matches.containsKey(matchID);

	}

	public void removeMatch(int matchID) {

		if (matchExists(matchID)) {

			matches.remove(matchID);

			for (int teamNumber : teams.keySet()) {

				if (hasTeamPerformance(matchID, teamNumber)) {

					removeTeamPerformance(matchID, teamNumber);

				}

			}

			ScoutingApp.unshowMatch(matchID);
			ScoutingApp.updateMatchHubTable();

		}

	}

	public Team getTeam(int teamNumber) {
		return teams.get(teamNumber);
	}

	public int[] getTeamList() {
		if (teams.size() == 0) {
			return new int[0];
		}

		Object[] teamObjects = teams.keySet().toArray();
		int[] teamList = new int[teamObjects.length];

		for (int i = 0; i < teamList.length; i++) {
			teamList[i] = (int) teamObjects[i];
		}

		Arrays.sort(teamList);

		return teamList;

	}

	public int[] getMatchIDList() {
		if (matches.size() == 0) {
			return new int[0];
		}

		Object[] matchIDObjects = matches.keySet().toArray();
		int[] matchIDList = new int[matchIDObjects.length];

		for (int i = 0; i < matchIDList.length; i++) {
			matchIDList[i] = (int) matchIDObjects[i];
		}

		return matchIDList;

	}

	public Match getMatch(int matchID) {
		return matches.get(matchID);

	}

	public void mergeCollection(RegionalCollection newCollection) throws ExistingException {

		for (Integer matchID : matches.keySet()) {

			if (newCollection.matchExists(matchID)) {

				for (Team team : matches.get(matchID).getBlueTeams()) {

					if (newCollection.hasTeamPerformance(matchID, team.getTeamNumber())) {

						throw new ExistingException(matchID, ExistingType.TEAM_PERFORMANCE);

					}

				}

				for (Team team : matches.get(matchID).getRedTeams()) {

					if (newCollection.hasTeamPerformance(matchID, team.getTeamNumber())) {

						throw new ExistingException(matchID, ExistingType.TEAM_PERFORMANCE);

					}

				}

			}

		}

		finalIntegrationOfCollection(newCollection);

	}

	/**
	 * 
	 * Use this method only if it is certain that there are no duplicates of any
	 * team performances!
	 * 
	 * @param newCollection
	 */
	private void finalIntegrationOfCollection(RegionalCollection newCollection) {

		HashMap<Integer, Team> combinedTeams = new HashMap<Integer, Team>();

		for (Integer teamNumber : teams.keySet()) {

			Team currTeam = teams.get(teamNumber);

			if (newCollection.teamExists(teamNumber)) {

				// Merge the two teams

				Team newTeam = newCollection.getTeam(teamNumber);

				currTeam.teamPerformances.putAll(newTeam.teamPerformances);

			}

			combinedTeams.put(teamNumber, currTeam);

		}

		for (Integer teamNumber : newCollection.teams.keySet()) {

			if (combinedTeams.containsKey(teamNumber) == false) {

				combinedTeams.put(teamNumber, newCollection.teams.get(teamNumber));

			}

		}

		HashMap<Integer, Match> combinedMatches = new HashMap<Integer, Match>();

		int[] emptyPowerups = { -1, -1, -1 };

		for (Integer matchID : matches.keySet()) {

			Match currMatch = matches.get(matchID);

			if (newCollection.matchExists(matchID)) {

				// Merge the two matches

				Match newMatch = newCollection.getMatch(matchID);

				for (int i = 0; i < 3; i++) {

					if (currMatch.bluePowerUps[i] != -1) {
						break;
					}

					if (i == 2) {
						currMatch.bluePowerUps = newMatch.bluePowerUps;
					}

				}

				for (int i = 0; i < 3; i++) {

					if (currMatch.redPowerUps[i] != -1) {
						break;
					}

					if (i == 2) {
						currMatch.redPowerUps = newMatch.redPowerUps;
					}

				}

				if (currMatch.blueScore == -1) {
					currMatch.blueScore = newMatch.blueScore;
				}

				if (currMatch.redScore == -1) {
					currMatch.redScore = newMatch.redScore;
				}

			}

			combinedMatches.put(matchID, currMatch);

		}

		for (Integer matchID : newCollection.matches.keySet()) {

			if (combinedMatches.containsKey(matchID) == false) {

				combinedMatches.put(matchID, newCollection.matches.get(matchID));

			}

		}

		teams = combinedTeams;
		matches = combinedMatches;

		fileName = "";
		
		JOptionPane.showMessageDialog(null,"Merged Succesfuly!", "Merge", JOptionPane.INFORMATION_MESSAGE);

	}

	public String findDuplicates(RegionalCollection newCollection) {

		StringBuilder duplicateMatches = new StringBuilder();

		for (Integer matchID : matches.keySet()) {

			if (newCollection.matchExists(matchID)) {

				for (Team team : matches.get(matchID).getBlueTeams()) {

					if (newCollection.hasTeamPerformance(matchID, team.getTeamNumber())) {

						duplicateMatches.append(
								"Match " + matchID + " has duplicate performances for team " + team.getTeamNumber());
						duplicateMatches.append("\n");

					}

				}

				for (Team team : matches.get(matchID).getRedTeams()) {

					if (newCollection.hasTeamPerformance(matchID, team.getTeamNumber())) {

						duplicateMatches.append(
								"Match " + matchID + " has duplicate performances for team " + team.getTeamNumber());
						duplicateMatches.append("\n");

					}

				}

			}

		}

		return duplicateMatches.toString();

	}
}
