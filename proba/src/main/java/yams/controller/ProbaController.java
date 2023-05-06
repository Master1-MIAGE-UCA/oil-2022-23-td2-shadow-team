package yams.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import yams.service.ProbaService;

@RestController
@AllArgsConstructor
@RequestMapping("api/yams/proba")
public class ProbaController {
    private final ProbaService probaService;

    @GetMapping("/brelan")
    public ResponseEntity<Double> getBrelan(){
        return ResponseEntity.of(probaService.brelan());
    }
    @GetMapping("/yams")
    public ResponseEntity<Double> getYams(){
        return ResponseEntity.of(probaService.yams());
    }
    @GetMapping("/chance")
    public ResponseEntity<Double> getChance(){
        return ResponseEntity.of(probaService.noCombinaison());
    }
    @GetMapping("/petite")
    public ResponseEntity<Double> getSmall(){
        return ResponseEntity.of(probaService.smallStraight());
    }
    @GetMapping("/grande")
    public ResponseEntity<Double> getLarge(){
        return ResponseEntity.of(probaService.largeStraight());
    }
    @GetMapping("/carre")
    public ResponseEntity<Double> getSquare(){
        return ResponseEntity.of(probaService.square());
    }
    @GetMapping("/full")
    public ResponseEntity<Double> getFull(){
        return ResponseEntity.of(probaService.full());
    }
}
