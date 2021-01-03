package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;

public class GriechischerSalat extends FeedbackTest {

    private void richtig(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nimmAusSchrank(z1);
        schneide();
        legAufTeller();
        nimmAusSchrank(z2);
        schneide();
        legAufTeller();
        nimmAusSchrank(z3);
        schneide();
        legAufTeller();
        nimmAusSchrank(z4);
        schneide();
        legAufTeller();
        nimmAusSchrank(z5);
        schneide();
        legAufTeller();
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        serviere();
    }

    @Test
    public void richtigTestAll() {
        String zutat[] = new String[] { "Tomate", "Zwiebel", "Gruke", "Oliven", "Feta" };
        ArrayList<int[]> perms = FeedbackTest.perms(zutat.length);
        for (int[] p : perms) {
            richtig(zutat[p[0]],zutat[p[1]],zutat[p[2]],zutat[p[3]],zutat[p[4]]);
            Assert.assertEquals(Comment.richtig, kunde.feedback());
        }
    }
}
