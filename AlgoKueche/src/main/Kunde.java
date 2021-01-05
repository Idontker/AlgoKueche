package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kunde
{
    private final String pathToRecipes= "AlgoKueche/res/rezpete.txt";
    private String serviert;
    private Boolean gewuerzt;
    private int zeit; //-1 fuer keine ueberpruefung der Zeit.
    private HashMap<String,Rezept> menueMap; 
   
    private ArrayList<RezeptKomponente> komponenten;
    private String gemeldeterFehler; //frage an alle: wollen wir die Fehler nicht doch als Strings codieren? Dadurch waere eine viel genauere bestimmung des Fehlers moeglich (siehe meinen Code unten).

    public Kunde()
    {
        //TODO: init menueMap
        initMap();
    }

    private void initMap() {
        menueMap = new HashMap<String,Rezept>();

        File file = new File("AlgoKueche/res/rezpete.txt"); 
        try{
            BufferedReader br = new BufferedReader(new FileReader(file)); 
            
            String line; 
            int i = 0;
            while ((line = br.readLine()) != null){
                String s[] = line.split(":");
                if(s.length != 2 && s.length != 3){
                    System.err.println("Error reading line " + i);
                    continue;
                }
                boolean wirdGewuert = s.length == 3;
                String name = s[0];
                String zutaten = s[1];
                menueMap.put(name, new Rezept(name, zutaten, wirdGewuert)); 
                i++;
            } 
        }catch(IOException e){
            e.printStackTrace();
            System.exit(1);
        }

    }


    public void rezeptauswahl(int t1) {
        serviert="";
        gemeldeterFehler="";
        zeit=t1;
        gewuerzt=null;
        komponenten=new ArrayList<RezeptKomponente>();
    }

    public void rezeptauswahl(int t1, Boolean t2) {
        serviert="";
        gemeldeterFehler="";
        zeit=t1;
        gewuerzt=t2;
        komponenten=new ArrayList<RezeptKomponente>();
    }

    public void rezeptauswahl(int t1, ArrayList<RezeptKomponente> t2) {
        serviert="";
        gemeldeterFehler="";
        zeit=t1;
        gewuerzt=null;
        komponenten=t2;
    }

    public void rezeptauswahl(int t1, Boolean t2, ArrayList<RezeptKomponente> t3) {
        serviert="";
        gemeldeterFehler="";
        zeit=t1;
        gewuerzt=t2;
        komponenten=t3;
    }

    public void komponenteHinzufuegen(String t1, String t2) {
        komponenten.add(new RezeptKomponente(t1, t2));
    }

    public String arbeitsschritt(String t) {
        serviert+=t;
        if(zeit>0) {
            zeit--;
        }
        if(zeit==0) {
            return "Das hat zu lange gedauert. Der Kunde ist mittlerweile gegangen. :("; //evtl Exception werfen um Programm zu unterbrechen?
        }
        return null;
    }

    public void meldeFehler(String t) {
        gemeldeterFehler=t;
    }

    public String bewerte() {
        if(serviert.length()==0) { //pruefe ob nichts serviert wurde.
            return "Leerer Teller. :(";
        }
        //serviert=serviert.replaceAll("[()]",""); //entferne die Klammern, da sonst replaceFirst() Probleme hat (regulaere Ausdruecke in Java).

        for(int i=0;i<komponenten.size();i++) {
            RezeptKomponente komponente=komponenten.get(i);
            if(komponente.komponenteIstVorhanden(serviert)) { //ist die Komponente vorhanden?
                if(komponente.komponenteUndZubereitungIstVorhanden(serviert)) { //ist die Komponente auch in der richtigen zubereitungsart vorhanden?
                    serviert=komponente.entferneKomponenteUndZubereitung(serviert); //falls ja, entferne sie damit auch mehrfach benötigte Komponenten abgefragt werden koennen.
                } else {
                    return komponente.gibKomponente()+" falsch zubereitet. Sollte "+komponente.gibZubereitung()+" sein. :("; //falls nein, gebe aus wie die Komponente haette zubereitet werden sollen.
                }
            } else {
                return "Es fehlt "+komponente.gibKomponente()+komponente.gibZubereitung()+". :("; //falls die Komponente gar nicht mehr vorhanden ist, gebe aus was fehlt.
            }
        }
        if(serviert.length()!=0) { //ist ausserhalb der benoetigten Komponenten noch etwas uebrig?
            for(int i=0;i<komponenten.size();i++) { //handelt es sich um eine Zutat aus dem Rezept die nur zu oft da ist? Falls ja, gib sie aus (falls sie auch noch richtig zubereitet wurde mit der Zubereitungsart).
                RezeptKomponente komponente=komponenten.get(i);
                if(komponente.komponenteIstVorhanden(serviert)) {
                    if(komponente.komponenteUndZubereitungIstVorhanden(serviert)) {
                        return "Zu viele "+komponente.gibKomponente()+komponente.gibZubereitung()+". :(";
                    } else {
                        return "Zu viele "+komponente.gibKomponente()+". :(";
                    }
                }
            }
            return serviert+" gehoert da nicht rein. :("; //falls es sich um unbekannte Komponenten handelt, gib sie alle aus.
        }
        if(gewuerzt!=null&&gewuerzt==false) { //falls es auf die Wuerzung ankommt ueberpruefe sie, und gib aus, falls sie falsch ist.
            return "Die Zutaten stimmen, aber leider ist es nicht richtig gewuerzt. :(";
        }
        if(gemeldeterFehler.length()!=0) { //falls ein Fehler während des Kochens geschehen ist, gib ihn aus. Zum Beispiel: Zutatenverschwendung
            return gemeldeterFehler;
        }

        return "Schmeckt gut. :)"; //falls nichts falsch ist, sollte es gut schmecken :).
    }

    public class RezeptKomponente {
        private String komponente; //zutat
        private String zubereitung; //zubereitungsart: Achtung, wegen der Syntax regulärer Ausdrücke in Java hier bitte keine Klammern verwenden

        private RezeptKomponente(String t1, String t2) {
            komponente=""+t1;
            zubereitung=""+t2;
        }

        private String gibKomponente() {
            return komponente;
        }

        private String gibZubereitung() {
            return zubereitung;
        }

        private boolean komponenteIstVorhanden(String t) {
            if(t.contains(komponente)) {
                return true;
            }
            return false;
        }

        private boolean komponenteUndZubereitungIstVorhanden(String t) {
            if(t.contains(komponente+"("+zubereitung+")")) {
                return true;
            }
            return false;
        }

        private String entferneKomponente(String t) {
            return t.replaceFirst(komponente, "");
        }

        private String entferneKomponenteUndZubereitung(String t) {
            return t.replaceFirst(komponente+"\\("+zubereitung+"\\)", "");
        }
    }
}