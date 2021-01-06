package main;

import java.util.ArrayList;
import java.util.Arrays;

public class Lehrling {
    private String aktZutat;
    private ArrayList<String> zutatenInTopf;
    protected Kunde kunde;
    protected GUI animation;
    private int aktWuerze;
    private boolean bearbeitet;
    private boolean inTopf;
    private boolean serviert;

    public Lehrling() {
        animation = GUI.startGUI();
        kunde = new Kunde(); 
    }

    public void wirKochenJetzt(String rezept) {
        //init
        serviert = false;
        aktZutat = "";
        aktWuerze = 42;
        bearbeitet = false;
        inTopf = false;
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
        if(aktZutat.isEmpty()){
            kunde.meldeFehler(Comment.schneidenOhneZutat);
        }
        else{
            bearbeitet = true;
            aktZutat = aktZutat + "geschnitten,";
            animation.goToFrame("schneide");
        }
        
    }

    public void gibInTopf() {
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

    public void gibAufTeller() {
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
        if (aktWuerze == 0) {
            kunde.setzeGewuerzt(true); //setze bitte den boolean in Kunde
            animation.goToFrame("istGewuerztTrue");
            return true;
        } 
        if(aktWuerze > 0){
            animation.goToFrame("istGewuerztFalse");
            kunde.setzeGewuerzt(false);
            return false;
        }
        if(aktWuerze < 0){
            animation.goToFrame("istGewuerztFalse");
            kunde.setzeGewuerzt(false);
            kunde.meldeFehler(Comment.versalzen);
            return true;
        }
        return true;
        
    
    }

    public void wuerze(){
        if(aktWuerze == 42){
            aktWuerze = (int) (Math.random() * 3) + 1;
        }
        aktWuerze--; 
    }

    public void serviere() {
        if(!serviert){
            animation.goToFrame("serviere");
        animation.goToFeedback(kunde.bewerte());
        }
        else{System.out.println("Das Gericht wurde bereits serviert.");}
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
            kunde.meldeFehler(Comment.zutatUnbekannt);
            return "badf00d(";
        }
    }
}
