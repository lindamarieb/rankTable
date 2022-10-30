/**
 * if points are equal, order alphabetically
 */
public class TeamScore implements Comparable<TeamScore> {
	private String name;
	private Integer points;

	/**
	 * @param result (example: Lions 3)
	 */
	public TeamScore(String result) {
		this.name = result.replaceAll("[0-9]", "").trim();
		try {
			this.points = Integer.parseInt(result.replaceAll("[^0-9]", ""));
		} catch (Exception ex) {
			throw new IllegalArgumentException(String.format("TeamScore invalid input: %s", result));
		}
	}

	public TeamScore(String name, Integer points) {
		this.name = name;
		this.points = points;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPoints() {
		return points;
	}
	public void setPoints(Integer points) {
		this.points = points;
	}

	@Override
	public int compareTo(TeamScore o) {
		// if points are equal, order alphabetically
		return points == o.getPoints() ? name.compareTo(o.getName()) : o.getPoints() - points;
	}
}