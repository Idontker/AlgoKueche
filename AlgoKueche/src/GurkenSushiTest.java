import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import hilfsklassen.cooking.Comment;
import hilfsklassen.cooking.Feedback;

public class GurkenSushiTest extends FeedbackTest {

    @Test
    public void richtig() {
        ArrayList<int[]> perms = perms(3);
        for (int[] p: perms) {
            wirKochenJetzt("Sushi mit Gurke");
            for (int i = 0; i < 6; i++) {
                for (int j = 0; j < p.length; j++) {
                    execBlock(p[j]);
                }
            }
            serviere();
            System.out.println();
            Feedback f = feedback();
            Assert.assertEquals(f.gibFeedbackString(), Comment.richtig, f.getComment());
       
        }
        
    }

    private void execBlock(int b) {
        if (b == 0) {
            nimmAusSchrank("Gurke");
            schneide();
            legAufTeller();
            System.out.println("Gurke geschnitten und auf den Teller gelegt");
        } else if (b == 1) { // theoretisch sollte man dies auch trennen koennen :S
            nimmAusSchrank("Reis");
            gibInTopf();
            System.out.println("Reis in den Topf gegeben");
            koche(20);
            gibTopfAufTeller();
            System.out.println("gekocht 20 min");
        } else { // if (b == 3){
            nimmAusSchrank("Noriblatt");
            legAufTeller();
            System.out.println("Gurke auf den Teller gelegt");
        }
    }
}
