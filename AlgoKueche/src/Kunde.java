import hilfsklassen.cooking.*;
import hilfsklassen.gui.MainFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Kunde {
    private final String pathToRecipes = "res/rezepte.txt";
    private String serviert;
    private String rezeptName;
    private Boolean gewuerzt;
    private HashMap<String, Rezept> menueMap;

    private ArrayList<RezeptKomponente> komponenten;
    private Comment gemeldeterFehler; // frage an alle: wollen wir die Fehler nicht doch als Strings codieren? Dadurch
                                      // waere eine viel genauere bestimmung des Fehlers moeglich (siehe meinen Code
                                      // unten).

    public Kunde() {
        initMap();
    }

    private void initMap() {
        menueMap = new HashMap<String, Rezept>();
        File file = new File(MainFrame.pathToAlgoKueche + pathToRecipes);
        try {
            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;
            int i = 0;
            while ((line = br.readLine()) != null) {
                int maxTime = -1;
                line = line.toLowerCase();
                if (line.contains("!")) {
                    String[] tmp = line.split("!");
                    if (tmp.length != 2) {
                        System.err.println("Error reading line " + i + "\tSyntax for recepie is brocken, too many !");
                        continue;
                    }
                    maxTime = Integer.parseInt(tmp[0]);
                    line = tmp[1];
                }

                String s[] = line.split(":");
                if (s.length != 2 && s.length != 3) {
                    System.err.println("Error reading line " + i + "\tSyntax for recepie is brocken, too many :");
                    continue;
                }
                boolean wirdGewuert = s.length == 3;
                String name = Formatierung.formatiere(s[0]);
                String zutaten = s[1].trim();
                menueMap.put(name, new Rezept(name, zutaten, wirdGewuert, maxTime));
                i++;
            }
            br.close();
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

    }


    /**
     * 
     * @return
     *  int[0]: is it a valid recipe. 0 stands for false, 1 for true
     *  int[1]: the max count of steps 
     */
    public int[] rezeptauswahl(String t) {
        rezeptName = t;
        serviert = "";
        gemeldeterFehler = null;
        Rezept r = menueMap.get(t);
        if (r == null) {
            System.err.println("Das Rezept " + t + " gibt es nicht");
            System.err.println("Der Code kann wird nicht abgebrochen und weiter ausgefuehrt!");
            return new int[]{0,0};
        } else {
            if (r.wirdGewuerzt()) {
                gewuerzt = false;
            } else {
                gewuerzt = null;
            }
            komponenten = new ArrayList<RezeptKomponente>();
            komponenten.addAll(r.gibRezeptKomponenten());
            return new int[]{1,r.getMaxTime()};
        }
    }

    public void komponenteHinzufuegen(String t1, String t2) {
        komponenten.add(new RezeptKomponente(t1, t2));
    }

    public void arbeitsschritt(String t) {
        serviert += t;
    }

    public void setzeGewuerzt(boolean t) {
        if (gewuerzt != null) {
            gewuerzt = t;
        }
    }

    public void meldeFehler(Comment t) {
        if (gemeldeterFehler == null) {
            gemeldeterFehler = t;
        }
    }

    public Feedback bewerte() {
        String serviertKlon = new String(serviert); // ich denke String serviertKlon = serviert sollte auch gehen
        if (serviertKlon.length() == 0) { // pruefe ob nichts serviert wurde.
            return new Feedback(Comment.serviereLeerenTeller, "", "");
        }

        if (komponenten == null) {
            return new Feedback(Comment.unbekanntesRezept, rezeptName, "");
        }

        for (int i = 0; i < komponenten.size(); i++) {
            RezeptKomponente komponente = komponenten.get(i);
            if (!komponente.zutatIstVorhanden(serviertKlon)) { // ist die Komponente nicht vorhanden?
                return new Feedback(Comment.fehlendeZutat, komponente.gibZutat(), komponente.gibZubereitung());
            } else {
                serviertKlon = komponente.entferneZutat(serviertKlon);
            }
        }
        serviertKlon = new String(serviert);
        for (int i = 0; i < komponenten.size(); i++) {
            RezeptKomponente komponente = komponenten.get(i);
            if (komponente.zutatUndZubereitungIstVorhanden(serviertKlon)) { // ist die Komponente auch in der
                                                                            // richtigen
                // zubereitungsart vorhanden?
                serviertKlon = komponente.entferneZutatUndZubereitung(serviertKlon); // falls ja, entferne sie damit
                                                                                     // auch
                // mehrfach benötigte Komponenten
                // abgefragt werden koennen.
            } else {
                return new Feedback(Comment.falschZubereitet, komponente.gibZutat(), komponente.gibZubereitung());
                // return komponente.gibZutat()+" falsch zubereitet. Sollte
                // "+komponente.gibZubereitung()+" sein. :("; //falls nein, gebe aus wie die
                // Komponente haette zubereitet werden sollen.
            }
        }
        // System.out.println(serviertKlon);
        if (serviertKlon.length() != 0) { // ist ausserhalb der benoetigten Komponenten noch etwas uebrig?
            for (int i = 0; i < komponenten.size(); i++) { // handelt es sich um eine Zutat aus dem Rezept die nur zu
                                                           // oft da ist? Falls ja, gib sie aus (falls sie auch noch
                                                           // richtig zubereitet wurde mit der Zubereitungsart).
                RezeptKomponente komponente = komponenten.get(i);
                if (komponente.zutatIstVorhanden(serviertKlon)) {
                    return new Feedback(Comment.zuVielServiert, komponente.gibZutat(), komponente.gibZubereitung());
                    // return "Zu viele "+komponente.gibZutat()+komponente.gibZubereitung()+". :(";
                }
            }
            return new Feedback(Comment.falscheZutatEnthalten, serviertKlon.split("\\(")[0], "");
            // return serviert+" gehoert da nicht rein. :("; //falls es sich um unbekannte
            // Komponenten handelt, gib sie alle aus.
        }
        if (gewuerzt != null && gewuerzt == false) { // falls es auf die Wuerzung ankommt ueberpruefe sie, und gib aus,
                                                     // falls sie falsch ist.
            return new Feedback(Comment.falschGewuerzt, "", "");
            // return "Die Zutaten stimmen, aber leider ist es nicht richtig gewuerzt. :(";
        }
        if (gemeldeterFehler != null) { // falls ein Fehler während des Kochens geschehen ist, gib ihn aus. Zum
                                        // Beispiel: Zutatenverschwendung
            return new Feedback(gemeldeterFehler, "", "");
            // return gemeldeterFehler;
        }
        return new Feedback(Comment.richtig, "", "");
        // return "Schmeckt gut. :)"; //falls nichts falsch ist, sollte es gut schmecken
        // :).
    }

}
