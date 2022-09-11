package com.example.fljavagateway.leadaggregator;

import com.example.fljavagateway.leadaggregator.dto.EndRoundModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("/readAggregatedModelUpdate")
    public byte[] readAggregatedModelUpdate(@RequestParam String modelId, @RequestParam String round) {
        return leadAggregatorBl.readAggregatedModelUpdate(modelId, round);
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
    
    @PostMapping("/checkInLeadAggregator")
    public byte[] checkInLeadAggregator() {
        return leadAggregatorBl.checkInLeadAggregator();
    }

    @GetMapping("/getPersonalInfo")
    public List<byte[]> getPersonalInfo() {
        return leadAggregatorBl.getPersonalInfo();
    }


}
