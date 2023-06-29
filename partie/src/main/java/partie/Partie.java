package partie;


import commun.*;
import commun.constants.TypeCombinaison;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;


import java.security.SecureRandom;
import java.util.*;

@Component
public class Partie {
    private final EtatJeuService etatJeuService = new EtatJeuService();
    LinkedList<YamsPlayer> players = new LinkedList<>();
    LinkedList<Map<TypeCombinaison, CaseYams>> feuilleDesJoueurs = new LinkedList<>();
    private final SecureRandom random = new SecureRandom();
    private List<Integer> des = new ArrayList<>();

    private int nombreRelance = 1;

    public void addPlayer(YamsPlayer player) {
        this.players.add(player);
    }

    /**
     * code à mieux structurer, ne pas mettre les sout ici (séparer vue / modèle)
     * passer plus de paramètre pour jouer, etc.
     */

    public void demarreGame() {
        this.initFeuilleYams();
        System.out.println("Nombre joueur "+this.players.size());
        while (!this.etatJeuService.estNombreDeTourFini()) {
            if (this.players != null && !CollectionUtils.isEmpty(this.players)) {

                for (int i = 0 ; i < this.players.size(); i++) {
                    this.lanceDes(new ArrayList<>());
                    this.etatJeuService.setDes(this.des);
                    this.etatJeuService.setFeuilleYams(this.feuilleDesJoueurs.get(i));
                    System.out.println("Player name "+this.players.get(i).getName());
                    Decision decision = this.players.get(i).play(etatJeuService);
                    this.remplirFeuillePlayer(decision, i);
                }
            }
            this.etatJeuService.incrementeNombreDeTour();
        }

    }

    private void affichageDesStatistiques(YamsPlayer player) {
        System.out.println("=================================================");
        System.out.println("Statistiques de la partie Yams");
        if (player != null) {
            System.out.println("=================================================");
            System.out.println("Points marques par le joueur " + player.getName());
            System.out.println("=================================================");
            for (Map.Entry<commun.constants.TypeCombinaison, CaseYams> entry : player.getCaseYamsAR().entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getScore() + " " + entry.getValue().estCaseScoreBarre());
            }
        }
        Map<String, Integer> winners = new HashMap<>();
        if (player != null) {
            List<Integer> scores = new ArrayList<>();
            for (Map.Entry<TypeCombinaison, CaseYams> entry : player.getCaseYamsAR().entrySet()) {
                scores.add(entry.getValue().getScore());
            }
            winners.put(player.getName(), scores.stream().mapToInt(Integer::intValue).sum());
        }
        System.out.println("=================================================");

        winners.forEach((s, integer) -> System.out.println(s + " a marqué " + integer + " points le jeu"));

        System.out.println("=================================================");
    }

    private void initFeuilleYams(){
        if (CollectionUtils.isEmpty(this.players)){
            return;
        }
        CaseYams caseYams1 = new CaseYams();
        for (int i = 0; i < this.players.size(); i++){
            Map<TypeCombinaison, CaseYams> feuilleYams = new HashMap<>();
            feuilleYams.put(TypeCombinaison.YAMS, caseYams1);
            feuilleYams.put(TypeCombinaison.BRELAN, caseYams1);
            feuilleYams.put(TypeCombinaison.CARRE, caseYams1);
            feuilleYams.put(TypeCombinaison.PETITE_SUITE, caseYams1);
            feuilleYams.put(TypeCombinaison.GRANDE_SUITE, caseYams1);
            feuilleYams.put(TypeCombinaison.CHANCE, caseYams1);
            feuilleYams.put(TypeCombinaison.FULL, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_1, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_2, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_3, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_4, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_5, caseYams1);
            feuilleYams.put(TypeCombinaison.TOTAL_6, caseYams1);
            this.feuilleDesJoueurs.add(i, feuilleYams);
        }
    }


    public void lanceDes(List<Integer> des) {
        int seuil = des != null && des.size() > 0 ? des.size(): 5;
        this.des = new ArrayList<>();
        for (int i = 1; i <= seuil; i++) {
            this.des.add(this.random.nextInt(6) + 1);
        }
        this.des.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }
    public void relanceDes(Decision decision){
        if (CollectionUtils.isEmpty(decision.getDesARelancer()) || CollectionUtils.isEmpty(decision.getDesAGarder())){
            return;
        }
        List<Integer> desGardees = decision.getDesAGarder();
        this.lanceDes(decision.getDesARelancer());
        desGardees.addAll(this.getDes());
        this.setDes(desGardees);
    }

    private void remplirFeuillePlayer(Decision decision, int indexPlayer){
        if (decision == null){
            System.out.println("Le player doit retourner une decision");
            return;
        }
        if (this.feuilleDesJoueurs.get(indexPlayer).get(decision.getCombinaison()).estCaseScoreBarre()
                ||
                this.feuilleDesJoueurs.get(indexPlayer).get(decision.getCombinaison()).getScore() != 0){
            System.out.println("Combinaison deja realisee");
            return;
        }
        CaseYams caseYams = new CaseYams();
        if (decision.estCombinaisonAbarrer()){
            caseYams.setTypeCombinaison(decision.getCombinaison());
            caseYams.setEstCaseScoreBarre(true);
            caseYams.setScore(0);
        }else {
            CombinaisonService service =  new CombinaisonService(decision.getCombinaison());
            service.calculeScore(this.getDes());
            caseYams.setScore(service.getScore());
            caseYams.setTypeCombinaison(decision.getCombinaison());
        }
        this.feuilleDesJoueurs.get(indexPlayer).put(caseYams.getTypeCombinaison(),caseYams);
        System.out.println("Le Joueur "+this.players.get(indexPlayer).getName()+" a choisit la combinaison "+caseYams.getTypeCombinaison()+" et marque "+caseYams.getScore()+" points");
    }

    public List<Integer> getDes() {
        return des;
    }
    public void setDes(List<Integer> des){
        this.des = des;
    }

    public int getNombreRelance() {
        return nombreRelance;
    }

    public void incrementeNombreRelance() {
        if (this.nombreRelance < 4){
            this.nombreRelance += 1;
        }
    }
}
