package hilfsklassen.cooking;

import java.util.Arrays;

public class Formatierung {

    private Formatierung(){}

    public static String formatiere(String in){
        String out = in.trim().toLowerCase();
        out = out.replace(" ", "");
        if(out.length() >= 1){
            out = out.substring(0, 1).toUpperCase() + out.substring(1);
        }
        return out;
    }

    /**
     * Kontrolliert, ob "zutat" eine bekannte Zutat (evtl. mit Artikel) ist. 
     * Wenn ja gibt sie in interner Formatierung zurueck 
     * @param in Die Unformatierte Zutat
     * @return Den Namen der Zutat, 1. Buchstabe gross, keine Klammer, wenn bekannt, BADF00D sonst
     */
    public static String formatiereZutat(String zutat){
        String[] bekannteZutaten = new String[]{//weitere Zutaten bitte hier eintragen, evtl sollte das mal ein Enum werden...
            "Essig",    "Feta",     "Joghurt",  "Salz",     "Reis",     "Schnittlauch",
            "Avocado",  "Fisch",    "Fleisch",  "Steak",    "Gurke",    "Kartoffel",
            "Blatt",    "Nori",     "Tomate",   "Salat",    "Oel",      "Noriblatt",
            "Olive",    "Paprika",  "Zwiebel",  "Salatkopf"
        };
        String[] artikel = new String[] { "ein", "eine", "einen", "der", "die", "das" };
        String[] str = zutat.split(" ");
        String out = "BADF00D";

        if (str.length == 2 && Arrays.asList(artikel).contains(str[0])) {
            out = str[1];
        } else if (str.length == 1) {
            out = str[0];
        }
        out = formatiere(out);
        if(Arrays.asList(bekannteZutaten).contains(out)){
            return out;
        }
        else{
            return "BADF00D";
        }


        

    }
}
