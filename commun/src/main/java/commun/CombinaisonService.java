package commun;

import commun.constants.TypeCombinaison;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.*;
public class CombinaisonService extends FeuilleYams{
    private static final int NOMBRE_DE = 5;
    private static final int NOMBRE_FACE = 6;
    public CombinaisonService(TypeCombinaison typeCombinaison){
        this.setTypeCombinaison(typeCombinaison);
    }

    public void calculeScore(List<Integer> des){
        int score = switch (this.getTypeCombinaison()) {
            case BRELAN -> calculeScoreBrelanOuCarreOuYams(des, 3);
            case CARRE -> calculeScoreBrelanOuCarreOuYams(des, 4);
            case FULL -> calculeScoreFull(des);
            case PETITE_SUITE -> calculeScorePetiteSuiteOuGrandeSuite(des, 30);
            case GRANDE_SUITE -> calculeScorePetiteSuiteOuGrandeSuite(des, 40);
            case YAMS -> calculeScoreBrelanOuCarreOuYams(des, 5);
            case CHANCE -> calculeScoreChance(des);
            case TOTAL_1 -> this.obtenirOccurrence(des, 1);
            case TOTAL_2 -> this.obtenirOccurrence(des, 2) * 2;
            case TOTAL_3 -> this.obtenirOccurrence(des, 3) * 3;
            case TOTAL_4 -> this.obtenirOccurrence(des, 4) * 4;
            case TOTAL_5 -> this.obtenirOccurrence(des, 5) * 5;
            case TOTAL_6 -> this.obtenirOccurrence(des, 6) * 6;
        };
        System.out.println(this.getTypeCombinaison() +": "+score);
        this.setScore(score);
    }

    private int calculeScoreChance(List<Integer> des) {
        int score = 0;
       if (des != null && !CollectionUtils.isEmpty(des)){
           score = des.stream().mapToInt(Integer::intValue).sum();
       }
       return score;
    }


    private int calculeScorePetiteSuiteOuGrandeSuite(List<Integer> des, int points) {
        int score = 0;
        boolean notBreaked = true;
        if (des != null && !CollectionUtils.isEmpty(des)){
            Collections.sort(des);
            int i = 1;
            while (i < des.size()){
                if(des.get(i) - des.get(i - 1) != 1){
                    notBreaked = false;
                    break;
                }
                i++;
            }
        }
        if (notBreaked) score = points;

        return score;
    }

    private int calculeScoreFull(List<Integer> des) {
        int score = 0;
        int brelan = this.calculeScoreBrelanOuCarreOuYams(des, 3);
        int paire = this.calculeScoreBrelanOuCarreOuYams(des, 2);
        if (brelan != 0 && paire != 0){
            score = 25;
        }
        return score;
    }

    private int calculeScoreBrelanOuCarreOuYams(List<Integer> des, int brelanOuCarreOuYams) {
        int score = 0;
        Map<Integer, Integer> occurences = this.compterOccurenceDe(des);
        if (occurences != null && !CollectionUtils.isEmpty(occurences)){
            for (Map.Entry<Integer, Integer> entry : occurences.entrySet()) {
                if (brelanOuCarreOuYams < 5 && entry.getValue() == brelanOuCarreOuYams ){
                    score = entry.getKey() * brelanOuCarreOuYams;
                    break;
                }else if (entry.getValue() == brelanOuCarreOuYams){
                    score = 50;
                    break;
                }
            }
        }
        return score;
    }

    public Map<Integer, Integer> compterOccurenceDe(List<Integer> des){
        Map<Integer, Integer> occurence = new HashMap<>();
        if (des != null && !CollectionUtils.isEmpty(des)){
            for (int de : des) {
                occurence.put(de, occurence.getOrDefault(de, 0) + 1);
            }
        }
        return occurence;
    }
    public double calculeProbabilite(List<Integer> des, TypeCombinaison typeCombinaison){

        int compteur = 0;
        int cible = 0;
        if (des == null || des.isEmpty()){
            return 0.0;
        }
        switch (typeCombinaison){

            case BRELAN:
                cible = 3;
                break;
            case CARRE:
                cible = 4;
                break;
            case FULL:
                cible = 3;
                break;
            case PETITE_SUITE:
                cible = 4;
                break;
            case GRANDE_SUITE:
                cible = 5;
                break;
            case YAMS:
                cible = 5;
                break;
            case CHANCE:
                return 1.0;
        }

        compteur = obtenirOccurrence(des, cible);

        return obtenirCombinaisonProba(compteur);
    }

    private double obtenirCombinaisonProba(int compteur) {

        int nombreDeRestant = NOMBRE_DE - compteur;
        int resultatPossible = (int) Math.pow(NOMBRE_FACE, nombreDeRestant);

        return  (double) compteur / NOMBRE_DE * (1.0 / resultatPossible);
    }

    public int obtenirOccurrence(List<Integer> des, int cible) {
        if (des == null || des.isEmpty()) {
            return 0;
        }
        return Collections.frequency(des, cible);
    }
}
