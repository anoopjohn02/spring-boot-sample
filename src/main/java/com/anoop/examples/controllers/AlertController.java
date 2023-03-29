package com.anoop.examples.controllers;

import com.anoop.examples.config.InjectIotoUser;
import com.anoop.examples.model.Alert;
import com.anoop.examples.model.IotoUser;
import com.anoop.examples.services.AlertService;
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
@RequestMapping("v1/alerts")
public class AlertController {

  @Autowired
  private AlertService alertService;

  @PostMapping
  public Alert send(@InjectIotoUser IotoUser user, @RequestBody Alert alert) {
    return alertService.create(alert, user);
  }

  @GetMapping
  public List<Alert> getAll(@InjectIotoUser IotoUser user) {
    return alertService.getByDeviceId(user.getUserId());
  }

  @GetMapping("/{id}")
  public Alert get(@PathVariable(value = "id") String id) {
    return alertService.get(id);
  }

  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public ResponseEntity<?> delete(@InjectIotoUser IotoUser user,
                                  @PathVariable(value = "id") String id) {
    alertService.delete(id, user);
    return ResponseEntity.noContent().build();
  }
}
