package scoutingapp.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import scoutingapp.views.MatchOverview;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	private int teamNumber;
	private String teamName;

	private HashMap<Integer, Integer> matches;
	public ArrayList<Integer> numCubesOnSwitchTeleop;
	public ArrayList<Integer> numCubesOnScaleTeleop;
	public ArrayList<Integer> numCubesOnSwitchAuto;
	public ArrayList<Integer> numCubesOnScaleAuto;
	public ArrayList<Integer> climb; // 0 means no climb, 1 means climbed
	public ArrayList<Integer> crossedBaseLine; // 0 means no climb, 1 means
												// climbed

	private RegionalCollection regionalCollection = MatchOverview.regionalCollection;
	
	public Team(int teamNumber, String teamName) {

		this.teamNumber = teamNumber;
		this.teamName = teamName;

	}

	public Team(int teamNumber) {

		this.teamNumber = teamNumber;
		this.teamName = Integer.toString(teamNumber);

	}

	public int getMatchesPlayed() {
		return 9;// TODO: matches.size();
	}

	public double calcAverage(ArrayList<Integer> data) {
		double sum = 0;

		for (int i = 0; i < data.size(); i++) {
			sum += data.get(i);
		}

		return sum / (data.size() * 1.0);
	}

	public double calcConsistency(ArrayList<Integer> data) {
		double average = calcAverage(data);
		int numAboveAverage = 0;

		for (int i = 0; i < data.size(); i++) {
			if (data.get(i) >= average) {
				numAboveAverage++;
			}
		}

		return numAboveAverage / data.size() * 100;
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
		return regionalCollection.getMatch(matchID);
	}

}
