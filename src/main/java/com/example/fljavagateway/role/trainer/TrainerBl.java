package com.example.fljavagateway.role.trainer;


import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Conditional(IsTrainerCondition.class)
@Service
public class TrainerBl {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Contract trainerOrg1Contract;
    private final Contract trainerOrg2Contract;
    private final Contract trainerContract;

    public TrainerBl(Contract trainerOrg1Contract, Contract trainerOrg2Contract, Contract contract) {
        logger.info("Role: trainer");
        this.trainerOrg1Contract = trainerOrg1Contract;
        this.trainerOrg2Contract = trainerOrg2Contract;
        this.trainerContract = contract;
    }

    public List<byte[]> addModelSecret(String modelId, String weights1, String weights2, String datasetSize) {
        logger.info(String.format("addModelSecret modelId: %s datasetSize: %s", modelId, datasetSize));

        List<byte[]> list = new ArrayList<>();

        ExecutorService executor = Executors.newFixedThreadPool(2);
        Runnable org1 = () -> {
            try {
                byte[] resp1 = trainerOrg1Contract.submitTransaction("addModelSecret", modelId, weights1, datasetSize);
                list.add(resp1);
                logger.info(String.format("addModelSecret modelId: %s datasetSize: %s, first was successful", modelId, datasetSize));
            } catch (GatewayException | CommitException e) {
                throw new RuntimeException(e);
            }
        };
        Runnable org2 = () -> {
            try {
                byte[] resp2 = trainerOrg2Contract.submitTransaction("addModelSecret", modelId, weights2, datasetSize);
                list.add(resp2);
                logger.info(String.format("addModelSecret modelId: %s datasetSize: %s, second was successful", modelId, datasetSize));
            } catch (GatewayException | CommitException e) {
                throw new RuntimeException(e);
            }
        };

        executor.submit(org1);
        executor.submit(org2);
        executor.shutdown();

        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            logger.debug("Task executor failed.");
        }
        return list;
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
