package tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;

import main.Comment;
import main.GUI;
import main.Lehrling;

public abstract class FeedbackTest {

    protected Lehrling lehrling;

    @Before
    public void initTest() {
        lehrling = new Traitor();
    }

    protected void wirKochenJetzt(String rezept) {
        lehrling.wirKochenJetzt(rezept);
    }

    protected void nimmAusSchrank(String zutat) {
        lehrling.nimmAusSchrank(zutat);
    }

    protected void stellZurueck() {
        lehrling.stellZurueck();
    }

    protected void schneide() {
        lehrling.schneide();
    }

    protected void gebeInTopf() {
        lehrling.gebeInTopf();
    }

    protected void koche(int zeit) {
        lehrling.koche(zeit);
    }

    protected boolean istGewuerzt() {
        return lehrling.istGewuerzt();
    }

    protected void legAufTeller() {
        lehrling.gebeAufTeller();
    }

    protected void serviere() {
        lehrling.serviere();
    }

    protected Comment feedback() {
        return ((Traitor) lehrling).getComment();
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

    public static void main(String[] args) {
        ArrayList<int[]> sols = perms(9);
        for (int[] a : sols) {
            System.out.println(Arrays.toString(a));
        }
        System.out.println(sols.size());

    }
}

class Traitor extends Lehrling {
    Traitor() {
        super();
        animation = GUI.startDummyGUI();
    }

    public Comment getComment() {
        return kunde.bewerte();
    }
}