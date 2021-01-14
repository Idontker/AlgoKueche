package tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;

import main.hilfsklassen.gui.MainFrame;
import main.hilfsklassen.gui.GUI;
import main.hilfsklassen.cooking.Feedback;
import main.Lehrling;

public abstract class FeedbackTest {

    protected Lehrling lehrling;

    @Before
    public void initTest() {
        // If your or your IDE complie the files right here, you can uncomment this line
        // Otherwise set this path to the hilfsklassen folder.
        MainFrame.pathToAlgoKueche = "C:/Users/Karol/proj/AlgoKueche/AlgoKueche/src/main/hilfsklassen/";
        GUI.runningTestcase = true;
        lehrling = new Traitor();
    }

    protected void wirKochenJetzt(String rezept) {
        lehrling.wirKochenJetzt(rezept);
    }

    protected void nimmAusSchrank(String zutat) {
        lehrling.nimmAusSchrank(zutat);
    }

    protected void stellZurueck() {
        lehrling.stellZutatZurueck();
    }

    protected void schneide() {
        lehrling.schneide();
    }

    protected void gibInTopf() {
        lehrling.gibInTopf();
    }

    protected void gibTopfAufTeller() {
        lehrling.gibTopfAufTeller();
    }

    protected void koche(int zeit) {
        lehrling.koche(zeit);
    }

    protected boolean istGewuerzt() {
        return lehrling.istGewuerzt();
    }

    protected void legAufTeller() {
        lehrling.gibZutatAufTeller();
    }

    protected void serviere() {
        lehrling.serviere();
    }

    protected void nsl(String zutat) {
        ((Traitor) lehrling).nsl(zutat);
    }

    protected Feedback feedback() {
        return ((Traitor) lehrling).getFeedback();
    }

    protected static ArrayList<int[]> perms(int n) {
        ArrayList<int[]> sols = new ArrayList<int[]>();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = i;
        }
        permsH(n, arr, sols);
        return sols;
    }

    public static void permsH(int n, int[] elements, ArrayList<int[]> perms) {

        if (n == 1) {
            perms.add(Arrays.copyOf(elements, elements.length));
        } else {
            for (int i = 0; i < n - 1; i++) {
                permsH(n - 1, elements, perms);
                if (n % 2 == 0) {
                    swap(elements, i, n - 1);
                } else {
                    swap(elements, 0, n - 1);
                }
            }
            permsH(n - 1, elements, perms);
        }
    }

    private static void swap(int[] input, int a, int b) {
        int tmp = input[a];
        input[a] = input[b];
        input[b] = tmp;
    }
}

class Traitor extends Lehrling {
    Traitor() {
        super();
    }

    public Feedback getFeedback() {
        return kunde.bewerte();
    }

    public void nsl(String zutat) {
        nimmAusSchrank(zutat);
        schneide();
        gibZutatAufTeller();
    }
}
