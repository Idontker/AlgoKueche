package main;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.imageio.ImageIO;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.CountDownLatch;

public class GUI {
	public static final String pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/";

	// static fields
	private final static String[] methods = new String[] { "wirKochenJetzt", "nimmAusSchrank", "stellZurueck",
			"schneide", "wirfInTopf", "koche", "istGewuerzt", "serviere", "reactionHappy", "reactionSad",
			"gebeAufTeller", " istGewuerztTrue", "istGewuerztFalse" };
	private final static Color[] colors = new Color[] { Color.blue, Color.cyan, Color.gray, Color.green, Color.magenta,
			Color.orange, Color.red, Color.yellow, new Color(50, 200, 10), new Color(200, 100, 50), Color.pink,
			Color.green, Color.red };
	private final static String[] imageNames = new String[] { "book.png", "fridge.png", "fridge.png", "schneiden.jpg",
			"topf.png", "kochen.jpg", "abschmecken.png", "glocke.jpg", "happy.png", "sad.png", "teller.png",
			"abschmecken.png", "abschmecken.png" };
	private final static Slide BADF00D = new Slide("badf00d", Color.black);

	// static values
	private static final double SCALE = 0.8;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);
	private static final int waittingTime = 1500;
	public static boolean runningTestcase = false;

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

	private HashMap<String, Slide> map;

	private static CountDownLatch countDownLatch;
	private boolean clickAble;

	// Buttons for testing
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

		initMap();
	}

	// Init Methods
	private void initMap() {

		map = new HashMap<String, Slide>();
		for (int i = 0; i < methods.length; i++) {
			String pathToImage = pathToAlgoKueche + "res/" + imageNames[i];
			try {
				File f = new File(pathToImage);
				BufferedImage buf = ImageIO.read(f);

				map.put(methods[i], new Slide(methods[i], colors[i], buf));
			} catch (Exception e) {
				System.err.println("[ERROR]:Failed loading Image for\t" + methods[i] + "\t<" + pathToImage + ">");
				e.printStackTrace();
			}

		}
	}

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

		initInterruptMouseAdapter();
		initInterruptKeyAdapter();
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

		left = new JButton("<");
		left.setSize(80, 80);
		left.setVisible(true);
		left.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentSlideIdx = (currentSlideIdx + methods.length - 1) % methods.length;
				System.out.println("links");
				goToFrame(methods[currentSlideIdx]);
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
			public void actionPerformed(ActionEvent e) {
				currentSlideIdx = (currentSlideIdx + 1) % methods.length;
				System.out.println("rechts");
				goToFrame(methods[currentSlideIdx]);
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

	private static void awaitCountdown(int waitBefore, CountDownLatch countDown) {
		try {
			Thread.sleep(waitBefore);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		countDown.countDown();

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
		// System.out.println("show:" + next);
	}

	// methods for testing.
	private void slideShow() {
		for (String key : methods) {
			goToFrame(key);
		}
		goToFrame("b4df00d");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}

	private void initInterruptMouseAdapter() {
		MouseAdapter adapter = new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (clickAble) {
					GUI.awaitCountdown(50, countDownLatch);
					clickAble = false;
				}
			}
		};
		canvas.addMouseListener(adapter);
	}

	private void initInterruptKeyAdapter() {
		KeyAdapter adapter = new KeyAdapter() {
			public void keyPressed(KeyEvent e) {
				if (clickAble) {
					GUI.awaitCountdown(50, countDownLatch);
					clickAble = false;
				}
			}
		};
		frame.addKeyListener(adapter);
	}
}

class Slide {
	String method;
	Color c;
	BufferedImage image;
	String comment;

	Slide(String method, Color c) {
		this.method = method;
		this.c = c;
		this.comment = method;
	}

	Slide(String method, Color c, String comment) {
		this(method, c);
		this.comment = comment;
	}

	Slide(String method, Color c, BufferedImage image) {
		this(method, c);
		this.image = image;
	}

	@Override
	public String toString() {
		return "[name=" + method + ", comment=" + comment + ", color=(" + c.getRed() + ", " + c.getGreen() + ", "
				+ c.getBlue() + ") ]";
	}
}
