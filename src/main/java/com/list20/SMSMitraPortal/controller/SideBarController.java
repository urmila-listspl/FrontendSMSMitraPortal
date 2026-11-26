package com.list20.SMSMitraPortal.controller;

import java.sql.SQLException;
import java.util.List;

import com.list20.filter.InsertLogBilling;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
@CrossOrigin(
        origins = {"http://localhost:8085"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class SideBarController {
    public SideBarController() {
    }

    @GetMapping({"/populateSidebar"})
    public List<String> getAllServices() throws SQLException, ClassNotFoundException {
        return (new InsertLogBilling()).getAllChargableServies();
    }
}
