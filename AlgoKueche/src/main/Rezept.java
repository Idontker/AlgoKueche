package main;

import java.util.ArrayList;

public class Rezept {
    private String name;
    private ArrayList<RezeptKomponente> rezeptKomponenten;
    private boolean wirdGewuerzt;

    public Rezept(String name, String zutatenString, boolean wirdGewuerzt) {
        this.name = name;
        this.wirdGewuerzt = wirdGewuerzt;
        rezeptKomponenten = new ArrayList<RezeptKomponente>();

        zutatenString = zutatenString.replaceAll(")", "");
        String zutaten[] = zutatenString.split(" ");
        for (int i = 0; i < zutaten.length; i++) {
            RezeptKomponente r = createRezeptKomponente(zutaten[i]);
            rezeptKomponenten.add(r);
        }

    }
    
    public boolean wirdGewuerzt() {
        return wirdGewuerzt;
    }

    private RezeptKomponente createRezeptKomponente(String str) {
        String zutat = "";
        ArrayList<String> zubereitung = new ArrayList<String>();

        String tmp[] = str.split("\\(");
        zutat = tmp[0];
        if (tmp.length == 2) {
            tmp = tmp[1].split(",");
            for (String zub : tmp) {
                zubereitung.add(zub);
            }
        } else if (tmp.length > 2) {
            System.err.println("Rezept " + name + " falsch eingelesen: " + str + " hat keine korrekte Syntax");
        }

        return new RezeptKomponente(zutat, zubereitung);
    }

    public ArrayList<RezeptKomponente> gibRezeptKomponenten() {
        return rezeptKomponenten;
    }
}

class RezeptKomponente {
    private String zutat;
    private ArrayList<String> zubereitung;

    public RezeptKomponente(String zutat, ArrayList<String> zubereitung) {
        this.zutat = zutat;
        this.zubereitung = zubereitung;
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
