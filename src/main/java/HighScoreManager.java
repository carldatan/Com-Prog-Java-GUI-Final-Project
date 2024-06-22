import java.util.*;

public class HighScoreManager {

	private List<HighScoreEntry> highScores;
	private static final int MAX_HIGH_SCORES = 5;

	HighScoreManager() {
		highScores = new ArrayList<>();
	}

	public void addHighScore(String name, int score) {
		System.out.println("addedHighScore");
		highScores.add(new HighScoreEntry(name, score));
		Collections.sort(highScores);
		if (highScores.size() > MAX_HIGH_SCORES) {
			highScores = highScores.subList(0, MAX_HIGH_SCORES);
		}
	}

	public List<HighScoreEntry> getScores() {
		return highScores;
	}
}
