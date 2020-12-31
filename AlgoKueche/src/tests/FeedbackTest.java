package tests;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import main.Feedback;

public abstract class FeedbackTest {
    protected Feedback kunde;
    protected String aktZutat;

    @Before
    public void initTest() {
        kunde = new Feedback();
    }

    protected void wirKochenJetzt(String rezept) {
        kunde.rezeptauswahl(rezept);
    }

    protected void nimmAusSchrank(String zutat) {
        aktZutat = zutat;
        kunde.arbeitschritt("nimmAusSchrank" + zutat);
    }

    protected void stellZurueck() {
        aktZutat = "leer";
        kunde.arbeitschritt("stellZurueck");
    }

    protected void schneide() {
        kunde.arbeitschritt("schneide" + aktZutat);
    }

    protected void gebeInTopf() {
        kunde.arbeitschritt("gebeInTopf" + aktZutat);
    }

    protected void koche(int zeit) {
        kunde.arbeitschritt("koche" + zeit);
    }

    protected void istGewuerzt(String s) {
        kunde.arbeitschritt("istGewuerzt" + s);
    }

    protected void legAufTeller() {
        aktZutat = "leer";
        kunde.arbeitschritt("legAufTeller");
    }

    protected void serviere() {
        kunde.bewerte();
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


    public static void main(String[] args){
        ArrayList<int[]> sols = perms(9);
        for(int[] a : sols){
            System.out.println(Arrays.toString(a));
        }
        System.out.println(sols.size());

    }
}
