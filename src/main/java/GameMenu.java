import java.util.Random;

import javax.swing.Timer;

import java.awt.*;
import java.awt.event.*;

public class GameMenu extends Panel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	Color color;
	Random random = new Random();
	private HighScoreManager highScoresManager;

	GameMenu(HighScoreManager highScoreManager) {
		this.highScoresManager = highScoreManager;
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.setVisible(true);
		startColorChangeTimer();
	}

	public void setUpButtons() {

		Button newGameBtn = new Button("New Game");
		Button optBtn = new Button("Options");
		Button exitBtn = new Button("Exit");
		Button highScoreBtn = new Button("High Scores");
		Panel panel = new Panel();
		panel.setLayout(null);
		//panel.setBackground(Color.white);
		panel.setBounds(200, 350, 200, 200);
		newGameBtn.setBounds(50, 10, 100, 40);
		newGameBtn.setBackground(Color.red);
		newGameBtn.setForeground(Color.black);
		newGameBtn.setFont(new Font("Hack", Font.BOLD, 16));
		newGameBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToNewGame();
			}
		});
		optBtn.setBounds(50, 60, 100, 40);
		optBtn.setBackground(Color.red);
		optBtn.setForeground(Color.black);
		optBtn.setFont(new Font("Hack", Font.BOLD, 16));
		optBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToOptions();
			}
		});
		exitBtn.setBounds(50, 160, 100, 40);
		exitBtn.setBackground(Color.red);
		exitBtn.setForeground(Color.black);
		exitBtn.setFont(new Font("Hack", Font.BOLD, 16));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		highScoreBtn.setBounds(50, 110, 100, 40);
		highScoreBtn.setBackground(Color.red);
		highScoreBtn.setForeground(Color.black);
		highScoreBtn.setFont(new Font("Hack", Font.BOLD, 12));
		highScoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToLeaderboards();
			}
		});

		panel.add(highScoreBtn);
		panel.add(exitBtn);
		panel.add(newGameBtn);
		panel.add(optBtn);
		this.add(panel);

	}

	@Override
	public void paint(Graphics g) {
		g.setFont(new Font("Monospace", Font.BOLD, 90));
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.setColor(Color.red);
		g.setColor(color);
		g.drawString("SNAKE", (SCREEN_WIDTH - metrics.stringWidth("SNAKE")) / 2, 200);
		setUpButtons();
	}

	public void switchToNewGame() {
		Container parent = this.getParent();
		parent.remove(this);
		GamePanel gamePanel = new GamePanel(highScoresManager);
		parent.add(gamePanel);
		parent.validate();
		gamePanel.requestFocusInWindow();
		parent.repaint();
	}

	public void switchToOptions() {
		ontainer parent = this.getParent();
		if (parent == null) {
			return;
		}
		parent.remove(this);
		GamePanel gamePanel = new GamePanel(highScoresManager);
		parent.add(gamePanel);
		GameOptions newgameOptions = new GameOptions(highScoresManager, gameDifficulty);
		parent.add(newgameOptions);
		parent.validate();
		gamePanel.requestFocusInWindow();
		newgameOptions.requestFocusInWindow();
		parent.repaint();
	}

	public void switchToLeaderboards() {
		Container parent = this.getParent();
		parent.remove(this);
		GameLeaderBoards gameLeaderBoards = new GameLeaderBoards(highScoresManager, gameDifficulty);
		parent.add(gameLeaderBoards);
		parent.validate();
		gameLeaderBoards.requestFocusInWindow();
		parent.repaint();
	}

	public void changeColor() {
		color = new Color(random.nextInt(256), random.nextInt(256), random.nextInt(256));
	}

	private void startColorChangeTimer() {
		Timer timer = new Timer(500, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				changeColor();
				repaint();
			}
		});
		timer.start();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
