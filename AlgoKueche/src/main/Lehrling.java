package main;

public class Lehrling {
    private String aktZutat;
    private Feedback kunde;
    private GUI animation;
    private int wuerze;

    public Lehrling(){
        aktZutat = "leer";
        kunde = new Feedback();
        animation = GUI.startGUI();
        wuerze = -1;
    }

    public void wirKochenJetzt(String rezept){
        kunde.rezeptauswahl(rezept);
        animation.goToFrame("wirKochenJetzt");
    }

    public void nimmAusSchrank(String zutat) {
        aktZutat = zutat;
        kunde.arbeitschritt("nimmAusSchrank" + zutat);
        animation.goToFrame("nimmAusSchrank");
    }

    public void stellZurueck() {
        aktZutat = "leer";
        kunde.arbeitschritt("stellZurueck");
        animation.goToFrame("stellZurueck");
    }

    public void schneide() {
        kunde.arbeitschritt("schneide" + aktZutat);
        animation.goToFrame("schneide");
    }

    public void gebeInTopf() {
        kunde.arbeitschritt("gebeInTopf" + aktZutat);
        animation.goToFrame("gebeInTopf");
    }

    public void koche(int zeit) {
        kunde.arbeitschritt("koche" + zeit);
        animation.goToFrame("koche");
    }

    public boolean istGewuerzt() {
        if(wuerze > 0) {
            wuerze--;
            kunde.arbeitschritt("istGewuerztFalse");
            animation.goToFrame("istGewuerztFalse");
            return false;
        } else if(wuerze == 0) {
            wuerze--;
            kunde.arbeitschritt("istGewuerztTrue");
            animation.goToFrame("istGewuerztTrue");
            return true;
        } else { // if(wuerze < 0){
            wuerze = (int)Math.random()*3;
            return istGewuerzt();
        }
    }

    public void legAufdenTeller() {
        aktZutat = "leer";
        kunde.arbeitschritt("legAufdenTeller");
        animation.goToFrame("legAufdenTeller");
    }

    public void serviere(){
        kunde.bewerte();
        animation.goToFrame("serviere");
    }
}
