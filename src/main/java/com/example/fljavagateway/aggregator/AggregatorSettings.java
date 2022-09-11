package com.example.fljavagateway.aggregator;

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
public class AggregatorSettings extends Settings {

    @Value("${aggregator.organization}")
    private String organization;

    @Value("${aggregator.msp.id}")
    private String mspId;

    @Value("${aggregator.peer.endpoint}")
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

    @Bean(name = "aggregatorContract")
    @Override
    public Contract getContract(Network aggregatorNetwork) {
        return super.getContract(aggregatorNetwork);
    }

    @Bean(name = "aggregatorNetwork")
    @Override
    public Network getNetwork(Gateway aggregatorGateway) {
        return super.getNetwork(aggregatorGateway);
    }

    @Bean(name = "aggregatorGateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
