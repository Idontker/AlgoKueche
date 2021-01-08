package tests;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;

public class EintopfTest extends FeedbackTest {

    @Test
    public void Eintopf_vegetarisch_1() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.richtig, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_2() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_3() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.zuVielServiert, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_4() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.falschZubereitet, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_5() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.falscheZutatEnthalten, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_6() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gebeInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gebeInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.fehlendeZutat, feedback().getComment());
    }

}
