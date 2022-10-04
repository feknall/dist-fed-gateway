package com.example.fljavagateway.role.leadaggregator;

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

@Configuration
@ConditionalOnProperty(prefix = "fl", name = "role", havingValue = "leadAggregator")
public class LeadAggregatorSettings extends Settings {

    @Value("${leadAggregator.organization}")
    private String organization;

    @Value("${leadAggregator.msp.id}")
    private String mspId;

    @Value("${leadAggregator.peer.endpoint}")
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

    @Bean(name = "leadAggregatorContract")
    @Override
    public Contract getContract(Network leadAggregatorNetwork) {
        return super.getContract(leadAggregatorNetwork);
    }

    @Bean(name = "leadAggregatorNetwork")
    @Override
    public Network getNetwork(Gateway leadAggregatorGateway) {
        return super.getNetwork(leadAggregatorGateway);
    }

    @Bean(name = "leadAggregatorGateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
