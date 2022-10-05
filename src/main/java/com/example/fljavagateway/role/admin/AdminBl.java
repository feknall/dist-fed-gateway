package com.example.fljavagateway.role.admin;

import com.example.fljavagateway.common.ApiError;
import com.example.fljavagateway.common.CommonBl;
import org.hyperledger.fabric.client.CommitException;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.EndorseException;
import org.hyperledger.fabric.client.GatewayException;
import org.hyperledger.fabric.protos.gateway.ErrorDetail;
import org.springframework.context.annotation.Conditional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Conditional(IsAdminCondition.class)
@Service
public class AdminBl {

    private final Contract contract;

    public AdminBl(Contract contract) {
        this.contract = contract;
    }

    public byte[] initLedger() {
        try {
            return contract.submitTransaction("initLedger");
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] startTraining(String modelId) {
        try {
            return contract.submitTransaction("startTraining", modelId);
        } catch (GatewayException | CommitException e) {
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<Object> createModelMetadata(String modelId,
                                                      String modelName,
                                                      String clientsPerRound,
                                                      String secretsPerClient,
                                                      String trainingRounds) {
        try {
            byte[] bytes = contract.submitTransaction("createModelMetadata",
                    modelId,
                    modelName,
                    clientsPerRound,
                    secretsPerClient,
                    trainingRounds);
            return new ResponseEntity<>(bytes, HttpStatus.ACCEPTED);
        } catch (EndorseException e) {
            String message = e.getDetails().stream().map(ErrorDetail::getMessage).collect(Collectors.joining());
            return new ApiError(HttpStatus.FORBIDDEN, message, e).toResponseEntity();
        } catch (GatewayException | CommitException e) {
            return new ApiError(HttpStatus.BAD_REQUEST, e).toResponseEntity();
        }
    }

    public byte[] getCheckInInfo() {
        try {
            return contract.evaluateTransaction("getCheckInInfo");
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> getPersonalInfo() {
        return CommonBl.getPersonalInfo(contract);
    }

}
