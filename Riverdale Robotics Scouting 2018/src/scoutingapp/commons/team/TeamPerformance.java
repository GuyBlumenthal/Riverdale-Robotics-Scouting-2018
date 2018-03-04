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
	public ArrayList<Integer> cubesOnAllianceSwitchTeleop;
	public ArrayList<Integer> cubesOnOpponentSwitchTeleop;
	public ArrayList<Integer> cubesOnScaleTeleop;
	public ArrayList<Integer> cubesInVaultTeleop;

	// auto
	public ArrayList<Integer> cubesOnSwitchAuto;
	public ArrayList<Integer> cubesOnScaleAuto;
	public ArrayList<Integer> cubesInVaultAuto;

	// Tables

	public TableModel rawSwitchTable;
	public TableModel rawScaleTable;
	public TableModel rawVaultTable;

	public int climb;

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

		climb = -1;
		crossedBaseLine = false;

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

		if(!teamPerformanceWindow.txtClimb.getText().trim().equals("")){
			climb = timeInSeconds(teamPerformanceWindow.txtClimb.getText());
		}
		
		crossedBaseLine = teamPerformanceWindow.chkBaseline.isSelected();

		// adjust times
		
		cubesOnAllianceSwitchTeleop = calcCycleTime(cubesOnAllianceSwitchTeleop);
		cubesOnOpponentSwitchTeleop = calcCycleTime(cubesOnOpponentSwitchTeleop);
		cubesOnScaleTeleop = calcCycleTime(cubesOnScaleTeleop);
		cubesInVaultTeleop = calcCycleTime(cubesInVaultTeleop);

		cubesOnSwitchAuto = calcCycleTime(cubesOnSwitchAuto);
		cubesOnScaleAuto = calcCycleTime(cubesOnScaleAuto);
		cubesInVaultAuto = calcCycleTime(cubesInVaultAuto);
	}

	public TeamPerformanceWindow createWindow(int teamNumber, int matchID, boolean editable) {

		return new TeamPerformanceWindow(teamNumber, matchID, this, editable);

	}

	public static int timeInSeconds(String formattedTime) {

		int minutes = Integer.parseInt(formattedTime.split(":")[0]);
		int seconds = Integer.parseInt(formattedTime.split(":")[1]);

		return minutes * 60 + seconds;

	}

	public ArrayList<Integer> calcCycleTime(ArrayList<Integer> times) {

		ArrayList<Integer> data = new ArrayList<Integer>(times.size());

		Collections.sort(times);
		
		if(times.size() > 1){
			for (int i = 0; i < times.size() - 1; i++) {
	
				int lowest = Math.abs(data.get(i + 1) - data.get(i));
	
				for (int j = 0; j < times.size(); j++) {
					int diff = Math.abs(times.get(i) - times.get(j));
					if (diff <= lowest && i != j) {
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

}
