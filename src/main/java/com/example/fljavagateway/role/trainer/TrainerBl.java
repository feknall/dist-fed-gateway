package com.example.fljavagateway.role.trainer;


import com.example.fljavagateway.common.IsTrainerCondition;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Conditional(IsTrainerCondition.class)
@Service
public class TrainerBl {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final Contract trainerOrg1Contract;
    private final Contract trainerOrg2Contract;
    private final Contract trainerContract;

    public TrainerBl(Contract trainerOrg1Contract, Contract trainerOrg2Contract, Contract contract) {
        this.trainerOrg1Contract = trainerOrg1Contract;
        this.trainerOrg2Contract = trainerOrg2Contract;
        this.trainerContract = contract;
    }

    public byte[] checkInTrainer() {
        try {
            return trainerContract.submitTransaction("checkInTrainer");
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> addModelSecret(String modelId, String weights1, String weights2, String datasetSize) {
        logger.info(String.format("addModelSecret modelId: %s datasetSize: %s", modelId, datasetSize));
        try {
            List<byte[]> list = new ArrayList<>();

            byte[] resp1 = trainerOrg1Contract.submitTransaction("addModelSecret", modelId, weights1, datasetSize);
            list.add(resp1);

            byte[] resp2 = trainerOrg2Contract.submitTransaction("addModelSecret", modelId, weights2, datasetSize);
            list.add(resp2);

            return list;
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

    public List<byte[]> getPersonalInfo() {
        List<byte[]> list = new ArrayList<>();

        try {
            byte[] resp1 = trainerOrg1Contract.evaluateTransaction("getPersonalInfo");
            list.add(resp1);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }

        try {
            byte[] resp2 = trainerOrg2Contract.evaluateTransaction("getPersonalInfo");
            list.add(resp2);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }

        return list;
    }
}
