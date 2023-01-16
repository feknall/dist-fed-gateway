/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.fljavagateway.role.admin.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public record FedAvgModelMetadata(String modelId, String name,
                                  String clientsPerRound, String status,
                                  String trainingRounds) {

    public FedAvgModelMetadata(@JsonProperty("modelId") final String modelId,
                               @JsonProperty("name") final String name,
                               @JsonProperty("clientsPerRound") final String clientsPerRound,
                               @JsonProperty("status") final String status,
                               @JsonProperty("trainingRounds") final String trainingRounds) {
        this.modelId = modelId;
        this.name = name;
        this.clientsPerRound = clientsPerRound;
        this.status = status;
        this.trainingRounds = trainingRounds;
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public static FedAvgModelMetadata deserialize(final String ser) {
        return new Gson().fromJson(ser, FedAvgModelMetadata.class);
    }
}
