package com.anoop.examples.services.alerts;

import com.anoop.examples.data.entities.AlertEntity;
import com.anoop.examples.data.repos.AlertRepository;
import com.anoop.examples.exceptions.ErrorCodes;
import com.anoop.examples.exceptions.InternalServerException;
import com.anoop.examples.exceptions.NotFoundException;
import com.anoop.examples.model.Alert;
import com.anoop.examples.model.IotoUser;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * Method to create alert.
     *
     * @param alert to be created
     * @param user the logged-in user
     * @return created {@link Alert} Object
     */
    @Transactional
    public Alert create(Alert alert, IotoUser user){
        AlertEntity entity = modelMapper.map(alert, AlertEntity.class);
        entity = alertRepository.save(entity);
        publisher.publishEvent(new AlertCreatedEvent(alert, user));
        return getAlert(entity);
    }

    /**
     * Method to get a single alert.
     *
     * @param id the alert id
     * @return {@link Alert} Object
     */
    public Alert get(String id){
        AlertEntity alertEntity = getEntity(id);
        return getAlert(alertEntity);
    }

    /**
     * Method to get a list of {@link Alert} for a device.
     *
     * @param deviceId the device id
     * @return list of {@link Alert}
     */
    public List<Alert> getByDeviceId(String deviceId){
        try {
            List<AlertEntity> entities = alertRepository.findByDeviceId(deviceId);
            return entities.stream()
                    .map(this::getAlert)
                    .collect(Collectors.toList());
        } catch (Exception ex) {
            log.error("Exception Occurred ", ex);
            throw new InternalServerException(ex);
        }
    }

    /**
     * Method to delete an alert.
     * This will validate whether the user can permission to delete an alert.
     *
     * @param id the alert id
     * @param user the logged-in user
     */
    @Transactional
    public void delete(String id, IotoUser user){
        AlertEntity alertEntity = getEntity(id);
        alertRepository.delete(alertEntity);
    }

    private AlertEntity getEntity(String id) {
        Optional<AlertEntity> entityOpt = alertRepository.findById(new ObjectId(id));
        if(!entityOpt.isPresent()){
            throw new NotFoundException(ErrorCodes.ALERT_NOT_FOUND);
        }
        return entityOpt.get();
    }

    private Alert getAlert(AlertEntity entity){
        Alert alert = modelMapper.map(entity, Alert.class);
        String id = entity.get_id().toString();
        alert.setId(id);
        return alert;
    }
}
