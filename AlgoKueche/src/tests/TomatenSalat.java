package tests;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;

public class TomatenSalat extends FeedbackTest {

    @Test
    public void richtig_1() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, kunde.feedback());
    }

    @Test
    public void richtig_2() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        legAufTeller();
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, kunde.feedback());
    }

    private void unfertigeZutatenServiert(String zutat1, String zutat2, boolean schneide1, boolean schneide2) {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank(zutat1);
        if (schneide1) {
            schneide();
        }
        legAufTeller();
        nimmAusSchrank(zutat2);
        if (schneide2) {
            schneide();
        }
        legAufTeller();
        serviere();
    }

    @Test
    public void unfertigeZutatenServiert_1() {
        unfertigeZutatenServiert("Tomate", "Salat", false, true);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void unfertigeZutatenServiert_2() {
        unfertigeZutatenServiert("Tomate", "Salat", true, false);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void unfertigeZutatenServiert_3() {
        unfertigeZutatenServiert("Salat", "Tomate", false, true);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void unfertigeZutatenServiert_4() {
        unfertigeZutatenServiert("Salat", "Tomate", true, false);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void unfertigeZutatenServiert_5() {
        unfertigeZutatenServiert("Tomate", "Salat", true, false);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void unfertigeZutatenServiert_6() {
        unfertigeZutatenServiert("Salat", "Tomate", false, true);
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_1() {
        wirKochenJetzt("Salat mit Tomaten");
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_2a() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_2b() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_2c() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_3a() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_3b() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_3c() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_3d() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }



    @Test
    public void serviereLeerenTeller_4a() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_4sb() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_4c() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Tomate");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void serviereLeerenTeller_4d() {
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void schneidenOhneZutat_1(){
        wirKochenJetzt("Salat mit Tomaten");
        schneide();
        Assert.assertEquals(Comment.schneidenOhneZutat, kunde.feedback());
    }

    @Test
    public void schneidenOhneZutat_2(){
        wirKochenJetzt("Salat mit Tomaten");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.schneidenOhneZutat, kunde.feedback());
    }

    @Test
    public void schneidenOhneZutat_3(){
        wirKochenJetzt("Salat mit Tomaten");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        schneide();
        serviere();
        Assert.assertEquals(Comment.schneidenOhneZutat, kunde.feedback());
    }

    @Test
    public void flascheZutatEnthalten_1(){
        wirKochenJetzt("Salat mit Tomaten");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, kunde.feedback());
    }

}
