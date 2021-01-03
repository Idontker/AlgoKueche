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
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;

public class GUI {

	// static fields
	private final static String[] methods = new String[] { "wirKochenJetzt", "nimmAusSchrank", "stellZurueck",
			"schneide", "wirfInTopf", "koche", "istGewuerzt", "serviere", "reactionHappy", "reactionSad" };
	private final static Color[] colors = new Color[] { Color.blue, Color.cyan, Color.gray, Color.green, Color.magenta,
			Color.orange, Color.red, Color.yellow, new Color(50, 200, 10), new Color(200, 100, 50) };
	private final static String[] imageNames = new String[] { "book.png", "fridge.png", "fridge.png", "schneiden.jpg",
			"topf.png", "kochen.jpg", "abschmecken.png", "glocke.jpg", "happy.png", "sad.png" };
	private final static Slide BADF00D = new Slide("badf00d", Color.black);

	// static values
	private static final double SCALE = 0.9;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);

	private static final Color DEFAULT_COLOR = Color.lightGray;

	// GUI Main
	public static void main(String args[]) {
		startTestGUI();
		// GUI g = startGUI();
		// g.slideShow();
	}

	// GUI attributes
	private JFrame frame;
	private JPanel canvas;
	private JPanel actionPanel;
	private JPanel commentPanel;

	private JLabel imageLabel;
	private JLabel commentBox;

	private HashMap<String, Slide> map;
	private LinkedBlockingQueue<Slide> queuedImages;
	private SlideThread slideThread;

	private JLabel loadingBar;
	private JLabel whitespace;

	// Buttons for testing
	private JPanel testPanel;
	private JButton left; // go to the previous slide of the slide-list
	private JButton unknown; // show the slide for unknown inputs
	private JButton right; // go to the next slide of the slide-list
	private int currentSlideIdx; // idx of the current or last valid slide

	public static GUI startGUI() {
		return new GUI(false);
	}

	public static GUI startTestGUI() {
		return new GUI(true);
	}

	private GUI(boolean testing) {
		initFrame();
		initCanvas();

		int actionHeight = (int) (HEIGHT * 0.8);
		int commentHeight = (int) (HEIGHT * 0.1);
		int testHeight = (int) (HEIGHT * 0.1);

		queuedImages = new LinkedBlockingQueue<Slide>();
		slideThread = new SlideThread(3000);
		slideThread.start();

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

		goToFrame(methods[currentSlideIdx]);
	}

	// Init Methods
	private void initMap() {
		map = new HashMap<String, Slide>();

		for (int i = 0; i < methods.length; i++) {
			String pathToImage = "AlgoKueche/res/" + imageNames[i];
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
		commentPanel.setSize(new Dimension(WIDTH, h));

		commentBox = new JLabel("Willkommen in der AlgoKueche");
		commentPanel.add(commentBox);

		try {
			File f = new File("AlgoKueche/res/w.png");
			BufferedImage w = ImageIO.read(f);
			whitespace = new JLabel(new ImageIcon(w.getScaledInstance((int) (0.3 * WIDTH), h, Image.SCALE_SMOOTH)));
			whitespace.setSize((int) (0.3 * WIDTH), h);
			whitespace.setVisible(true);
			commentPanel.add(whitespace);
		} catch (Exception e) {
		}

		ImageIcon gif = new ImageIcon("AlgoKueche/res/circle-black.gif");
		loadingBar = new JLabel(gif);

		loadingBar.setVisible(true);
		loadingBar.setSize(h, h);
		commentPanel.add(loadingBar);
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
		// TODO: implement the (random) occurences of useless and funny comments of Sam
	}

	// TODO: rename method
	public void goToFrame(String slideName) {
		Slide next = map.get(slideName);
		if (next == null) {
			System.out.println("Slide: " + slideName + " not found in Database");
			next = BADF00D;
		}
		try {
			queuedImages.put(next);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

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
		commentBox.setText(next.comment);
	}

	class SlideThread extends Thread {
		private int sleepTime;

		SlideThread(int sleepTime) {
			this.sleepTime = sleepTime;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Slide next = queuedImages.take();
					showSlide(next);
					loadingBar.setVisible(true);
					Thread.sleep(sleepTime);
					// actionPanel.remove(loadingBar);
					loadingBar.setVisible(false);
					Thread.sleep(200);
					System.out.println("sleep done");
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}

class Slide {
	String method;
	String comment;
	Color c;
	BufferedImage image;

	Slide(String method, Color c) {
		this.method = method;
		this.c = c;
		this.comment = method;
	}

	Slide(String method, Color c, BufferedImage image) {
		this.method = method;
		this.c = c;
		this.image = image;
		this.comment = method;
	}

	@Override
	public String toString() {
		return "[name=" + method + ", color=(" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ") ]";
	}
}
