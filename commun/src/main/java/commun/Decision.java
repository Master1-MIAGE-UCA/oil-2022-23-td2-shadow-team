package commun;

import commun.constants.TypeCombinaison;

import java.util.ArrayList;
import java.util.List;

public class Decision {
    private TypeCombinaison combinaison;
    private Boolean estOptionRelance = false;
    private List<Integer> desARelancer =  new ArrayList<>();
    private List<Integer> desAGarder = new ArrayList<>();

    private Boolean estCombinaisonAbarrer;

    public Decision() {
    }

    public TypeCombinaison getCombinaison() {
        return combinaison;
    }

    public void setCombinaison(TypeCombinaison combinaison) {
        this.combinaison = combinaison;
    }

    public Boolean estOptionRelance() {
        return estOptionRelance;
    }

    public void setEstOptionRelance(Boolean estOptionRelance) {
        this.estOptionRelance = estOptionRelance;
    }

    public List<Integer> getDesARelancer() {
        return desARelancer;
    }

    public void setDesARelancer(List<Integer> desARelancer) {
        this.desARelancer = desARelancer;
    }

    public List<Integer> getDesAGarder() {
        return desAGarder;
    }

    public void setDesAGarder(List<Integer> desAGarder) {
        this.desAGarder = desAGarder;
    }

    public Boolean estCombinaisonAbarrer() {
        return estCombinaisonAbarrer;
    }

    public void setEstCombinaisonAbarrer(Boolean estCombinaisonAbarrer) {
        this.estCombinaisonAbarrer = estCombinaisonAbarrer;
    }
}
