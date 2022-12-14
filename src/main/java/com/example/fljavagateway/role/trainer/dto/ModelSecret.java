/*
 * SPDX-License-Identifier: Apache-2.0
 */

package com.example.fljavagateway.role.trainer.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.Gson;

public record ModelSecret(String modelId, String weights1, String weights2, String datasetSize) {

    public ModelSecret(@JsonProperty("modelId") final String modelId,
                       @JsonProperty("weights1") final String weights1,
                       @JsonProperty("weights2") final String weights2,
                       @JsonProperty("datasetSize") final String datasetSize) {
        this.modelId = modelId;
        this.weights1 = weights1;
        this.weights2 = weights2;
        this.datasetSize = datasetSize;
    }


    public String serialize() {
        return new Gson().toJson(this);
    }

    public static ModelSecret deserialize(final String json) {
        return new Gson().fromJson(json, ModelSecret.class);
    }

}
