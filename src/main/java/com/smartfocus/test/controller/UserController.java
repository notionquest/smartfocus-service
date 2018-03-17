package com.smartfocus.test.controller;

import com.smartfocus.test.model.User;
import com.smartfocus.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.logging.Logger;

@RestController("/user")
public class UserController {

    private Logger LOG = Logger.getLogger(getClass().getName());

    @Autowired
    @Qualifier("userService")
    private UserService userService;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getUsers() {
        LOG.info("Get users");
        return userService.getUsersDb();
    }

}