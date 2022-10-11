package com.example.fljavagateway.role.aggregator;

import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

@Conditional(IsAggregatorCondition.class)
@Service
public class AggregatorBl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Contract contract;

    public AggregatorBl(Contract contract) {
        logger.info("Role: aggregator");
        this.contract = contract;
    }

    public byte[] addAggregatedSecret(String modelId, String weights) {
        try {
            return contract.submitTransaction("addAggregatedSecret", modelId, weights);
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkAllSecretsReceived(String modelId) {
        try {
            return contract.evaluateTransaction("checkAllSecretsReceived", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getNumberOfReceivedSecrets(String modelId) {
        try {
            return contract.evaluateTransaction("getNumberOfReceivedSecrets", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getModelSecretList(String modelId, String round) {
        try {
            return contract.evaluateTransaction("getModelSecretList", modelId, round);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getModelSecretListForCurrentRound(String modelId) {
        try {
            return contract.evaluateTransaction("getModelSecretListForCurrentRound", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkInAggregator() {
        try {
            return contract.submitTransaction("checkInAggregator");
        } catch (GatewayException | CommitException e) {
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
