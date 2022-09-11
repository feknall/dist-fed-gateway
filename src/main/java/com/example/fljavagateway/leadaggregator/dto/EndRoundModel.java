/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.fljavagateway.leadaggregator.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public record EndRoundModel(String modelId, String weights) {
    public EndRoundModel(@JsonProperty("modelId") final String modelId, @JsonProperty("weights") final String weights) {
        this.modelId = modelId;
        this.weights = weights;
    }

    public String serialize() {
        return new Gson().toJson(this);
    }

    public static EndRoundModel deserialize(final String json) {
        return new Gson().fromJson(json, EndRoundModel.class);
    }

}
