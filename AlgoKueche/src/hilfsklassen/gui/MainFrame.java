package hilfsklassen.gui;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Color;


public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 2L;
    public static final String pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/";
    
	public static final Color DEFAULT_COLOR = Color.lightGray;

    // static values
    private static final double SCALE = 0.8;
    private static final int HEIGHT = (int) (SCALE * 720);
    private static final int WIDTH = (int) (SCALE * HEIGHT / 9 * 16);


    private JPanel canvas;

    public MainFrame() {
        initFrame();
        initCanvas();
        
        this.add(canvas);
    }

    // Init Methods

    private void initFrame() {
        this.setTitle("AlgoKueche");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
    }

    private void initCanvas() {
        canvas = new JPanel();
        canvas.setVisible(true);
        canvas.setBackground(MainFrame.DEFAULT_COLOR);

        canvas.setLayout(new BoxLayout(canvas, BoxLayout.Y_AXIS));
    }

    public void addToCanvas(Component comp) {
        canvas.add(comp);
    }
}
