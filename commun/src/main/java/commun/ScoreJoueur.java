package commun;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ScoreJoueur {
    private int totalScore;
    private Map<String, Integer> categoriesScore;
    private Map<String, Boolean> categoriesUsed;

    public ScoreJoueur() {
        this.totalScore = 0;
        this.categoriesScore = new HashMap<>();
        this.categoriesUsed = new HashMap<>();
        String[] categories = {"total1", "total2", "total3", "total4", "total5", "total6", "brelan", "carre", "fullHouse","petiteSuite","grandeSuite","yams", "chance"};
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

    public List<String> getCategoriesDisponibles(List<String> MesChoixPoussibles) {
        List<String> categoriesDisponibles = new ArrayList<>();
        for (String categorie : MesChoixPoussibles) {
            if (categoriesUsed.containsKey(categorie) && !categoriesUsed.get(categorie)) {
                categoriesDisponibles.add(categorie);
            }
        }
        return categoriesDisponibles;
    }


    public boolean isCategorieUsed(String categorie) {
        if (!categoriesUsed.containsKey(categorie)) {
            throw new IllegalArgumentException("Catégorie inconnue : " + categorie);
        }
        return categoriesUsed.get(categorie);
    }

    public void setcalculScore(List<Integer> des)
    {
        RegleYams regle = new RegleYams();

        // a faire

    }
    public   Map<String, Boolean> getListofCategorieused(){
        return this.categoriesUsed;
    }



    //deux methode pour l'affichage de tableau de score
    public String affichagetableauScore() {
        StringBuilder sb = new StringBuilder();

        // Ligne d'en-tête
        String headerFormat = "|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|\n";
        sb.append(String.format(headerFormat, "total1", "total2", "total3", "total4", "total5", "total6", "brelan", "carre", "fullHouse","petiteSuite","grandeSuite", "yams", "chance"));

        // Ligne de score
        String scoreFormat = "|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|%-10s|\n";
        sb.append(String.format(scoreFormat,
                formatScore("total1"),
                formatScore("total2"),
                formatScore("total3"),
                formatScore("total4"),
                formatScore("total5"),
                formatScore("total6"),
                formatScore("brelan"),
                formatScore("carre"),
                formatScore("fullHouse"),
                formatScore("petiteSuite"),
                formatScore("grandeSuite"),
                formatScore("yams"),
                formatScore("chance")
        ));

        return sb.toString();
    }

    private String formatScore(String categorie) {
        if (categoriesUsed.get(categorie)) {
            return String.valueOf(categoriesScore.get(categorie));
        } else {
            return "X";
        }
    }

    public List<String> getCategoriesNonUtilisees() {
        List<String> categoriesNonUtilisees = new ArrayList<>();
        for (String categorie : this.categoriesUsed.keySet()) {
            if (!this.isCategorieUsed(categorie)) {
                categoriesNonUtilisees.add(categorie);
            }
        }
        return categoriesNonUtilisees;
    }

}

