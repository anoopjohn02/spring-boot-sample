# spring-boot-sample
A spring boot sample project that represent following features.
- Spring Security
- Swagger
- MQTT Connectivity
- DB Connectivity(Mongo)
- RestTemplate Usage
- Method Level Security
- Spring Events
- Lombok
- Unit Testing and Mockito
- Docker
- Profiling
- Actuator & Prometheus

# Features
This microservice is implementation of simple fleet/edge device in IoT industry.

- Authenticate the device and manage the token to stay device actively.
- Measurement: A random measurement should send to cloud
- Measurement: API to send other device measurement to cloud. The device should be authenticated.
- Measurement: Save measurements into the local mongo db.
- Alert: A random alert should send to MQTT
- Alert: API to send other device alert to MQTT. The device should be authenticated.
- Alert: Save alerts into the local mongo db.
