package com.example.fljavagateway.role.leadaggregator;

import com.example.fljavagateway.role.leadaggregator.dto.EndRoundModel;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Conditional(IsLeadAggregatorCondition.class)
@RestController
@RequestMapping("/leadAggregator")
public class LeadAggregatorRest {
    private final LeadAggregatorBl leadAggregatorBl;

    public LeadAggregatorRest(LeadAggregatorBl leadAggregatorBl) {
        this.leadAggregatorBl = leadAggregatorBl;
    }

    @PostMapping("/addEndRoundModel")
    public byte[] addEndRoundModel(@RequestBody EndRoundModel endRoundModel) {
        return leadAggregatorBl.addEndRoundModel(endRoundModel.modelId(),
                endRoundModel.weights());
    }

    @GetMapping("/checkAllAggregatedSecretsReceived")
    public byte[] checkAllAggregatedSecretsReceived(@RequestParam String modelId) {
        return leadAggregatorBl.checkAllAggregatedSecretsReceived(modelId);
    }

    @GetMapping("/getNumberOfReceivedAggregatedSecrets")
    public byte[] getNumberOfReceivedAggregatedSecrets(@RequestParam String modelId) {
        return leadAggregatorBl.getNumberOfReceivedAggregatedSecrets(modelId);
    }

    @GetMapping("/getAggregatedSecretListForCurrentRound")
    public byte[] getAggregatedSecretListForCurrentRound(@RequestParam String modelId) {
        return leadAggregatorBl.getAggregatedSecretListForCurrentRound(modelId);
    }

    @GetMapping("/getPersonalInfo")
    public byte[] getPersonalInfo() {
        return leadAggregatorBl.getPersonalInfo();
    }


}
