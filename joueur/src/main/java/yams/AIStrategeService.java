package yams;

import commun.CombinaisonService;
import commun.EtatJeuService;
import commun.FeuilleYams;
import commun.YamsPlayer;
import commun.constants.TypeCombinaison;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class AIStrategeService extends YamsPlayer {
    private FeuilleYams feuilleYams;

    private List<CombinaisonService> combinaisonPossibles = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

    private CombinaisonService combinaisonChoisie;

    private List<Integer> des = new ArrayList<>();

    private List<Integer> desARelancer = new ArrayList<>();
    private Map<TypeCombinaison, FeuilleYams> feuilleYamsAR = new HashMap<TypeCombinaison, FeuilleYams>();
    private int nombreDeLance = 0;

    public AIStrategeService() {
        this.init();
        this.initFeuilleARemplir();
    }


    public void choisirDeAGarder() {

    }

    public void lanceDes() {
        System.out.println("Le joueur : " + this.getName() + " lance les dés");
        for (int i = 1; i <= 5; i++) {
            this.des.add(this.random.nextInt(6) + 1);
        }
        this.des.forEach(integer -> System.out.print(integer + " "));
        System.out.println();
    }

    public List<Integer> getDes() {
        return des;
    }

    public CombinaisonService getCombinaisonChoisie() {
        return combinaisonChoisie;
    }

    public void setCombinaisonChoisie(CombinaisonService combinaisonChoisie) {
        this.combinaisonChoisie = combinaisonChoisie;
    }

    public void lanceDeProgressif(List<Integer> desARelancer) {
        for (int i = 1; i < desARelancer.size(); i++) {
            this.desARelancer.add(random.nextInt(6) + 1);
        }
    }

    public void setDes(List<Integer> des) {
        this.des = des;
    }

    public void setDesARelancer(List<Integer> desARelancer) {
        this.desARelancer = desARelancer;
    }

    public void reinitieDes() {
        this.setDes(new ArrayList<>());
        this.setDesARelancer(new ArrayList<>());
    }

    public FeuilleYams getFeuilleYams() {
        return feuilleYams;
    }

    public void setFeuilleYams(FeuilleYams feuilleYams) {
        this.feuilleYams = feuilleYams;
    }

    @Override
    public Map<TypeCombinaison, FeuilleYams> getFeuilleYamsAR() {
        return feuilleYamsAR;
    }

    public void setFeuilleYamsAR(Map<TypeCombinaison, FeuilleYams> feuilleYamsAR) {
        this.feuilleYamsAR = feuilleYamsAR;
    }

    public boolean estFeuilleRemplie() {
        for (Map.Entry<TypeCombinaison, FeuilleYams> feuilleYamsEntry : this.feuilleYamsAR.entrySet()) {
            if (!feuilleYamsEntry.getValue().estCaseScoreBarre() || feuilleYamsEntry.getValue().getScore() == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean estCetteCombinaisonRealisee() {
        return this.combinaisonChoisie != null && (this.getFeuilleYamsAR().get(this.combinaisonChoisie.getTypeCombinaison()).getScore() != 0
                || this.getFeuilleYamsAR().get(this.combinaisonChoisie.getTypeCombinaison()).estCaseScoreBarre());
    }

    public int getNombreDeLance() {
        return nombreDeLance;
    }

    public void setNombreDeLance(int nombreDeLance) {
        this.nombreDeLance = nombreDeLance;
    }

    public void decrementNombreDeLance() {
        this.nombreDeLance -= 1;
    }

    public boolean estNombreDeLanceFini() {
        return this.nombreDeLance <= 0;
    }

    public void barrerCaseFeuilleYams() {
        FeuilleYams feuilleCaseBarree = new FeuilleYams();
        feuilleCaseBarree.setTypeCombinaison(this.getCombinaisonChoisie().getTypeCombinaison());
        feuilleCaseBarree.setEstCaseScoreBarre(true);
        this.feuilleYamsAR.put(this.getCombinaisonChoisie().getTypeCombinaison(), feuilleCaseBarree);
    }

    public Map<TypeCombinaison, FeuilleYams> combinaisonFeuilleYamsPossibles() {
        return this.getFeuilleYamsAR()
                .entrySet()
                .stream()
                .filter(typeCombinaisonFeuilleYamsEntry -> !typeCombinaisonFeuilleYamsEntry.getValue().estCaseScoreBarre() && typeCombinaisonFeuilleYamsEntry.getValue().getScore() == 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void choisirCombinaison() {
        int maxScore = 0;
        CombinaisonService combinaisonSelectionnee = null, combinaisonService = null;

        List<TypeCombinaison> typeCombinaisons = this.combinaisonFeuilleYamsPossibles().keySet().stream().toList();
        for (TypeCombinaison combinaison : typeCombinaisons) {
            combinaisonService = new CombinaisonService(combinaison);
            combinaisonService.calculeScore(this.getDes());
            int score = combinaisonService.getScore();
            if (score > maxScore) {
                maxScore = score;
                combinaisonSelectionnee = combinaisonService;
            }
        }
        if (combinaisonSelectionnee == null) {
            return;
        }
        System.out.print("Le joueur " + getName() + " choisit une combinaison "+combinaisonSelectionnee.getTypeCombinaison());
        this.setCombinaisonChoisie(combinaisonSelectionnee);
    }

    public void choisirCombinaisonABarrer() {
        if (this.getFeuilleYamsAR() == null) {
            return;
        }
        Map<TypeCombinaison, FeuilleYams> combinaisonFeuilleYamsABarrers = this.combinaisonFeuilleYamsPossibles();
        List<TypeCombinaison> typeCombinaisons = combinaisonFeuilleYamsABarrers.keySet().stream().toList();
        this.identifierCombinaisonABarrer(typeCombinaisons);
        this.barrerCaseFeuilleYams();

    }

    private void identifierCombinaisonABarrer(List<TypeCombinaison> typeCombinaisons) {
        if (typeCombinaisons == null) {
            return;
        }
        CombinaisonService combinaisonSelectionnee = null;
        int minScore = 50;
        for (TypeCombinaison typeCombinaison : typeCombinaisons) {
            CombinaisonService combinaisonService = new CombinaisonService(typeCombinaison);
            combinaisonService.calculeScore(this.getDes());
            int score = combinaisonService.getScore();
            combinaisonService.setScore(score);
            if (score < minScore) {
                minScore = score;
                combinaisonSelectionnee = combinaisonService;
            }
        }
        if (combinaisonSelectionnee == null) {
            return;
        }
        this.setCombinaisonChoisie(combinaisonSelectionnee);
    }

    @Override
    public void init() {
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.BRELAN));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.CARRE));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.PETITE_SUITE));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.GRANDE_SUITE));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.FULL));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.YAMS));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.CHANCE));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_1));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_2));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_3));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_4));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_5));
        this.combinaisonPossibles.add(new CombinaisonService(TypeCombinaison.TOTAL_6));
    }


    public void initFeuilleARemplir() {
        for (CombinaisonService combinaisonService : this.getCombinaisonPossibles()) {
            System.out.println(combinaisonService.getTypeCombinaison());
            feuilleYams = new FeuilleYams();
            this.feuilleYams.setTypeCombinaison(combinaisonService.getTypeCombinaison());
            this.feuilleYams.setScore(0);
            this.feuilleYams.setEstCaseScoreBarre(false);
            this.getFeuilleYamsAR().put(this.feuilleYams.getTypeCombinaison(), feuilleYams);
        }
    }


    public void remplirFeuilleYams() {
        this.feuilleYams = new FeuilleYams();
        feuilleYams.setScore(this.getCombinaisonChoisie().getScore());
        feuilleYams.setTypeCombinaison(this.getCombinaisonChoisie().getTypeCombinaison());
        System.out.println(this.getName() + " a joué la combinaison " + this.getCombinaisonChoisie().getTypeCombinaison() + " pour " + this.getCombinaisonChoisie().getScore() + " points");
        getFeuilleYamsAR().put(feuilleYams.getTypeCombinaison(), feuilleYams);
    }


    @Override
    public List<CombinaisonService> getCombinaisonPossibles() {
        return combinaisonPossibles;
    }

    public void setCombinaisonPossibles(List<CombinaisonService> combinaisonPossibles) {
        this.combinaisonPossibles = combinaisonPossibles;
    }

    @Override
    public String play(EtatJeuService etatJeuService) {
        while (!estNombreDeLanceFini()) {
            this.lanceDes();
            this.choisirCombinaison();
            if (!this.estFeuilleRemplie() && !this.estCetteCombinaisonRealisee()) {
                this.remplirFeuilleYams();
            } else if (getNombreDeLance() == 1 && estCetteCombinaisonRealisee()) {
                choisirCombinaisonABarrer();
            }
            decrementNombreDeLance();
            reinitieDes();
        }
        setNombreDeLance(3);
        affichageDesStatistiques();
        return "Etat du jeu est au tour: " + etatJeuService.getNombreDeTour();
    }

    public void affichageDesStatistiques() {
        System.out.println("=================================================");
        System.out.println("Feuille Yams du joueur " + this.getName());
        System.out.println("=================================================");
        for (Map.Entry<commun.constants.TypeCombinaison, FeuilleYams> entry : this.getFeuilleYamsAR().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getScore() + " " + entry.getValue().estCaseScoreBarre());
        }
//        Map<String, Integer> winners = new HashMap<>();
        List<Integer> scores = new ArrayList<>();
        for (Map.Entry<TypeCombinaison, FeuilleYams> entry : this.getFeuilleYamsAR().entrySet()) {
            scores.add(entry.getValue().getScore());
        }
        System.out.println("=================================================");

        System.out.println(getName() + " marque " + scores.stream().mapToInt(Integer::intValue).sum() + " points dans le jeu");

        System.out.println("=================================================");
    }

}
