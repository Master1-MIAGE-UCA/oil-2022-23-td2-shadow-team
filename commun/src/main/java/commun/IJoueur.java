package commun;


public class IJoueur {
    private String name;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Coup jouer(EtatDuJeu etat) {
        Coup coup = new Coup("lancer", this);
        coup.lancer();
        return coup;
    }
}
