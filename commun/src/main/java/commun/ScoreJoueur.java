package commun;

import java.util.*;


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


    // methode pour la calculer de score
    public int calculerScorePourCategorie(String categorieChoisie, List<Integer> des) {
        int score = 0;
        switch (categorieChoisie) {
            case "yams":
                score = 50;
                break;
            case "grandeSuite":
                score = 40;
                break;
            case "petiteSuite":
                score = 30;
                break;
            case "fullHouse":
                score = 25;
                break;
            case "carre":
                // Le score est la somme des 4 dés identiques
                for (int i = 1; i <= 6; i++) {
                    if (Collections.frequency(des, i) == 4) {
                        score = i * 4;
                        break;
                    }
                }
                break;
            case "brelan":
                // Le score est la somme des 3 dés identiques
                for (int i = 1; i <= 6; i++) {
                    if (Collections.frequency(des, i) == 3) {
                        score = i * 3;
                        break;
                    }
                }
                break;
            case "total1":
            case "total2":
            case "total3":
            case "total4":
            case "total5":
            case "total6":
                int num = Integer.parseInt(categorieChoisie.substring(5));
                score = Collections.frequency(des, num) * num;
                break;
            case "chance":
                for (int de : des) {
                    score += de;
                }
                break;
            default:
                throw new IllegalArgumentException("Catégorie inconnue : " + categorieChoisie);
        }
        return score;
    }


}

