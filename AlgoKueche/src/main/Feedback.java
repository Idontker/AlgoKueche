package main;

public class Feedback {

    private Comment fehlerTyp;
    private String zutat;
    private String zubereitungsArt;

    public Feedback(Comment fehlerTyp, String zutat, String zubereitungsArt){
        this.fehlerTyp = fehlerTyp;
        this.zutat = zutat;
        this.zubereitungsArt = zubereitungsArt;
    }

    public String gibFeedbackString() {
        switch (fehlerTyp) {
            case richtig:
                return "Schmeckt gut. :)";
            case falsch:
                return "Hier lief etwas falsch. :(";
            case schneidenOhneZutat:
                return "Das Gericht passt, allerdings hast du schneiden ohne Zutat aufgerufen. :(";
            case serviereLeerenTeller:
                return "Du hast einen leeren Teller serviert. :(";
            case falscheZutatEnthalten:
                return zutat+" gehoert nicht in das Gericht. :(";
            case mehrAlsEineZutatInDerHand:
                return "Das Gericht passt, allerdings hast du eine Zutat in die Hand genommen, waehrend du schon eine Zutat in der Hand hattest. :(";
            case unfertigeZutatenServiert:
                return "Eine der Zutaten war leider noch nicht fertig gekocht. :(";
            case zuVielServiert:
                return "Du hast zu viel "+zutat+" ("+zubereitungsArt+") auf den Teller gelegt. :(";
            case verschwendung:
                return "Das Gericht passt, allerdings hast du Zutaten verschwendet. :(";
            case zutatUnbekannt:
                return "Das Gericht passt, allerdings hast du versucht eine unbekannte Zutat zu nehmen. :(";
            case kochtLeerenTopf:
                return "Das Gericht passt, allerdings hast du einen leeren Topf gekocht. :(";
			case falschGewuerzt:
				return "Die Zutaten stimmen, aber leider ist es nicht richtig gewuerzt. :(";
			case fehlendeZutat:
				return "Es fehlt "+zutat+" ("+zubereitungsArt+"). :(";
			case falschZubereitet:
				return zutat+" wurde falsch zubereitet. Sie sollte eigentlich "+zubereitungsArt+" sein. :(";
            default:
                return null;

        }
    }
}
