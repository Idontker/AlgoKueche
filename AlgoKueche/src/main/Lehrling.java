package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Lehrling {
    private String aktZutat;
    private ArrayList<String> zutatenInTopf;
    protected Kunde kunde;
    protected GUI animation;
    private int wuerze;
    private boolean bearbeitet;
    private boolean inTopf;

    public Lehrling() {
        aktZutat = "leer";
        kunde = new Kunde();
        animation = GUI.startGUI();
        wuerze = -1;
        bearbeitet = false;
        inTopf = false;
    }

    public void wirKochenJetzt(String rezept) {
        kunde.rezeptauswahl(rezept);
        animation.goToFrame("wirKochenJetzt");
    }

    public void nimmAusSchrank(String zutat) {
        if (!aktZutat.isEmpty()) {
            kunde.meldeFehler(Comment.mehrAlsEineZutatInDerHand);
        }
        aktZutat = entscheideZutat(zutat);
        animation.goToFrame("nimmAusSchrank");
    }

    public void stellZurueck() {
        if (bearbeitet) {
            kunde.meldeFehler(Comment.verschwendung);
        }
        inTopf = false; // oel darf in den Topf gegben werden, vor dem Braten.
        aktZutat = "";
        animation.goToFrame("stellZurueck");
    }

    public void schneide() {
        bearbeitet = true;
        aktZutat = aktZutat + "geschnitten,";
        animation.goToFrame("schneide");
    }

    public void gebeInTopf() {
        inTopf = true;
        zutatenInTopf.add(aktZutat);
        if (aktZutat.equals("oel(")) {
            kunde.arbeitsschritt("topfGeoelt()"); //ist eher schlecht fuer den Kunden, da keine reihenfolge geprueft wird
        }
        animation.goToFrame("gebeInTopf");
    }

    public void koche(int zeit) {
        if (!aktZutat.isEmpty() && inTopf) {
            bearbeitet = true;
            aktZutat = aktZutat + "gekocht" + zeit + ",";
            animation.goToFrame("koche");
        } else {
            kunde.meldeFehler(Comment.kochtLeerenTopf);
        }
    }

    public void gebeAufTeller() {
        if (aktZutat.endsWith(",")) {
            aktZutat = aktZutat.substring(0, aktZutat.length() - 1);
        }
        kunde.arbeitsschritt(aktZutat + ")");
        animation.goToFrame("gebeAufTeller");

        if (istKeinGewuerz(aktZutat)) {
            aktZutat = "";
        }
    }

    public boolean istGewuerzt() {
        if (wuerze > 0) {
            wuerze--;
            animation.goToFrame("istGewuerztFalse");
            return false;
        } else if (wuerze == 0) {
            wuerze--;
            kunde.arbeitsschritt("istGewuerzt"); //setze bitte den boolean in Kunde
            animation.goToFrame("istGewuerztTrue");
            return true;
        } else { // wuerze < 0
            wuerze = (int) Math.random() * 3;
            return istGewuerzt();
        }
    }

    public void serviere() {
        animation.goToFrame("serviere");
        animation.goToFeedback(kunde.bewerte());
    }

    private boolean istKeinGewuerz(String zutat) {
        switch (zutat) {
            case "oel(":
            case "essig(":
            case "salz(":
                return false;
            default:
                return true;
        }
    }

    /*
     * entfernt gross-/kleinschreibung. hängt ein "(" an das Ende bekannte zutaten:
     * salat, oel, zwiebel, gurke, oliven, feta, salz, essig
     */
    private String entscheideZutat(String eingabe) {
        String artikel[] = new String[] { "ein", "eine", "einen", "der", "die", "das" };
        String zutat = "";

        eingabe = eingabe.trim().toLowerCase();
        String str[] = eingabe.split(" ");

        if (str.length == 2 && Arrays.asList(artikel).contains(str[0])) {
            zutat = str[1];
        } else if (str.length == 1) {
            zutat = str[0];
        }

        switch (zutat) {
            
            case "essig":
            case "feta":
            case "salz":
                // zutat = zutat
                break;
            case "gurke":
            case "gurken":
                zutat = "gurke";
                break;
            case "öl":
            case "oel":
                zutat = "oel";
                break;
            case "oliven":
            case "olive":
                zutat = "olive";
                break;
            case "salat":
            case "salate":
            case "salatkopf":
                zutat = "salat";
                break;
            case "tomate":
            case "tomaten":
                zutat = "tomate";
                break;
            case "zwiebel":
            case "zwiebeln":
                zutat = "zwiebel";
                break;
            default:
                zutat = null;
                break;
        }
        if (zutat != null) {
            return zutat + "(";
        } else {
            return "badf00d(";
        }
    }
}
