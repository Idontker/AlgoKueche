package gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class TestPanel extends JPanel {

    private static final long serialVersionUID = 4L;

    private JButton left; // go to the previous slide of the slide-list
    private JButton unknown; // show the slide for unknown inputs
    private JButton right; // go to the next slide of the slide-list
    private int currentSlideIdx; // idx of the current or last valid slide

    private GUI gui;
    private String[] slideNames;

    public TestPanel(GUI gui, String[] slideNames, int w, int h) {
        this.gui = gui;
        this.slideNames = slideNames;

        this.setBackground(GUI.DEFAULT_COLOR);

        this.setPreferredSize(new Dimension(WIDTH, h));
        initButtons();
        this.setVisible(true);
    }

    private void initButtons() {
        currentSlideIdx = 0;

        left = new JButton("<");
        left.setSize(80, 80);
        left.setVisible(true);
        left.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentSlideIdx = (currentSlideIdx + slideNames.length - 1) % slideNames.length;
                System.out.println("links");
                gui.goToFrame(slideNames[currentSlideIdx]);
            }
        });

        unknown = new JButton("None");
        unknown.setSize(80, 80);
        unknown.setVisible(true);
        unknown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("unknown");
                gui.goToFrame("b4df00d");
            }
        });

        right = new JButton(">");
        right.setSize(80, 80);
        right.setVisible(true);
        right.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                currentSlideIdx = (currentSlideIdx + 1) % slideNames.length;
                System.out.println("rechts");
                gui.goToFrame(slideNames[currentSlideIdx]);
            }
        });

        this.add(left);
        this.add(unknown);
        this.add(right);

    }


}
