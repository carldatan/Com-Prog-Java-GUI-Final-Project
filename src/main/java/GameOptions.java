import java.awt.*;
import java.awt.event.*;

public class GameOptions extends Panel {

	public HighScoreManager highScoreManager;
	CheckboxGroup cg = new CheckboxGroup();
	GameOptions gameOptions;
	GameDifficulty gameDifficulty;

	public GameOptions(HighScoreManager highScoreManager, GameDifficulty gameDifficulty) {
		this.gameDifficulty = gameDifficulty;
		this.highScoreManager = highScoreManager;
		this.setPreferredSize(new Dimension(GameMenu.SCREEN_WIDTH, GameMenu.SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setLayout(null);
		Checkbox easy = new Checkbox("Easy", cg, false);
		Checkbox medium = new Checkbox("Medium", cg, false);
		Checkbox hard = new Checkbox("Hard", cg, false);
		Button menuBtn = new Button("Menu");

		switch (gameDifficulty.getDifficulty()) {
			case "Easy":
				easy.setState(true);
				break;
			case "Medium":
				medium.setState(true);
				break;
			case "Hard":
				hard.setState(true);
				break;

			default:
				easy.setState(true);
				break;
		}

		menuBtn.setBounds(250, GameMenu.SCREEN_HEIGHT - 50, 100, 40);
		menuBtn.setBackground(Color.red);
		menuBtn.setForeground(Color.black);
		menuBtn.setFont(new Font("Hack", Font.BOLD, 16));
		menuBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String selected = cg.getSelectedCheckbox().getLabel();
				gameDifficulty.setDifficulty(selected);
				switchToGameMenu();
			}
		});
		// Set the text color to be red
		easy.setForeground(Color.red);
		medium.setForeground(Color.red);
		hard.setForeground(Color.red);
		easy.setFont(new Font("Hack", Font.BOLD, 25));
		medium.setFont(new Font("Hack", Font.BOLD, 25));
		hard.setFont(new Font("Hack", Font.BOLD, 25));

		// Create a new panel for the checkboxes
		Panel checkboxPanel = new Panel();
		checkboxPanel.setLayout(new GridLayout(3, 1));
		checkboxPanel.setBounds(200, 100, 200, 200);
		checkboxPanel.add(easy);
		checkboxPanel.add(medium);
		checkboxPanel.add(hard);

		this.add(menuBtn); // Add the menu button to the bottom
		this.add(checkboxPanel); // Add the checkbox panel to the center
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.white);
		g.setFont(new Font("Monospace", Font.BOLD, 40));
		g.drawString("Options", 180, 50);

		g.setFont(new Font("Monospaced", Font.BOLD, 30));
		g.setColor(Color.red);
	}

	public void switchToGameMenu() {
		Container parent = this.getParent();
		System.out.println(gameDifficulty.getDifficulty());
		parent.remove(this);
		parent.add(new GameMenu(highScoreManager, gameDifficulty));
		parent.revalidate();
		parent.repaint();
	}

	public String getDifficulty() {
		return cg.getSelectedCheckbox().getLabel();
	}

}
