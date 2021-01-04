package tests;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;

public class EinfachSalatTest extends FeedbackTest {

    @Test
    public void simpler_salat_1() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, feedback());
    }

    @Test
    public void simpler_salat_2() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback());
    }

    @Test
    public void simpler_salat_3() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.zuVielServiert, feedback());
    }

    @Test
    public void simpler_salat_4() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.unfertigeZutatenServiert, feedback());
    }

    @Test
    public void simpler_salat_5() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomaten");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, feedback());
    }

    @Test
    public void simpler_salat_6() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomaten");
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, feedback());
    }

}
