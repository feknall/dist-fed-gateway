package com.example.fljavagateway.common;

import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonBl {
    private final Logger logger = LoggerFactory.getLogger(CommonBl.class);

    public static List<byte[]> getPersonalInfo(Contract contract) {
        List<byte[]> list = new ArrayList<>();
        try {
            byte[] resp = contract.evaluateTransaction("getPersonalInfo");
            list.add(resp);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}
