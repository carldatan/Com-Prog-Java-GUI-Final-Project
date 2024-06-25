import java.awt.*;
import java.awt.event.*;

public class GameLeaderBoards extends Panel {
	private HighScoreManager highScoreManager;
	GameDifficulty gameDifficulty;

	GameLeaderBoards(HighScoreManager highScoreManager, GameDifficulty gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
		this.highScoreManager = highScoreManager;
		this.setLayout(null);
		this.setPreferredSize(new Dimension(GameMenu.SCREEN_WIDTH, GameMenu.SCREEN_HEIGHT));
		Button menuBtn = new Button("Menu");

		menuBtn.setBounds(250, GameMenu.SCREEN_HEIGHT - 50, 100, 40);
		menuBtn.setBackground(Color.red);
		menuBtn.setForeground(Color.black);
		menuBtn.setFont(new Font("Hack", Font.BOLD, 16));
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToGameMenu();
			}
		});
		this.add(menuBtn);
		this.setBackground(Color.black);
	}

	public void switchToGameMenu() {
		Container parent = this.getParent();
		parent.remove(this);
		parent.add(new GameMenu(highScoreManager, gameDifficulty));
		parent.revalidate();
		parent.repaint();
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.setFont(new Font("Monospace", Font.BOLD, 40));
		g.drawString("High Scores", 180, 50);

		g.setFont(new Font("Monospace", Font.PLAIN, 30));
		g.setFont(new Font("Monospaced", Font.BOLD, 30));
		g.setColor(Color.red);
		int y = 100;
		for (HighScoreEntry entry : highScoreManager.getScores()) {
			System.out.println(entry.name);
			g.drawString(entry.name + ": " + entry.score + " Difficulty: " + gameDifficulty.getDifficulty(), 90, y);
			g.setColor(Color.red);
			y += 40;
			revalidate();
		}

	}

}
