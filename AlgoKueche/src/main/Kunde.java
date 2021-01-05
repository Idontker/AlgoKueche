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
    private HashMap<String,Rezept> menueMap; 
   
    private ArrayList<RezeptKomponente> komponenten;
    private Comment gemeldeterFehler; //frage an alle: wollen wir die Fehler nicht doch als Strings codieren? Dadurch waere eine viel genauere bestimmung des Fehlers moeglich (siehe meinen Code unten).

    public Kunde()
    {
        //TODO: init menueMap
        initMap();
    }

    private void initMap() {
        menueMap = new HashMap<String,Rezept>();

        File file = new File(pathToRecipes); 
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


    public void rezeptauswahl(String rezept) {
        serviert="";
        gemeldeterFehler=null;
        gewuerzt=null;
        komponenten=new ArrayList<RezeptKomponente>();
    }

    public void rezeptauswahl(Boolean t1) {
        serviert="";
        gemeldeterFehler=null;
        gewuerzt=t1;
        komponenten=new ArrayList<RezeptKomponente>();
    }

    public void rezeptauswahl(ArrayList<RezeptKomponente> t1) {
        serviert="";
        gemeldeterFehler=null;
        gewuerzt=null;
        komponenten=t1;
    }

    public void rezeptauswahl(Boolean t1, ArrayList<RezeptKomponente> t2) {
        serviert="";
        gemeldeterFehler=null;
        gewuerzt=t1;
        komponenten=t2;
    }

    public void komponenteHinzufuegen(String t1, String t2) {
        komponenten.add(new RezeptKomponente(t1, t2));
    }

    public void arbeitsschritt(String t) {
        serviert+=t;
    }

    public void meldeFehler(Comment t) {
        gemeldeterFehler=t;
    }

    public Feedback bewerte() {
        if(serviert.length()==0) { //pruefe ob nichts serviert wurde.
            return new Feedback(Comment.serviereLeerenTeller, "", "");
        }

        for(int i=0;i<komponenten.size();i++) {
            RezeptKomponente komponente=komponenten.get(i);
            if(komponente.zutatIstVorhanden(serviert)) { //ist die Komponente vorhanden?
                if(komponente.zutatUndZubereitungIstVorhanden(serviert)) { //ist die Komponente auch in der richtigen zubereitungsart vorhanden?
                    serviert=komponente.entferneZutatUndZubereitung(serviert); //falls ja, entferne sie damit auch mehrfach benötigte Komponenten abgefragt werden koennen.
                } else {
					return new Feedback(Comment.falschZubereitet, komponente.gibZutat(), komponente.gibZubereitung());
                    //return komponente.gibZutat()+" falsch zubereitet. Sollte "+komponente.gibZubereitung()+" sein. :("; //falls nein, gebe aus wie die Komponente haette zubereitet werden sollen.
                }
            } else {
				return new Feedback(Comment.fehlendeZutat, komponente.gibZutat(), komponente.gibZubereitung());
                //return "Es fehlt "+komponente.gibZutat()+komponente.gibZubereitung()+". :("; //falls die Komponente gar nicht mehr vorhanden ist, gebe aus was fehlt.
            }
        }
        if(serviert.length()!=0) { //ist ausserhalb der benoetigten Komponenten noch etwas uebrig?
            for(int i=0;i<komponenten.size();i++) { //handelt es sich um eine Zutat aus dem Rezept die nur zu oft da ist? Falls ja, gib sie aus (falls sie auch noch richtig zubereitet wurde mit der Zubereitungsart).
                RezeptKomponente komponente=komponenten.get(i);
                if(komponente.zutatIstVorhanden(serviert)) {
					return new Feedback(Comment.zuVielServiert, komponente.gibZutat(), komponente.gibZubereitung());
                    //return "Zu viele "+komponente.gibZutat()+komponente.gibZubereitung()+". :(";
                }
            }
			return new Feedback(Comment.falscheZutatEnthalten, serviert, "");
            //return serviert+" gehoert da nicht rein. :("; //falls es sich um unbekannte Komponenten handelt, gib sie alle aus.
        }
        if(gewuerzt!=null&&gewuerzt==false) { //falls es auf die Wuerzung ankommt ueberpruefe sie, und gib aus, falls sie falsch ist.
			return new Feedback(Comment.falschGewuerzt, "", "");
            //return "Die Zutaten stimmen, aber leider ist es nicht richtig gewuerzt. :(";
        }
        if(gemeldeterFehler!=null) { //falls ein Fehler während des Kochens geschehen ist, gib ihn aus. Zum Beispiel: Zutatenverschwendung
			return new Feedback(gemeldeterFehler, "", "");
            //return gemeldeterFehler;
        }
		return new Feedback(Comment.richtig, "", "");
        //return "Schmeckt gut. :)"; //falls nichts falsch ist, sollte es gut schmecken :).
    }

    public class RezeptKomponente {
        private String zutat; //zutat
        private String zubereitung; //zubereitungsart: Achtung, wegen der Syntax regulärer Ausdrücke in Java hier bitte keine Klammern verwenden

        private RezeptKomponente(String t1, String t2) {
            zutat=""+t1;
            zubereitung=""+t2;
        }

        private String gibZutat() {
            return zutat;
        }

        private String gibZubereitung() {
            return zubereitung;
        }

        private boolean zutatIstVorhanden(String t) {
            if(t.contains(zutat)) {
                return true;
            }
            return false;
        }

        private boolean zutatUndZubereitungIstVorhanden(String t) {
            if(t.contains(zutat+"("+zubereitung+")")) {
                return true;
            }
            return false;
        }

        private String entferneZutat(String t) {
            return t.replaceFirst(zutat, "");
        }

        private String entferneZutatUndZubereitung(String t) {
            return t.replaceFirst(zutat+"\\("+zubereitung+"\\)", "");
        }
    }
}