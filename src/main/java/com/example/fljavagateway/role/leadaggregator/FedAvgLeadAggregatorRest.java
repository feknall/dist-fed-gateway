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
@RequestMapping("/fedAvg/leadAggregator")
public class FedAvgLeadAggregatorRest {
    private final FedAvgLeadAggregatorBl fedAvgLeadAggregatorBl;

    public FedAvgLeadAggregatorRest(FedAvgLeadAggregatorBl fedAvgLeadAggregatorBl) {
        this.fedAvgLeadAggregatorBl = fedAvgLeadAggregatorBl;
    }

    @PostMapping("/addEndRoundModel")
    public byte[] addEndRoundModel(@RequestBody EndRoundModel endRoundModel) {
        return fedAvgLeadAggregatorBl.addEndRoundModel(endRoundModel.modelId(),
                endRoundModel.weights());
    }

    @GetMapping("/checkAllOriginalModelsReceived")
    public byte[] checkAllOriginalModelsReceived(@RequestParam String modelId) {
        return fedAvgLeadAggregatorBl.checkAllOriginalModelsReceived(modelId);
    }

    @GetMapping("/getNumberOfReceivedOriginalModels")
    public byte[] getNumberOfReceivedOriginalModels(@RequestParam String modelId) {
        return fedAvgLeadAggregatorBl.getNumberOfReceivedOriginalModels(modelId);
    }

    @GetMapping("/getOriginalModelListForCurrentRound")
    public byte[] getOriginalModelListForCurrentRound(@RequestParam String modelId) {
        return fedAvgLeadAggregatorBl.getOriginalModelListForCurrentRound(modelId);
    }

    @GetMapping("/getPersonalInfo")
    public byte[] getPersonalInfo() {
        return fedAvgLeadAggregatorBl.getPersonalInfo();
    }


}
