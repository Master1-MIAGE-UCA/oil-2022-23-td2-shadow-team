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
    private List<CombinaisonService> combinaisonPossibles = new ArrayList<>();

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

    public void setDes(List<Integer> des) {
        this.des = des;
    }

    @Override
    public Map<TypeCombinaison, CaseYams> getCaseYamsAR() {
        return caseYamsAR;
    }

    public void setCaseYamsAR(Map<TypeCombinaison, CaseYams> caseYamsAR) {
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
        System.out.println(this.getName() + "> j'ai choisit la combinaison "+combinaisonSelectionnee.getTypeCombinaison());
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
    @Override
    public List<CombinaisonService> getCombinaisonPossibles() {
        return combinaisonPossibles;
    }

    @Override
    public Decision play(EtatJeuService etatJeuService) {
            this.setDes(etatJeuService.getDes());
            this.setCaseYamsAR(etatJeuService.getFeuilleYams());
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

}
