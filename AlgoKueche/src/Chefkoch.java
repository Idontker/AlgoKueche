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
        
        c.salat("ohne Dressing");
        c.salat("Essig und Oel");
        c.salat("Joghurtdressing");

        c.spiegelei();

        c.gulasch();

        System.out.println( c.rikuSaettigen());        
    }

    public Chefkoch() {
        sam = new Lehrling();
    }

    /**
     * Wiederholungsaufgabe
     */
    public void salat(String dressing) {
        if(dressing.equals("ohne Dressing")){
            reinerSalat();
        }
        else{
            if(dressing.equals("Essig und Oel")){
                salatMitEssigUndOel();
            }
            else{
                if(dressing.equals("Joghurtdressing")){
                    salatMitJoghurtdressing();
                }
                else{
                    System.out.println("Dressing unbekannt.");
                }
            }
        }
    }



    public void spiegelei(){
        sam.wirKochenJetzt("Spiegelei");
        sam.nimmAusSchrank("Ei");
        sam.gibInTopf();
        sam.koche(6);
        while(sam.brauchtMehrWuerze() == true){
            sam.wuerze();
        }
        sam.gibTopfinhaltAufTeller();
        sam.serviere();
    }

    public void gulasch(){
        sam.wirKochenJetzt("gulasch");
        schneidenUndInTopf("Fleisch");
        schneidenUndInTopf("Zwiebel");
        for(int i=1; i <= 3; i = i+1){
            schneidenUndInTopf("Kartoffel");
            schneidenUndInTopf("Paprika");
        }
        sam.koche(40);
        while(sam.brauchtMehrWuerze() == true){
            sam.wuerze();
        }
        sam.gibTopfinhaltAufTeller();
        sam.serviere();
    }

    /*
    //Loesung Aufgabe 2
    public void rikuSaettigen(){
        while(sam.kundeIstSatt() == false){
            reinerSalat();
        }
    }
    */

    //Endgueltige Loesung Aufgabe 3b
    public double rikuSaettigen(){
        int anzahl = 0;
        while(sam.istDerKundeSatt() == false){
            reinerSalat();
            anzahl = anzahl + 1;
        }
        return anzahl * 1.25;
    }

    

    private void schneidenUndInTopf(String zutat){
        sam.nimmAusSchrank(zutat);
        sam.schneide();
        sam.gibInTopf();
    }
    /*
    Für die Wiederholungsaufgabe
    */
    public void reinerSalat() {
            sam.wirKochenJetzt("reiner Salat");
            sam.nimmAusSchrank("Salat");
            sam.schneide();
            sam.gibZutatAufTeller();
            sam.serviere();
    }

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
}
