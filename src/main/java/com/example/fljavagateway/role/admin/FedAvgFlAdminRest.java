package com.example.fljavagateway.role.admin;

import com.example.fljavagateway.role.admin.dto.FedAvgModelMetadata;
import com.example.fljavagateway.role.admin.dto.ModelMetadata;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Conditional(IsAdminCondition.class)
@RestController
@RequestMapping("/fedAvg/flAdmin")
public class FedAvgFlAdminRest {
    private final FedAvgAdminBl fedAvgAdminBl;
    public FedAvgFlAdminRest(FedAvgAdminBl fedAvgAdminBl) {
        this.fedAvgAdminBl = fedAvgAdminBl;
    }

    @PostMapping("/initLedger")
    public byte[] initLedger() {
        return fedAvgAdminBl.initLedger();
    }

    @PostMapping("/startTraining")
    public byte[] startTraining(@RequestParam String modelId) {
        return fedAvgAdminBl.startTraining(modelId);
    }

    @PostMapping("/createModelMetadata")
    public ResponseEntity<Object> createModelMetadata(@RequestBody FedAvgModelMetadata modelMetadata) {
        return fedAvgAdminBl.createModelMetadata(modelMetadata.modelId(),
                modelMetadata.name(), modelMetadata.clientsPerRound(),
                modelMetadata.trainingRounds());
    }

    @GetMapping("/getPersonalInfo")
    public byte[] getPersonalInfo() {
        return fedAvgAdminBl.getPersonalInfo();
    }

    @GetMapping("/getEndRoundModel")
    public byte[] getEndRoundModel(@RequestParam String modelId) {
        return fedAvgAdminBl.getEndRoundModel(modelId);
    }

}
