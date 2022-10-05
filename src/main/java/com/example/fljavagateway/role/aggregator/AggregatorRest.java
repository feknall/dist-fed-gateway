package com.example.fljavagateway.role.aggregator;

import com.example.fljavagateway.role.aggregator.dto.AggregatedSecret;
import org.springframework.context.annotation.Conditional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Conditional(IsAggregatorCondition.class)
@RestController
@RequestMapping("/aggregator")
public class AggregatorRest {

    private final AggregatorBl aggregatorBl;

    public AggregatorRest(AggregatorBl aggregatorBl) {
        this.aggregatorBl = aggregatorBl;
    }

    @PostMapping("/addAggregatedSecret")
    public byte[] addAggregatedSecret(@RequestBody AggregatedSecret aggregatedSecret) {
        return aggregatorBl.addAggregatedSecret(aggregatedSecret.modelId(), aggregatedSecret.weights());
    }

    @GetMapping("/checkAllSecretsReceived")
    public byte[] checkAllSecretsReceived(@RequestParam String modelId) {
        return aggregatorBl.checkAllSecretsReceived(modelId);
    }

    @GetMapping("/getNumberOfReceivedSecrets")
    public byte[] getNumberOfReceivedSecrets(@RequestParam String modelId) {
        return aggregatorBl.getNumberOfReceivedSecrets(modelId);
    }

    @GetMapping("/getModelSecretList")
    public byte[] getModelSecretList(@RequestParam String modelId, @RequestParam String round) {
        return aggregatorBl.getModelSecretList(modelId, round);
    }

    @GetMapping("/getModelSecretListForCurrentRound")
    public byte[] getModelSecretListForCurrentRound(@RequestParam String modelId) {
        return aggregatorBl.getModelSecretListForCurrentRound(modelId);
    }

    @PostMapping("/checkInAggregator")
    public byte[] checkInAggregator() {
        return aggregatorBl.checkInAggregator();
    }

    @GetMapping("/getPersonalInfo")
    public List<byte[]> getPersonalInfo() {
        return aggregatorBl.getPersonalInfo();
    }
}
