package main;

public class RezeptKomponente {
    private String zutat; // zutat
    private String zubereitung; // zubereitungsart: Achtung, wegen der Syntax regulärer Ausdrücke in Java hier
                                // bitte keine Klammern verwenden

    public RezeptKomponente(String t1, String t2) {
        zutat = "" + t1;
        zubereitung = "" + t2;
    }

    public String gibZutat() {
        return zutat;
    }

    public String gibZubereitung() {
        return zubereitung;
    }

    public boolean zutatIstVorhanden(String t) {
        if (t.contains(zutat)) {
            return true;
        }
        return false;
    }

    public boolean zutatUndZubereitungIstVorhanden(String t) {
        if(zutat.length==0) {
            return true;
        }
        if (t.contains(zutat + "(" + zubereitung + ")")) {
            return true;
        }
        return false;
    }

    public String entferneZutat(String t) {
        if(zutat.length!=0) {
            return t.replaceFirst(zutat, "");
        }
    }

    public String entferneZutatUndZubereitung(String t) {
        if(zutat.length!=0) {
            return t.replaceFirst(zutat + "\\(" + zubereitung + "\\)", "");
        }
    }
}
