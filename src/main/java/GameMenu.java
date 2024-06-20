import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

public class GameMenu extends Panel implements ActionListener {
	static final int SCREEN_WIDTH = 600;
	static final int SCREEN_HEIGHT = 600;

	GameMenu() {
		this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
		this.setBackground(Color.black);
		this.setFocusable(true);
		System.out.println("exe");
		Label label = new Label("TEST");
		Button btn = new Button("TEST");
		this.add(btn);
		this.add(label);
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
	}

}
