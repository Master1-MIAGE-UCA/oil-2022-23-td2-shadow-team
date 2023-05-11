package yams.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class ProbaService {
    public Optional<Double> brelan(){
        return Optional.of(Math.pow(2.0, 3.0) * Math.pow(5.0, 2.0) / Math.pow(6.0, 4.0));
    }
    public Optional<Double> full(){
        return Optional.of(2 * Math.pow(5.0, 2) / Math.pow(6.0, 4.0));
    }
    public Optional<Double> smallStraight(){
        return Optional.of(Math.pow((4.0 / 6.0), 4.0) * (5.0 / 6.0));
    }
    public Optional<Double> largeStraight(){
        return Optional.of(Math.pow((3.0 / 6.0), 5.0));
    }
    public Optional<Double> yams(){
        return Optional.of(1.0 / Math.pow(6.0, 4.0));
    }
    public Optional<Double> square(){
        return Optional.of(Math.pow(5.0, 2.0) / Math.pow(6.0, 4.0));
    }
    public Optional<Double> noCombinaison(){
        return Optional.of((5 * 4) / Math.pow(6.0, 3.0));
    }
    private Optional<Double> paire(){
        return Optional.of(Math.pow(5.0, 2.0) / Math.pow(6.0, 2.0));
    }
}
