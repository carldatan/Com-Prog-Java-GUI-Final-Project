import java.awt.*;

public class GameLeaderBoards extends Panel {
	private HighScoreManager highScoreManager;

	GameLeaderBoards(HighScoreManager highScoreManager) {
		this.highScoreManager = highScoreManager;
		this.setPreferredSize(new Dimension(GameMenu.SCREEN_WIDTH, GameMenu.SCREEN_HEIGHT));
		this.setBackground(Color.black);
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.setFont(new Font("Monospace", Font.BOLD, 40));
		g.drawString("High Scores", 180, 50);

		g.setFont(new Font("Monospace", Font.PLAIN, 30));
		int y = 100;
		for (HighScoreEntry entry : highScoreManager.getScores()) {
			System.out.println(entry.name);
			g.drawString(entry.name + ": " + entry.score, 180, y);
			g.setColor(Color.red);
			y += 40;
			repaint();
		}
	}

}