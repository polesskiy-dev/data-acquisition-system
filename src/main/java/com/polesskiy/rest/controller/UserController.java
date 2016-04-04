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
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService userService;

    /**
     * Retrieve all users.
     *
     * @return all users in JSON and/or HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<User>> listAllUsers() {
        Collection<User> users = userService.getAllUsers();
        if (!users.isEmpty()) {
            return new ResponseEntity<>(users, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieve user by login.
     *
     * @param login requested user login
     * @return user in JSON and/or HTTP status
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getUser(@PathVariable("login") String login) {
        System.out.println("Fetching User with login " + login);
        User user = userService.findUser(login);
        if (user != null) {
            return new ResponseEntity<>(user, HttpStatus.OK);
        }
        System.out.println("User with login " + login + " not found");
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Create new user.
     * Save to DB, create new URI user location.
     *
     * @param user      JSON serialized user
     * @param ucBuilder
     * @return HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    /**
     * Update user in DB
     *
     * @param user JSON serialized updated user
     * @return updated user in JSON and/or HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> editUser(@RequestBody User user) {
        System.out.printf("User for updating: %s\r\n", user);

        if (userService.isUserExists(user)) {
            userService.editUser(user);
            return new ResponseEntity<>(user, HttpStatus.OK);
        }

        System.out.printf("User with login: %s not found in DB\r\n", user.getLogin());
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * Delete user by login
     *
     * @param login user to be deleted login
     * @return HTTP status
     */
    @RequestMapping(value = "/{login}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("login") String login) {
        System.out.printf("Fetching & Deleting User with login: %s\r\n ", login);

        User user = userService.findUser(login);
        if (user != null) {
            userService.deleteUser(login);
            return new ResponseEntity<>(HttpStatus.OK);
        }

        System.out.printf("Unable to delete. User with login: %s not found\r\n", login);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    /**
     * Delete all existing users
     *
     * @return HTTP status
     */
    @RequestMapping(value = "/", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteAllUsers() {
        System.out.println("!!! Deleting All Users !!!");

        userService.deleteAllUsers();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
