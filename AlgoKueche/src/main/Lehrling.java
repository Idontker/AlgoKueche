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
        aktZutat = entscheideZutat(zutat);
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
        } else { // wuerze < 0
            wuerze = (int)Math.random()*3;
            return istGewuerzt();
        }
    }

    public void legAufTeller() {
        aktZutat = "leer";
        kunde.arbeitschritt("legAufTeller");
        animation.goToFrame("legAufTeller");
    }

    public void serviere(){
        kunde.bewerte();
        animation.goToFrame("serviere");
    }

    /*
    entfernt gross-/kleinschreibung.
    bekannte zutaten:
        salat, oel, zwiebel, gurke Oliven, Feta, Salz
    */
    private String entscheideZutat(String zutatAlt) {
        String zutat = "";
        switch (zutatAlt) {//"Zwiebel", "Gruke", "Oliven", "Feta"
            case "salat":
            case "Salat":
            case "salatkopf":
            case "Salatkopf":
            case "einen salat":
            case "einen Salat":
                zutat = "salat";
                break;
            case "oel":
            case "Oel":
                zutat = "oel";
                break;
            case "zwiebel":
            case "Zwiebel":
                zutat = "zwiebel";
                break;
            case "gurke":
            case "Gurke":
                zutat = "gurke";
                break;
            case "oliven":
            case "Oliven":
                zutat = "oliven";
                break;
            case "feta":
            case "Feta":
                zutat = "feta";
                break;
            case "salz":
            case "Salz":
                zutat = "salz";
                break;

            default:
                zutat = "unbekannt";
        }
        return zutat;
    }
}
