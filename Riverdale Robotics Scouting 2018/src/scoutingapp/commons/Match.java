package scoutingapp.commons;

import java.util.ArrayList;

public class Match {
	
	int redScore, blueScore, matchID;

	ArrayList<Team> teams;

	int[] redSwitchPlacements;
	int[] blueSwitchPlacements;

	int[] vaultPlacements;

	boolean[] climbs;

	double[] redPowerUps;
	double[] bluePowerUps;

	public Match(int matchID) {

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

}
