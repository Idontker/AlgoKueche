package hilfsklassen.gui;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;

import java.awt.Component;
import java.awt.Color;

import java.io.File;

public class MainFrame extends JFrame {
    
    private static final long serialVersionUID = 2L;
    public static String pathToAlgoKueche = new File("").getAbsolutePath() + "/hilfsklassen/";
    
	public static final Color DEFAULT_COLOR = Color.lightGray;


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
