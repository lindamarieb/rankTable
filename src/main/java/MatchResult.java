public class MatchResult {
	private TeamScore team1Score;
	private TeamScore team2Score;

	/**
	 * Parses a line of text into a set of two team scores
	 * eg. input: Lions 3, Snakes 3
	 * @param results
	 */
	public MatchResult(String results) {
		String teams[] = results.split(",");
		if (teams == null || teams.length < 2) {
			throw new IllegalArgumentException(String.format("MatchResult invalid input: %s", results));
		}
		this.team1Score = new TeamScore(teams[0]);
		this.team2Score = new TeamScore(teams[1]);
		if (this.team1Score.getName().equals(this.team2Score.getName())) {
			throw new IllegalArgumentException(String.format("MatchResult team names must differ: %s", results));
		}
	}

	public TeamScore getTeam1Score() {
		return team1Score;
	}
	public void setTeam1Score(TeamScore team1Score) {
		this.team1Score = team1Score;
	}
	public TeamScore getTeam2Score() {
		return team2Score;
	}
	public void setTeam2Score(TeamScore team2Score) {
		this.team2Score = team2Score;
	}
}