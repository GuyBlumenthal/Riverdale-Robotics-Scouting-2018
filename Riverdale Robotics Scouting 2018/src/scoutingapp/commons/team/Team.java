package scoutingapp.commons.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import scoutingapp.commons.Match;
import scoutingapp.views.MatchHub;
import scoutingapp.views.TeamPerformanceWindow;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	private int teamNumber;
	private String teamName;

	private HashMap<Integer, TeamPerformance> teamPerformances;

	/**
	 * matches map: given the nth match, a corresponding qualification match
	 * number can be found
	 */
	private HashMap<Integer, Integer> matches;

	public Team(int teamNumber, String teamName) {
		this.teamNumber = teamNumber;
		this.teamName = teamName;

		teamPerformances = new HashMap<Integer, TeamPerformance>();

	}

	public Team(int teamNumber) {

		this.teamNumber = teamNumber;
		this.teamName = Integer.toString(teamNumber);

		teamPerformances = new HashMap<Integer, TeamPerformance>();

	}

	public int getMatchesPlayed() {
		return teamPerformances.size();
	}

	public void addTeamPerformance(int matchID, TeamPerformanceWindow window) {

		teamPerformances.put(matchID, new TeamPerformance(window));

	}

	public double calcAverage(ArrayList<Integer> data) {
		double sum = 0;

		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}

		return sum / ((data.size() > 0 ? data.size() : 1) * 1.0);
	}

	public double calcNumCubesOnSwitchAverage() {
		double sum = 0;

		for (int i = 0; i < numCubesOnAllianceSwitch.size(); i++) {
			sum += numCubesOnAllianceSwitch.get(i);
		}

		for (int i = 0; i < numCubesOnOpponentsSwitch.size(); i++) {
			sum += numCubesOnOpponentsSwitch.get(i);
		}

		return sum / ((numCubesOnAllianceSwitch.size() + numCubesOnOpponentsSwitch.size()) * 1.0);
	}

	public double calcNumCubesOnSwitchConsistency() {
		double average = calcNumCubesOnSwitchAverage();
		int numAboveAverage = 0;

		for (int i = 0; i < numCubesOnAllianceSwitch.size(); i++) {
			if (numCubesOnAllianceSwitch.get(i) >= average) {
				numAboveAverage++;
			}
		}

		for (int i = 0; i < numCubesOnOpponentsSwitch.size(); i++) {
			if (numCubesOnOpponentsSwitch.get(i) >= average) {
				numAboveAverage++;
			}
		}

		return numAboveAverage / (numCubesOnAllianceSwitch.size() + numCubesOnOpponentsSwitch.size()) * 100;
	}

	public double calcConsistency(ArrayList<Integer> data) {
		double average = calcAverage(data);
		int numAboveAverage = 0;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i) >= average) {
				numAboveAverage++;
			}
		}

		return numAboveAverage / (data.size() > 0 ? data.size() : 1) * 100;
	}

	public HashMap<Integer, Integer> getMatches() {
		return matches;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public Match getMatch(int matchID) {
		return MatchHub.regionalCollection.getMatch(matchID);
	}

}
