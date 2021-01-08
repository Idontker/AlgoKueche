package gui;

import main.Feedback;
import java.awt.Color;
import java.util.ArrayList;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.concurrent.CountDownLatch;

public class GUI {
	public static final String pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/";

	// static values
	private static final double SCALE = 0.8;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);
	private static final int waittingTime = 1500;

	private final static Slide BADF00D = new Slide("badf00d", Color.black);
	public static final Color DEFAULT_COLOR = Color.lightGray;

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

	private CommentPanel commentPanel;

	private SlideMap map;

	private static CountDownLatch countDownLatch;
	private boolean clickAble;

	// for testing
	private TestPanel testPanel;
	public static boolean runningTestcase = false;

	public static GUI startGUI() {
		if (runningTestcase) {
			return new GUI();
		} else {
			return new GUI(false);
		}
	}

	public static GUI startGUI_TEST() {
		return new GUI(true);
	}

	private GUI() {
		notActive = true;
	}

	private GUI(boolean testing) {
		map = new SlideMap();

		frame = new MainFrame();

		int actionHeight = (int) (HEIGHT * 0.8);
		int commentHeight = (int) (HEIGHT * 0.1);
		int testHeight = (int) (HEIGHT * 0.1);

		actionPanel = new ActionPanel(GUI.WIDTH, actionHeight);
		commentPanel = new CommentPanel(GUI.WIDTH, commentHeight);

		frame.addToCanvas(actionPanel);
		frame.addToCanvas(commentPanel);
		if (testing) {
			initTestEnvoirment(testHeight);
		}

		addInterruptAdapter();

		frame.setVisible(true);
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
			commentPanel.setText(next.comment);
		} else {
			System.err.println("Slide: " + slideName + " not found in Database");
			showSlide(BADF00D);
			commentPanel.setText(BADF00D.comment);
		}

		countDownLatch = new CountDownLatch(1);

		(new Thread() {
			@Override
			public void run() {
				awaitCountdown(waittingTime, countDownLatch);
			}
		}).start();

		clickAble = true;
		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		clickAble = false;
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
		commentPanel.setText(f.gibFeedbackString());
	}

	private void showSlide(Slide next) {
		actionPanel.showSlide(next);
		System.out.println("show:" + next);
	}

	private void addInterruptAdapter() {
		MouseAdapter adapterM = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clickAble) {
					if (e.getButton() == MouseEvent.BUTTON1) {
						clickAble = false;
						GUI.awaitCountdown(50, countDownLatch);
					}
				}
			}
		};
		KeyAdapter adapterK = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (clickAble) {
					if (e.getKeyCode() == KeyEvent.VK_SPACE || e.getKeyCode() == KeyEvent.VK_RIGHT
							|| e.getKeyCode() == KeyEvent.VK_KP_RIGHT) {
						clickAble = false;
						GUI.awaitCountdown(50, countDownLatch);
					}
				}
			}
		};
		frame.addMouseListener(adapterM);
		frame.addKeyListener(adapterK);
	}

	private static void awaitCountdown(int waitBefore, CountDownLatch countDown) {
		try {
			Thread.sleep(waitBefore);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countDown.countDown();

	}

	// init Test
	public void initTestEnvoirment(int testHeight) {
		ArrayList<String> keys = new ArrayList<String>();
		keys.addAll((map.keySet()));

		testPanel = new TestPanel(this, keys.toArray(new String[keys.size()]), GUI.WIDTH, testHeight);
		frame.addToCanvas(testPanel);
	}

	// methods for testing.
	private void slideShow() {
		for (String key : map.keySet()) {
			goToFrame(key);
		}
		goToFrame("b4df00d");
	}

}