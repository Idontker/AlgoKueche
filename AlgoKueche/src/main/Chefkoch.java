package main;

public class Chefkoch {

    private Lehrling sam;

    public static void main(String args[]) {
        Chefkoch albert = new Chefkoch();
        albert.reinerSalat();
    }

    public Chefkoch() {
        sam = new Lehrling();
    }

    public void reinerSalat() {
        sam.wirKochenJetzt("reiner Salat");
        sam.nimmAusSchrank("einen Salat");
        sam.schneide();
        sam.gibAufTeller();
        sam.serviere();
    }
}
