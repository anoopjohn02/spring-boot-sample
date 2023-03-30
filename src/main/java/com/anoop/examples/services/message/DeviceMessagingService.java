package com.anoop.examples.services.message;

import com.anoop.examples.model.IotoMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class DeviceMessagingService implements IotoMessageHandler {

    @Value("${ioto.login.userId}")
    private String userName;

    @Autowired
    private IotoGateway gateway;

    @Scheduled(fixedDelay = 5000)
    public void sendHeartBeat() {
        try {
            gateway.sendHeartBeat(userName);
        } catch (Exception exception) {
            log.error("Error while sending heart beat", exception);
        }
    }

    @Override
    public void onMessageReceived(IotoMessage message) {
        log.info("Message arrived ");
    }
}
