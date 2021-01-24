import hilfsklassen.gui.MainFrame;

/**
 * Das ist die Klasse Chefkoch, in der ihr arbeiten werdet. Alle
 * Arbeitsauftraege stehen auf dem jeweiligen Arbeitsblatt. Die Arbeitsschritte,
 * die Sam beherrscht, findet ihr in der Dokumentation des Lehrlings
 * 
 * @Author Karol Bakas, Stefan Gebhart, Silas Kuder
 */

public class Chefkoch {
    private Lehrling sam; // Alberts Lehrling Sam, er kocht die Rezepte

    public static void main(String args[]) {
        // If your or your IDE complie the files right here, you can uncomment this line
        // Otherwise set this path to the hilfsklassen folder.
        //MainFrame.pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/src/hilfsklassen/";
        MainFrame.pathToAlgoKueche = "D:/XData/Dokumente/Ausbildung/Uni/7.Semester/Studienbegl Praktikum/AlgoKueche/AlgoKueche/src/hilfsklassen/";
        Chefkoch c = new Chefkoch();
        while(!c.sam.istDerKundeSatt()){
            c.salatMitEssigUndOel();
        }
    }

    public Chefkoch() {
        sam = new Lehrling();
    }

    /**
     * Wiederholungsaufgabe
     */
    public void salatMitEssigUndOel() {
        sam.wirKochenJetzt("Salat mit Essig und Oel");
        sam.nimmAusSchrank("Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("Essig");
        sam.gibZutatAufTeller();
        sam.stellZutatZurueck();
        sam.nimmAusSchrank("Oel");
        sam.gibZutatAufTeller();
        sam.stellZutatZurueck();
        sam.serviere();
    }

    /**
     * Aufgabe zur Motivierung von Wiederholungen mit fester Anzahl mit for
     */
    public void familiensalatMitEssigUndOel() {
        sam.wirKochenJetzt("Familiensalat mit Essig und Oel");

        for (int i = 1; i <= 4; i = i + 1) {
            sam.nimmAusSchrank("Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
            sam.nimmAusSchrank("Essig");
            sam.gibZutatAufTeller();
            sam.stellZutatZurueck();
            sam.nimmAusSchrank("Oel");
            sam.gibZutatAufTeller();
            sam.stellZutatZurueck();
        }
        sam.serviere();
    }

    /**
     * Aufgabe 3
     */
    public void sushi() {
        sam.wirKochenJetzt("Sushi");

        for (int i = 0; i < 3; i++) {
            sam.nimmAusSchrank("Reis");
            sam.gibInTopf();
            sam.koche(20);
            sam.gibTopfinhaltAufTeller();

            sam.nimmAusSchrank("Fisch");
            sam.schneide();
            sam.gibZutatAufTeller();

            sam.nimmAusSchrank("Noriblatt");
            sam.gibZutatAufTeller();
        }

        sam.serviere();
    }

    /**
     * Aufgabe 4
     */
    public void californiaRole() {
        sam.wirKochenJetzt("California Role");

        for (int i = 0; i < 3; i++) {
            sam.nimmAusSchrank("Reis");
            sam.gibInTopf();
            sam.koche(20);
            sam.gibTopfinhaltAufTeller();

            sam.nimmAusSchrank("Fisch");
            sam.schneide();
            sam.gibZutatAufTeller();

            sam.nimmAusSchrank("Avocado");
            sam.schneide();
            sam.gibZutatAufTeller();

            sam.nimmAusSchrank("Noriblatt");
            sam.gibZutatAufTeller();
        }

        sam.serviere();
    }

    /**
     * Abschliessende Aufgabe
     */
    public void vieleSalate(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            salatMitEssigUndOel();
        }
    }
}
