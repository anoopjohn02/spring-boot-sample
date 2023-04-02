package com.anoop.examples.controllers;

import com.anoop.examples.config.InjectIotoUser;
import com.anoop.examples.model.IotoUser;
import com.anoop.examples.model.Measurement;
import com.anoop.examples.services.measurements.MeasurementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("v1/measurements")
public class MeasurementController {

    @Autowired
    private MeasurementService measurementService;

    @PostMapping
    public Measurement send(@InjectIotoUser IotoUser user, @RequestBody Measurement measurement) {
        return measurementService.create(user, measurement);
    }

    @GetMapping
    public List<Measurement> getAll(@InjectIotoUser IotoUser user) {
        return measurementService.getByDeviceId(user.getUserName());
    }

    @GetMapping("/{id}")
    public Measurement get(@PathVariable(value = "id") String id) {
        return measurementService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> delete(@InjectIotoUser IotoUser user,
                                    @PathVariable(value = "id") String id) {
        measurementService.delete(id, user);
        return ResponseEntity.noContent().build();
    }
}
