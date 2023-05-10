package commun;

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
        fini = (nbTours > 3);
        return fini;
    }


}
