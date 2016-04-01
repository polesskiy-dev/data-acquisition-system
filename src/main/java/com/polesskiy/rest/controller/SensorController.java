package com.polesskiy.rest.controller;

import com.polesskiy.entity.Sensor;
import com.polesskiy.entity.User;
import com.polesskiy.service.sensor.SensorService;
import com.polesskiy.service.user.UserService;
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

    /**
     * Retrieve sensor by id and users login
     * @param login users login
     * @param sensorID sensors id
     * @return sensor in JSON and/or HTTP status
     */
    @RequestMapping(value = "/{sensorID}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sensor> getSensor(@PathVariable("login") String login, @PathVariable("sensorID") long sensorID) {
        Sensor sensor = sensorService.findSensor(login, sensorID);

        System.out.printf("User %s sensorID %d requested\r\nResult: %s\r\n", login, sensorID, sensor);

        if (sensor != null) {
            return new ResponseEntity<>(sensor, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<Void> createSensor(@RequestBody Sensor sensor, UriComponentsBuilder ucBuilder) {
//        System.out.println("Creating Sensor " + sensor.getId());
//
//        if (userService.isUserExists(user)) {
//            System.out.println("A User with name " + user.getLogin() + " already exist");
//            return new ResponseEntity<>(HttpStatus.CONFLICT);
//        }
//
//        userService.saveUser(user);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(ucBuilder.path("/{login}").buildAndExpand(user.getLogin()).toUri());
//        return new ResponseEntity<>(headers, HttpStatus.CREATED);
//    }
}