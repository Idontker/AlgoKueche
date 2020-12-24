import org.junit.Before;
import org.junit.Test;

public class FeedbackTest {
    private Feedback kunde;
    private String aktZutat;


    @Before
    public void initTest(){
        kunde = new Feedback();
    }

    @Test
    public void simpler_salat_1() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        
    }




    public void wirKochenJetzt(String rezept){
        kunde.rezeptauswahl(rezept);
    }

    public void nimmAusSchrank(String zutat) {
        aktZutat = zutat;
        kunde.arbeitschritt("nimmAusSchrank" + zutat);
    }

    public void stellZurueck() {
        aktZutat = "leer";
        kunde.arbeitschritt("stellZurueck");
    }

    public void schneide() {
        kunde.arbeitschritt("schneide" + aktZutat);
    }

    public void gebeInTopf() {
        kunde.arbeitschritt("gebeInTopf" + aktZutat);
    }

    public void koche(int zeit) {
        kunde.arbeitschritt("koche" + zeit);
    }

    public void istGewuerzt(String s) {
        kunde.arbeitschritt("istGewuerzt" + s);
    }

    public void serviere(){
        kunde.bewerte();
    }

}
