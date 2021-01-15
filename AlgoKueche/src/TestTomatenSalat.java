import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import hilfsklassen.cooking.Comment;

public class TestTomatenSalat extends TestMain {

    @Test
    public void richtig_1() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, feedback().getComment());
    }

    @Test
    public void richtig_2() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        legAufTeller();
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, feedback().getComment());
    }

    private void falschZubereitet(String zutat1, String zutat2, boolean schneide1, boolean schneide2) {
        wirKochenJetzt("Tomatensalat");
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
    public void falschZubereitet_1() {
        falschZubereitet("Tomate", "Salat", false, true);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void falschZubereitet_2() {
        falschZubereitet("Tomate", "Salat", true, false);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void falschZubereitet_3() {
        falschZubereitet("Salat", "Tomate", false, true);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void falschZubereitet_4() {
        falschZubereitet("Salat", "Tomate", true, false);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void falschZubereitet_5() {
        falschZubereitet("Tomate", "Salat", true, false);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void falschZubereitet_6() {
        falschZubereitet("Salat", "Tomate", false, true);
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_1() {
        wirKochenJetzt("Tomatensalat");
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_2a() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_2b() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_2c() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_3a() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_3b() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_3c() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        gibInTopf();
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_3d() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        gibInTopf();
        nimmAusSchrank("Tomate");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_4a() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_4sb() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_4c() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Tomate");
        schneide();
        gibInTopf();
        nimmAusSchrank("Salat");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void serviereLeerenTeller_4d() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        gibInTopf();
        nimmAusSchrank("Tomate");
        schneide();
        stellZurueck();
        nimmAusSchrank("Kartoffel");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void schneidenOhneZutat_1() {
        wirKochenJetzt("Tomatensalat");
        schneide();
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("eine Tomate");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.schneidenOhneZutat, feedback().getComment());
    }

    @Test
    public void schneidenOhneZutat_2() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        schneide();
        nimmAusSchrank("eine Tomate");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.schneidenOhneZutat, feedback().getComment());
    }

    @Test
    public void schneidenOhneZutat_3() {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("eine Tomate");
        schneide();
        legAufTeller();
        schneide();
        serviere();
        Assert.assertEquals(Comment.schneidenOhneZutat, feedback().getComment());
    }

    @Test
    public void flascheZutatEnthalten_All() {
        String z[] = new String[] {"Tomate","ein Salat", "Feta"};
        ArrayList<int[]> ps = perms(z.length);
        for (int[] p : ps) {
            flascheZutatEnthalten(z[p[0]],z[p[1]],z[p[2]]);
            String call = "flascheZutatEnthalten(" + z[p[0]] + ", " + z[p[1]]+ ", "+ z[p[2]]+ ")";
            System.out.println(call);
            Assert.assertEquals(call,Comment.falscheZutatEnthalten, feedback().getComment());
        }
    }

    private void flascheZutatEnthalten(String z1, String z2, String z3) {
        wirKochenJetzt("Tomatensalat");
        nimmAusSchrank(z1);
        schneide();
        legAufTeller();
        nimmAusSchrank(z2);
        schneide();
        legAufTeller();
        nimmAusSchrank(z3);
        schneide();
        legAufTeller();
        serviere();
    }

}
