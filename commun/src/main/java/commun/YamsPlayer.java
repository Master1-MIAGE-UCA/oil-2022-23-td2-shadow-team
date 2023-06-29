package commun;

import commun.constants.TypeCombinaison;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class YamsPlayer {


    private String name;

    public Decision play( EtatJeuService etatJeuService){
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<TypeCombinaison, CaseYams> getCaseYamsAR() {
        return new HashMap<>();
    }

    public void init(){}
    public List<CombinaisonService> getCombinaisonPossibles(){
        return new ArrayList<>();
    }
}
