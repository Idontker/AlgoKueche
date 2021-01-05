package main;

import java.util.ArrayList;

public class Rezept {
    private String name;
    private ArrayList<RezeptKomponente> rezeptKomponenten;

    public Rezept(String name, String str){
        // TODO:
    }

    public ArrayList<RezeptKomponente> gibRezeptKomponenten(){
        return rezeptKomponenten;
    }
}

class RezeptKomponente {
    private String zutat;
    private ArrayList<String> zubereitung;

    private RezeptKomponente(String t1, String t2) {
        zutat = t1;
        zubereitung = new ArrayList<String>();
    }

    private String gibZutat() {
        return zutat;
    }

    private ArrayList<String> gibZubereitung() {
        return zubereitung;
    }

    private boolean komponenteIstVorhanden(String t) {
        if (t.contains(zutat)) {
            return true;
        }
        return false;
    }

    private boolean komponenteUndZubereitungIstVorhanden(String t) {
        if (t.contains(zutat + zubereitung)) {
            return true;
        }
        return false;
    }

    private String entferneKomponente(String t) {
        return t.replaceFirst(zutat, "");
    }

    private String entferneKomponenteUndZubereitung(String t) {
        return t.replaceFirst(zutat + zubereitung, "");
    }
}