package scoutingapp.commons;

import java.io.Serializable;
import java.util.HashMap;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	int teamNumber, numCubesOnSwitchInAuto, numCubesOnSwitchInTeleop, numCubesOnScaleInAuto, numCubesOnScaleInTeleop;
	String teamName;

	boolean crossedBaseLine, didClimb;
	
	HashMap<Integer, Match> matches;

	public Team (int teamNumber, String teamName) {
		
		this.teamNumber = teamNumber;
		this.teamName = teamName;
		
	}
	
	public Team (int teamNumber) {
		
		this.teamNumber = teamNumber;
		this.teamName = Integer.toString(teamNumber);
		
	}
	
	public int getMatchesPlayed(){
		return 9;//matches.size();
	}
	
	public double calcAverage(int element, int pos){
		/*	1: cubes on switch
		 * 	2: cubes on scale
		 * 	3: baseline
		 * 	4: climb
		 */
		double sum = 0;
		int num = matches.size();
		
		switch(element){
		case 4:
			for(int i = 0 ; i < matches.size(); i++){
				sum = (matches.get(i).climbs[pos]) ? sum + 1: sum;
			}
		}
		
		if(sum != 0 && num != 0){
			return Math.round(sum/(num * 1.0));
		}
		return 0;
	}
	
	public double calcConsistency(int element, int pos){
		switch(element){
		case 4:
			int num = 0;
			for(int i = 0 ; i < matches.size(); i++){
				if(matches.get(i).climbs[pos]){
					num++;
				}
			}
			
			return (num/matches.size()) * 100;
		}
		return 0;
	}

	public HashMap<Integer, Match> matches() {
		return matches;
	}
	
	public String getName() {
		return teamName;
	}
	
	public int getNumber() {
		return teamNumber;
	}
	
}
