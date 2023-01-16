/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.fljavagateway.role.trainer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public record OriginalModel(String modelId, String weights, String datasetSize) {

    public OriginalModel(@JsonProperty("modelId") final String modelId,
                         @JsonProperty("weights") final String weights,
                         @JsonProperty("datasetSize") final String datasetSize) {
        this.modelId = modelId;
        this.weights = weights;
        this.datasetSize = datasetSize;
    }


    public String serialize() {
        return new Gson().toJson(this);
    }

    public static OriginalModel deserialize(final String json) {
        return new Gson().fromJson(json, OriginalModel.class);
    }

}
