package scoutingapp.commons;

import java.io.Serializable;
import java.util.HashMap;

public class Team implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8517481588123163761L;

	int teamNumber;
	String teamName;

	HashMap<Integer, Match> matches;

	public Team (int teamNumber, String teamName) {
		
		this.teamNumber = teamNumber;
		this.teamName = teamName;
		
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
