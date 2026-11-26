package com.list20.SMSMitraPortal.controller;


import com.list20.SMSMitraPortal.request.SignInRequest;
import com.list20.SMSMitraPortal.request.UserRequest;
import com.list20.SMSMitraPortal.response.JwtResponse;
import com.list20.SMSMitraPortal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/api/auth"})
public class AuthenticationController {
    @Autowired
    UserService userService;

    public AuthenticationController() {
    }

    @PostMapping(
            value = {"/logOn"},
            consumes = {"application/json"}
    )
    public ResponseEntity<String> signUpAdmin(@RequestBody UserRequest userRequest) {
        System.out.println("in controller");
        long id = this.userService.signUp(userRequest).getId();
        return ResponseEntity.status(HttpStatus.CREATED).body("id : " + id);
    }

    @PostMapping(
            value = {"/logIn"},
            consumes = {"application/json"}
    )
    @CrossOrigin
    public ResponseEntity<JwtResponse> signInAdmin(@RequestBody SignInRequest signInRequest) {
        System.out.println("in controller");
        JwtResponse jwtResponse = this.userService.signIn(signInRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(jwtResponse);
    }
}
