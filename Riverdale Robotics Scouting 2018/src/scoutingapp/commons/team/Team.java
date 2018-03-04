package scoutingapp.commons.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import scoutingapp.commons.Match;
import scoutingapp.commons.ScoutingApp;
import scoutingapp.views.TeamPerformanceWindow;

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

	public void addTeamPerformance(int matchID, TeamPerformanceWindow window) {

		teamPerformances.put(matchID, new TeamPerformance(window));

	}

	public HashMap<Integer, Integer> getMatches() {
		return matches;
	}

	public boolean hasTeamPerformance(int matchID) {

		return teamPerformances.containsKey(matchID);

	}

	public TeamPerformance getTeamPerformance(int matchID) {

		return teamPerformances.get(matchID);

	}

	public void removeTeamPerformance(int matchID) {

		if (teamPerformances.containsKey(matchID)) {
			teamPerformances.remove(matchID);
		}

	}

	public String getTeamName() {
		return teamName;
	}

	public int getTeamNumber() {
		return teamNumber;
	}

	public Match getMatch(int matchID) {
		return ScoutingApp.regionalCollection().getMatch(matchID);
	}

	public double calcAverage(int scenario) {
		double sum = 0;
		int total = teamPerformances.size();

		for (TeamPerformance performance : teamPerformances.values()) {
			ArrayList<Integer> data = getData(scenario, performance);

			sum += data.size();
		}
		return sum / (total > 0 ? total : 1);
	}

	/**
	 * @param scenario
	 *            <br />
	 *            <b>0</b> - switch auto <br />
	 *            <b>1</b> - scale auto <br />
	 *            <b>2</b> - scale teleop <br />
	 *            <b>3</b> - vault auto <br />
	 *            <b>4</b> - vault teleop <br />
	 *            <b>5</b> - Alliance Switch Tele-Op <br />
	 *            <b>6</b> - Opponent Switch Tele-Op
	 * 
	 */
	public double calcConsistency(int scenario) {
		double average = calcAverage(scenario);
		int numAboveAverage = 0;
		int total = teamPerformances.size();

		for (TeamPerformance performance : teamPerformances.values()) {
			ArrayList<Integer> data = getData(scenario, performance);
			if (data.size() >= average) {
				numAboveAverage++;
			}
		}

		return numAboveAverage * 1.0 / (total > 0 ? total : 1) * 100;
	}

	public double calcAutoCubesAverage() {
		return (calcAverage(0) + calcAverage(1)) / 2;
	}

	public double calcTeleopNumCubesOnSwitchAverage() {
		return (calcAverage(5) + calcAverage(6));
	}

	public double calcTeleopNumCubesOnSwitchConsistency() {
		double average = calcTeleopNumCubesOnSwitchAverage();
		int numAboveAverage = 0;
		int total = teamPerformances.size();

		for (TeamPerformance performance : teamPerformances.values()) {
			if (performance.cubesOnAllianceSwitchTeleop.size() >= average
					|| performance.cubesOnOpponentSwitchTeleop.size() >= average) {
				numAboveAverage++;
			}
		}

		return (numAboveAverage * 1.0 / (total > 0 ? total : 1)) * 100;
	}

	/**
	 * @param scenario
	 *            set to <code>true</code> for baseline and <code>false</code> for
	 *            climb
	 */
	public double calcBooleanAverage(boolean scenario) {
		return calcSum(scenario) * 1.0 / (teamPerformances.size() > 0 ? teamPerformances.size() : 1);
	}

	/**
	 * @param scenario
	 *            set to <code>true</code> for baseline and <code>false</code> for
	 *            climb
	 */
	public double calcBooleanConsistency(boolean scenario) {
		return calcBooleanAverage(scenario) * 100;
	}

	/**
	 * @param scenario
	 *            set to <code>true</code> for baseline and <code>false</code> for
	 *            climb
	 */
	public int calcSum(boolean scenario) {
		int sum = 0;

		for (TeamPerformance performances : teamPerformances.values()) {
			if (!scenario && performances.climb > -1) {
				sum++;
			} else if (performances.crossedBaseLine) {
				sum++;
			}
		}

		return sum;
	}

	public int calcAverageTime(int scenario) {
		int sum = 0;
		int total = 0;

		for (TeamPerformance performances : teamPerformances.values()) {
			ArrayList<Integer> data = getData(scenario, performances);
			for (Integer i : data) {
				sum += i;
			}
			total += data.size();
		}

		return sum / (total > 0 ? total : 1);
	}

	public int calcAverageSwitchTimeTeleop() {
		return (calcAverageTime(5) + calcAverageTime(6)) / 2;
	}

	public int calcAverageClimbTime(){
		int sum = 0;
		int total = teamPerformances.size();
		
		for (TeamPerformance performances : teamPerformances.values()) {
			if(performances.climb != -1){
				sum += performances.climb;
			}
		}
		return sum / (total > 0 ? total : 1);
	}

	
	public ArrayList<Integer> getData(int scenario, TeamPerformance performance) {
		switch (scenario) {
		case 0:
			return performance.cubesOnSwitchAuto;
		case 1:
			return performance.cubesOnScaleAuto;
		case 2:
			return performance.cubesOnScaleTeleop;
		case 3:
			return performance.cubesInVaultAuto;
		case 4:
			return performance.cubesInVaultTeleop;
		case 5:
			return performance.cubesOnAllianceSwitchTeleop;
		case 6:
			return performance.cubesOnOpponentSwitchTeleop;
		default:
			return performance.cubesOnSwitchAuto;
		}
	}

}
