package scoutingapp.commons;

import scoutingapp.commons.team.Team;

public class Match {

	int redScore, blueScore, matchID;

	int[] redPowerUps;
	int[] bluePowerUps;

	int[] blueTeams;
	int[] redTeams;

	public Match(int matchID, int[] blueTeams, int[] redTeams) {

		this.blueTeams = blueTeams;
		this.redTeams = redTeams;
		this.matchID = matchID;

		int[] redPowerUps = new int[3];
		int[] bluePowerUps = new int[3];

		for (int i = 0; i < 3; i++) {

			redPowerUps[i] = -1;
			bluePowerUps[i] = -1;

		}
		
		this.redPowerUps = redPowerUps;
		this.bluePowerUps = bluePowerUps;

	}

	public Match(int matchID, Team[] blueTeams, Team[] redTeams) {

		this.blueTeams = new int[blueTeams.length];

		for (int i = 0; i < blueTeams.length; i++) {
			this.blueTeams[i] = blueTeams[i].getTeamNumber();
		}

		this.redTeams = new int[redTeams.length];

		for (int i = 0; i < redTeams.length; i++) {
			this.redTeams[i] = redTeams[i].getTeamNumber();
		}

		this.matchID = matchID;

		int[] redPowerUps = new int[3];
		int[] bluePowerUps = new int[3];

		for (int i = 0; i < 3; i++) {

			redPowerUps[i] = -1;
			bluePowerUps[i] = -1;

		}
		
		this.bluePowerUps = bluePowerUps;
		this.redPowerUps = redPowerUps;

	}

	public int getMatchID() {
		return this.matchID;
	}
	
	public int[] getBluePowerups() {
		return bluePowerUps;
	}
	
	public int[] getRedPowerups() {
		return redPowerUps;
	}

	public Team[] getBlueTeams() {

		Team[] teams = new Team[this.blueTeams.length];

		for (int i = 0; i < teams.length; i++) {
			teams[i] = ScoutingApp.regionalCollection.getTeam(this.blueTeams[i]);
		}

		return teams;
	}

	public Team[] getRedTeams() {

		Team[] teams = new Team[this.redTeams.length];

		for (int i = 0; i < teams.length; i++) {
			teams[i] = ScoutingApp.regionalCollection.getTeam(this.redTeams[i]);
		}

		return teams;
	}

}
