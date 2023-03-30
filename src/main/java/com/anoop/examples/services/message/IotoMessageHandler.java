package com.anoop.examples.services.message;

import com.anoop.examples.model.IotoMessage;

public interface IotoMessageHandler {
    void onMessageReceived(IotoMessage message);
}
