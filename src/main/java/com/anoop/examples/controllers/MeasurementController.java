package com.anoop.examples.controllers;

import com.anoop.examples.config.InjectIotoUser;
import com.anoop.examples.model.IotoUser;
import com.anoop.examples.model.Measurement;
import com.anoop.examples.services.measurements.MeasurementService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
