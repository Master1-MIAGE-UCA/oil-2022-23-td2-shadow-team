package commun;


import commun.constants.TypeCombinaison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EtatJeuService {
    private int nombreDeTour = 1;
    private int nombreRelance = 0;
    private Map<TypeCombinaison, CaseYams> feuilleYams = new HashMap<>();
    private List<Integer> des = new ArrayList<>();
    public int getNombreDeTour() {
        return nombreDeTour;
    }
    public boolean estNombreDeTourFini(){
        return this.getNombreDeTour() > 13;
    }
    public void incrementeNombreDeTour(){
       this.nombreDeTour += 1;
    }



    public Map<TypeCombinaison, CaseYams> getFeuilleYams() {
        return feuilleYams;
    }

    public void setFeuilleYams(Map<TypeCombinaison, CaseYams> feuilleYams) {
        this.feuilleYams = feuilleYams;
    }

    public List<Integer> getDes() {
        return des;
    }
    public void setDes(List<Integer> des){
        this.des = des;
    }

    public int getNombreRelance() {
        return this.nombreRelance;
    }
    public void setNombreRelance(int nombreRelance){
        this.nombreRelance = nombreRelance;
    }
}
