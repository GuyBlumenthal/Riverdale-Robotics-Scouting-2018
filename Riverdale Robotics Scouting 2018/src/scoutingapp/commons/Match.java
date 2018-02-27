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

	Team[] blueTeam;
	Team[] redTeam;

	public Match(int matchID, Team[] blueTeam, Team[] redTeam) {

		this.blueTeam = blueTeam;
		this.redTeam = redTeam;
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
		return blueTeam;
	}

	public Team[] getRedTeam() {
		return redTeam;
	}

}
