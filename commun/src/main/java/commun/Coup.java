package commun;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Coup {

    String unAttributPourQueLeJSONNeSoitPasVide = "";
    IJoueur IJoueur;

    private RegleYams yams = new RegleYams();;
    Random rand = new SecureRandom();

    private List<Integer> des = new ArrayList<>(); // définition de la liste des dés


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

    public List<Integer> getDes() { return des; }

    public List<String> getCategories()
    {
        return  yams.determinerCategories(des);
    }

    public void lancer() {
        for (int i = 0; i < 5; i++) {
            int chiffre = (int) (Math.random() * 6) + 1; // Générer un chiffre aléatoire entre 1 et 6
            des.add(chiffre); // Ajouter le chiffre à la liste des dés
        }
        System.out.println("Les dés après le lancer : " + obtenirRepresentationDes());
        System.out.println("Catégories possibles : " + yams.determinerCategories(des));
    }

    public void relancer(List<Integer> listeARelancer) {
        for (int i = 0; i < des.size(); i++) {
            if (listeARelancer.get(i) == 1) {
                int chiffre = (int) (Math.random() * 6) + 1; // Générer un nouveau chiffre aléatoire entre 1 et 6
                des.set(i , chiffre); // Remplacer le chiffre du dé numéro i par le nouveau chiffre
            }
        }
        System.out.println("Les dés après le lancer : " + obtenirRepresentationDes());
        System.out.println("Catégories possibles : " + yams.determinerCategories(des));
    }

    public String obtenirRepresentationDes() {
        StringBuilder representation = new StringBuilder();
        representation.append("[ ");

        for (int i = 0; i < des.size(); i++) {
            representation.append("| ").append(des.get(i)).append(" |");

            if (i < des.size() - 1) {
                representation.append(", ");
            }
        }

        representation.append(" ]");

        return representation.toString();
    }
    public  List<Integer> ListeARelancer()
    {
        List<Integer> listeARelancer = new ArrayList<>();
        for(int i = 0; i < 5; i++) {
            listeARelancer.add(rand.nextInt(2)); // ajout d'un chiffre aléatoire qui est soit 0 soit 1
        }
        return listeARelancer;
    }



}
