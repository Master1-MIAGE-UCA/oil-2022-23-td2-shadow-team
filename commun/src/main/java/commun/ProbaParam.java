package commun;

import commun.constants.TypeCombinaison;

import java.util.List;

public class ProbaParam {
    private TypeCombinaison typeCombinaison;
    private List<Integer> des;

    private String plauerName;

    public ProbaParam(){}

    public TypeCombinaison getTypeCombinaison() {
        return typeCombinaison;
    }

    public void setTypeCombinaison(TypeCombinaison typeCombinaison) {
        this.typeCombinaison = typeCombinaison;
    }

    public List<Integer> getDes() {
        return des;
    }

    public void setDes(List<Integer> des) {
        this.des = des;
    }

    public String getPlauerName() {
        return plauerName;
    }

    public void setPlauerName(String plauerName) {
        this.plauerName = plauerName;
    }
}
