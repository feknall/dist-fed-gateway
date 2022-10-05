package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.ContractNetworkPair;
import com.example.fljavagateway.common.OrgEnum;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Conditional(IsTrainerCondition.class)
@Configuration
public class TrainerDefaultSettings {

    private final Contract contract;
    private final Network network;

    public TrainerDefaultSettings(@Value("${trainer.org}") String trainerOrg,
                                  Contract trainerOrg1Contract, Contract trainerOrg2Contract,
                                  Network trainerOrg1Network, Network trainerOrg2Network) {
        ContractNetworkPair pair = getDefaultContractNetwork(trainerOrg1Contract, trainerOrg2Contract,
                trainerOrg1Network, trainerOrg2Network, trainerOrg);
        this.contract = pair.contract();
        this.network = pair.network();
    }

    private ContractNetworkPair getDefaultContractNetwork(Contract trainerOrg1Contract,
                                                          Contract trainerOrg2Contract,
                                                          Network trainerOrg1Network,
                                                          Network trainerOrg2Network,
                                                          String trainerOrg) {
        if (OrgEnum.toEnum(trainerOrg) == OrgEnum.ORG1) {
            return new ContractNetworkPair(trainerOrg1Contract, trainerOrg1Network);
        } else {
            return new ContractNetworkPair(trainerOrg2Contract, trainerOrg2Network);
        }
    }

    @Bean(name = "contract")
    public Contract getContract() {
        return contract;
    }

    @Bean(name = "network")
    public Network getNetwork() {
        return network;
    }
}
