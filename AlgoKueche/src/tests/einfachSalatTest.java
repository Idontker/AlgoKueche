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
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, kunde.feedback());
    }

}
