package commun;

public class Coup {

    String unAttributPourQueLeJSONNeSoitPasVide = "";
    YamsPlayer yamsPlayer;

    public Coup() {
        this("vide", null);
    }

    public Coup(String s, YamsPlayer j) {
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
        return "["+ yamsPlayer.getName()+" joue : "+getUnAttributPourQueLeJSONNeSoitPasVide()+"]";
    }

    public void setIJoueur(YamsPlayer yamsPlayer) {
        this.yamsPlayer = yamsPlayer;
    }

    public YamsPlayer getIJoueur() {
        return this.yamsPlayer;
    }
}
