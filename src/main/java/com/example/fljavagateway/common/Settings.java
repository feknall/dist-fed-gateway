package com.example.fljavagateway.common;

import io.grpc.ManagedChannel;
import io.grpc.netty.shaded.io.grpc.netty.GrpcSslContexts;
import io.grpc.netty.shaded.io.grpc.netty.NettyChannelBuilder;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Gateway;
import org.hyperledger.fabric.client.Network;
import org.hyperledger.fabric.client.identity.Identities;
import org.hyperledger.fabric.client.identity.Identity;
import org.hyperledger.fabric.client.identity.Signer;
import org.hyperledger.fabric.client.identity.Signers;
import org.hyperledger.fabric.client.identity.X509Identity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

public abstract class Settings {

    private final Logger logger = LoggerFactory.getLogger(Settings.class);

    @Value("${fl.channel.name}")
    private String channelName;

    @Value("${fl.chaincode.name}")
    private String chaincodeName = "dist-fed-chaincode";

    public abstract String getMspID();

    public abstract String getOrganization();

    public abstract String getPeerEndpoint();

    public abstract String getCertPath();

    public abstract String getKeyDirPath();

    public abstract String getTlsCertPath();



    public String getChaincodeName() {
        return chaincodeName;
    }

    public Gateway getGateway() throws CertificateException, IOException, InvalidKeyException {
        var channel = newGrpcConnection();

        var builder = Gateway.newInstance()
                .identity(newIdentity())
                .signer(newSigner())
                .connection(channel)
                .evaluateOptions(options -> options.withDeadlineAfter(5, TimeUnit.SECONDS))
                .endorseOptions(options -> options.withDeadlineAfter(15, TimeUnit.SECONDS))
                .submitOptions(options -> options.withDeadlineAfter(5, TimeUnit.SECONDS))
                .commitStatusOptions(options -> options.withDeadlineAfter(1, TimeUnit.MINUTES));

        return builder.connect();
    }


    public Contract getContract(Network network) {
        return network.getContract(chaincodeName);
    }

    public Network getNetwork(Gateway gateway) {
        return gateway.getNetwork(channelName);
    }

    private Identity newIdentity() throws IOException, CertificateException {
        var certReader = Files.newBufferedReader(Paths.get(getCertPath()));
        var certificate = Identities.readX509Certificate(certReader);

        return new X509Identity(getMspID(), certificate);
    }

    private ManagedChannel newGrpcConnection() throws IOException, CertificateException {
        var tlsCertReader = Files.newBufferedReader(Paths.get(getTlsCertPath()));
        var tlsCert = Identities.readX509Certificate(tlsCertReader);

        String overrideAuth = "peer0." + getOrganization();
        logger.info("OverrideAuth: {}", overrideAuth);


        return NettyChannelBuilder.forTarget(getPeerEndpoint())
                .sslContext(GrpcSslContexts.forClient().trustManager(tlsCert).build())
                .overrideAuthority(overrideAuth)
                .maxInboundMessageSize(4 * 4194304)
                .build();
    }

    private Signer newSigner() throws IOException, InvalidKeyException {
        var keyReader = Files.newBufferedReader(getPrivateKeyPath());
        var privateKey = Identities.readPrivateKey(keyReader);

        return Signers.newPrivateKeySigner(privateKey);
    }

    private Path getPrivateKeyPath() throws IOException {
        try (var keyFiles = Files.list(Paths.get(getKeyDirPath()))) {
            return keyFiles.findFirst().orElseThrow();
        }
    }

}
