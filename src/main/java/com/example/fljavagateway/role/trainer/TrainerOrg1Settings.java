package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.Settings;
import com.example.fljavagateway.event.SocketHandler;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

@Conditional(IsTrainerCondition.class)
@Configuration
public class TrainerOrg1Settings extends Settings {

    private final Logger logger = LoggerFactory.getLogger(TrainerOrg1Settings.class);

    @Value("${trainer.org1.cert.path}")
    private String certPath;
    @Value("${trainer.org1.key-dir.path}")
    private String keyDirPath;
    @Value("${trainer.org1.tls-cert.path}")
    private String tlsCertPath;
    @Value("${trainer.org1.organization}")
    private String organization;
    @Value("${trainer.org1.msp.id}")
    private String mspId;
    @Value("${trainer.org1.peer.endpoint}")
    private String endpoint;


    @Override
    public String getMspID() {
        logger.info("SETTINGS mspId: {}", mspId);
        return mspId;
    }

    @Override
    public String getOrganization() {
        logger.info("SETTINGS organization: {}", organization);
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
