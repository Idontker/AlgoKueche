package main.hilfsklassen.cooking;

public class Feedback {

    private Comment fehlerTyp;
    private String zutat;
    private String zubereitungsArt;

    private String chefkochName = "Albert";

    public Feedback(Comment fehlerTyp, String zutat, String zubereitungsArt) {
        this.fehlerTyp = fehlerTyp;
        this.zutat = zutat.substring(0, 1).toUpperCase() + zutat.substring(1);
        this.zubereitungsArt = zubereitungsArt;
    }

    public Comment getComment() {
        return fehlerTyp;
    }

    /**
     * @return 0 == negativ, 1 == neutral, 2 == positiv
     */
    public int bewertungsKategorie() {
        switch (fehlerTyp) {
            case richtig:
                return 2; // positiv
            case kochtLeerenTopf:
            case mehrAlsEineZutatInDerHand:
            case schneidenOhneZutat:
            case verschwendung:
            case zutatUnbekannt:
                return 1; // neutral
            case falsch:
            case falscheZutatEnthalten:
            case falschGewuerzt:
            case falschZubereitet:
            case fehlendeZutat:
            case serviereLeerenTeller:
            case unfertigeZutatenServiert:
            case zuVielServiert:
                return 0; // negativ
            default:
                return -1;
        }

    }

    public String gibFeedbackString() {
        switch (fehlerTyp) {
            case richtig:
                return "Schmeckt gut. :]";
            case falsch:
                return "Hier lief etwas falsch. :[";
            case schneidenOhneZutat:
                return chefkochName + ": Das Gericht passt, allerdings hast du schneiden ohne Zutat aufgerufen. :[";
            case rollenOhneZutat:
                return chefkochName + ": Das Gericht passt, allerdings hast du rollen ohne Zutat aufgerufen. :[";
            case serviereLeerenTeller:
                return "Du hast einen leeren Teller serviert. :[";
            case falscheZutatEnthalten:
                return zutat + " gehoert nicht in das Gericht. :[";
            case mehrAlsEineZutatInDerHand:
                return chefkochName
                        + ": Das Gericht passt, allerdings hast du eine Zutat in die Hand genommen, waehrend du schon eine Zutat in der Hand hattest. :[";
            case unfertigeZutatenServiert:
                return "Eine der Zutaten war leider noch nicht fertig gekocht. :[";
            case zuVielServiert:
                return "Du hast zu viel " + zutat + "auf den Teller gelegt. :[";
            case verschwendung:
                return chefkochName + ": Das Gericht passt, allerdings hast du Zutaten verschwendet. :[";
            case zutatUnbekannt:
                return chefkochName
                        + ": Das Gericht passt, allerdings hast du versucht eine unbekannte Zutat zu nehmen. :[";
            case kochtLeerenTopf:
                return chefkochName + ": Das Gericht passt, allerdings hast du einen leeren Topf gekocht. :[";
            case falschGewuerzt:
                return "Die Zutaten stimmen, aber leider ist es nicht richtig gewuerzt. :[";
            case fehlendeZutat:
                return "Es fehlt " + zutat + ". :[";
            case falschZubereitet:
                return zutat + " wurde falsch zubereitet. Sollte eigentlich " + zubereitungsArt + " sein. :[";
            default:
                return "[Error]";
        }
    }
}
