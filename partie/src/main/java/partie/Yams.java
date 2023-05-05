package partie;

public class Yams {
    private AIJoueur joueur1;
    private AIJoueur joueur2;
    private int scoreJoueur1;
    private int scoreJoueur2;

    public Yams(AIJoueur joueur1, AIJoueur joueur2) {
        this.joueur1 = joueur1;
        this.joueur2 = joueur2;
    }

    public void jouer() {
        int nbRounds = 10; // à modifier
        for (int i = 0; i < nbRounds; i++) {
            int scoreRoundjoueur1 = joueur1.jouerPartie();
            int scoreRoundjoueur2 = joueur2.jouerPartie();

            if (scoreRoundjoueur1 > scoreRoundjoueur2) {
                this.scoreJoueur1++;
            } else if (scoreRoundjoueur2 > scoreRoundjoueur1) {
                this.scoreJoueur2++;
            }
        }

        System.out.println("Final score: Player 1: " + this.scoreJoueur1 + " | Player 2: " + this.scoreJoueur2);
    }
}
