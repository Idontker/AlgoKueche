import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.util.HashMap;

public class GUI {

	// static fields
	private final static String[] methods = new String[] { "wirKochenJetzt", "nimmAusSchrank", "stellZurueck",
			"schneide", "wirfInTopf", "koche", "istGewuerzt", "serviere", "reactionHappy", "reactionSad" };
	private final static Color[] colors = new Color[] { Color.blue, Color.cyan, Color.gray, Color.green, Color.magenta,
			Color.orange, Color.red, Color.yellow, new Color(50, 200, 10), new Color(200, 100, 50) };

	// static values
	private static final double SCALE = 0.5;
	private static final int HEIGHT = (int) (SCALE * 720);
	private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);

	private static final Color DEFAULT_COLOR = Color.lightGray;
	// GUI Main
	public static void main(String args[]) {
		GUI g = new GUI();
	}

	// GUI attributes
	private JFrame frame;
	private JPanel canvas;
	private JPanel actionPanel;

	private HashMap<String, Slide> map;

	// Buttons for testing
	private JPanel testPanel;
	private JButton left; // go to the previous slide of the slide-list
	private JButton unknown; // show the slide for unknown inputs
	private JButton right; // go to the next slide of the slide-list
	private int currentSlideIdx; // idx of the current or last valid slide

	public GUI() {
		initFrame();
		initCanvas();

		int actionHeight = (int) (HEIGHT * 0.7);
		//int commentHeight = (int) (height * 0.2);
		int testHeight = (int) (HEIGHT * 0.1);
		
		initActionPanel(actionHeight);
		initTestGUI(testHeight);

		canvas.add(actionPanel);
		canvas.add(testPanel);

		frame.add(canvas);
		frame.setVisible(true);

		initMap();

		goToFrame(methods[currentSlideIdx]);
	}

	// Init Methods
	private void initMap() {
		map = new HashMap<String, Slide>();
		for (int i = 0; i < methods.length; i++) {
			map.put(methods[i], new Slide(methods[i], colors[i]));
		}
	}

	private void initFrame() {
		frame = new JFrame("Hallo Welt");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
	}

	private void initCanvas() {
		canvas = new JPanel();
		canvas.setVisible(true);
		canvas.setBackground(Color.white);

		canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
	}

	private void initActionPanel(int h) {
		actionPanel = new JPanel();
		actionPanel.setVisible(true);
		actionPanel.setBackground(Color.red);
		actionPanel.setPreferredSize(new Dimension(WIDTH, h));
	}

	private void initTestGUI(int h) {
		testPanel = new JPanel();
		testPanel.setVisible(true);
		testPanel.setBackground(Color.lightGray);

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
				System.out.println("unknow");
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

	public void setComment() {
		// TODO: implement
	}

	// TODO: rename method
	public void goToFrame(String slideName) {
		Slide next = map.get(slideName);
		if (next != null) {
			System.out.println(next);
			actionPanel.setBackground(next.c);
		} else {
			System.out.println("Slide: " + slideName + " not found in Database");
			actionPanel.setBackground(Color.black);

		}
	}

	// methods for testing.

	private void slideShow() {
		for (String key : methods) {
			goToFrame(key);
			try {
				Thread.sleep(800);
			} catch (Exception e) {
			}
		}
		goToFrame("NaN");
		try {
			Thread.sleep(500);
		} catch (Exception e) {
		}
	}
}

class Slide {
	String method;
	Color c;

	Slide(String method, Color c) {
		this.method = method;
		this.c = c;
	}

	@Override
	public String toString() {
		return "[name=" + method + ", color= (" + c.getRed() + ", " + c.getGreen() + ", " + c.getBlue() + ") ]";
	}
}
