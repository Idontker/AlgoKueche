package gui;

import main.Feedback;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
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
	private static boolean notActive;
	private JFrame frame;

	private JPanel canvas;
	private JPanel actionPanel;
	private JPanel commentPanel;

	private JLabel imageLabel;
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
		initFrame();
		initCanvas();

		int actionHeight = (int) (HEIGHT * 0.8);
		int commentHeight = (int) (HEIGHT * 0.1);
		int testHeight = (int) (HEIGHT * 0.1);

		initActionPanel(actionHeight);
		initCommentPanel(commentHeight);
		if (testing) {
			initTestGUI(testHeight);
		}

		canvas.add(actionPanel);
		canvas.add(commentPanel);
		if (testing) {
			canvas.add(testPanel);
		}

		frame.add(canvas);
		frame.setVisible(true);

		map = new SlideMap();
	}

	// Init Methods

	private void initFrame() {
		frame = new JFrame("AlgoKueche");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
	}

	private void initCanvas() {
		canvas = new JPanel();
		canvas.setVisible(true);
		canvas.setBackground(DEFAULT_COLOR);

		canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
	}

	private void initActionPanel(int h) {
		imageLabel = new JLabel();
		imageLabel.setVisible(false);

		actionPanel = new JPanel();
		actionPanel.setVisible(true);
		actionPanel.setBackground(Color.red);
		actionPanel.setPreferredSize(new Dimension(WIDTH, h));

		actionPanel.add(imageLabel);
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
		JLabel tmp = null;
		if (next.image != null) { // create new Label with ImageIcon
			ImageIcon icon = new ImageIcon(
					next.image.getScaledInstance(actionPanel.getWidth(), actionPanel.getHeight(), Image.SCALE_SMOOTH));
			tmp = new JLabel(icon);
			tmp.setVisible(true);
		}
		if (imageLabel != null) { // remove old Label with ImageIcon
			actionPanel.remove(imageLabel);
		}
		if (tmp != null) { // add new Label with ImageIcon
			actionPanel.add(tmp);
			imageLabel = tmp;
		}
		// update Background and comment
		actionPanel.setBackground(next.c);
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