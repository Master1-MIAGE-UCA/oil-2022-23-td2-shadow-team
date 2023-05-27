package commun;

import java.util.HashMap;
import java.util.Map;

public class ScoreJoueur {
    private int totalScore;
    private Map<String, Integer> categoriesScore;
    private Map<String, Boolean> categoriesUsed;

    public ScoreJoueur() {
        this.totalScore = 0;
        this.categoriesScore = new HashMap<>();
        this.categoriesUsed = new HashMap<>();
        String[] categories = {"total1", "total2", "total3", "total4", "total5", "total6", "brelan", "carre", "fullHouse", "grandeSuite", "yams", "chance"};
        for (String categorie : categories) {
            categoriesScore.put(categorie, 0);
            categoriesUsed.put(categorie, false);
        }
    }

    public int getTotalScore() {
        return totalScore;
    }

    public void setScoreCategorie(String categorie, int score) {
        if (!categoriesScore.containsKey(categorie) || categoriesUsed.get(categorie)) {
            throw new IllegalArgumentException("Catégorie inconnue ou déjà utilisée : " + categorie);
        }
        categoriesScore.put(categorie, score);
        categoriesUsed.put(categorie, true);
        totalScore += score;
    }

    public boolean isCategorieUsed(String categorie) {
        if (!categoriesUsed.containsKey(categorie)) {
            throw new IllegalArgumentException("Catégorie inconnue : " + categorie);
        }
        return categoriesUsed.get(categorie);
    }
}

