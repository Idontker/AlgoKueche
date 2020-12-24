package tests;

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

    protected void legAufdenTeller() {
        aktZutat = "leer";
        kunde.arbeitschritt("legAufdenTeller");
    }

    protected void serviere() {
        kunde.bewerte();
    }
}
