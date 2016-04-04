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
     *
     * @param login    owner user login
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

    /**
     * Create new sensor
     * Save to DB, create new URI sensor location.
     *
     * @param login     owner user login
     * @param sensor    sensor to create
     * @param ucBuilder
     * @return HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createSensor(@PathVariable("login") String login, @RequestBody Sensor sensor, UriComponentsBuilder ucBuilder) {

        //update sensor
        sensor.setOwnerUser(userService.findUser(login));
        sensor.setId(0);

        System.out.printf("Creating Sensor %s\r\n ", sensor);

        sensorService.saveSensor(sensor);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(sensor.getId()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /**
     * Update sensor in DB
     *
     * @param login  owner user login
     * @param sensor sensor to edit
     * @return updated sensor in JSON and/or HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Sensor> editSensor(@PathVariable("login") String login, @RequestBody Sensor sensor) {
        System.out.printf("Sensor for updating: %s\r\n", sensor);

        if (sensorService.findSensor(login, sensor.getId()) != null) {
            sensor.setOwnerUser(userService.findUser(login));

            sensorService.editSensor(sensor);
            return new ResponseEntity<>(sensor, HttpStatus.OK);
        }

        System.out.printf("Sensor with ID: %d not found in DB\r\n", sensor.getId());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete sensor from DB
     *
     * @param login    owner user login
     * @param sensorID sensor for deleting ID
     * @return
     */
    @RequestMapping(value = "/{sensorID}", method = RequestMethod.DELETE)
    public ResponseEntity<Sensor> deleteSensor(@PathVariable("login") String login, @PathVariable("sensorID") long sensorID) {
        System.out.printf("Fetching & Deleting sensor with ID: %s\r\n ", sensorID);

        if (sensorService.findSensor(login, sensorID) != null) {
            sensorService.deleteSensor(sensorID);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        System.out.printf("Unable to delete. User with login: %s not found\r\n", login);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}