package com.anoop.examples.services.message;

import com.anoop.examples.model.Alert;
import com.anoop.examples.services.message.IotoMessageHandler;

public interface IotoGateway {

    void sendHeartBeat(String deviceId)throws Exception ;
    void sendAlert(String deviceId, Alert alert)throws Exception;

    void subscribe(String deviceId, IotoMessageHandler handler)throws Exception;
    boolean isConnected();

}
