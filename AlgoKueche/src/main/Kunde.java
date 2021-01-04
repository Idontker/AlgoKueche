package main;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.ArrayList;

public class Kunde {
    private String s;
    private String regex;
    private Pattern pattern;
    private Boolean b;
    private int max;
    private int curr;
    private ArrayList<ErrorCase> errorCases;

    public Kunde() {
        // leer
    }

    public void giveMeRecipeToCheck(String t1, int t2) {
        s = "";
        curr = 0;
        regex = t1;
        max = t2;
        b = null;
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        errorCases = new ArrayList<ErrorCase>();
    }

    public void giveMeRecipeToCheck(String t1, int t2, Boolean t3) {
        s = "";
        curr = 0;
        regex = t1;
        max = t2;
        b = t3;
        pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        errorCases = new ArrayList<ErrorCase>();
    }

    public void addErrorCase(String t1, String t2) {
        errorCases.add(new ErrorCase(t1, t2));
    }

    public String arbeitsschritt(String t) {
        s += t;
        curr++;
        if (curr >= max) {
            return "Das Rezept hat zu lange gedauert. Mittlerweile ist der Kunde gegangen.";
        }
        return null;
    }

    public boolean checkIfNotDisgusting() {
        if (b != null && !b) {
            return false;
        }
        Matcher matcher = pattern.matcher(s);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }

    public void meldeFehler(Comment c) {
        // TODO
    }

    public String whyIsItDisgusting() {
        if (b != null && !b) {
            return "It's bland!";
        }
        for (int i = 0; i < errorCases.size(); i++) {
            String t = errorCases.get(i).isThisTheError(s);
            if (t != null) {
                return t;
            }
        }
        return null;
    }

    private class ErrorCase {
        private Pattern pattern;
        private String error;

        private ErrorCase(String t1, String t2) {
            pattern = Pattern.compile(t1, Pattern.CASE_INSENSITIVE);
            error = t2;
        }

        private String isThisTheError(String s) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.find()) {
                return error;
            }
            return null;
        }
    }

    public void rezeptauswahl(String rezept) {
        // TODO
    }

    public Comment bewerte() {
        // TODO
        return null;
    }

}