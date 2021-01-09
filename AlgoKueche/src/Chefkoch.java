/**
 * Das ist die Klasse Chefkoch, in der ihr arbeiten werdet. 
 * Alle Arbeitsauftraege stehen auf dem jeweiligen Arbeitsblatt.
 * Die Arbeitsschritte, die Sam beherrscht, findet ihr in der Dokumentation des Lehrlings 
 * @Author Karol Bakas, Stefan Gebhart, Silas Kuder
 */

public class Chefkoch {

    private Lehrling sam; // Alberts Lehrling Sam, er kocht die Rezepte

    public static void main(String args[]) {
        Chefkoch albert = new Chefkoch();
        albert.reinerSalat();
    }

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
    
    public void Eintopf_vegetarisch_1() {
        sam.wirKochenJetzt("Eintopf(vegetarisch)");
        sam.nimmAusSchrank("Kartoffel");
        sam.schneide();
        sam.gibInTopf();
        sam.nimmAusSchrank("Zwiebel");
        sam.schneide();
        sam.gibInTopf();
        sam.nimmAusSchrank("Paprika");
        sam.schneide();
        sam.gibInTopf();
        sam.koche(10);
        sam.gibTopfAufTeller();
        sam.serviere();
    }

}