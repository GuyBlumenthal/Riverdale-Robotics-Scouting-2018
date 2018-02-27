package scoutingapp.commons;

import java.util.HashMap;

public class RegionalCollection {

	HashMap<Integer, Team> Teams;
	HashMap<Integer, Match> Matches;

	public RegionalCollection() {

	}

	public void createTeam(int teamNumber, String teamName) throws ExistingException {

		if (Teams.containsKey(teamNumber)) {
			throw new ExistingException();
		} else {
			Team team = new Team(teamNumber, teamName);

			Teams.put(teamNumber, team);
		}

	}

	public void createTeam(int teamNumber) throws ExistingException {

		if (Teams.containsKey(teamNumber)) {
			throw new ExistingException();
		} else {
			Team team = new Team(teamNumber);

			Teams.put(teamNumber, team);
		}
	}

	public void createMatch(int matchID, int[] blueTeams, int[] redTeams) throws ExistingException {

		if (Matches.containsKey(matchID)) {
			throw new ExistingException();
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			Matches.put(matchID, match);
		}
	}

	public void createMatch(int matchID, Team[] blueTeams, Team[] redTeams) throws ExistingException {

		if (Matches.containsKey(matchID)) {
			throw new ExistingException();
		} else {
			Match match = new Match(matchID, blueTeams, redTeams);

			Matches.put(matchID, match);
		}
	}

	public Team getTeam(int teamNumber) {
		return Teams.get(teamNumber);
	}

	public Match getMatch(int matchID) {
		return Matches.get(matchID);

	}
}
