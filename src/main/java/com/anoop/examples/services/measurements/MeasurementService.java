package com.anoop.examples.services.measurements;

import com.anoop.examples.data.entities.MeasurementEntity;
import com.anoop.examples.data.repos.MeasurementRepository;
import com.anoop.examples.enums.CustomAlertType;
import com.anoop.examples.exceptions.AccessDeniedException;
import com.anoop.examples.exceptions.ErrorCodes;
import com.anoop.examples.exceptions.NotFoundException;
import com.anoop.examples.model.IotoUser;
import com.anoop.examples.model.Measurement;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MeasurementService {

    @Value("${ioto.login.userId}")
    private String userId;
    @Autowired
    private MeasurementRepository repository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private ApplicationEventPublisher publisher;

    @Scheduled(cron = "0 0/15 * * * ?") // run every 15 min
    public void sendEnergyMeasurement() {
        try {
            int energy = getRandomNumbers(500, 1500);
            Measurement measurement = new Measurement();
            measurement.setDeviceId(userId);
            measurement.setEnd(new Date());
            measurement.setStart(Date.from(getStartDate(15).toInstant()));
            measurement.setMetadata(new HashMap());
            measurement.setValue(energy);
            measurement.setUnit("Wh");
            measurement.setType("ENERGY_CONSUMPTION");

            //create(null, measurement); TODO - Uncomment when user is ready
        } catch (Exception ex) {
            log.error("Error while Sending Measurement ", ex);
        }
    }

    @Transactional
    @PreAuthorize("@measurementService.userCanCreateMeasurement(#user)")
    public Measurement create(IotoUser user, Measurement measurement) {
        if (measurement.getValue() > 1200){
            publisher.publishEvent(
                    new MeasurementCrossedLimitEvent(user, CustomAlertType.ENERGY_CONSUMPTION_CROSSED));
        }

        MeasurementEntity entity = modelMapper.map(measurement, MeasurementEntity.class);
        entity = repository.save(entity);

        //TODO - Send this measurement to cloud.
        return getMeasurement(entity);
    }

    public boolean userCanCreateMeasurement(IotoUser user) {
        if(user.getRoles().contains("DEVICE")) {
            return true;
        }
        throw new AccessDeniedException(ErrorCodes.ACCESS_DENIED);
    }

    public Measurement get(String id) {
        MeasurementEntity entity = getEntity(id);
        return getMeasurement(entity);
    }

    public List<Measurement> getByDeviceId(String deviceId) {
        List<MeasurementEntity> entities = repository.findByDeviceId(deviceId);
        return entities.stream()
                .map(this::getMeasurement)
                .collect(Collectors.toList());
    }

    @Transactional
    @PreAuthorize("@measurementService.userCanDeleteMeasurement(#user)")
    public void delete(String id, IotoUser user) {
        MeasurementEntity entity = getEntity(id);
        repository.delete(entity);
    }

    public boolean userCanDeleteMeasurement(IotoUser user) {
        if(user.getRoles().contains("COMPANY_ADMIN")) {
            return true;
        }
        throw new AccessDeniedException(ErrorCodes.ACCESS_DENIED);
    }

    private MeasurementEntity getEntity(String id) {
        Optional<MeasurementEntity> entityOpt = repository.findById(new ObjectId(id));
        if (!entityOpt.isPresent()) {
            throw new NotFoundException(ErrorCodes.MEASUREMENT_NOT_FOUND);
        }
        return entityOpt.get();
    }

    private ZonedDateTime getStartDate(int minute) {
        ZonedDateTime now = LocalDateTime.now().atZone(ZoneId.systemDefault());
        if (now.getMinute() >= minute) {
            return now.withMinute(minute);
        } else {
            return now.withMinute(0);
        }
    }

    private int getRandomNumbers(int low, int high) {
        Random random = new Random();
        int result = random.nextInt(high - low) + low;
        return result;
    }

    private Measurement getMeasurement(MeasurementEntity entity) {
        Measurement measurement = modelMapper.map(entity, Measurement.class);
        String id = entity.get_id().toString();
        measurement.setId(id);
        return measurement;
    }

}
