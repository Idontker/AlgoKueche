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
        MainFrame.pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/src/main/hilfsklassen/";
        Chefkoch c = new Chefkoch();
        c.sushi();
    }

    public Chefkoch() {
        sam = new Lehrling();
    }

    /**
     * Wiederholungsaufgabe
     */
    public void reinerSalat() {
        sam.wirKochenJetzt("reiner Salat");
        sam.nimmAusSchrank("Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.serviere();
    }

    /**
     * Aufgabe zur Motivierung von Wiederholungen mit fester Anzahl
     */

    public void familienReinerSalat() {
        sam.wirKochenJetzt("reiner Salat Familienportion");

        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();

        sam.serviere();
    }

    /**
     * Aufgabe zur Motivierung von Wiederholungen mit fester Anzahl mit for geloest
     */
    public void familienReinerSalatBesser() {
        sam.wirKochenJetzt("reiner Salat Familienportion");

        for (int i = 0; i < 4; i++) {
            sam.nimmAusSchrank("einen Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
        }
        sam.serviere();
    }

    public void familienTomatensalat() {
        sam.wirKochenJetzt("Tomatensalat Familienportion");

        for (int i = 0; i < 4; i++) {
            sam.nimmAusSchrank("einen Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
            sam.nimmAusSchrank("Tomate");
            sam.schneide();
            sam.gibZutatAufTeller();
        }
        sam.serviere();
    }

    /**
     * Aufgabe 2
     */
    public void sushi() {
        sam.wirKochenJetzt("Sushi");

        for (int i = 0; i < 3; i++) {
            sam.nimmAusSchrank("Reis");
            sam.gibInTopf();
            sam.koche(20);
            sam.gibTopfAufTeller();

            sam.nimmAusSchrank("Fisch");
            sam.schneide();
            sam.gibZutatAufTeller();

            sam.nimmAusSchrank("Noriblatt");
            sam.gibZutatAufTeller();
        }

        sam.serviere();
    }

    /**
     * Aufgabe 3
     */
    public void californiaRole() {
        sam.wirKochenJetzt("California Role");

        for (int i = 0; i < 3; i++) {
            sam.nimmAusSchrank("Reis");
            sam.gibInTopf();
            sam.koche(20);
            sam.gibTopfAufTeller();

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
    public void serviereVieleReineSalate(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            sam.wirKochenJetzt("reiner Salat");
            sam.nimmAusSchrank("einen Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
            sam.serviere();
        }
    }

    /**
     * Abschliessende Aufgabe
     */
    public void serviereVieleReineSalateBesser(int anzahl) {
        for (int i = 0; i < anzahl; i++) {
            reinerSalat();
        }
    }
}
