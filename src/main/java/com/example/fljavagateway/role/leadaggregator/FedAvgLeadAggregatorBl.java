package com.example.fljavagateway.role.leadaggregator;

import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Service
@Conditional(IsLeadAggregatorCondition.class)
public class FedAvgLeadAggregatorBl {
    private final Logger logger = LoggerFactory.getLogger(FedAvgLeadAggregatorBl.class);
    private final Contract contract;

    public FedAvgLeadAggregatorBl(Contract contract) {
        logger.info("Role: leadAggregator");
        this.contract = contract;
    }

    public byte[] addEndRoundModel(String modelId, String weights) {
        try {
            return contract.submitTransaction("addEndRoundModel", modelId, weights);
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkAllOriginalModelsReceived(String modelId) {
        try {
            return contract.evaluateTransaction("checkAllOriginalModelsReceived", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getNumberOfReceivedOriginalModels(String modelId) {
        try {
            return contract.evaluateTransaction("getNumberOfReceivedOriginalModels", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getOriginalModelListForCurrentRound(String modelId) {
        try {
            return contract.evaluateTransaction("getOriginalModelListForCurrentRound", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] getPersonalInfo() {
        try {
            return contract.evaluateTransaction("getPersonalInfo");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }
}
