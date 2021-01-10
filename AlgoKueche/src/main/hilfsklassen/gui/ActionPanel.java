package main.hilfsklassen.gui;

import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.Image;

public class ActionPanel extends JPanel {

    private static final long serialVersionUID = 3L;

    private JLabel imageLabel;

    public ActionPanel(int w, int h) {
        imageLabel = new JLabel();
		imageLabel.setVisible(false);

		this.setBackground(MainFrame.DEFAULT_COLOR);
		this.setPreferredSize(new Dimension(w, h));
		
		this.add(imageLabel);
		this.setVisible(true);
    }

    public void showSlide(Slide next) {
		JLabel tmp = null;
		if (next.image != null) { // create new Label with ImageIcon
			ImageIcon icon = new ImageIcon(
					next.image.getScaledInstance(this.getWidth(), this.getHeight(), Image.SCALE_SMOOTH));
			tmp = new JLabel(icon);
			tmp.setVisible(true);
		}
		if (imageLabel != null) { // remove old Label with ImageIcon
			this.remove(imageLabel);
		}
		if (tmp != null) { // add new Label with ImageIcon
			this.add(tmp);
			imageLabel = tmp;
		}
		// update Background and comment
		this.setBackground(next.c);
	}
}
