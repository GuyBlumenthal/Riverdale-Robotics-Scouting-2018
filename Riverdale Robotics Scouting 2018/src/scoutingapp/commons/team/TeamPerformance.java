package scoutingapp.commons.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.table.TableModel;

import scoutingapp.views.TeamPerformanceWindow;

public class TeamPerformance implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 386764827887862297L;
	// teleop
	public ArrayList<Integer> cubesOnAllianceSwitchTeleop = new ArrayList<Integer>();
	public ArrayList<Integer> cubesOnOpponentSwitchTeleop = new ArrayList<Integer>();
	public ArrayList<Integer> cubesOnScaleTeleop = new ArrayList<Integer>();
	public ArrayList<Integer> cubesInVaultTeleop = new ArrayList<Integer>();

	// auto
	public ArrayList<Integer> cubesOnSwitchAuto = new ArrayList<Integer>();
	public ArrayList<Integer> cubesOnScaleAuto = new ArrayList<Integer>();
	public ArrayList<Integer> cubesInVaultAuto = new ArrayList<Integer>();

	// Tables

	public TableModel rawSwitchTable;
	public TableModel rawScaleTable;
	public TableModel rawVaultTable;

	public int climb;

	public boolean crossedBaseLine;
	
	public String comments;

	private ArrayList<Integer> allTimes = new ArrayList<Integer>();
	
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

		climb = -1;
		crossedBaseLine = false;
		comments = "";
	}

	private void parsePerformanceWindow(TeamPerformanceWindow teamPerformanceWindow) {

		rawSwitchTable = teamPerformanceWindow.tblSwitch.getModel();

		for (int i = 0; i < rawSwitchTable.getRowCount(); i++) {
			//if(rawSwitchTable.getValueAt(i, 2) != null){
				if (((boolean) rawSwitchTable.getValueAt(i, 0)) == false) {
					if (((boolean) rawSwitchTable.getValueAt(i, 1)) == false) {
						cubesOnAllianceSwitchTeleop.add(timeInSeconds((String) rawSwitchTable.getValueAt(i, 2)));
					} else {
						cubesOnOpponentSwitchTeleop.add(timeInSeconds((String) rawSwitchTable.getValueAt(i, 2)));
					}
	
				} else {
	
					cubesOnSwitchAuto.add(timeInSeconds((String) rawSwitchTable.getValueAt(i, 2)));
	
				}
			//}
		}

		rawScaleTable = teamPerformanceWindow.tblScale.getModel();

		for (int i = 0; i < rawScaleTable.getRowCount(); i++) {
			//if(rawScaleTable.getValueAt(i, 1)  != null){
				if (((boolean) rawScaleTable.getValueAt(i, 0)) == false) {
						cubesOnScaleTeleop.add(timeInSeconds((String) rawScaleTable.getValueAt(i, 1)));
				} else {
						cubesOnScaleAuto.add(timeInSeconds((String) rawScaleTable.getValueAt(i, 1)));
				}
			//}
		}

		rawVaultTable = teamPerformanceWindow.tblVault.getModel();

		for (int i = 0; i < rawVaultTable.getRowCount(); i++) {
			//if(rawVaultTable.getValueAt(i, 1) != null){
				if (((boolean) rawVaultTable.getValueAt(i, 0)) == false) {
					cubesInVaultTeleop.add(timeInSeconds((String) rawVaultTable.getValueAt(i, 1)));
				} else {
					cubesInVaultAuto.add(timeInSeconds((String) rawVaultTable.getValueAt(i, 1)));
				}
			//}
		}

		if(!teamPerformanceWindow.txtClimb.getText().trim().equals(null)){
			climb = timeInSeconds(teamPerformanceWindow.txtClimb.getText());
		}
		
		crossedBaseLine = teamPerformanceWindow.chkBaseline.isSelected();
		
		comments = teamPerformanceWindow.txtComments.getText();

		// adjust times
		allTimes.clear();
		for(int i = 3; i <= 6; i++){
			allTimes.addAll(getData(i));
		}
		Collections.sort(allTimes);
		cubesOnAllianceSwitchTeleop = calcCycleTime(cubesOnAllianceSwitchTeleop, false);
		cubesOnOpponentSwitchTeleop = calcCycleTime(cubesOnOpponentSwitchTeleop, false);
		cubesOnScaleTeleop = calcCycleTime(cubesOnScaleTeleop, false);
		cubesInVaultTeleop = calcCycleTime(cubesInVaultTeleop, false);

		allTimes.clear();
		for(int i = 0; i <= 3; i++){
			allTimes.addAll(getData(i));
		}
		Collections.sort(allTimes);
		cubesOnSwitchAuto = calcCycleTime(cubesOnSwitchAuto, true);
		cubesOnScaleAuto = calcCycleTime(cubesOnScaleAuto, true);
		cubesInVaultAuto = calcCycleTime(cubesInVaultAuto, true);
	}

	public TeamPerformanceWindow createWindow(int teamNumber, int matchID, boolean editable) {

		return new TeamPerformanceWindow(teamNumber, matchID, this, editable);

	}

	public static int timeInSeconds(String formattedTime) {

		int minutes = Integer.parseInt(formattedTime.split(":")[0]);
		int seconds = Integer.parseInt(formattedTime.split(":")[1]);

		return minutes * 60 + seconds;

	}

	public ArrayList<Integer> calcCycleTime(ArrayList<Integer> times, boolean isAuto) {

		ArrayList<Integer> data = new ArrayList<Integer>(times.size());
		
		Collections.sort(times);
		int max;
		if(isAuto){ max = 15; }else{ max = 135;}	
			
		if(times.size() > 1){
			for (int i = 0; i < times.size(); i++) {
	
				if(times.get(i) == allTimes.get(allTimes.size() - 1)){
					data.add(max - times.get(i));
					break;
				}
				
				int lowest = Integer.MAX_VALUE;
	
				for (int j = 0; j < allTimes.size(); j++) {
					int diff = Math.abs(times.get(i) - allTimes.get(j));
					if (diff < lowest && times.get(i) != allTimes.get(j)) {
						lowest = diff;
					}
				}
				
				data.add(lowest);
			}
			
		}else if(times.size() == 1){
			data.add(times.get(0));
		}
		return data;
	}

	public ArrayList<Integer> getData(int scenario) {
		switch (scenario) {
		case 0:
			return cubesOnSwitchAuto;
		case 1:
			return cubesOnScaleAuto;
		case 2:
			return cubesInVaultAuto;
		case 3:
			return cubesOnScaleTeleop;
		case 4:
			return cubesInVaultTeleop;
		case 5:
			return cubesOnAllianceSwitchTeleop;
		case 6:
			return cubesOnOpponentSwitchTeleop;
		default:
			return cubesOnSwitchAuto;
		}
	}
}
