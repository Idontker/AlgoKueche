package tests;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;

public class einfachSalatTest extends FeedbackTest {

    @Test
    public void simpler_salat_1() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufdenTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, kunde.feedback());
    }

    @Test
    public void simpler_salat_2() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

    @Test
    public void simpler_salat_3() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufdenTeller();
        nimmAusSchrank("Salat");
        schneide();
        legAufdenTeller();
        serviere();
        Assert.assertEquals(Comment.zuVielServiert, kunde.feedback());
    }

    @Test
    public void simpler_salat_4() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        legAufdenTeller();
        serviere();
        Assert.assertEquals(Comment.unfertigeZutatenServiert, kunde.feedback());
    }

    @Test
    public void simpler_salat_5() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufdenTeller();
        nimmAusSchrank("Tomaten");
        schneide();
        legAufdenTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, kunde.feedback());
    }

    @Test
    public void simpler_salat_6() {
        wirKochenJetzt("simpler Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufdenTeller();
        nimmAusSchrank("Tomaten");
        legAufdenTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, kunde.feedback());
    }

}
