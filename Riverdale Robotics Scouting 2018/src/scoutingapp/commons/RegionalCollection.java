package scoutingapp.commons;

import java.util.HashMap;

public class RegionalCollection {

	HashMap<Integer, Team> Teams;
	HashMap<Integer, Match> Matches;

	public RegionalCollection() {

	}

	public void createTeam(int teamNumber, String teamName) {

		Team team = new Team(teamNumber, teamName);

		Teams.put(teamNumber, team);

	}

	public void createTeam(int teamNumber) throw  {

		Team team = new Team(teamNumber);

		Teams.put(teamNumber, team);

	}
	
	public void createMatch
}
