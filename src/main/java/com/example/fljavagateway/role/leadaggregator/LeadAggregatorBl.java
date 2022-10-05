package com.example.fljavagateway.role.leadaggregator;

import com.example.fljavagateway.common.CommonBl;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Conditional(IsLeadAggregatorCondition.class)
public class LeadAggregatorBl {

    private final Contract contract;

    public LeadAggregatorBl(Contract contract) {
        this.contract = contract;
    }

    public byte[] addEndRoundModel(String modelId, String weights) {
        try {
            return contract.submitTransaction("addEndRoundModel", modelId, weights);
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] readAggregatedModelUpdate(String modelId, String round) {
        try {
            return contract.evaluateTransaction("readAggregatedModelUpdate", modelId, round);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkAllAggregatedSecretsReceived(String modelId) {
        try {
            return contract.evaluateTransaction("checkAllAggregatedSecretsReceived", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getNumberOfReceivedAggregatedSecrets(String modelId) {
        try {
            return contract.evaluateTransaction("getNumberOfReceivedAggregatedSecrets", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getAggregatedSecretListForCurrentRound(String modelId) {
        try {
            return contract.evaluateTransaction("getAggregatedSecretListForCurrentRound", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkInLeadAggregator() {
        try {
            return contract.submitTransaction("checkInLeadAggregator");
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> getPersonalInfo() {
        return CommonBl.getPersonalInfo(contract);
    }
}
