package hilfsklassen.gui;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;

public class CommentPanel extends JPanel {

    private static final long serialVersionUID = 13L;

    private JLabel commentBox;

    public CommentPanel(int w, int h) {
        this.setBackground(Color.white);
        this.setPreferredSize(new Dimension(w, h));

        commentBox = new JLabel("");
        this.add(commentBox);
        this.setVisible(true);
    }

    public void setText(String text) {
        commentBox.setText(text);
    }

    public void showComment(Slide next, String note) {
        String str = buildOutputString(next, note);

        commentBox.setText(str);
        // TODO: add commit aus 22.01
        if (next.getMethod().equals("gebeAufTeller") || next.getMethod().equals("gibInTopf")) {
            System.out.println(str);
        }
    }

    private String buildOutputString(Slide next, String note) {
        String str = next.getComment();

        // remove annotitations on "how was it prepeared"
        if (next.moreInfo()) {
            if (note.contains("(")) {
                note = note.split("\\(")[0];
            }
            // first Letter to uppercase
            note = note.substring(0, 1).toUpperCase() + note.substring(1);
            str += "     \t" + note;
        }
        return str;
    }
}
