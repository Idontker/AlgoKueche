import hilfsklassen.gui.GUI;
import hilfsklassen.cooking.*;

/**
 * Die Klasse Lehrling
 * @author Karol Bakas, Stefan Gebhart, Silas Kuder
 */
import java.util.ArrayList;
import java.util.Arrays;

public class Lehrling {

    private String aktZutat;
    private ArrayList<String> zutatenInTopf;
    protected Kunde kunde;
    protected GUI animation;
    private int aktWuerze;
    private boolean bearbeitet;
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
        boolean fine = kunde.rezeptauswahl(Formatierung.formatiere(rezept));
        if (!fine) {
            animation.goToFrame("alert", "Das Rezept \"" + rezept
                    + "\" ist nicht bekannt. Du kannst das Programm abbrechen, oder trotzdem laufen lassen");
        }
        animation.goToFrame("wirKochenJetzt", rezept);

    }

    /**
     * Nimmt eine Zutat aus dem Schrank in die Hand, als aktuelle Zutat. Achtung: Es
     * kann nur eine Zutat gleichzeitig in der Hand gehalten werden.
     * 
     * @param zutat Die Zutat, die genommen werden soll. Gross- Kleinschreibung wird
     *              nicht beachtet
     */
    public void nimmAusSchrank(String zutat) {
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
    public void gibTopfAufTeller() {
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
        }
        zutatenInTopf.sort(null);
        String zusammenGekocht = "_zusammengekocht";
        for (int i = 0; i < zutatenInTopf.size(); i++) {
            zusammenGekocht += "_" + zutatenInTopf.get(i).substring(0, zutatenInTopf.get(i).indexOf("("));
        }
        zusammenGekocht += ")";
        zusammenGekocht=zusammenGekocht.toLowerCase();
        for (int i = 0; i < zutatenInTopf.size(); i++) {
            kunde.arbeitsschritt(zutatenInTopf.get(i) + zusammenGekocht);
        }
        zutatenInTopf.clear();
        animation.goToFrame("gebeAufTeller", "alles aus dem Topf");
    }

    /**
     * Platziert die Aktuelle Zutat auf dem Servierteller.
     */
    public void gibZutatAufTeller() {
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
     * @return false, wenn noch nicht genug gewuerzt wurde und true, sobald genug
     *         gewuerzt wurde (oder bereits zu viel)
     */
    public boolean istGewuerzt() {
        if (aktWuerze == 0) {
            kunde.setzeGewuerzt(true);
            animation.goToFrame("istGewuerztTrue");
            return true;
        }
        if (aktWuerze > 0) {
            animation.goToFrame("istGewuerztFalse");
            kunde.setzeGewuerzt(false);
            return false;
        }
        if (aktWuerze < 0) {
            animation.goToFrame("istGewuerztFalse");
            kunde.setzeGewuerzt(false);
            kunde.meldeFehler(Comment.versalzen);
            return true;
        }
        return true;

    }

    /**
     * Der Lehrling wuerzt das Gericht etwas. Muss eventuell mehrfach aufgerufen
     * werden.
     */
    public void wuerze() {
        if (aktWuerze == 42) {
            aktWuerze = (int) (Math.random() * 3) + 1;
        }
        aktWuerze--;
    }

    /**
     * Serviert alles, was sich gerade auf dem Servierteller befindet.
     */
    public void serviere() {
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
