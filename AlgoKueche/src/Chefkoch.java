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

    public Chefkoch() {
        sam = new Lehrling();
    }

    /*
     *  2021-01-29
     */
   
    // 2021-01-29 Wiederholungsaufgabe
    public void eintopf(boolean vegetarisch) {
        if(vegetarisch==true) {
            sam.wirKochenJetzt("vegetarischer Eintopf");
        } else {
            sam.wirKochenJetzt("Eintopf");
            sam.nimmAusSchrank("Fleisch");
            sam.schneide();
            sam.gibInTopf();
        }
        sam.nimmAusSchrank("Zwiebel");
        sam.schneide();
        sam.gibInTopf();
        for(int i=1;i<=3;i=i+1) {
            sam.nimmAusSchrank("Paprika");
            sam.schneide();
            sam.gibInTopf();
            sam.nimmAusSchrank("Kartoffel");
            sam.schneide();
            sam.gibInTopf();
        }
        sam.koche(10);
        sam.gibTopfinhaltAufTeller();
        sam.serviere();
    }

    // 2021-01-29 Blatt Stufe I
    public void burger(String burger) {
        if(burger.equals("Hamburger")) {
            sam.wirKochenJetzt("Hamburger");
            sam.nimmAusSchrank("Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
        } else {
            sam.wirKochenJetzt("Cheeseburger");
            sam.nimmAusSchrank("Kaesescheibe");
            sam.gibZutatAufTeller();
        }
        sam.nimmAusSchrank("Tomate");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("Broetchen");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("Burgerpatty");
        sam.gibInTopf();
        sam.koche(10);
        sam.gibTopfinhaltAufTeller();
        sam.serviere();
    }

    // 2021-01-29 Hilfsmethode fuer Blatt Stufe III
    private void paprikaUndZucchiniZubereiten() {
        sam.nimmAusSchrank("Paprika");
        sam.schneide();
        sam.gibInTopf();
        sam.nimmAusSchrank("Zucchini");
        sam.schneide();
        sam.gibInTopf();
        sam.koche(20);
        sam.gibTopfinhaltAufTeller();
    }

    // 2021-01-29 Hilfsmethode fuer Blatt Stufe III
    private void bratkartoffelnZubereiten() {
        sam.nimmAusSchrank("Zwiebel");
        sam.schneide();
        sam.gibInTopf();
        for(int i=1;i<=3;i=i+1) {
            sam.nimmAusSchrank("Kartoffel");
            sam.schneide();
            sam.gibInTopf();
        }
        sam.koche(20);
        sam.gibTopfinhaltAufTeller();
    }

    // 2021-01-29 Blatt Stufe II + III
    public void steak(String garungsgrad, String beilage) {
        if(garungsgrad.equals("rare")) {
            if(beilage.equals("Paprika und Zucchini")) {
                sam.wirKochenJetzt("Steak rare mit Paprika und Zucchini");
                paprikaUndZucchiniZubereiten();
            } else {
                sam.wirKochenJetzt("Steak rare mit Bratkartoffeln");
                bratkartoffelnZubereiten();
            }
            sam.nimmAusSchrank("Steak");
            sam.gibInTopf();
            sam.koche(5);
        } else {
            if(garungsgrad.equals("medium")) {
                if(beilage.equals("Paprika und Zucchini")) {
                    sam.wirKochenJetzt("Steak medium mit Paprika und Zucchini");
                    paprikaUndZucchiniZubereiten();
                } else {
                    sam.wirKochenJetzt("Steak medium mit Bratkartoffeln");
                    bratkartoffelnZubereiten();
                }
                sam.nimmAusSchrank("Steak");
                sam.gibInTopf();
                sam.koche(8);
            } else {
                if(beilage.equals("Paprika und Zucchini")) {
                    sam.wirKochenJetzt("Steak well-done mit Paprika und Zucchini");
                    paprikaUndZucchiniZubereiten();
                } else {
                    sam.wirKochenJetzt("Steak well-done mit Bratkartoffeln");
                    bratkartoffelnZubereiten();
                }
                sam.nimmAusSchrank("Steak");
                sam.gibInTopf();
                sam.koche(10);
            }
        }
        sam.gibTopfinhaltAufTeller();
        sam.serviere();
    }

    /*
     *  2021-01-22
     */

    /*
     * 2021-01-22 Aufgabe 3
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
     * 2021-01-22 Aufgabe 4
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

    /*
     *  2021-01-15
     */

    // 2021-01-15 gemeinsamer Einstieg, nach Aufgabe 4
    public void reinerSalat() {
            sam.wirKochenJetzt("reiner Salat");
            salatVorbereiten();
            sam.serviere();
    }

    // 2021-01-15 Aufgabe 2
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

    // 2021-01-15 Aufgabe 3
    public void salatVorbereiten(){
        sam.nimmAusSchrank("Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
    }

    // 2021-01-15 Aufgabe 5a)
    public void salatMitJoghurtdressing() {
        sam.wirKochenJetzt("Salat mit Joghurtdressing");
        sam.nimmAusSchrank("Salat");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("Schnittlauch");
        sam.schneide();
        sam.gibZutatAufTeller();
        sam.nimmAusSchrank("Joghurt");
        sam.gibZutatAufTeller();
        sam.serviere();
    }

    // 2021-01-15 Aufgabe 5b)
    public void griechischerSalat(){
        sam.wirKochenJetzt("griechischer Salat");
        gemueseVorbereiten("Salat");
        gemueseVorbereiten("Tomate");
        gemueseVorbereiten("Zwiebel");
        gemueseVorbereiten("Gurke");
        gemueseVorbereiten("Feta");
        sam.nimmAusSchrank("Oel");
        sam.gibZutatAufTeller();
        sam.stellZutatZurueck();
        sam.nimmAusSchrank("Olive");
        sam.gibZutatAufTeller();
        sam.serviere();
    }
    
    //praktische Hilfsmethode
    public void gemueseVorbereiten(String zutat){ //nimm, schneide, gib auf Teller
        sam.nimmAusSchrank(zutat);
        sam.schneide();
        sam.gibZutatAufTeller();
    }
}
