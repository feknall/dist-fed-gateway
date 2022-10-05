package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.Settings;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;

@Conditional(IsTrainerCondition.class)
@Configuration
public class TrainerOrg2Settings extends Settings {

    @Value("${trainer.org2.cert.path}")
    private String certPath;
    @Value("${trainer.org2.key-dir.path}")
    private String keyDirPath;
    @Value("${trainer.org2.tls-cert.path}")
    private String tlsCertPath;
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

    @Override
    public String getCertPath() {
        return certPath;
    }

    @Override
    public String getKeyDirPath() {
        return keyDirPath;
    }

    @Override
    public String getTlsCertPath() {
        return tlsCertPath;
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
