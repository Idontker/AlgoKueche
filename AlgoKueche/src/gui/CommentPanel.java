package gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

class CommentPanel extends JPanel {

    private static final long serialVersionUID = 13L;

    private JLabel commentBox;

    public CommentPanel(int w, int h) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(w, h));

        commentBox = new JLabel("Willkommen in der AlgoKueche");
        this.add(commentBox);
        this.setVisible(true);
    }

    public void setText(String text) {
        commentBox.setText(text);
    }

}
