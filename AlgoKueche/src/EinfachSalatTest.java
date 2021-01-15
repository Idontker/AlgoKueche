import org.junit.Assert;
import org.junit.Test;
import hilfsklassen.cooking.Comment;

public class EinfachSalatTest extends FeedbackTest {

    @Test
    public void simpler_salat_1() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, feedback().getComment());
    }

    @Test
    public void simpler_salat_2() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        schneide();
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void simpler_salat_3() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.zuVielServiert, feedback().getComment());
    }

    @Test
    public void simpler_salat_4() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void simpler_salat_5() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomaten");
        schneide();
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, feedback().getComment());
    }

    @Test
    public void simpler_salat_6() {
        wirKochenJetzt("reiner Salat");
        nimmAusSchrank("Salat");
        schneide();
        legAufTeller();
        nimmAusSchrank("Tomaten");
        legAufTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, feedback().getComment());
    }

}
