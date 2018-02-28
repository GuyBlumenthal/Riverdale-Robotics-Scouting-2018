package scoutingapp.commons.team;

import java.util.ArrayList;

import javax.swing.table.TableModel;

import scoutingapp.views.TeamPerformanceWindow;

public class TeamPerformance {

	// teleop
	public ArrayList<Integer> cubesOnAllianceSwitchTeleop;
	public ArrayList<Integer> cubesOnOpponentSwitchTeleop;
	public ArrayList<Integer> cubesOnScaleTeleop;
	
	// auto
	public ArrayList<Integer> cubesOnSwitchAuto;
	public ArrayList<Integer> cubesOnScaleAuto;
	public boolean climb; // 0 means no climb, 1 means climbed
	public boolean crossedBaseLine; // 0 means no climb, 1 means climbed

	public TeamPerformance(TeamPerformanceWindow teamPerformanceWindow) {

		initDetail();

		parsePerformanceWindow(teamPerformanceWindow);

	}

	private void initDetail() {

		cubesOnAllianceSwitchTeleop = new ArrayList<Integer>();
		cubesOnOpponentSwitchTeleop = new ArrayList<Integer>();
		cubesOnScaleTeleop = new ArrayList<Integer>();

		cubesOnSwitchAuto = new ArrayList<Integer>();
		cubesOnScaleAuto = new ArrayList<Integer>();

		climb = false;
		crossedBaseLine = false;

	}

	private void parsePerformanceWindow(TeamPerformanceWindow teamPerformanceWindow) {

		TableModel cubesOnSwitches = teamPerformanceWindow.tblSwitch.getModel();

		for (int i = 0; i < cubesOnSwitches.getRowCount(); i++) {

		}

		climb = teamPerformanceWindow.chkClimb.isSelected();
		crossedBaseLine = teamPerformanceWindow.chkBaseline.isSelected();

	}

}
