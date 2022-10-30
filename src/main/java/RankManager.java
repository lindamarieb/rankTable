import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.Map.Entry;

public class RankManager {

	static final Integer DRAW_POINTS = 1;
	static final Integer WIN_POINTS = 3;
	static final Integer LOSS_POINTS = 0;

	/**
	 * Calculate the ranking table for a league
	 * In this league, a draw (tie) is worth 1 point and a win is worth 3 points. A loss is worth 0 points.
	 * 
	 * @param matchResults
	 * @return
	 */
	public static Set<TeamScore> calculateRank (List<MatchResult> matchResults) {
		
		Map<String, Integer> pointsMap = calculatePoints(matchResults);
		SortedSet<TeamScore> sortedSet = new TreeSet<TeamScore>();

		for (Entry<String, Integer> entry: pointsMap.entrySet()) {
			sortedSet.add(new TeamScore(entry.getKey(), entry.getValue()));
		}

		return sortedSet;
	}

	/**
	 * calculate how many points each team has
	 * @param matchResults
	 * @return
	 */
	private static Map<String, Integer> calculatePoints(List<MatchResult> matchResults) {
		Map<String, Integer> points = new HashMap<String, Integer>();

		// calculate how many points each team has
		for (MatchResult matchResult: matchResults) {

			// initialise points if empty
			if (points.get(matchResult.getTeam1Score().getName()) == null) {
				points.put(matchResult.getTeam1Score().getName(), 0) ;
			}
			if (points.get(matchResult.getTeam2Score().getName()) == null) {
				points.put(matchResult.getTeam2Score().getName(), 0) ;
			}

			if (matchResult.getTeam1Score().getPoints().equals(matchResult.getTeam2Score().getPoints())) {
				
				// it's a draw, each team gets 1 point
				points.put(matchResult.getTeam1Score().getName(), points.get(matchResult.getTeam1Score().getName()) + DRAW_POINTS);
				points.put(matchResult.getTeam2Score().getName(), points.get(matchResult.getTeam2Score().getName()) + DRAW_POINTS);

			} else if (matchResult.getTeam1Score().getPoints() > matchResult.getTeam2Score().getPoints()) {

				// team1 won
				points.put(matchResult.getTeam1Score().getName(), points.get(matchResult.getTeam1Score().getName()) + WIN_POINTS);
				points.put(matchResult.getTeam2Score().getName(), points.get(matchResult.getTeam2Score().getName()) + LOSS_POINTS);

			} else {

				// team2 won
				points.put(matchResult.getTeam1Score().getName(), points.get(matchResult.getTeam1Score().getName()) + LOSS_POINTS);
				points.put(matchResult.getTeam2Score().getName(), points.get(matchResult.getTeam2Score().getName()) + WIN_POINTS);

			}
		}

		return points;
	}
}