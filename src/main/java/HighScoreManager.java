import java.util.*;

public class HighScoreManager {

	private List<HighScoreEntry> highScores;
	private static final int MAX_HIGH_SCORES = 5;

	HighScoreManager() {
		highScores = new ArrayList<>();
	}

	public void addHighScore(String name, int score) {
		highScores.add(new HighScoreEntry(name, score));
		Collections.sort(highScores);
		if (highScores.size() > MAX_HIGH_SCORES) {
			highScores.remove(5);
		}
	}

	public List<HighScoreEntry> getScores() {
		return highScores;
	}
}
