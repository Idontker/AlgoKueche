import org.junit.Assert;
import org.junit.Test;
import hilfsklassen.cooking.Comment;

public class TestEintopf extends TestMain {

    @Test
    public void Eintopf_vegetarisch_1() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
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
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
        koche(10);
        serviere();
        Assert.assertEquals(Comment.serviereLeerenTeller, feedback().getComment());
    }

    @Test
    public void Eintopf_vegetarisch_3() {
        wirKochenJetzt("Eintopf(vegetarisch)");
        nimmAusSchrank("Kartoffel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Kartoffel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
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
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
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
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        nimmAusSchrank("Paprika");
        schneide();
        gibInTopf();
        koche(10);
        gibTopfAufTeller();
        nimmAusSchrank("Tomate");
        schneide();
        gibInTopf();
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
        gibInTopf();
        nimmAusSchrank("Zwiebel");
        schneide();
        gibInTopf();
        koche(10);
        gibTopfAufTeller();
        serviere();
        Assert.assertEquals(Comment.fehlendeZutat, feedback().getComment());
    }

}
