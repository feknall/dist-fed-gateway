package com.example.fljavagateway.general;

import com.example.fljavagateway.common.CommonBl;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GeneralBl {
    private final Logger logger = LoggerFactory.getLogger(GeneralBl.class);
    private final Contract contract;

    public GeneralBl(CommonBl commonBl) {
        this.contract = commonBl.getContract();
    }

    public byte[] getTrainedModel(String modelId) {
        try {
            return contract.evaluateTransaction("getTrainedModel", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }


    public byte[] checkHasFlAdminAttribute() {
        try {
            return contract.evaluateTransaction("checkHasFlAdminAttribute");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkHasLeadAggregatorAttribute() {
        try {
            return contract.evaluateTransaction("checkHasLeadAggregatorAttribute");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkHasAggregatorAttribute() {
        try {
            return contract.evaluateTransaction("checkHasAggregatorAttribute");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkHasTrainerAttribute() {
        try {
            return contract.evaluateTransaction("checkHasTrainerAttribute");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getSelectedTrainersForCurrentRound() {
        try {
            return contract.evaluateTransaction("getSelectedTrainersForCurrentRound");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] checkIAmSelectedForRound() {
        try {
            return contract.evaluateTransaction("checkIAmSelectedForRound");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }


}
