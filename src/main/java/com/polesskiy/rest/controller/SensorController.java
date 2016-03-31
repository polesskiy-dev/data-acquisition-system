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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by polesskiy on 23.03.16.
 */

/**
 * REST Sensor controller
 */
@RestController
@RequestMapping("/users/{login}/")
public class SensorController {
    @Autowired
    UserService userService;
    @Autowired
    SensorService sensorService;


//    private User user = userService.findUser();
//
//
//    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createSensor(@RequestBody Sensor sensor, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating Sensor " + sensor.getName());
//
//        if (sensorService.isSensorExists(sensor)) {
//            System.out.println("A Sensor with name " + sensor.getName() + " already exist");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        sensorService.saveSensor(sensor);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/{name}").buildAndExpand(sensor.getName()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
}