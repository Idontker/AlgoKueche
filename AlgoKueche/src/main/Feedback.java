package main;

public class Feedback {

    private Comment fehlerTyp;
    private String zutat;
    private String zubereitungSoll;

    public Feedback(Comment fehlerTyp, String zutat, String zubereitungSoll){
        this.fehlerTyp = fehlerTyp;
        this.zutat = zutat;
        this.zubereitungSoll = zubereitungSoll;
    }

    public String gibFeedbackString() {
        // TODO:
        switch (fehlerTyp) {
            case richtig:
                return "";
            case falsch:
                return "";
            case schneidenOhneZutat:
                return "";
            case serviereLeerenTeller:
                return "";
            case falscheZutatEnthalten:
                return "";
            case mehrAlsEineZutatInDerHand:
                return "";
            case unfertigeZutatenServiert:
                return "";
            case zuVielServiert:
                return "";
            case verschwendung:
                return "";
            case zutatUnbekannt:
                return "";
            case kochtLeerenTopf:
                return "";
            default:
                return null;

        }
    }
}
