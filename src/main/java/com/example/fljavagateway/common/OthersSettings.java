package com.example.fljavagateway.common;

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

    private final Logger logger = LoggerFactory.getLogger(OthersSettings.class);

    private final String certPath;
    private final String keyDirPath;
    private final String tlsCertPath;
    private final String organization;
    private final String mspId;
    private final String endpoint;

    public OthersSettings(@Value("${others.cert.path}") String certPath,
                          @Value("${others.key-dir.path}") String keyDirPath,
                          @Value("${others.tls-cert.path}") String tlsCertPath,
                          @Value("${others.organization}") String organization,
                          @Value("${others.msp.id}") String mspId,
                          @Value("${others.peer.endpoint}") String endpoint) {
        this.certPath = certPath;
        this.keyDirPath = keyDirPath;
        this.tlsCertPath = tlsCertPath;

        logger.info("Fuck SETTINGS organization: {}", organization);
        this.organization = organization;

        logger.info("SETTINGS mspId: {}", mspId);
        this.mspId = mspId;
        this.endpoint = endpoint;
    }

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
        logger.info("SETTINGS endpoint: {}", endpoint);
        return endpoint;
    }

    @Override
    public String getCertPath() {
        logger.info("SETTINGS certPath: {}", certPath);
        return certPath;
    }

    @Override
    public String getKeyDirPath() {
        logger.info("SETTINGS keyDirPath: {}", keyDirPath);
        return keyDirPath;
    }

    @Override
    public String getTlsCertPath() {
        logger.info("SETTINGS tlsCertPath: {}", tlsCertPath);
        return tlsCertPath;
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
