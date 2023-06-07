package commun;


public class EtatJeuService {
    private int nombreDeTour = 1;
    public int getNombreDeTour() {
        return nombreDeTour;
    }

    public void setNombreDeTour(int nombreDeTour) {
        this.nombreDeTour = nombreDeTour;
    }
    public boolean estNombreDeTourFini(){
        return this.getNombreDeTour() > 13;
    }
    public void incrementeNombreDeTour(){
       this.nombreDeTour += 1;
    }
}
