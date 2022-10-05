package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.role.trainer.dto.ModelSecret;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Conditional(IsTrainerCondition.class)
@RestController
@RequestMapping("/trainer")
public class TrainerRest {
    
    private final TrainerBl trainerBl;

    public TrainerRest(TrainerBl trainerBl) {
        this.trainerBl = trainerBl;
    }

    @PostMapping("/checkInTrainer")
    public byte[] checkInTrainer() {
        return trainerBl.checkInTrainer();
    }


    @PostMapping("/addModelSecret")
    public List<byte[]> addModelSecret(@RequestBody ModelSecret modelSecret) {
        return trainerBl.addModelSecret(modelSecret.modelId(),
                modelSecret.weights1(),
                modelSecret.weights2(),
                modelSecret.datasetSize());
    }

    @GetMapping("/getEndRoundModel")
    public byte[] getEndRoundModel(@RequestParam String modelId) {
        return trainerBl.getEndRoundModel(modelId);
    }


    @GetMapping("/getPersonalInfo")
    public List<byte[]> getPersonalInfo() {
        return trainerBl.getPersonalInfo();
    }
}
