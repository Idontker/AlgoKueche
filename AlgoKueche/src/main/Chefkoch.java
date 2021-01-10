package main;

import main.hilfsklassen.gui.MainFrame;
/**
 * Das ist die Klasse Chefkoch, in der ihr arbeiten werdet. Alle
 * Arbeitsauftraege stehen auf dem jeweiligen Arbeitsblatt. Die Arbeitsschritte,
 * die Sam beherrscht, findet ihr in der Dokumentation des Lehrlings
 * 
 * @Author Karol Bakas, Stefan Gebhart, Silas Kuder
 */

public class Chefkoch {

    public static void main(String args[]) {   
        // If your or your IDE complie the files right here, you can uncomment this line
        // Otherwise set this path to the hilfsklassen folder.
        MainFrame.pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/src/main/hilfsklassen/";
        Chefkoch c = new Chefkoch();
        c.reinerSalat();
    }

    private Lehrling sam; // Alberts Lehrling Sam, er kocht die Rezepte

    public Chefkoch() {
        sam = new Lehrling();
    }

    /**
     * Die Mustermethode aus der ersten Stunde.
     */
    public void reinerSalat() {
        sam.wirKochenJetzt("reiner Salat");
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.serviere();
    }
    
    public void reinerSalat2() {
        sam.wirKochenJetzt("reiner Salat");
        sam.nimmAusSchrank("einen Salat");
        sam.stellZurueck();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.serviere();
    }
    
    public void reinerSalat3() {
        sam.wirKochenJetzt("reiner Salat");
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.stellZurueck();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.serviere();
    }
    
    public void reinerSalat4() {
        sam.wirKochenJetzt("reiner Salat");
        sam.schneide();
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.serviere();
    }
}
