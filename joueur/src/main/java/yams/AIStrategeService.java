package yams;

import commun.*;
import commun.constants.TypeCombinaison;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Component
public class AIStrategeService extends YamsPlayer {
    private CaseYams caseYams;

    private List<CombinaisonService> combinaisonPossibles = new ArrayList<>();
    private final SecureRandom random = new SecureRandom();

    private CombinaisonService combinaisonChoisie;

    private List<Integer> des = new ArrayList<>();

    private List<Integer> desARelancer = new ArrayList<>();
    private Map<TypeCombinaison, CaseYams> caseYamsAR = new HashMap<TypeCombinaison, CaseYams>();
    private int nombreDeLance = 0;

    public AIStrategeService() {
        this.init();
    }


    public void choisirDeAGarder() {

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

    public CaseYams getcaseYams() {
        return caseYams;
    }

    public void setcaseYams(CaseYams caseYams) {
        this.caseYams = caseYams;
    }

    @Override
    public Map<TypeCombinaison, CaseYams> getCaseYamsAR() {
        return caseYamsAR;
    }

    public void setcaseYamsAR(Map<TypeCombinaison, CaseYams> caseYamsAR) {
        this.caseYamsAR = caseYamsAR;
    }

    public boolean estFeuilleRemplie() {
        for (Map.Entry<TypeCombinaison, CaseYams> caseYamsEntry : this.caseYamsAR.entrySet()) {
            if (!caseYamsEntry.getValue().estCaseScoreBarre() || caseYamsEntry.getValue().getScore() == 0) {
                return false;
            }
        }

        return true;
    }

    public boolean estCetteCombinaisonRealisee() {
        return this.combinaisonChoisie != null && (this.getCaseYamsAR().get(this.combinaisonChoisie.getTypeCombinaison()).getScore() != 0
                || this.getCaseYamsAR().get(this.combinaisonChoisie.getTypeCombinaison()).estCaseScoreBarre());
    }

    public int getNombreDeLance() {
        return nombreDeLance;
    }

    private void setNombreDeLance(int nombreDeLance){
        this.nombreDeLance = nombreDeLance;
    }

    public Map<TypeCombinaison, CaseYams> combinaisoncaseYamsPossibles() {
        return this.getCaseYamsAR()
                .entrySet()
                .stream()
                .filter(typeCombinaisoncaseYamsEntry -> !typeCombinaisoncaseYamsEntry.getValue().estCaseScoreBarre() && typeCombinaisoncaseYamsEntry.getValue().getScore() == 0)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }

    public void choisirCombinaison() {
        int maxScore = 0;
        CombinaisonService combinaisonSelectionnee = null, combinaisonService = null;

        List<TypeCombinaison> typeCombinaisons = this.combinaisoncaseYamsPossibles().keySet().stream().toList();
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
        System.out.print(this.getName() + " j'ai choisit la combinaison "+combinaisonSelectionnee.getTypeCombinaison());
        this.setCombinaisonChoisie(combinaisonSelectionnee);
    }

    public void choisirCombinaisonABarrer() {
        if (this.getCaseYamsAR() == null) {
            return;
        }
        Map<TypeCombinaison, CaseYams> combinaisoncaseYamsABarrers = this.combinaisoncaseYamsPossibles();
        List<TypeCombinaison> typeCombinaisons = combinaisoncaseYamsABarrers.keySet().stream().toList();
        this.identifierCombinaisonABarrer(typeCombinaisons);

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
            caseYams = new CaseYams();
            this.caseYams.setTypeCombinaison(combinaisonService.getTypeCombinaison());
            this.caseYams.setScore(0);
            this.caseYams.setEstCaseScoreBarre(false);
            this.getCaseYamsAR().put(this.caseYams.getTypeCombinaison(), caseYams);
        }
    }


    public void remplircaseYams() {
        this.caseYams = new CaseYams();
        caseYams.setScore(this.getCombinaisonChoisie().getScore());
        caseYams.setTypeCombinaison(this.getCombinaisonChoisie().getTypeCombinaison());
        System.out.println(this.getName() + " a joué la combinaison " + this.getCombinaisonChoisie().getTypeCombinaison() + " pour " + this.getCombinaisonChoisie().getScore() + " points");
        this.getCaseYamsAR().put(caseYams.getTypeCombinaison(), caseYams);
    }


    @Override
    public List<CombinaisonService> getCombinaisonPossibles() {
        return combinaisonPossibles;
    }

    public void setCombinaisonPossibles(List<CombinaisonService> combinaisonPossibles) {
        this.combinaisonPossibles = combinaisonPossibles;
    }

    @Override
    public Decision play(EtatJeuService etatJeuService) {
            this.setDes(etatJeuService.getDes());
            this.caseYamsAR = etatJeuService.getFeuilleYams();
            this.setNombreDeLance(etatJeuService.getNombreRelance());
            Decision decision = new Decision();
            this.choisirCombinaison();
            if (!this.estFeuilleRemplie() && !this.estCetteCombinaisonRealisee()) {
                decision.setEstCombinaisonAbarrer(false);
            } else if (this.getNombreDeLance() == 3 && this.estCetteCombinaisonRealisee()) {
                this.choisirCombinaisonABarrer();
                decision.setEstCombinaisonAbarrer(true);
            }
            decision.setCombinaison(this.getCombinaisonChoisie().getTypeCombinaison());
        return decision;
    }

    public void affichageDesStatistiques() {
        System.out.println("=================================================");
        System.out.println("Feuille Yams du joueur " + this.getName());
        System.out.println("=================================================");
        for (Map.Entry<commun.constants.TypeCombinaison, CaseYams> entry : this.getCaseYamsAR().entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getScore() + " " + entry.getValue().estCaseScoreBarre());
        }
//        Map<String, Integer> winners = new HashMap<>();
        List<Integer> scores = new ArrayList<>();
        for (Map.Entry<TypeCombinaison, CaseYams> entry : this.getCaseYamsAR().entrySet()) {
            scores.add(entry.getValue().getScore());
        }
        System.out.println("=================================================");

        System.out.println(getName() + " marque " + scores.stream().mapToInt(Integer::intValue).sum() + " points dans le jeu");

        System.out.println("=================================================");
    }

}
