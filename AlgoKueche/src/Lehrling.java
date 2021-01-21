import hilfsklassen.gui.GUI;
import hilfsklassen.cooking.*;

/**
 * Die Klasse Lehrling
 * @author Karol Bakas, Stefan Gebhart, Silas Kuder
 */
import java.util.ArrayList;

public class Lehrling {

    private String aktZutat;
    private ArrayList<String> zutatenInTopf;
    protected Kunde kunde;
    protected GUI animation;
    private int aktWuerze;
    private boolean bearbeitet;
    private boolean serviert;
    private int schritte;
    private int aktHunger;

    /**
     * Initialisiert einen Lehrling.
     */
    public Lehrling() {
        animation = GUI.startGUI();
        kunde = new Kunde();
        aktHunger = 42;
    }

    /**
     * Muss zu Beginn jedes Rezepts aufgerufen werden, damit die Korrektheit der Zubereitung ueberprüft werden kann.
     * Nutzt genau den Namen, der auf dem Arbeitsblatt steht.
     * 
     * @param rezept der Name des Rezepts. Achtet darauf, dass es genauso
     *               geschrieben ist, wie auf dem Arbeitsblatt.
     */
    public void wirKochenJetzt(String rezept) {
        // init
        serviert = false;
        aktZutat = "";
        aktWuerze = 42;
        bearbeitet = false;
        zutatenInTopf = new ArrayList<String>();
        int[] ret = kunde.rezeptauswahl(Formatierung.formatiere(rezept));
        boolean fine = ret[0] == 1;
        if (!fine) {
            animation.goToFrame("alert", "Das Rezept \"" + rezept
                    + "\" ist nicht bekannt. Du kannst das Programm abbrechen, oder trotzdem laufen lassen");
        }
        schritte = ret[1];
        animation.goToFrame("wirKochenJetzt", rezept);
    }

    private void schrittZaehler() {
        schritte--;
        if (schritte <= 0) {
            animation.goToFrame("timeWaste", "Die Zubereitung braucht verdaechtig lange.");
            RuntimeException e = new ZubereitungDauertZuLangeFehler(
                    "Die Zubereitung wurde abgebrochen, da sie deutlich zu lange braucht.");
            e.setStackTrace(new StackTraceElement[0]);
            throw e;
        }
    }

    private class ZubereitungDauertZuLangeFehler extends RuntimeException {
        private ZubereitungDauertZuLangeFehler(String s) {
            super(s);
        }
    }

    /**
     * Nimmt eine Zutat aus dem Schrank in die Hand, als aktuelle Zutat. Achtung: Es
     * kann nur eine Zutat gleichzeitig in der Hand gehalten werden.
     * 
     * @param zutat Die Zutat, die genommen werden soll. Gross- Kleinschreibung wird
     *              nicht beachtet
     */
    public void nimmAusSchrank(String zutat) {
        schrittZaehler();
        if (!aktZutat.isEmpty()) {
            kunde.meldeFehler(Comment.mehrAlsEineZutatInDerHand);
        }
        bearbeitet = false;
        aktZutat = entscheideZutat(zutat);
        animation.goToFrame("nimmAusSchrank", aktZutat + ")");
    }

    /**
     * Stellt die aktuelle Zutat zurueck.
     */
    public void stellZutatZurueck() {
        schrittZaehler();
        if (bearbeitet) {
            kunde.meldeFehler(Comment.verschwendung);
        }
        animation.goToFrame("stellZutatZurueck", aktZutat + ")");
        aktZutat = "";
    }

    /**
     * Schneidet die aktuelle Zutat klein, diese ist nun in geschnittener Form die
     * aktuelle Zutat.
     */
    public void schneide() {
        schrittZaehler();
        if (aktZutat.isEmpty()) {
            animation.goToFrame("schneide", "nichts");
            kunde.meldeFehler(Comment.schneidenOhneZutat);
        } else {
            animation.goToFrame("schneide", aktZutat + ")");
            bearbeitet = true;
            aktZutat = aktZutat + "geschnitten,";
        }
    }

    /**
     * Gibt die aktuelle Zutat in den Topf. Sie kann jetzt gekocht werden. Der
     * Lehrling hat jetzt wieder die Haende frei.
     */
    public void gibInTopf() {
        schrittZaehler();
        animation.goToFrame("gibInTopf", aktZutat + ")");
        zutatenInTopf.add(aktZutat);
        if (wirdVerbraucht(aktZutat)) {
            aktZutat = "";
        }
    }

    /**
     * Kocht alle Zutaten im Topf. Der Lehrling ist solange mit Umruehren
     * beschaftigt.
     * 
     * @param zeit Die Kochzeit in Minuten
     */
    public void koche(int zeit) {
        schrittZaehler();
        if (zutatenInTopf.size() != 0) {
            animation.goToFrame("koche", zeit + " Minuten");
            for (int i = 0; i < zutatenInTopf.size(); i++) {
                final String GEKOCHT = "gekocht_";
                if (zutatenInTopf.get(i).contains(GEKOCHT)) {
                    int zeitZutat = Integer
                            .parseInt(zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).indexOf(GEKOCHT) + 8));
                    zutatenInTopf.set(i, zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).indexOf(GEKOCHT) - 1)
                            + GEKOCHT + zeitZutat);
                } else {
                    zutatenInTopf.set(i, zutatenInTopf.get(i) + GEKOCHT + zeit);
                }
            }
        } else {
            kunde.meldeFehler(Comment.kochtLeerenTopf);
        }
    }

    /**
     * Platziert den Inhalt des Topfes auf dem Servierteller.
     */
    public void gibTopfinhaltAufTeller() {
        schrittZaehler();
        String ausgabe="";
        for (int i = 0; i < zutatenInTopf.size(); i++) {
            if (!zutatenInTopf.get(i).contains("gekocht_")) {
                String zutat = zutatenInTopf.get(i);
                if (zutat.endsWith(",")) {
                    zutat = zutat.substring(0, zutat.length() - 1);
                }
                kunde.arbeitsschritt(zutat + ")");
                zutatenInTopf.remove(i);
                i--;
            }
            ausgabe+=zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).indexOf("("))+", ";
        }
        if(ausgabe.length()!=0) {
            ausgabe=ausgabe.substring(0,ausgabe.length()-2);
        }
        zutatenInTopf.sort(null);
        String zusammenGekocht = "_zusammengekocht";
        boolean istReisSchonDrin = false;
        for (int i = 0; i < zutatenInTopf.size(); i++) {
            if(!zutatenInTopf.get(i).contains("Reis")||istReisSchonDrin==false) {
                zusammenGekocht += "_" + zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).indexOf("("));
                if(zutatenInTopf.get(i).contains("Reis")) {
                    istReisSchonDrin=true;
                }
            }
        }
        zusammenGekocht += ")";
        zusammenGekocht = zusammenGekocht.toLowerCase();
        for (int i = 0; i < zutatenInTopf.size(); i++) {
            kunde.arbeitsschritt(zutatenInTopf.get(i) + zusammenGekocht);
        }
        zutatenInTopf.clear();
        animation.goToFrame("gebeAufTeller", ausgabe);
    }

    /**
     * Platziert die Aktuelle Zutat auf dem Servierteller.
     */
    public void gibZutatAufTeller() {
        schrittZaehler();
        if (aktZutat.endsWith(",")) {// entfernt das letzte Komma
            aktZutat = aktZutat.substring(0, aktZutat.length() - 1);
        }
        if (aktZutat.length() != 0) {
            kunde.arbeitsschritt(aktZutat + ")");
            animation.goToFrame("gebeAufTeller", aktZutat + ")");
        } else {
            animation.goToFrame("gebeAufTeller", "nichts");
        }

        if (wirdVerbraucht(aktZutat)) {
            aktZutat = "";
        }
    }

    /**
     * Probiert, ob das Gericht bereits ausreichend gewuerzt ist.
     * 
     * @return true, wenn noch weiter gewuerzt werden muss. Und false, wenn genug gewuerzt wurde.
     * 
     */
    public boolean brauchtMehrWuerze() {
        schrittZaehler();
        if (aktWuerze == 0) {
            kunde.setzeGewuerzt(true);
            animation.goToFrame("gutGewuerzt");
            return false;
        }
        if (aktWuerze > 0) {
            animation.goToFrame("schlechtGewuerzt");
            kunde.setzeGewuerzt(false);
            return true;
        }
        if (aktWuerze < 0) {
            animation.goToFrame("schlechtGewuerzt");
            kunde.setzeGewuerzt(false);
            kunde.meldeFehler(Comment.versalzen);
            return false;
        }
        return false;
    }

    /**
     * Fragt den Kunden, ob er noch immer Hunger hat
     * 
     * @return true, wenn der Kunde satt ist, false wenn er noch Hunger hat
     *         
     */
    public boolean istDerKundeSatt() {
        if (aktHunger == 42) {
            aktHunger = (int) (Math.random() * 8) + 3;
        }
        
        if (aktHunger == 0) {
            animation.goToFrame("sumoSatt");
            aktHunger--;
            return true;
        }
        if (aktHunger > 0) {
            animation.goToFrame("sumoHungrig");
            aktHunger--;
            return false;
        }
        if (aktHunger < 0) {
            animation.goToFrame("sumoHungrig");
            kunde.meldeFehler(Comment.satt);
            aktHunger--;
            return true;
        }
        aktHunger--;
        return true;
    }

    /**
     * Der Lehrling wuerzt das Gericht etwas. Muss eventuell mehrfach aufgerufen
     * werden.
     */
    public void wuerze() {
        schrittZaehler();
        if (aktWuerze == 42) {
            aktWuerze = (int) (Math.random() * 3) + 1;
        }
        animation.goToFrame("wuerze");
        aktWuerze--;
    }

    /**
     * Serviert alles, was sich gerade auf dem Servierteller befindet.
     */
    public void serviere() {
        schrittZaehler();
        if (!serviert) {
            animation.goToFrame("serviere");
            animation.goToFeedback(kunde.bewerte());
            serviert = true;
        } else {
            System.out.println("Das Gericht wurde bereits serviert.");
        }
    }

    /*
     * Testet, ob eine Zutat verbraucht wird, oder wieder zurück gestellt werden
     * muss
     */
    private boolean wirdVerbraucht(String zutat) {
        switch (zutat) {
            case "Oel(":
            case "Essig(":
            case "Salz(":
                return false;
            default:
                return true;
        }
    }

    /*
     * Entfernt gross-/kleinschreibung. haengt ein "(" an das Ende bekannte zutaten:
     * salat, oel, zwiebel, gurke, oliven, feta, salz, essig
     */
    private String entscheideZutat(String eingabe) {
        String zutat = Formatierung.formatiereZutat(eingabe);
        if (zutat.equals("BADF00D")) {
            kunde.meldeFehler(Comment.zutatUnbekannt);
            return "BADF00D(";
        } else {
            return zutat + "(";
        }
    }
}
