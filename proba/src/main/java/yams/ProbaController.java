package yams;


import commun.ProbaParam;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("api/yams/proba")
public class ProbaController {
    @Autowired
    private ProbaService probaService;

    @GetMapping("/obtenirProba")
    public Double getProba(@RequestBody ProbaParam probaParam){
        System.out.println("Le player "+probaParam.getPlauerName()+" a fait appel a proba");
        return this.probaService.calculeProbabilite(probaParam.getDes(), probaParam.getTypeCombinaison());
    }
}
