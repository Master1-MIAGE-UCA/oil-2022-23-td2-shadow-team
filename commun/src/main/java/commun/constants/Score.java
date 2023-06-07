package commun.constants;

public enum Score {
    SCORE_CARRE(20),
    SCORE_FULL(20),
    SCORE_YAMS(20),
    SCORE_BRELAN(20),
    SCORE_PETITE_SUITE(20),
    SCORE_GRANDE_SUITE(20),
    SCORE_CHANCE(20);
    private final int score;

    Score(int score) {
        this.score = score;
    }
    public int getScore(){
       return this.score;
    }
}
