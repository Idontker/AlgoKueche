package main;

public class Lehrling {
    private String aktZutat;
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
        if (aktZutat.equals("oel(")) {
            kunde.arbeitsschritt("topfGeoelt");
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
            aktZutat = aktZutat.substring(0, aktZutat.length() - 2);
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
            kunde.arbeitsschritt("istGewuerzt");
            animation.goToFrame("istGewuerztTrue");
            return true;
        } else { // wuerze < 0
            wuerze = (int) Math.random() * 3;
            return istGewuerzt();
        }
    }

    public void serviere() {
        kunde.bewerte();
        animation.goToFrame("serviere");
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
     * salat, oel, zwiebel, gurke Oliven, Feta, Salz, Essig
     */
    private String entscheideZutat(String eingabe) {
        String zutat = eingabe;

        return zutat + "(";
    }
}
