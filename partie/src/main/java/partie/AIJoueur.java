package partie;

import java.util.Random;

public class AIJoueur {
    private String nom;
    private int difficulte;

    public AIJoueur(String nom, int difficulte) {
        this.nom = nom;
        this.difficulte = difficulte;
    }

    public int jouerPartie() {
        int scorePartie = 0;
        for (int i = 0; i < 5; i++) {
            int resultat = lancementDes();
            scorePartie += resultat;
        }

        return scorePartie;
    }

    private int lancementDes() {
        Random random = new Random();
        int desValeur = random.nextInt(6) + 1;

        // Ici, vous pouvez ajouter de la logique pour simuler les choix de l'IA en fonction de la difficulté
        // Par exemple, si la difficulté est élevée, l'IA peut conserver les dés qui ont une valeur supérieure à 3,
        // sinon elle peut tout conserver, ou elle peut utiliser une stratégie différente selon les règles de votre jeu.

        return desValeur;
    }
}
