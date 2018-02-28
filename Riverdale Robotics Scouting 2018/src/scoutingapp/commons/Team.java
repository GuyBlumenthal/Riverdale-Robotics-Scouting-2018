package scoutingapp.commons;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import scoutingapp.views.MatchHub;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	private int teamNumber;
	private String teamName;
	private boolean allianceColour; 
	
	/**
	 * matches map: given the nth match, a corresponding qualification match number can be found  
	 * all times added onto team abilities will be converted to SECONDS
	 * set alliance colour to true if red and false if blue
	 * 
	 */
	
	private HashMap<Integer, Integer> matches;
	
	//teleop
	public ArrayList<Integer> numCubesOnAllianceSwitch;
	public ArrayList<Integer> numCubesOnOpponentsSwitch;
	public ArrayList<Integer> numCubesOnScaleTeleop;
	public ArrayList<Integer> timeCubesOnSwitchTeleop;
	public ArrayList<Integer> timeCubesOnScaleTeleop;
	public ArrayList<Integer> timeCubesInVaultTeleop;
	
	//auto
	public ArrayList<Integer> numCubesOnSwitchAuto;
	public ArrayList<Integer> numCubesOnScaleAuto;
	public ArrayList<Integer> timeCubesOnSwitchAuto;
	public ArrayList<Integer> timeCubesOnScaleAuto;
	public ArrayList<Integer> timeCubesInVaultAuto;
	public ArrayList<Integer> climb; // 0 means no climb, 1 means climbed
	public ArrayList<Integer> crossedBaseLine; // 0 means no climb, 1 means climbed

	
	public Team(int teamNumber, String teamName, boolean allianceColour) {
		this.teamNumber = teamNumber;
		this.teamName = teamName;
		this.allianceColour = allianceColour;
	}

	public Team(int teamNumber, boolean allianceColour) {

		this.teamNumber = teamNumber;
		this.allianceColour = allianceColour;
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
	
	public double calcNumCubesOnSwitchAverage(){
		double sum = 0;

		for (int i = 0; i < numCubesOnAllianceSwitch.size(); i++) {
			sum += numCubesOnAllianceSwitch.get(i);
		}
		
		for (int i = 0; i < numCubesOnOpponentsSwitch.size(); i++) {
			sum += numCubesOnOpponentsSwitch.get(i);
		}

		return sum / ((numCubesOnAllianceSwitch.size() + numCubesOnOpponentsSwitch.size()) * 1.0);
	}
	
	public double calcNumCubesOnSwitchConsistency(){
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

	public HashMap<Integer, Integer> getMatches() {
		return matches;
	}

	public String getTeamName() {
		return teamName;
	}

	public int getTeamNumber() {
		return teamNumber;
	}
	
	public String getAllianceColour() {
		return this.allianceColour ? "red" : "blue";
	}

	public Match getMatch(int matchID) {
		return MatchHub.regionalCollection.getMatch(matchID);
	}

	/**
	 * @param times: the raw string values from the table
	 * @param scenario: 0 = switch in auto
	 * 					1 = switch in teleop
	 * 					2 = scale in auto
	 * 					3 = scale in teleop 
	 * 					4 = vault in auto
	 * 					5 = vault in teleop
	 */
	public void convertTimes(String[] times, int scenario) {
		ArrayList<Integer> timeScenario = timeCubesOnSwitchAuto;
		
		switch(scenario){
		case 0:
			timeScenario = timeCubesOnSwitchAuto;
		case 1:
			timeScenario = timeCubesOnSwitchTeleop;
		case 2:
			timeScenario = timeCubesOnScaleAuto;
		case 3:
			timeScenario = timeCubesOnScaleTeleop;
		case 4:
			timeScenario = timeCubesInVaultAuto;
		case 5:
			timeScenario = timeCubesInVaultTeleop;
		}
		
		for(int i = 0; i < times.length; i++){
			if(!times[i].equals("")){
				int minutes = Integer.parseInt(times[i].substring(0, times[i].indexOf(':')));
				int seconds = Integer.parseInt(times[i].substring(times[i].indexOf(':') + 1));
				timeScenario.add(minutes * 60 + seconds);
			}
		}	
	
		
	}

}
