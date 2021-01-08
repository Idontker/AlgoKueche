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

        zutatenString = zutatenString.replaceAll("\\)", "");
        String zutaten[] = zutatenString.split(" ");
        for (int i = 0; i < zutaten.length; i++) {
            int wdh = 1;
            String zutat = zutaten[i];
            if (zutat.contains("_")) {
                String tmp[] = name.split("_");
                if (tmp.length > 2) {
                    System.err.println("Error in " + name + ": in " + zutat + " sind haben zu viele _ und wird daher übersprungen");
                    continue;
                }
                wdh = Integer.parseInt(tmp[0]);
                zutat = tmp[1];
            }
            for (int j = 0; j < wdh; j++) {
                RezeptKomponente r = createRezeptKomponente(zutat);
                rezeptKomponenten.add(r);
            }
        }

    }

    public boolean wirdGewuerzt() {
        return wirdGewuerzt;
    }

    private RezeptKomponente createRezeptKomponente(String str) {
        String zutat = "";
        String zubereitung = "";

        String tmp[] = str.split("\\(");
        zutat = tmp[0];
        if (tmp.length == 2) {
            zubereitung = tmp[1];
        } else if (tmp.length > 2) {
            System.err.println("Rezept " + name + " falsch eingelesen: " + str + " hat keine korrekte Syntax");
        }

        return new RezeptKomponente(zutat, zubereitung);
    }

    public ArrayList<RezeptKomponente> gibRezeptKomponenten() {
        return rezeptKomponenten;
    }
}