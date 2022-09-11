package com.example.fljavagateway.common;

import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.GatewayException;
import org.hyperledger.fabric.client.Network;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonBl {

    private final RoleEnum roleEnum;
    private final Contract contract;
    private final Network network;

    public CommonBl(@Value("${fl.role}") String role,
                    @Value("${trainer.org}") String trainerOrg,
                    Contract adminContract,
                    Contract aggregatorContract,
                    Contract leadAggregatorContract,
                    Contract trainerOrg1Contract,
                    Contract trainerOrg2Contract,
                    Network adminNetwork,
                    Network aggregatorNetwork,
                    Network leadAggregatorNetwork,
                    Network trainerOrg1Network,
                    Network trainerOrg2Network) {
        this.roleEnum = RoleEnum.toEnum(role);
        ContractNetworkPair pair = getDefaultContractNetwork(adminContract, aggregatorContract, leadAggregatorContract,
                trainerOrg1Contract, trainerOrg2Contract,
                adminNetwork, aggregatorNetwork, leadAggregatorNetwork,
                trainerOrg1Network, trainerOrg2Network,
                trainerOrg);
        this.contract = pair.contract();
        this.network = pair.network();
    }

    public RoleEnum getRoleEnum() {
        return roleEnum;
    }

    public Contract getContract() {
        return contract;
    }

    public Network getNetwork() {
        return network;
    }

    private ContractNetworkPair getDefaultContractNetwork(Contract adminContract,
                                                          Contract aggregatorContract,
                                                          Contract leadAggregatorContract,
                                                          Contract trainerOrg1Contract,
                                                          Contract trainerOrg2Contract,
                                                          Network adminNetwork,
                                                          Network aggregatorNetwork,
                                                          Network leadAggregatorNetwork,
                                                          Network trainerOrg1Network,
                                                          Network trainerOrg2Network,
                                                          String trainerOrg) {
        if (roleEnum == RoleEnum.FL_ADMIN) {
            return new ContractNetworkPair(adminContract, adminNetwork);
        } else if (roleEnum == RoleEnum.AGGREGATOR) {
            return new ContractNetworkPair(aggregatorContract, aggregatorNetwork);
        } else if (roleEnum == RoleEnum.LEAD_AGGREGATOR) {
            return new ContractNetworkPair(leadAggregatorContract, leadAggregatorNetwork);
        } else if (roleEnum == RoleEnum.TRAINER) {
            if (OrgEnum.valueOf(trainerOrg) == OrgEnum.ORG1) {
                return new ContractNetworkPair(trainerOrg1Contract, trainerOrg1Network);
            } else {
                return new ContractNetworkPair(trainerOrg2Contract, trainerOrg2Network);
            }
        }
        throw new IllegalArgumentException("role is unknown");
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
