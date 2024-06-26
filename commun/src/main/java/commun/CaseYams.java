package commun;

import commun.constants.TypeCombinaison;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CaseYams {
    private TypeCombinaison typeCombinaison;
    private int score;
    private boolean estCaseScoreBarre = false;

    public TypeCombinaison getTypeCombinaison() {
        return typeCombinaison;
    }

    public int getScore() {
        return score;
    }

    public boolean estCaseScoreBarre() {
        return estCaseScoreBarre;
    }

    public void setTypeCombinaison(TypeCombinaison typeCombinaison) {
        this.typeCombinaison = typeCombinaison;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setEstCaseScoreBarre(boolean estCaseScoreBarre) {
        this.estCaseScoreBarre = estCaseScoreBarre;
    }
}
