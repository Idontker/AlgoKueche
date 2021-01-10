package main.hilfsklassen.gui;

import java.awt.Color;
import java.util.HashMap;

public class SlideMap extends HashMap<String, Slide> {

    private static final long serialVersionUID = 42L;

    public SlideMap() {
        String[] methods = new String[] { "welcome", "wirKochenJetzt", "nimmAusSchrank", "stellZurueck", "schneide", "gibInTopf",
                "koche", "serviere", "reactionHappy", "reactionSad", "gebeAufTeller" };
        initSlides();
        for (String s : methods) {
            if (!this.containsKey(s)) {
                System.err.println("Missing a slide for the action:" + s);
            } else {
                Slide slide = this.get(s);
                if (slide.image == null) {
                    System.err.println("Missing a image for the slide:" + s);
                }
            }
        }
    }

    private void initSlides() {
        this.put("welcome", new Slide("welcome", Color.cyan, "kueche.png", "Willkommen in der AlgoKueche!"));
        this.put("wirKochenJetzt", new Slide("wirKochenJetzt", Color.blue, "book.png", "Sam ist bereit!"));
        this.put("nimmAusSchrank", new Slide("nimmAusSchrank", Color.cyan, "fridge.png", "Sam hat aus dem Schrank genommen:",true));
        this.put("stellZurueck", new Slide("stellZurueck", Color.gray, "fridge.png", "Sam hat zurueck gestellt:",true));
        this.put("schneide", new Slide("schneide", Color.green, "schneiden.jpg", "Sam hat geschnitten:",true));
        this.put("gibInTopf", new Slide("gibInTopf", Color.magenta, "topf.png", "Sam hat in den Topf geworfen:",true));
        this.put("koche", new Slide("koche", Color.orange, "kochen.jpg", "Sam hat den Topf kochen lassen:",true));
        this.put("serviere", new Slide("serviere", Color.yellow, "glocke.jpg", "Sam hat das Gericht serviert!"));
        this.put("reactionHappy", new Slide("reactionHappy", new Color(50, 200, 10), "happy.png", "Der Kunde ist zufrieden!"));
        this.put("reactionSad", new Slide("reactionSad", new Color(200, 100, 50), "sad.png", "Der Kunde ist unzufrieden!"));
        this.put("gebeAufTeller", new Slide("gebeAufTeller", Color.pink, "teller.png", "Sam hat folgendes dem Teller beigefuegt:",true));
        this.put("istGewuerztTrue", new Slide("istGewuerztTrue", Color.green, "abschmecken.png", "Sam hat abgeschmeckt: Es schmeckt lecker!"));
        this.put("istGewuerztFalse", new Slide("istGewuerztFalse", Color.red, "abschmecken.png", "Sam hat abgeschmeckt: Es schmeckt nicht gut!"));
    }

}