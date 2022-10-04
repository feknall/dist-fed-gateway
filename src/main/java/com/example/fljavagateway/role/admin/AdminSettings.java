package com.example.fljavagateway.role.admin;

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

@ConditionalOnProperty(prefix = "fl", name = "role", havingValue = "admin")
@Configuration
public class AdminSettings extends Settings {

    @Value("${admin.organization}")
    private String organization;

    @Value("${admin.msp.id}")
    private String mspId;

    @Value("${admin.peer.endpoint}")
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

    @Bean(name = "adminContract")
    @Override
    public Contract getContract(Network adminNetwork) {
        return super.getContract(adminNetwork);
    }

    @Bean(name = "adminNetwork")
    @Override
    public Network getNetwork(Gateway adminGateway) {
        return super.getNetwork(adminGateway);
    }

    @Bean(name = "adminGateway")
    @Override
    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        return super.getGateway();
    }
}
