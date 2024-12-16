package net.uway.journalApp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HealthCheck {

    @GetMapping("/Health-check")
    public String Healthcheck(){
        return "ok";
    }

}
