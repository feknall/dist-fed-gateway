package com.example.fljavagateway.trainer;

import com.example.fljavagateway.common.Settings;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

@Configuration
public class TrainerOrg1Settings extends Settings {
    @Value("${trainer.org1.organization}")
    private String organization;

    @Value("${trainer.org1.msp.id}")
    private String mspId;

    @Value("${trainer.org1.peer.endpoint}")
    private String endpoint;

    @Override
    public String getMspID() {
        return mspId;
    }

    @Override
    public String getOrganization() {
        return organization;
    }

    @Override
    public String getPeerEndpoint() {
        return endpoint;
    }

    @Bean(name = "trainerOrg1Contract")
    @Override
    public Contract getContract(Network trainerOrg1Network) {
        return super.getContract(trainerOrg1Network);
    }

    @Bean(name = "trainerOrg1Network")
    @Override
    public Network getNetwork(Gateway trainerOrg1Gateway) {
        return super.getNetwork(trainerOrg1Gateway);
    }

    @Bean(name = "trainerOrg1Gateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
