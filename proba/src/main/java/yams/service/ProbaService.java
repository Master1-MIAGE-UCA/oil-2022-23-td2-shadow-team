package yams.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProbaService {
    public Optional<Double> brelan(){
        return Optional.of(0.0);
    }
    public Optional<Double> full(){
        return Optional.of(paire() + brelan().get());
    }
    public Optional<Double> smallStraight(){
        return Optional.of(0.0);
    }
    public Optional<Double> largeStraight(){
        return Optional.of(0.0);
    }
    public Optional<Double> yams(){
        return Optional.of(0.0);
    }
    public Optional<Double> square(){
        return Optional.of(0.0);
    }
    public Optional<Double> noCombinaison(){
        return Optional.of(0.0);
    }
    private double paire(){
        return 0.0;
    }
}
