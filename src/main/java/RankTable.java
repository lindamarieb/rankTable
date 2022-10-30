import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/*
The input and output will be text. Either using stdin/stdout or taking filenames on the command
line is fine.
The input contains results of games, one per line. See “Sample input” for details.
The output should be ordered from most to least points, following the format specified in
“Expected output”.

The rules:
	In this league, 
	- a draw (tie) is worth 1 point and 
	- a win is worth 3 points. 
	- A loss is worth 0 points.
	If two or more teams have the same number of points, they should have the same rank and be
	printed in alphabetical order (as in the tie for 3rd place in the sample data).

Sample input:
	Lions 3, Snakes 3
	Tarantulas 1, FC Awesome 0
	Lions 1, FC Awesome 1
	Tarantulas 3, Snakes 1
	Lions 4, Grouches 0

Expected output:
	1. Tarantulas, 6 pts
	2. Lions, 5 pts
	3. FC Awesome, 1 pt
	3. Snakes, 1 pt
	5. Grouches, 0 pts
*/
public class RankTable {
	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws IOException {

		if (args.length < 1) {
			System.out.println();
			System.out.println("***[ RankTable ERROR ]*************************************************");
			System.out.println(" Please specify an input file path");
			System.out.println("***********************************************************************");
			System.out.println();
			return;
		}
		// read in match results
		BufferedReader br = new BufferedReader(new FileReader(args[0]));
 
		// build up list of results
		List<MatchResult> results = new ArrayList<MatchResult>();
		try {
			
			String line;
			while ((line = br.readLine()) != null) {
				results.add(new MatchResult(line));
			}

			// Calculate rank
			Set<TeamScore> scores = RankManager.calculateRank(results);

			// print out results
			int i = 1;	
			for (TeamScore score: scores) {
				System.out.println(String.format("%d. %s, %d pt%s", i++, score.getName(), score.getPoints(), score.getPoints() == 1 ? "" : "s"));
			}

		} finally {
			br.close();
		}
	}
}
