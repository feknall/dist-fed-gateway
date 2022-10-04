package com.example.fljavagateway.event;

import com.example.fljavagateway.common.CommonBl;
import com.example.fljavagateway.common.Settings;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.hyperledger.fabric.client.ChaincodeEvent;
import org.hyperledger.fabric.client.CloseableIterator;
import org.hyperledger.fabric.client.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Service
public class EventListener {

    private final Logger logger = LoggerFactory.getLogger(EventListener.class);

    private final Network network;
    private final WebSocketEventProcessor webSocketEventProcessor;

    public EventListener(CommonBl commonBl, WebSocketEventProcessor webSocketEventProcessor) {
        this.network = commonBl.getNetwork();
        this.webSocketEventProcessor = webSocketEventProcessor;
    }

    @Bean
    public CloseableIterator<ChaincodeEvent> listen() {
        logger.info("Start listening for events...");
        var eventIter = network.getChaincodeEvents(Settings.chaincodeName);
        CompletableFuture.runAsync(() -> {
            eventIter.forEachRemaining(event -> {
                CompletableFuture.runAsync(() -> {
                    var payload = prettyJson(event.getPayload());
                    logger.info("\n<-- Chaincode event received: " + event.getEventName() + " - " + payload);
                    webSocketEventProcessor.sendEvent(event);
                });
            });
        });
        return eventIter;
    }

    private String prettyJson(final byte[] json) {
        return prettyJson(new String(json, StandardCharsets.UTF_8));
    }

    private String prettyJson(final String json) {
        var parsedJson = JsonParser.parseString(json);
        return new GsonBuilder().setPrettyPrinting().create().toJson(parsedJson);
    }

}