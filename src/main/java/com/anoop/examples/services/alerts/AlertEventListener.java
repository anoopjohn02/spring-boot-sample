package com.anoop.examples.services.alerts;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AlertEventListener {

    @Async
    @EventListener
    public void handleAlertCreatedEvent(AlertCreatedEvent event) {

    }
}
