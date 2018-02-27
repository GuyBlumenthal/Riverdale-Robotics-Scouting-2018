package scoutingapp.commons;

public class Match {

	int redScore, blueScore, matchID;

	Team[] teams;

	int[] redSwitchPlacements;
	int[] blueSwitchPlacements;

	int[] vaultPlacements;

	boolean[] climbs;

	double[] redPowerUps;
	double[] bluePowerUps;

	int[] blueTeams;
	int[] redTeams;

	public Match(int matchID, int[] blueTeams, int[] redTeams) {

		this.blueTeams = blueTeams;
		this.redTeams = redTeams;
		this.matchID = matchID;

		int[] redSwitchPlacements = new int[6];
		int[] blueSwitchPlacements = new int[6];

		int[] vaultPlacements = new int[6];

		boolean[] climbs = new boolean[6];

		for (int i = 0; i < 6; i++) {

			redSwitchPlacements[i] = -1;
			blueSwitchPlacements[i] = -1;

			vaultPlacements[i] = -1;

			climbs[i] = false;

		}

		double[] redPowerUps = new double[3];
		double[] bluePowerUps = new double[3];

		for (int i = 0; i < 3; i++) {

			redPowerUps[i] = -1;
			bluePowerUps[i] = -1;

		}

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

		int[] redSwitchPlacements = new int[6];
		int[] blueSwitchPlacements = new int[6];

		int[] vaultPlacements = new int[6];

		boolean[] climbs = new boolean[6];

		for (int i = 0; i < 6; i++) {

			redSwitchPlacements[i] = -1;
			blueSwitchPlacements[i] = -1;

			vaultPlacements[i] = -1;

			climbs[i] = false;

		}

		double[] redPowerUps = new double[3];
		double[] bluePowerUps = new double[3];

		for (int i = 0; i < 3; i++) {

			redPowerUps[i] = -1;
			bluePowerUps[i] = -1;

		}

	}

	public Team[] getBlueTeam() {
		return blueTeams;
	}

	public Team[] getRedTeam() {
		return redTeams;
	}

}
