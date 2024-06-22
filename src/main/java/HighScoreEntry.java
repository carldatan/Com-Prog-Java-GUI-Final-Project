public class HighScoreEntry implements Comparable<HighScoreEntry> {
	String name;
	int score;

	HighScoreEntry(String name, int score) {
		this.name = name;
		this.score = score;
	}

	@Override
	public int compareTo(HighScoreEntry arg0) {
		return Integer.compare(arg0.score, this.score);
	}

}
