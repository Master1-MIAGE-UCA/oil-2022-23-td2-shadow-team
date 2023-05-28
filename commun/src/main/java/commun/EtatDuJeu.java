package commun;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EtatDuJeu {

    int nbTours = 1;
    boolean fini = false;




    public int getNbTours() {
        return nbTours;
    }

    public void setNbTours(int nbTours) {
        this.nbTours = nbTours;
    }

    public boolean isFini() {
        fini = (nbTours > 13);
        return fini;
    }





    @Override
    public String toString() {
        return "EtatDuJeu{" + "nbTours=" + nbTours + ", fini=" + fini + '}';
    }




}
