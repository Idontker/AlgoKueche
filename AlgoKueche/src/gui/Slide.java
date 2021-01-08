package gui;

import java.awt.Color;
import java.awt.image.BufferedImage;


public class Slide {
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
