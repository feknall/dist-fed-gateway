package com.example.fljavagateway.role.trainer;

import com.example.fljavagateway.common.ContractNetworkPair;
import com.example.fljavagateway.common.IsTrainerCondition;
import com.example.fljavagateway.common.OrgEnum;
import com.example.fljavagateway.common.OthersSettings;
import com.example.fljavagateway.common.RoleEnum;
import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.hyperledger.fabric.client.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Conditional(IsTrainerCondition.class)
@Service
public class TrainerCommonBl {
    private final Logger logger = LoggerFactory.getLogger(TrainerCommonBl.class);
    private final RoleEnum roleEnum;
    private final Contract contract;
    private final Network network;

    public TrainerCommonBl(@Value("${fl.role}") String role, @Value("${trainer.org}") String trainerOrg,
                           Contract trainerOrg1Contract, Contract trainerOrg2Contract,
                           Network trainerOrg1Network, Network trainerOrg2Network) {
        this.roleEnum = RoleEnum.toEnum(role);
        logger.debug(trainerOrg);
        ContractNetworkPair pair = getDefaultContractNetwork(trainerOrg1Contract, trainerOrg2Contract,
                trainerOrg1Network, trainerOrg2Network, trainerOrg);
        this.contract = pair.contract();
        this.network = pair.network();
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    @Bean(name = "contract")
    public Contract getContract() {
        return contract;
    }

    @Bean(name = "network")
    public Network getNetwork() {
        return network;
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

    public static List<byte[]> getPersonalInfo(Contract contract) {
        List<byte[]> list = new ArrayList<>();
        try {
            byte[] resp = contract.evaluateTransaction("getPersonalInfo");
            list.add(resp);
        } catch (GatewayException e) {
            throw new RuntimeException(e);
        }

        return list;
    }

}
