package tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import main.Comment;
import main.Feedback;

public class GriechischerSalat extends FeedbackTest {

    private void richtig_1(String z1, String z2, String z3, String z4, String z5) {
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

    private void richtig_0(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nsl(z1);
        nsl(z2);
        nsl(z3);
        nsl(z4);
        nsl(z5);
        nimmAusSchrank("Oel");
        legAufTeller();
        serviere();
    }

    private void richtig_2(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nsl(z1);
        nsl(z2);
        nsl(z3);
        nsl(z4);
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        nsl(z5);
        serviere();
    }

    private void richtig_3(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nsl(z1);
        nsl(z2);
        nsl(z3);
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        nsl(z4);
        nsl(z5);
        serviere();
    }

    private void richtig_4(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nsl(z1);
        nsl(z2);
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        nsl(z3);
        nsl(z4);
        nsl(z5);
        serviere();
    }

    private void richtig_5(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nsl(z1);
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        nsl(z2);
        nsl(z3);
        nsl(z4);
        nsl(z5);
        serviere();
    }

    private void richtig_6(String z1, String z2, String z3, String z4, String z5) {
        wirKochenJetzt("griechischer Salat");
        nimmAusSchrank("Oel");
        legAufTeller();
        stellZurueck();
        nsl(z1);
        nsl(z2);
        nsl(z3);
        nsl(z4);
        nsl(z5);
        serviere();
    }

    @Test
    public void richtigTestAll_1() {
        String zutat[] = new String[] { "Tomate", "Zwiebel", "Gurke", "Oliven", "Feta" };
        ArrayList<int[]> perms = FeedbackTest.perms(zutat.length);
        for (int[] p : perms) {
            richtig_0(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_1(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_2(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_3(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_4(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_5(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
        for (int[] p : perms) {
            richtig_6(zutat[p[0]], zutat[p[1]], zutat[p[2]], zutat[p[3]], zutat[p[4]]);
            String call = "richtig(" + zutat[p[0]] + ", " + zutat[p[1]] + ", " + zutat[p[2]] + ", " + zutat[p[3]] + ", "
                    + zutat[p[4]] + ")";
            Feedback f = feedback();
            Assert.assertEquals(call + "\t" + f.gibFeedbackString(), Comment.richtig, f.getComment());
        }
    }
}
