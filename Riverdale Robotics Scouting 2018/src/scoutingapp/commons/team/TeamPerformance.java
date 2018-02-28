package scoutingapp.commons.team;

import java.util.ArrayList;

public class TeamPerformance {

	// teleop
	public ArrayList<Integer> cubesOnSwitchTeleop;
	public ArrayList<Integer> cubesOnScaleTeleop;
	// auto
	public ArrayList<Integer> cubesOnSwitchAuto;
	public ArrayList<Integer> cubesOnScaleAuto;
	public boolean climb; // 0 means no climb, 1 means climbed
	public boolean crossedBaseLine; // 0 means no climb, 1 means climbed

	public TeamPerformance() {

		initTeam();
	}

	private void initTeam() {

		cubesOnSwitchTeleop = new ArrayList<Integer>();
		cubesOnScaleTeleop = new ArrayList<Integer>();

		cubesOnSwitchAuto = new ArrayList<Integer>();
		cubesOnScaleAuto = new ArrayList<Integer>();
		cubesOnSwitchAuto = new ArrayList<Integer>();
		cubesOnScaleAuto = new ArrayList<Integer>();
		climb = false;
		crossedBaseLine = false;

	}

}
