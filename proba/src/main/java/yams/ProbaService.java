package yams;

import commun.constants.TypeCombinaison;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
public class ProbaService {
    private static final int NOMBRE_DE = 5;
    private static final double NOMBRE_FACE = 6;

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
