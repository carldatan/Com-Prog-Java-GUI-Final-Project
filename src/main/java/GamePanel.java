import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {

	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;
	static final int UNIT_SIZE = 25;
	static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / UNIT_SIZE;
	static final int DELAY = 75;
	final int x[] = new int[GAME_UNITS];
	final int y[] = new int[GAME_UNITS];
	int bodyParts = 6;
	int applesEaten;
	int appleX;
	int appleY;
	char direction = 'R';
	boolean running = false;
	Timer timer;
	Random random;
	boolean gameOverHandled = false;

	GamePanel() {
		random = new Random();
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		this.addKeyListener(new MyKeyAdapter());
		startGame();
	}

	public void startGame() {
		newApple();
		running = true;
		timer = new Timer(DELAY, this);
		timer.start();
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		draw(g);
	}

	public void draw(Graphics g) {
		if (running) {
			// for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
			// g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
			// g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
			// }
			g.setColor(Color.red);
			g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

			for (int i = 0; i < bodyParts; i++) {
				if (i == 0) {
					g.setColor(Color.green);
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				} else {
					g.setColor(new Color(45, 180, 0));
					g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
				}
			}

			g.setFont(new Font("Hack", Font.BOLD, 25));
			g.setColor(Color.red);
			FontMetrics metrics = getFontMetrics(g.getFont());
			g.drawString("Score: " + applesEaten,
					(SCREEN_WIDTH - metrics.stringWidth("Score: " + applesEaten)) / 2,
					g.getFont().getSize());
		} else {
			if (!gameOverHandled)
				gameOver(g);
		}
	}

	public void move() {
		for (int i = bodyParts; i > 0; i--) {
			x[i] = x[i - 1];
			y[i] = y[i - 1];
		}

		switch (direction) {
			case 'U':
				y[0] = y[0] - UNIT_SIZE;
				break;
			case 'D':
				y[0] = y[0] + UNIT_SIZE;
				break;
			case 'L':
				x[0] = x[0] - UNIT_SIZE;
				break;
			case 'R':
				x[0] = x[0] + UNIT_SIZE;
				break;

			default:
				break;
		}
	}

	public void newApple() {
		appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
		appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;

	}

	public void checkApple() {
		if ((x[0] == appleX) && (y[0] == appleY)) {
			bodyParts++;
			applesEaten++;
			newApple();
		}
	}

	public void checkCollision() {
		// head and body collision check
		for (int i = bodyParts; i > 0; i--) {
			if ((x[0] == x[i]) && (y[0] == y[i])) {
				running = false;
			}
		}
		if (x[0] < 0)
			running = false;
		if (x[0] > SCREEN_WIDTH)
			running = false;
		if (y[0] < 0)
			running = false;
		if (y[0] > SCREEN_HEIGHT)
			running = false;

		if (!running)
			timer.stop();
	}

	public void gameOver(Graphics g) {
		gameOverHandled = true;

		Panel panel = new Panel();
		panel.setLayout(null);
		// panel.setBackground(Color.white);
		panel.setBounds(200, 350, 200, 200);
		System.out.println(panel.getLocation());
		Button restartBtn = new Button("Restart");
		restartBtn.setBounds(50, 10, 100, 40);
		restartBtn.setBackground(Color.red);
		restartBtn.setForeground(Color.black);
		restartBtn.setFont(new Font("Hack", Font.BOLD, 16));
		restartBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				switchToGameMenu();
			}
		});
		Button exitBtn = new Button("Exit");
		exitBtn.setBounds(50, 60, 100, 40);
		exitBtn.setBackground(Color.red);
		exitBtn.setForeground(Color.black);
		exitBtn.setFont(new Font("Hack", Font.BOLD, 16));
		exitBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		panel.add(restartBtn);
		panel.add(exitBtn);
		this.add(panel);
		// this.validate();
		// game over text display
		g.setFont(new Font("Hack", Font.BOLD, 75));
		g.setColor(Color.red);
		FontMetrics metrics = getFontMetrics(g.getFont());
		g.drawString("WASTED", (SCREEN_WIDTH - metrics.stringWidth("WASTED")) / 2, SCREEN_HEIGHT / 2);
		// game over score display
		g.setFont(new Font("Hack", Font.BOLD, 25));
		g.setColor(Color.red);
		FontMetrics metrics2 = getFontMetrics(g.getFont());
		g.drawString("Score: " + applesEaten,
				(SCREEN_WIDTH - metrics2.stringWidth("Score: " + applesEaten)) / 2,
				g.getFont().getSize());
	}

	public void switchToGameMenu() {
		Container parent = this.getParent();
		parent.remove(this);
		parent.add(new GameMenu());
		parent.revalidate();
		parent.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (running) {
			move();
			checkApple();
			checkCollision();
		}
		repaint();
	}

	public class MyKeyAdapter extends KeyAdapter {
		@Override
		public void keyPressed(KeyEvent e) {
			switch (e.getKeyCode()) {
				case KeyEvent.VK_LEFT:
					if (direction != 'R')
						direction = 'L';
					break;
				case KeyEvent.VK_RIGHT:
					if (direction != 'L')
						direction = 'R';
					break;
				case KeyEvent.VK_UP:
					if (direction != 'D')
						direction = 'U';
					break;
				case KeyEvent.VK_DOWN:
					if (direction != 'U')
						direction = 'D';
					break;
				case KeyEvent.VK_A:
					if (direction != 'R')
						direction = 'L';
					break;
				case KeyEvent.VK_D:
					if (direction != 'L')
						direction = 'R';
					break;
				case KeyEvent.VK_W:
					if (direction != 'D')
						direction = 'U';
					break;
				case KeyEvent.VK_S:
					if (direction != 'U')
						direction = 'D';
					break;

				default:
					break;
			}
		}
	}

}
