package hilfsklassen.gui;

import java.awt.Color;
import java.util.HashMap;

public class SlideMap extends HashMap<String, Slide> {

    private static final long serialVersionUID = 42L;

    public SlideMap() {
        String[] methods = new String[] { "welcome", "wirKochenJetzt", "nimmAusSchrank", "stellZutatZurueck", "schneide", "gibInTopf",
                "koche", "serviere", "reactionHappy", "reactionSad", "gebeAufTeller", "gutGewuerzt", "schlechtGewuerzt", "alert", "sumoHungrig", "sumoSatt" };
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
        this.put(       "welcome",             
            new Slide(  "welcome",            Color.cyan,     "Albert.png",           "Willkommen in der AlgoKueche!"));
        this.put(       "wirKochenJetzt",      
            new Slide(  "wirKochenJetzt",     Color.blue,     "sam.png",              "Sam ist bereit!"));
        this.put(       "nimmAusSchrank",      
            new Slide(  "nimmAusSchrank",     Color.cyan,     "nimmAusSchrank.png",   "Sam hat aus dem Schrank genommen:",true));
        this.put(       "stellZutatZurueck",   
            new Slide(  "stellZutatZurueck",  Color.gray,     "nimmAusSchrank.png",   "Sam hat zurueck gestellt:",true));
        this.put(       "schneide",            
            new Slide(  "schneide",           Color.green,    "schneideSalat.png",    "Sam hat geschnitten:",true));
        this.put(       "gibInTopf",           
            new Slide(  "gibInTopf",          Color.magenta,  "gibInTopf.png",        "Sam hat in den Topf geworfen:",true));
        this.put(       "koche",               
            new Slide(  "koche",              Color.orange,   "koche.png",           "Sam hat den Topf kochen lassen:",true));
        this.put(       "serviere",            
            new Slide(  "serviere",           Color.yellow,   "serviere.png",         "Sam hat das Gericht serviert!"));
        this.put(       "reactionHappy",       
            new Slide(  "reactionHappy",      Color.green,    "happy.png",            "Der Kunde ist zufrieden!",true));
        this.put(       "reactionSad",         
            new Slide(  "reactionSad",        Color.red,      "sad.png",              "Der Kunde ist unzufrieden!",true));
        this.put(       "gebeAufTeller",       
            new Slide(  "gebeAufTeller",      Color.pink,     "gibZutatAufTeller.png","Sam hat folgendes dem Teller beigefuegt:",true));
        this.put(       "gutGewuerzt",     
            new Slide(  "gutGewuerzt",        Color.green,    "gutGewuerzt.png",      "Sam hat abgeschmeckt: Es schmeckt lecker!"));
        this.put(       "schlechtGewuerzt",    
            new Slide(  "schlechtGewuerzt",   Color.red,      "schlechtGewuerzt.png", "Sam hat abgeschmeckt: Es schmeckt nicht gut!"));
        this.put(       "alert",               
            new Slide(  "alert",              Color.DARK_GRAY, "alert.png",           "Ein Fehler ist aufgetreten: ", true));
        this.put(       "sumoHungrig",             
            new Slide(  "sumoHungrig",        Color.red,       "sumoHungrig.png",       "Der Sumoringer ist noch hungrig."));
        this.put(       "sumoSatt",             
            new Slide(  "sumoSatt",           Color.green,     "sumoSatt.png",        "Der Sumoringer ist endlich satt."));
    }

}