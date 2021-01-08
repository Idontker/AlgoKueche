package gui;

import main.Feedback;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.ArrayList;

public class GUI {
	public static final String pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/";

	// static fields
	private final static Slide BADF00D = new Slide("badf00d", Color.black);

	// static values
	private static final double SCALE = 0.8;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);
	private static final int waittingTime = 1500;

	private static final Color DEFAULT_COLOR = Color.lightGray;

	// GUI Main
	public static void main(String args[]) {
		// startTestGUI();
		GUI g = startGUI();
		g.slideShow();
	}

	// GUI attributes
	private boolean notActive;
	private MainFrame frame;
	private ActionPanel actionPanel;

	private JPanel commentPanel;

	private JLabel commentBox;

	private SlideMap map;

	// for testing
	public static boolean runningTestcase = false;
	private JPanel testPanel;
	private JButton left; // go to the previous slide of the slide-list
	private JButton unknown; // show the slide for unknown inputs
	private JButton right; // go to the next slide of the slide-list
	private int currentSlideIdx; // idx of the current or last valid slide

	public static GUI startGUI() {
		if (runningTestcase) {
			return startDummyGUI();
		} else {
			return new GUI(false);
		}
	}

	public static GUI startDummyGUI() {
		return new GUI();
	}

	public static GUI startTestGUI() {
		if (runningTestcase) {
			return startDummyGUI();
		} else {
			return new GUI(true);
		}
	}

	private GUI() {
		notActive = true;
	}

	private GUI(boolean testing) {
		frame = new MainFrame();

		int actionHeight = (int) (HEIGHT * 0.8);
		int commentHeight = (int) (HEIGHT * 0.1);
		int testHeight = (int) (HEIGHT * 0.1);

		actionPanel = new ActionPanel(GUI.WIDTH, actionHeight);

		initCommentPanel(commentHeight);
		if (testing) {
			initTestGUI(testHeight);
		}

		frame.addToCanvas(actionPanel);
		frame.addToCanvas(commentPanel);
		if (testing) {
			frame.addToCanvas(testPanel);
		}

		frame.setVisible(true);

		map = new SlideMap();
	}


	private void initCommentPanel(int h) {
		commentPanel = new JPanel();
		commentPanel.setVisible(true);
		commentPanel.setBackground(Color.white);
		commentPanel.setPreferredSize(new Dimension(WIDTH, h));

		commentBox = new JLabel("Willkommen in der AlgoKueche");
		commentPanel.add(commentBox);
	}

	private void initTestGUI(int h) {
		testPanel = new JPanel();
		testPanel.setVisible(true);
		testPanel.setBackground(DEFAULT_COLOR);

		testPanel.setPreferredSize(new Dimension(WIDTH, h));
		initButtons();
	}

	private void initButtons() {
		currentSlideIdx = 0;

		ArrayList<String> keySet = new ArrayList<String>();
		keySet.addAll(map.keySet());

		left = new JButton("<");
		left.setSize(80, 80);
		left.setVisible(true);
		left.addActionListener(new ActionListener() {
			ArrayList<String> methods = keySet;

			public void actionPerformed(ActionEvent e) {
				currentSlideIdx = (currentSlideIdx + methods.size() - 1) % methods.size();
				System.out.println("links");
				goToFrame(methods.get(currentSlideIdx));
			}
		});

		unknown = new JButton("None");
		unknown.setSize(80, 80);
		unknown.setVisible(true);
		unknown.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("unknown");
				goToFrame("b4df00d");
			}
		});

		right = new JButton(">");
		right.setSize(80, 80);
		right.setVisible(true);
		right.addActionListener(new ActionListener() {
			ArrayList<String> methods = keySet;

			public void actionPerformed(ActionEvent e) {
				currentSlideIdx = (currentSlideIdx + 1) % methods.size();
				System.out.println("rechts");
				goToFrame(methods.get(currentSlideIdx));
			}
		});

		testPanel.add(left);
		testPanel.add(unknown);
		testPanel.add(right);

	}

	// public methods
	public void samSays() {
		if (notActive)
			return;
		// TODO: implement the (random) occurences of useless and funny comments of Sam
	}

	// TODO: rename method
	public void goToFrame(String slideName) {
		if (notActive)
			return;

		Slide next = map.get(slideName);
		if (next != null) {
			showSlide(next);
			commentBox.setText(next.comment);
		} else {
			System.err.println("Slide: " + slideName + " not found in Database");
			showSlide(BADF00D);
			commentBox.setText(BADF00D.comment);
		}
		try {
			Thread.sleep(waittingTime);
		} catch (Exception e) {

		}
	}

	public void goToFeedback(Feedback f) {
		if (notActive)
			return;
		int k = f.bewertungsKategorie();
		Slide next;
		if (k == 0) {
			next = map.get("reactionSad");
		} else if (k == 1) {
			next = map.get("reactionSad");
		} else if (k == 2) {
			next = map.get("reactionHappy");
		} else {
			next = BADF00D;
		}

		showSlide(next);
		commentBox.setText(f.gibFeedbackString());
	}

	private void showSlide(Slide next) {
		actionPanel.showSlide(next);
		System.out.println("show:" + next);
	}

	// methods for testing.
	private void slideShow() {
		for (String key : map.keySet()) {
			goToFrame(key);
		}
		goToFrame("b4df00d");
	}
}