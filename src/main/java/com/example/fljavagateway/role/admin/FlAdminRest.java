package com.example.fljavagateway.role.admin;

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
@RequestMapping("/flAdmin")
public class FlAdminRest {
    private final AdminBl adminBl;
    public FlAdminRest(AdminBl adminBl) {
        this.adminBl = adminBl;
    }

    @PostMapping("/initLedger")
    public byte[] initLedger() {
        return adminBl.initLedger();
    }

    @PostMapping("/startTraining")
    public byte[] startTraining(@RequestParam String modelId) {
        return adminBl.startTraining(modelId);
    }

    @PostMapping("/createModelMetadata")
    public ResponseEntity<Object> createModelMetadata(@RequestBody ModelMetadata modelMetadata) {
        return adminBl.createModelMetadata(modelMetadata.modelId(),
                modelMetadata.name(), modelMetadata.clientsPerRound(),
                modelMetadata.secretsPerClient(),
                modelMetadata.trainingRounds());
    }

    @GetMapping("/getPersonalInfo")
    public byte[] getPersonalInfo() {
        return adminBl.getPersonalInfo();
    }

    @GetMapping("/getEndRoundModel")
    public byte[] getEndRoundModel(@RequestParam String modelId) {
        return adminBl.getEndRoundModel(modelId);
    }

}
