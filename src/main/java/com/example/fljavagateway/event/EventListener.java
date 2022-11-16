package com.example.fljavagateway.event;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.hyperledger.fabric.client.ChaincodeEvent;
import org.hyperledger.fabric.client.CloseableIterator;
import org.hyperledger.fabric.client.Network;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;

@Configuration
public class EventListener {
    private final Logger logger = LoggerFactory.getLogger(EventListener.class);
    private final WebSocketEventProcessor webSocketEventProcessor;
    private final Network network;

    public EventListener(WebSocketEventProcessor webSocketEventProcessor, Network network) {
        this.webSocketEventProcessor = webSocketEventProcessor;
        this.network = network;
    }

    @Bean
    public CloseableIterator<ChaincodeEvent> listen() {
        logger.info("Start listening for events...");
        var eventIter = network.getChaincodeEvents("dist-fed-chaincode");
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
