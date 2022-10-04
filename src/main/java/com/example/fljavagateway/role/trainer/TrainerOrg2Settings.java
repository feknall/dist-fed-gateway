package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.Settings;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

@ConditionalOnProperty(prefix = "fl", name = "role", havingValue = "trainer")
@Configuration
public class TrainerOrg2Settings extends Settings {
    @Value("${trainer.org2.organization}")
    private String organization;

    @Value("${trainer.org2.msp.id}")
    private String mspId;

    @Value("${trainer.org2.peer.endpoint}")
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

    @Bean(name = "trainerOrg2Contract")
    @Override
    public Contract getContract(Network trainerOrg2Network) {
        return super.getContract(trainerOrg2Network);
    }

    @Bean(name = "trainerOrg2Network")
    @Override
    public Network getNetwork(Gateway trainerOrg2Gateway) {
        return super.getNetwork(trainerOrg2Gateway);
    }

    @Bean(name = "trainerOrg2Gateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
