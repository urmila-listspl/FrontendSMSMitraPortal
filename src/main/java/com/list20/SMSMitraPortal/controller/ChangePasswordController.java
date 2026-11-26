package com.list20.SMSMitraPortal.controller;


import java.util.Map;

import com.list20.SMSMitraPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(
        origins = {"http://localhost:8085"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
public class ChangePasswordController {
    @Autowired
    UserService userService;

    public ChangePasswordController() {
    }

    @PostMapping(
            value = {"/changePassword"},
            consumes = {"application/json"}
    )
    public ResponseEntity<String> signUpAdmin(@RequestBody Map<String, String> changePasswordRequest) {
        System.out.println("in controller");
        String passwordUpdatedResponse = this.userService.changePassword(changePasswordRequest);
        return ResponseEntity.status(HttpStatus.OK).body(passwordUpdatedResponse);
    }
}
