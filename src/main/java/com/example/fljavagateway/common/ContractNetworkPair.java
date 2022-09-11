package com.example.fljavagateway.common;

import org.hyperledger.fabric.client.Contract;
import org.hyperledger.fabric.client.Network;


public record ContractNetworkPair(Contract contract, Network network) {


}
