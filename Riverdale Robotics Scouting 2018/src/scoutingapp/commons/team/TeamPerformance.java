package scoutingapp.commons.team;

import java.util.ArrayList;

import javax.swing.table.TableModel;

import scoutingapp.views.TeamPerformanceWindow;

public class TeamPerformance {

	// teleop
	public ArrayList<Integer> cubesOnAllianceSwitchTeleop;
	public ArrayList<Integer> cubesOnOpponentSwitchTeleop;
	public ArrayList<Integer> cubesOnScaleTeleop;
	public ArrayList<Integer> cubesInVaultTeleop;

	// auto
	public ArrayList<Integer> cubesOnSwitchAuto;
	public ArrayList<Integer> cubesOnScaleAuto;
	public ArrayList<Integer> cubesInVaultAuto;

	public boolean climb;
	public boolean crossedBaseLine;

	public TeamPerformance(TeamPerformanceWindow teamPerformanceWindow) {

		initDetail();

		parsePerformanceWindow(teamPerformanceWindow);

	}

	private void initDetail() {

		cubesOnAllianceSwitchTeleop = new ArrayList<Integer>();
		cubesOnOpponentSwitchTeleop = new ArrayList<Integer>();
		cubesOnScaleTeleop = new ArrayList<Integer>();
		cubesInVaultTeleop = new ArrayList<Integer>();

		cubesOnSwitchAuto = new ArrayList<Integer>();
		cubesOnScaleAuto = new ArrayList<Integer>();
		cubesInVaultAuto = new ArrayList<Integer>();

		climb = false;
		crossedBaseLine = false;

	}

	private void parsePerformanceWindow(TeamPerformanceWindow teamPerformanceWindow) {

		TableModel cubesOnSwitches = teamPerformanceWindow.tblSwitch.getModel();

		for (int i = 0; i < cubesOnSwitches.getRowCount(); i++) {

			if (((boolean) cubesOnSwitches.getValueAt(i, 0)) == false) {
				if (((boolean) cubesOnSwitches.getValueAt(i, 1)) == false) {
					cubesOnAllianceSwitchTeleop.add(timeInSeconds((String) cubesOnSwitches.getValueAt(i, 2)));
				} else {
					cubesOnOpponentSwitchTeleop.add(timeInSeconds((String) cubesOnSwitches.getValueAt(i, 2)));
				}
			} else {

				cubesOnSwitchAuto.add(timeInSeconds((String) cubesOnSwitches.getValueAt(i, 2)));

			}
		}

		TableModel cubesOnScale = teamPerformanceWindow.tblScale.getModel();

		for (int i = 0; i < cubesOnScale.getRowCount(); i++) {

			if (((boolean) cubesOnScale.getValueAt(i, 0)) == false) {
				cubesOnScaleTeleop.add(timeInSeconds((String) cubesOnScale.getValueAt(i, 2)));
			} else {
				cubesOnScaleAuto.add(timeInSeconds((String) cubesOnScale.getValueAt(i, 2)));
			}

		}

		TableModel cubesInVault = teamPerformanceWindow.tblVault.getModel();

		for (int i = 0; i < cubesInVault.getRowCount(); i++) {

			if (((boolean) cubesInVault.getValueAt(i, 0)) == false) {
				cubesInVaultTeleop.add(timeInSeconds((String) cubesInVault.getValueAt(i, 2)));
			} else {
				cubesInVaultAuto.add(timeInSeconds((String) cubesInVault.getValueAt(i, 2)));
			}

		}

		climb = teamPerformanceWindow.chkClimb.isSelected();
		crossedBaseLine = teamPerformanceWindow.chkBaseline.isSelected();
		
		//adjust times
		cubesOnAllianceSwitchTeleop = calcCycleTime(cubesOnAllianceSwitchTeleop);
		cubesOnOpponentSwitchTeleop = calcCycleTime(cubesOnOpponentSwitchTeleop);
		cubesOnScaleTeleop = calcCycleTime(cubesOnScaleTeleop);
		cubesInVaultTeleop = calcCycleTime(cubesInVaultTeleop);

		cubesOnSwitchAuto = calcCycleTime(cubesOnSwitchAuto);
		cubesOnScaleAuto = calcCycleTime(cubesOnScaleAuto);
		cubesInVaultAuto = calcCycleTime(cubesInVaultAuto);
	}

	public static int timeInSeconds(String formattedTime) {

		int minutes = Integer.parseInt(formattedTime.split(":")[0]);
		int seconds = Integer.parseInt(formattedTime.split(":")[1]);

		return minutes * 60 + seconds;

	}
	
	public ArrayList<Integer> calcCycleTime(ArrayList<Integer> times){
		ArrayList<Integer> data = times;
		
		for(int i = 0 ; i < times.size(); i++){
			
			int lowest = Integer.MAX_VALUE;
			
			for(int j = 0; j < times.size(); j++){
				int diff = Math.abs(times.get(i) - times.get(j));
				if(diff <= lowest && i != j){
					lowest = diff;
				}
			}
			
			data.add(lowest);
		}
		
		return data;
	}

}
