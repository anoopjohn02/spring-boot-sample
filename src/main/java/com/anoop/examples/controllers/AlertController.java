package com.anoop.examples.controllers;

import com.anoop.examples.config.InjectIotoUser;
import com.anoop.examples.model.Alert;
import com.anoop.examples.model.IotoUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@Controller
@RestController
@RequestMapping("v1/alert")
public class AlertController {

  @PostMapping
  public Alert send(@InjectIotoUser IotoUser user, @RequestBody Alert alert) {
    log.info("Alert message from {}", user.getUserId());
    return alert;
  }

  @GetMapping
  public List<Alert> getAll(@InjectIotoUser IotoUser user) {
    return List.of();
  }
}
