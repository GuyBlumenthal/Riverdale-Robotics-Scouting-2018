package scoutingapp.commons.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import scoutingapp.commons.Match;
import scoutingapp.views.MatchHub;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	private int teamNumber;
	private String teamName;

	public HashMap<Integer, TeamPerformance> teamPerformances;

	/**
	 * matches map: given the nth match, a corresponding qualification match number
	 * can be found
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

	public double calcAverage(ArrayList<Integer> data) {
		double sum = 0;

		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}

		return sum / ((data.size() > 0 ? data.size() : 1) * 1.0);
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
