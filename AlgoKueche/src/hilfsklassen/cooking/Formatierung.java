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
     * Entfernt eventuelle Artikel von der eingegebenen Zutat und gibt sie in interner Formatierung zurueck.
     * Strings, die nicht (Artikel)<space>(Zutat) sein können, wird BADF00D zurückgegeben 
     * @param in Die Unformatierte Zutat
     * @return Den Namen der Zutat, 1. Buchstabe gross, keine Klammer
     */
    public static String formatiereZutat(String zutat){
        String[] artikel = new String[] { "ein", "eine", "einen", "der", "die", "das" };
        String[] str = zutat.split(" ");
        String out = "BADF00D";

        if (str.length == 2 && Arrays.asList(artikel).contains(str[0])) {
            out = str[1];
        } else if (str.length == 1) {
            out = str[0];
        }
        out = formatiere(out);
        return out;
        
    }
}
