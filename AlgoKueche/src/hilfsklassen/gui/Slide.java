package hilfsklassen.gui;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;

public class Slide {
	String method;
	Color c;
	BufferedImage image;
	String comment;

	public Slide(String method, Color c) {
		this.method = method;
		this.c = c;
		this.comment = method;
	}

	public Slide(String method, Color c, String comment) {
		this(method, c);
		this.comment = comment;
	}

	public Slide(String method, Color c, BufferedImage image) {
		this(method, c);
		this.image = image;
	}

	public Slide(String method, Color c, String imageName, String comment) {
		this(method, c, comment);
		String pathToImage = MainFrame.pathToAlgoKueche + "res/" + imageName;
		try {
			File f = new File(pathToImage);
			image = ImageIO.read(f);
		} catch (Exception e) {
			System.err.println("[ERROR]:Failed loading Image for\t" + method + "\t<" + pathToImage + ">");
			e.printStackTrace();
			image = null;
		}
	}

	public String getComment(){
		return comment;
	}

	@Override
	public String toString() {
		return "[name=" + method + ", comment=" + comment + ", color=(" + c.getRed() + ", " + c.getGreen() + ", "
				+ c.getBlue() + ") ]";
	}
}