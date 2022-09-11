package com.example.fljavagateway.event;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.AbstractWebSocketHandler;

public class SocketHandler extends AbstractWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(SocketHandler.class);
    private final WebSocketEventProcessor eventProcessor;

    public SocketHandler(WebSocketEventProcessor eventProcessor) {
        logger.debug("New socket!");
        this.eventProcessor = eventProcessor;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) {
        logger.info("New socket connected successfully.");
        eventProcessor.setSession(session);
    }
}
