
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Test;

public class RankTableTest {
	
	@Test
	public void TeamScore_noPoints() {
		try {
			TeamScore teamScore = new TeamScore("Lions nopoints");
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("TeamScore invalid input: Lions nopoints", e.getMessage());
		}
	}

	@Test
	public void TeamScore_invalidPoints() {
		try {
			TeamScore teamScore = new TeamScore("Lions, sometext");
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("TeamScore invalid input: Lions, sometext", e.getMessage());
		}
	}

	@Test
	public void MatchResult_noSeparator() {
		try {
			MatchResult matchResult = new MatchResult("Lions 2 Snakes 2");
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("MatchResult invalid input: Lions 2 Snakes 2", e.getMessage());
		}
	}

	@Test
	public void MatchResult_sameName() {
		try {
			MatchResult matchResult = new MatchResult("Lions 2, Lions 2");
			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("MatchResult team names must differ: Lions 2, Lions 2", e.getMessage());
		}
	}	

	@Test
	public void rankTable_invalidInput1() {
		try {
			List<MatchResult> input = new ArrayList<MatchResult>();
			input.add(new MatchResult("Tarantulas 1, FC Awesome 0"));
			input.add(new MatchResult("Lions 1, FC Awesome 1"));
			input.add(new MatchResult("Lions 3 Snakes 3"));
			input.add(new MatchResult("Tarantulas 3, Snakes 1"));
			input.add(new MatchResult("Lions 4, Grouches 0"));

			Set<TeamScore> rankTable = RankManager.calculateRank(input);

			fail("Exception not thrown");
		} catch (IllegalArgumentException e) {
			assertEquals("MatchResult invalid input: Lions 3 Snakes 3", e.getMessage());
		}
	}

	@Test
	public void rankTable_success() {

		List<MatchResult> input = new ArrayList<MatchResult>();
		input.add(new MatchResult("Lions 3, Snakes 3"));
		input.add(new MatchResult("Tarantulas 1, FC Awesome 0"));
		input.add(new MatchResult("Lions 1, FC Awesome 1"));
		input.add(new MatchResult("Tarantulas 3, Snakes 1"));
		input.add(new MatchResult("Lions 4, Grouches 0"));

		SortedSet<TeamScore> output = new TreeSet<TeamScore>();
		output.add(new TeamScore("Tarantulas", 6));		
		output.add(new TeamScore("Lions", 5));		
		output.add(new TeamScore("FC Awesome", 1));		
		output.add(new TeamScore("Snakes", 1));		
		output.add(new TeamScore("Grouches", 0));		

		Set<TeamScore> rankTable = RankManager.calculateRank(input);

		assertEquals(output, rankTable);
	}	
}