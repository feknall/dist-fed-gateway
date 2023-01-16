package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.role.trainer.dto.OriginalModel;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Conditional(IsTrainerCondition.class)
@RestController
@RequestMapping("/fedAvg/trainer")
public class FedAvgTrainerRest {
    private final FedAvgTrainerBl fedAvgTrainerBl;

    public FedAvgTrainerRest(FedAvgTrainerBl fedAvgTrainerBl) {
        this.fedAvgTrainerBl = fedAvgTrainerBl;
    }

    @PostMapping("/addOriginalModel")
    public byte[] addModelSecret(@RequestBody OriginalModel originalModel) {
        return fedAvgTrainerBl.addOriginalModel(originalModel.modelId(),
                originalModel.weights(),
                originalModel.datasetSize());
    }

    @GetMapping("/getEndRoundModel")
    public byte[] getEndRoundModel(@RequestParam String modelId) {
        return fedAvgTrainerBl.getEndRoundModel(modelId);
    }


    @GetMapping("/getPersonalInfo")
    public byte[] getPersonalInfo() {
        return fedAvgTrainerBl.getPersonalInfo();
    }
}
