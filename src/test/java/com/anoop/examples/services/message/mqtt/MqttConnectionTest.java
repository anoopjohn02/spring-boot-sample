package com.anoop.examples.services.message.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestPropertySource;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest(classes = MqttConnection.class)
@TestPropertySource(properties = {
        "ioto.login.userId=aUser",
        "ioto.login.password=pwd"
})
public class MqttConnectionTest {

    private final String host = "http://host";
    private final String userName = "aUser";
    private final String password = "pwd";

    @MockBean
    private IotoMqttCallback callback;
    @MockBean
    private MqttListener mqttListener;
    @MockBean
    private MqttClient mqttClient;

    @Autowired
    private MqttConnection mqttConnection;

    @Test
    void testConnect() throws MqttException {
        doNothing().when(mqttClient).connect(any());
        doNothing().when(mqttClient).setCallback(any());

        mqttConnection.connect();

        verify(mqttClient, times(1)).connect(any());
        verify(mqttClient, times(1)).setCallback(any());
    }
}
