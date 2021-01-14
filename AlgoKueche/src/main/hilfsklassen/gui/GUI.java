package main.hilfsklassen.gui;

import main.hilfsklassen.cooking.*;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.util.concurrent.CountDownLatch;
 
public class GUI {
	// static values
	private static final double SCALE = 0.8;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);
	private static final int waittingTime = 4000;

	private final static Slide BADF00D = new Slide("badf00d",  Color.black, "Folie nicht gefunden!");

	// GUI Main
	public static void main(String args[]) {
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
	public static boolean runningTestcase = false;

	public static GUI startGUI() {
		if (runningTestcase) {
			return new GUI();
		} else {
			return new GUI(false);
		}
	}
	private GUI() {
		notActive = true;
	}

	private GUI(boolean dummy) {
		map = new SlideMap();

		frame = new MainFrame();

		int actionHeight = (int) (HEIGHT * 0.8);
		int commentHeight = (int) (HEIGHT * 0.1);

		actionPanel = new ActionPanel(GUI.WIDTH, actionHeight);
		commentPanel = new CommentPanel(GUI.WIDTH, commentHeight);

		frame.addToCanvas(actionPanel);
		frame.addToCanvas(commentPanel);

		addInterruptAdapter();

		frame.setVisible(true);
		goToFrame("welcome");
	}

	// TODO: rename method
	public void goToFrame(String slideName) {
		goToFrame(slideName, "");
	}

	public void goToFrame(String slideName, String note) {
		if (notActive)
			return;

		Slide next = map.get(slideName);
		if (next != null) {
			showSlide(next,note);
		} else {
			System.err.println("Slide: " + slideName + " not found in Database");
			showSlide(BADF00D,"");
			commentPanel.setText(BADF00D.getComment());
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
			next = map.get("reactionHappy");
		} else if (k == 2) {
			next = map.get("reactionHappy");
		} else {
			next = BADF00D;
		}

		String feedbackComment =next.getComment() + "        " + f.gibFeedbackString();
		showSlide(next, feedbackComment);
		System.out.println("---------------------------------------------------------");
		System.out.println();
	}

	private void showSlide(Slide next, String note) {
		actionPanel.showSlide(next);
		String str = next.getComment();
		if (next.moreInfo()) {
			str += "     \t" + note;
			commentPanel.setText(next.getComment() + "        " + note);
		} 
		commentPanel.setText(str);
		System.out.println(str);
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

	// methods for testing.
	private void slideShow() {
		for (String key : map.keySet()) {
			goToFrame(key);
		}
		goToFrame("b4df00d");
	}

}