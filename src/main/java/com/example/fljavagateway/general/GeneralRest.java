package com.example.fljavagateway.general;

import com.example.fljavagateway.event.EventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/general")
public class GeneralRest {

    private final Logger logger = LoggerFactory.getLogger(EventListener.class);
    private final GeneralBl generalBl;

    public GeneralRest(GeneralBl generalBl) {
        this.generalBl = generalBl;
    }

    @GetMapping("/getTrainedModel")
    public byte[] getTrainedModel(@RequestParam String modelId) {
        return generalBl.getTrainedModel(modelId);
    }

    @GetMapping("/checkHasFlAdminAttribute")
    public byte[] hasFlAdminAttribute() {
        return generalBl.checkHasFlAdminAttribute();
    }

    @GetMapping("/checkHasAggregatorAttribute")
    public byte[] hasAggregatorAttribute() {
        return generalBl.checkHasAggregatorAttribute();
    }

    @GetMapping("/checkHasLeadAggregatorAttribute")
    public byte[] hasLeadAggregatorAttribute() {
        return generalBl.checkHasLeadAggregatorAttribute();
    }

    @GetMapping("/checkHasTrainerAttribute")
    public byte[] hasTrainerAttribute() {
        return generalBl.checkHasTrainerAttribute();
    }

}
