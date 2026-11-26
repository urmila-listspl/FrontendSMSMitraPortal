package com.list20.SMSMitraPortal.controller;


import java.sql.SQLException;

import com.list20.filter.InsertLogBilling;
import com.list20.response.DashBoardResponse;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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
public class DashBoardController {
    public DashBoardController() {
    }

    @GetMapping({"/portal"})
    public DashBoardResponse getAllData(@RequestHeader("bankName") String bankName) throws SQLException, ClassNotFoundException {
        return InsertLogBilling.getApiCountAndData(bankName);
    }
}
