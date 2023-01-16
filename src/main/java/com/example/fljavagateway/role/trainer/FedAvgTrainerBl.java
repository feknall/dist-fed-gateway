package com.example.fljavagateway.role.trainer;


import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

@Conditional(IsTrainerCondition.class)
@Service
public class FedAvgTrainerBl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Contract trainerContract;

    public FedAvgTrainerBl(Contract contract) {
        logger.info("Role: trainer");
        this.trainerContract = contract;
    }

    public byte[] addOriginalModel(String modelId, String weights, String datasetSize) {
        logger.info(String.format("addModelSecret modelId: %s datasetSize: %s", modelId, datasetSize));
        try {
            byte[] resp1 = trainerContract.submitTransaction("addOriginalModel", modelId, weights, datasetSize);
            logger.info(String.format("addModelSecret modelId: %s datasetSize: %s, first was successful", modelId, datasetSize));
            return resp1;
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getEndRoundModel(String modelId) {
        try {
            return trainerContract.evaluateTransaction("getEndRoundModel", modelId);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] getPersonalInfo() {
        try {
            return trainerContract.evaluateTransaction("getPersonalInfo");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }
}
