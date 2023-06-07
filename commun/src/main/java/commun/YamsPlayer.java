package commun;

import commun.constants.TypeCombinaison;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class YamsPlayer {


    private String name;

    public String play( EtatJeuService etatJeuService){
        return null;
    }
    public Map<TypeCombinaison, FeuilleYams> getFeuilleYamsAR (){
        return new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void init(){}
    public List<CombinaisonService> getCombinaisonPossibles(){
        return new ArrayList<>();
    }
}
