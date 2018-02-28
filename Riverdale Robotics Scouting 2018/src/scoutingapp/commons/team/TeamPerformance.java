package scoutingapp.commons.team;

import java.util.ArrayList;

import javax.swing.table.TableModel;

import scoutingapp.views.TeamPerformanceWindow;

public class TeamPerformance {

	// teleop
	public ArrayList<String> cubesOnAllianceSwitchTeleop;
	public ArrayList<String> cubesOnOpponentSwitchTeleop;
	public ArrayList<String> cubesOnScaleTeleop;
	// auto
	public ArrayList<String> cubesOnSwitchAuto;
	public ArrayList<String> cubesOnScaleAuto;
	public boolean climb; // 0 means no climb, 1 means climbed
	public boolean crossedBaseLine; // 0 means no climb, 1 means climbed

	public TeamPerformance(TeamPerformanceWindow teamPerformanceWindow) {

		initDetail();

		parsePerformanceWindow(teamPerformanceWindow);

	}

	private void initDetail() {

		cubesOnAllianceSwitchTeleop = new ArrayList<String>();
		cubesOnOpponentSwitchTeleop = new ArrayList<String>();
		cubesOnScaleTeleop = new ArrayList<String>();

		cubesOnSwitchAuto = new ArrayList<String>();
		cubesOnScaleAuto = new ArrayList<String>();

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
