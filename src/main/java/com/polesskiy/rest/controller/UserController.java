package com.polesskiy.rest.controller;

import com.polesskiy.entity.User;
import com.polesskiy.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collection;

/**
 * REST User controller
 */
@RestController
public class UserController {
    @Autowired
    UserService userService;

    /*
    Retrieve all users
     */
    @RequestMapping(value = "/users/", method = RequestMethod.GET)
    public ResponseEntity<Collection<User>> listAllUsers() {
        Collection<User> users = userService.getAllUsers();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    /*
    Retrieve Single User
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("login") String login) {
        System.out.println("Fetching User with login " + login);
        User user = userService.findUser(login);
        if (user == null) {
            System.out.println("User with login " + login + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    /*
    Create a User
     */
    @RequestMapping(value = "/new-user/", method = RequestMethod.POST)
    public ResponseEntity<Void> createUser(@RequestBody User user, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating User " + user.getLogin());

        if (userService.isUserExists(user)) {
            System.out.println("A User with name " + user.getLogin() + " already exist");
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }

        userService.saveUser(user);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{login}").buildAndExpand(user.getLogin()).toUri());
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }

    /*
    Update a User
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.PUT)
    public ResponseEntity<User> updateUser(@PathVariable("login") String login, @RequestBody User user) {
        System.out.println("Updating User " + login);

        User currentUser = userService.findUser(login);

        if (currentUser == null) {
            System.out.println("User with login " + login + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        currentUser.setSensors(user.getSensors());
        //...
        // etc, like password and other fields

        userService.editUser(currentUser);
        return new ResponseEntity<>(currentUser, HttpStatus.OK);
    }

    /*
    Delete a User
    */
    @RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("login") String login) {
        System.out.println("Fetching & Deleting User with login " + login);

        User user = userService.findUser(login);
        if (user == null) {
            System.out.println("Unable to delete. User with login " + login + " not found");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        userService.deleteUser(login);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


    /*
    Delete All Users
     */
    @RequestMapping(value = "/users/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("Deleting All Users");

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
