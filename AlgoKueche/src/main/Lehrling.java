package main;

/**
 * Die Klasse Lehrling
 * @author Karol Bakas, Stefan Gebhart, Silas Kuder
 */
// import java.util.ArrayList;
import java.util.Arrays;

public class Lehrling {

    private String aktZutat;
    private ArrayList<String> zutatenInTopf;
    protected Kunde kunde;
    protected GUI animation;
    private int aktWuerze;
    private boolean bearbeitet;
    //private boolean inTopf;
    private boolean serviert;
    /**
     * Initialisiert einen Lehrling.
     */
    public Lehrling() {
        animation = GUI.startGUI();
        kunde = new Kunde(); 
    }
    
    /**
     * Muss zu Beginn jedes Rezepts aufgerufen werden.
     * @param rezept der Name des Rezepts. Achtet darauf, dass es genauso 
     * geschrieben ist, wie auf dem Arbeitsblatt.
     */
    public void wirKochenJetzt(String rezept) {
        //init
        serviert = false;
        aktZutat = "";
        aktWuerze = 42;
        bearbeitet = false;
        //inTopf = false;
        zutatenInTopf = new ArrayList<String>();
        kunde.rezeptauswahl(rezept);
        animation.goToFrame("wirKochenJetzt");
    }

    /**
     * Nimmt eine Zutat aus dem Schrank in die Hand, als aktuelle Zutat. Achtung: Es kann nur eine Zutat gleichzeitig in der Hand gehalten werden.
     * @param zutat Die Zutat, die genommen werden soll. Gross- Kleinschreibung wird nicht beachtet
     */
    public void nimmAusSchrank(String zutat) {
        if (!aktZutat.isEmpty()) {
            kunde.meldeFehler(Comment.mehrAlsEineZutatInDerHand);
        }
        bearbeitet = false;
        aktZutat = entscheideZutat(zutat);
        animation.goToFrame("nimmAusSchrank");
    }

    /**
     * Stellt die aktuelle Zutat zur�ck. 
     */
    public void stellZurueck() {
        if (bearbeitet) {
            kunde.meldeFehler(Comment.verschwendung);
        }
        //inTopf = false; // oel darf in den Topf gegben werden, vor dem Braten.
        aktZutat = "";
        animation.goToFrame("stellZurueck");
    }

    /**
     * Schneidet die aktuelle Zutat klein, diese ist nun in geschnittener Form die aktuelle Zutat.
     */
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

    /**
     * Gibt die aktuelle Zutat in den Topf. Sie kann jetzt gekocht werden. Der Lehrling hat jetzt wieder die H�nde frei.
     */
    public void gibInTopf() {
        //inTopf = true;
        zutatenInTopf.add(aktZutat);
        aktZutat = "";
        animation.goToFrame("gebeInTopf");
    }

    /**
     * Kocht alle Zutaten im Topf. Der Lehrling ist solange mit Umr�hren beschaftigt.
     * @param zeit Die Kochzeit in Minuten
     */
    public void koche(int zeit) {
        if (zutatenInTopf.size()!=0) {
            for(int i=0;i<zutatenInTopf.size();i++) {
                if(zutatenInTopf.get(i).contains("gekocht")) {
                    int zeitZutat = Integer.parseInt(zutatenInTopf.substring(zutatenInTopf.get(i).indexOf("gekocht")+7));
                    zutatenInTopf.get(i) = zutatenInTopf.get(i).substring(0,zutatenInTopf.get(i).indexOf("gekocht")-1)) + "gekocht" + zeitZutat;
                } else {
                    zutatenInTopf.get(i) = zutatenInTopf.get(i) + "gekocht" + zeit;
                }
            }
            animation.goToFrame("koche");
        } else {
            kunde.meldeFehler(Comment.kochtLeerenTopf);
        }
    }

    /**
     * Platziert den Inhalt des Topfes auf dem Servierteller.
     */
    public void gibTopfAufTeller() {
        for(int i=0;i<zutatenInTopf.size();i++) {
            if(!zutatenInTopf.get(i).contains("gekocht")) {
                 if (zutatenInTopf.get(i).endsWith(",")) {
                    zutatenInTopf.get(i) = zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).length() - 1);
                 }
                kunde.arbeitsschritt(zutatenInTopf.get(i) + ")");
                zutatenInTopf.remove(i);
                i--;
            }
        }
        zutatenInTopf.sort(Comparator.comparing(String::toString));
        String zusammenGekocht="ZusammenGekocht+";
        for(int i=0;i<zutatenInTopf.size();i++) {
            zusammenGekocht+=zutatenInTopf.get(i).substring(0,zutatenInTopf.get(i).indexOf("(")-1)+"+";
        }
        String zusammenGekocht=zusammenGekocht.substring(0,zusammenGekocht.length()-2);
        for(int i=0;i<zutatenInTopf.size();i++) {
            zutatenInTopf.get(i)+=zusammenGekocht+")";
            kunde.arbeitsschritt(zutatenInTopf.get(i));
        }
        animation.goToFrame("gebeAufTeller");
    }

    /**
     * Platziert die Aktuelle Zutat auf dem Servierteller.
     */
    public void gibZutatAufTeller() {
        if (aktZutat.endsWith(",")) {
            aktZutat = aktZutat.substring(0, aktZutat.length() - 1);
        }
        if(aktZutat.length!=0) {
        kunde.arbeitsschritt(aktZutat + ")");
        }
        animation.goToFrame("gebeAufTeller");

        if (wirdNichtVerbraucht(aktZutat)) {
            aktZutat = "";
        }
    }

    /**
     * Probiert, ob das Gericht bereits ausreichend gewuerzt ist.
     * @return false, wenn noch nicht genug gewuerzt wurde und 
     * true, sobald genug gewuerzt wurde (oder bereits zu viel)
     */
    public boolean istGewuerzt() {
        if (aktWuerze == 0) {
            kunde.setzeGewuerzt(true); 
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

    /**
     * Der Lehrling wuerzt das Gericht etwas. Muss eventuell mehrfach aufgerufen werden.
     */
    public void wuerze(){
        if(aktWuerze == 42){
            aktWuerze = (int) (Math.random() * 3) + 1;
        }
        aktWuerze--; 
    }

    /**
     * Serviert alles, was sich gerade auf dem Servierteller befindet.
     */
    public void serviere() {
        if(!serviert){
            animation.goToFrame("serviere");
            animation.goToFeedback(kunde.bewerte());
            serviert = true;
        }
        else{System.out.println("Das Gericht wurde bereits serviert.");}
    }

    private boolean wirdNichtVerbraucht(String zutat) {
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
     * entfernt gross-/kleinschreibung. haengt ein "(" an das Ende bekannte zutaten:
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
