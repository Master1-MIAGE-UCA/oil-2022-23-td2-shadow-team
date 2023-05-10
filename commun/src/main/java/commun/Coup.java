package commun;

public class Coup {

    String unAttributPourQueLeJSONNeSoitPasVide = "";
    IJoueur IJoueur;

    public Coup() {
        this("vide", null);
    }

    public Coup(String s, IJoueur j) {
        setUnAttributPourQueLeJSONNeSoitPasVide(s);
        setIJoueur(j);
    }

    public String getUnAttributPourQueLeJSONNeSoitPasVide() {
        return unAttributPourQueLeJSONNeSoitPasVide;
    }

    public void setUnAttributPourQueLeJSONNeSoitPasVide(String unAttributPourQueLeJSONNeSoitPasVide) {
        this.unAttributPourQueLeJSONNeSoitPasVide = unAttributPourQueLeJSONNeSoitPasVide;
    }

    public String toString() {
        return "["+ IJoueur.getName()+" joue : "+getUnAttributPourQueLeJSONNeSoitPasVide()+"]";
    }

    public void setIJoueur(IJoueur IJoueur) {
        this.IJoueur = IJoueur;
    }

    public IJoueur getIJoueur() {
        return IJoueur;
    }
}
