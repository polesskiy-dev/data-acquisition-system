package com.polesskiy.rest.controller;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.service.sensor.SensorService;
import com.polesskiy.service.user.UserService;
import com.polesskiy.service.user.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by polesskiy on 23.03.16.
 */

/**
 * REST Sensor controller
 */
@RestController
@RequestMapping("/user/{login}/")
public class SensorController {
    @Autowired
    UserService userService;
    @Autowired
    SensorService sensorService;

    @RequestMapping(value = "/{sensorName}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sensor> getSensor(@PathVariable("login") String login, @PathVariable("sensorName") String sensorName) {
        User user = userService.findUser(login);
        Sensor sensor = sensorService.findSensor(0);//!!!
        if (user!=null && sensor!=null && user.getSensors().contains(sensor)){
                return new ResponseEntity<>(sensor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
}