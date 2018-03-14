package scoutingapp.commons.team;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import scoutingapp.views.TeamPerformanceWindow;

public class TeamPerformance implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 386764827887862297L;
	// teleop
	public ArrayList<Integer> cubesOnAllianceSwitchTeleop;
	public ArrayList<Integer> cubesOnOpponentSwitchTeleop;
	public ArrayList<Integer> cubesOnScaleTeleop;
	public ArrayList<Integer> cubesInVaultTeleop;

	private ArrayList<Integer> rawCubesOnAllianceSwitchTeleop;
	private ArrayList<Integer> rawCubesOnOpponentSwitchTeleop;
	private ArrayList<Integer> rawCubesOnScaleTeleop;
	private ArrayList<Integer> rawCubesInVaultTeleop;
	
	// auto
	public ArrayList<Integer> cubesOnSwitchAuto;
	public ArrayList<Integer> cubesOnScaleAuto;
	public ArrayList<Integer> cubesInVaultAuto;
	
	private ArrayList<Integer> rawCubesOnSwitchAuto;
	private ArrayList<Integer> rawCubesOnScaleAuto;
	private ArrayList<Integer> rawCubesInVaultAuto;

	public int climb;
	public boolean crossedBaseLine;
	public boolean parked;

	public String comments;
	public String scouterName;

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
		parked = false;
		comments = "";
		scouterName = "";
	}

	private void parsePerformanceWindow(TeamPerformanceWindow teamPerformanceWindow) {

		TableModel rawSwitchTable = teamPerformanceWindow.tblSwitch.getModel();

		for (int i = 0; i < rawSwitchTable.getRowCount(); i++) {
			if (((boolean) rawSwitchTable.getValueAt(i, 0)) == false) {
				if (((boolean) rawSwitchTable.getValueAt(i, 1)) == false) {
					cubesOnAllianceSwitchTeleop.add((Integer) rawSwitchTable.getValueAt(i, 2));
				} else {
					cubesOnOpponentSwitchTeleop.add((Integer) rawSwitchTable.getValueAt(i, 2));
				}

			} else {

				cubesOnSwitchAuto.add((Integer) rawSwitchTable.getValueAt(i, 2));

			}
		}

		TableModel rawScaleTable = teamPerformanceWindow.tblScale.getModel();

		for (int i = 0; i < rawScaleTable.getRowCount(); i++) {
			if (((boolean) rawScaleTable.getValueAt(i, 0)) == false) {
				cubesOnScaleTeleop.add(Integer.parseInt(String.valueOf(rawScaleTable.getValueAt(i, 1))));
			} else {
				cubesOnScaleAuto.add(Integer.parseInt(String.valueOf(rawScaleTable.getValueAt(i, 1))));
			}
		}

		TableModel rawVaultTable = teamPerformanceWindow.tblVault.getModel();

		for (int i = 0; i < rawVaultTable.getRowCount(); i++) {
			if (((boolean) rawVaultTable.getValueAt(i, 0)) == false) {
				cubesInVaultTeleop.add(Integer.parseInt(String.valueOf(rawVaultTable.getValueAt(i, 1))));
			} else {
				cubesInVaultAuto.add(Integer.parseInt(String.valueOf(rawVaultTable.getValueAt(i, 1))));
			}
		}

		if (!teamPerformanceWindow.txtClimb.getText().trim().equals("")) {
			climb = Integer.parseInt(teamPerformanceWindow.txtClimb.getText());
		}

		rawCubesOnAllianceSwitchTeleop = cubesOnAllianceSwitchTeleop;
		rawCubesOnOpponentSwitchTeleop = cubesOnOpponentSwitchTeleop;
		rawCubesOnScaleTeleop = cubesOnScaleTeleop;
		rawCubesInVaultTeleop = cubesInVaultTeleop;
		
		rawCubesOnSwitchAuto = cubesOnSwitchAuto;
		rawCubesOnScaleAuto = cubesOnScaleAuto;
		rawCubesInVaultAuto = cubesInVaultAuto;
		
		crossedBaseLine = teamPerformanceWindow.chkBaseline.isSelected();

		comments = teamPerformanceWindow.txtaComments.getText();

		parked = teamPerformanceWindow.chkParked.isSelected();

		scouterName = teamPerformanceWindow.txtScouter.getText();

		// adjust times
		allTimes.clear();
		for (int i = 3; i <= 6; i++) {
			allTimes.addAll(getData(i));
		}
		cubesOnAllianceSwitchTeleop = calcCycleTime(cubesOnAllianceSwitchTeleop, false);
		cubesOnOpponentSwitchTeleop = calcCycleTime(cubesOnOpponentSwitchTeleop, false);
		cubesOnScaleTeleop = calcCycleTime(cubesOnScaleTeleop, false);
		cubesInVaultTeleop = calcCycleTime(cubesInVaultTeleop, false);

		allTimes.clear();
		for (int i = 0; i < 3; i++) {
			allTimes.addAll(getData(i));
		}
		cubesOnSwitchAuto = calcCycleTime(cubesOnSwitchAuto, true);
		cubesOnScaleAuto = calcCycleTime(cubesOnScaleAuto, true);
		cubesInVaultAuto = calcCycleTime(cubesInVaultAuto, true);
	}

	public TeamPerformanceWindow createWindow(int teamNumber, int matchID, boolean editable) {

		return new TeamPerformanceWindow(teamNumber, matchID, this, editable);

	}

	public ArrayList<Integer> calcCycleTime(ArrayList<Integer> times, boolean isAuto) {

		Collections.sort(allTimes);
		Collections.sort(times);
		ArrayList<Integer> data = new ArrayList<Integer>();
		final int MAX_TIME = isAuto ? 15 : 135;

		int toMaxArrayIndex;

		if (times.size() > 0) {

			if (times.get(times.size() - 1) == allTimes.get(allTimes.size() - 1)) {
				data.add(MAX_TIME - allTimes.get(allTimes.indexOf(times.get(times.size() - 1))));
				toMaxArrayIndex = times.size() - 1;
			} else {
				toMaxArrayIndex = times.size();
			}

			for (int i = 0; i < toMaxArrayIndex; i++) {
				data.add(Math.abs(allTimes.get(allTimes.indexOf(times.get(i)) + 1)
						- allTimes.get(allTimes.indexOf(times.get(i)))));
			}
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
	
	private ArrayList<Integer> getRawData(int scenario) {
		switch (scenario) {
		case 0:
			return rawCubesOnSwitchAuto;
		case 1:
			return rawCubesOnScaleAuto;
		case 2:
			return rawCubesInVaultAuto;
		case 3:
			return rawCubesOnScaleTeleop;
		case 4:
			return rawCubesInVaultTeleop;
		case 5:
			return rawCubesOnAllianceSwitchTeleop;
		case 6:
			return rawCubesOnOpponentSwitchTeleop;
		default:
			return rawCubesOnSwitchAuto;
		}
	}

	public DefaultTableModel constructVaultTableModel() {

		DefaultTableModel vaultTable;

		if ((getRawData(1).size() + getRawData(3).size()) == 0) {
			vaultTable = new DefaultTableModel(new Object[][] {}, new String[] { "In Auto", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		} else {

			allTimes.clear();
			allTimes.addAll(getRawData(2));
			allTimes.addAll(getRawData(4));

			Object[][] times = new Object[allTimes.size()][2];

			int row = 0;
			int max =getRawData(2).size();
			
			for (int i = row; i < max; i++) {

				times[i][0] = true;
				times[i][1] = getRawData(2).get(i - row);

				row++;

			}

			row = max;
			max += getRawData(4).size();
			
			for (int i = row; i < max; i++) {

				times[i][0] = false;
				times[i][1] = getRawData(4).get(i - row);

				row++;

			}

			vaultTable = new DefaultTableModel(times, new String[] { "In Auto", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};

		}

		return vaultTable;
	}

	public DefaultTableModel constructScaleTableModel() {

		DefaultTableModel scaleTable;

		if ((getRawData(1).size() + getRawData(3).size()) == 0) {
			scaleTable = new DefaultTableModel(new Object[][] {}, new String[] { "In Auto", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		} else {

			allTimes.clear();
			allTimes.addAll(getRawData(1));
			allTimes.addAll(getRawData(3));

			Object[][] times = new Object[allTimes.size()][2];

			int row = 0;
			int max = getRawData(1).size();

			for (int i = row; i < max; i++) {

				times[i][0] = true;
				times[i][1] = getRawData(1).get(i - row);

				row++;

			}

			row = max;
			max += getRawData(3).size();
			
			for (int i = row; i < max; i++) {

				times[i][0] = false;
				times[i][1] = getRawData(3).get(i - row);

				row++;

			}

			scaleTable = new DefaultTableModel(times, new String[] { "In Auto", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};

		}

		return scaleTable;
	}

	public DefaultTableModel constructSwitchTableModel() {

		DefaultTableModel switchTable;

		if ((getRawData(0).size() + getRawData(5).size() + getRawData(6).size()) == 0) {
			switchTable = new DefaultTableModel(new Object[][] {}, new String[] { "Auto", "Offense", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		} else {

			allTimes.clear();
			allTimes.addAll(getRawData(0));
			allTimes.addAll(getRawData(5));
			allTimes.addAll(getRawData(6));

			Object[][] times = new Object[allTimes.size()][3];

			int row = 0;
			int max = getRawData(0).size();

			for (int i = row; i < max; i++) {

				times[i][0] = true;
				times[i][1] = false;
				times[i][2] = getRawData(0).get(i - row);
				
			}
			
			row = max;
			max += getRawData(5).size();
			
			for (int i = row; i < max; i++) {
			
				times[i][0] = false;
				times[i][1] = false;
				times[i][2] = getRawData(5).get(i - row);

			}

			row = max;
			max += getRawData(6).size();
			
			for (int i = row; i < getRawData(6).size() + row; i++) {

				times[i][0] = false;
				times[i][1] = true;
				times[i][2] = getRawData(6).get(i - row);

			}

			switchTable = new DefaultTableModel(times, new String[] { "Auto", "Offense", "Time" }) {
				Class[] columnTypes = new Class[] { Boolean.class, Boolean.class, Integer.class };

				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			};
		}

		return switchTable;

	}
}
