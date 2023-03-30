package com.anoop.examples.services.measurements;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MeasurementEventListener {

    @Async
    @EventListener
    public void handleVoltageCrossedLimitEvent(MeasurementCrossedLimitEvent event) {

    }
}
