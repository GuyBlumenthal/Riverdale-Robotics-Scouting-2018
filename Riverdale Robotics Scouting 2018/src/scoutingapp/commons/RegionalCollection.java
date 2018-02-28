package scoutingapp.commons;

import java.awt.EventQueue;
import java.util.HashMap;

import scoutingapp.commons.team.Team;
import scoutingapp.views.TeamPerformanceWindow;

public class RegionalCollection {

	HashMap<Integer, Team> teams;
	HashMap<Integer, Match> matches;

	public RegionalCollection() {

		teams = new HashMap<Integer, Team>();
		matches = new HashMap<Integer, Match>();

	}

	public void createTeam(int teamNumber, String teamName) throws ExistingException {

		if (teams.containsKey(teamNumber)) {
			throw new ExistingException();
		} else {
			Team team = new Team(teamNumber, teamName);

			teams.put(teamNumber, team);
		}

	}

	public void createTeam(int teamNumber) throws ExistingException {

		if (teams.containsKey(teamNumber)) {
			throw new ExistingException();
		} else {
			Team team = new Team(teamNumber);

			teams.put(teamNumber, team);
		}
	}

	public void createTeam(Team team) throws ExistingException {

		if (teams.containsKey(team.getTeamNumber())) {
			throw new ExistingException();
		} else {
			teams.put(team.getTeamNumber(), team);
		}
	}

	public void addTeamPerformance(int teamNumber, int matchID, TeamPerformanceWindow window) {

		teams.get(teamNumber).addTeamPerformance(matchID, window);

	}

	public boolean hasTeamPerformance(int matchID, int teamNumber) {

		return teams.get(teamNumber).hasTeamPerformance(matchID);

	}

	public void showTeamPerformance(int matchID, int teamNumber) throws ExistingException {

		if (hasTeamPerformance(matchID, teamNumber)) {

			EventQueue.invokeLater(new Runnable() {
				public void run() {
					try {
						TeamPerformanceWindow frame = teams.get(teamNumber).getTeamPerformance(matchID)
								.createWindow(teamNumber, matchID);
						frame.setVisible(true);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			});

		} else {
			throw new ExistingException();
		}

	}

	public boolean teamExists(int teamNumber) {

		return teams.containsKey(teamNumber);

	}

	public void createMatch(int matchID, int[] blueTeams, int[] redTeams) throws ExistingException {

		if (matches.containsKey(matchID)) {
			throw new ExistingException();
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			matches.put(matchID, match);
		}
	}

	public void createMatch(int matchID, Team[] blueTeams, Team[] redTeams) throws ExistingException {

		if (matches.containsKey(matchID)) {
			throw new ExistingException();
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			matches.put(matchID, match);
		}
	}

	public boolean matchExists(int matchID) {

		return matches.containsKey(matchID);

	}

	public Team getTeam(int teamNumber) {
		return teams.get(teamNumber);
	}

	public int[] getMatchIDList() {
		if (matches.size() == 0) {
			return null;
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
}
