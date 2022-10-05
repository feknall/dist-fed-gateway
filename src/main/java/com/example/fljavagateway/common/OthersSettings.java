package com.example.fljavagateway.common;

import com.example.fljavagateway.event.SocketHandler;
import com.example.fljavagateway.role.trainer.TrainerOrg1Settings;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

@ConditionalOnMissingBean(TrainerOrg1Settings.class)
@Configuration
public class OthersSettings extends Settings {

    @Value("${others.org}")
    private String organization;

    @Value("${others.msp.id}")
    private String mspId;

    @Value("${others.peer.endpoint}")
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

    @Bean(name = "contract")
    @Override
    public Contract getContract(Network network) {
        return super.getContract(network);
    }

    @Bean(name = "network")
    @Override
    public Network getNetwork(Gateway gateway) {
        return super.getNetwork(gateway);
    }

    @Bean(name = "gateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
