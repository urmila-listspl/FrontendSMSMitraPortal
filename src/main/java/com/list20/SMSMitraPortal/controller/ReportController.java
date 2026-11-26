package com.list20.SMSMitraPortal.controller;


import java.util.Map;

import com.list20.filter.InsertLogBilling;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/getTrack")
public class ReportController {
    public ReportController() {
    }
   @PostMapping({"/{reportType}"})
    public ResponseEntity<Object> reportGenerator(@PathVariable("reportType") String reportType,
                                                  @RequestHeader("bankName") String bankName,
                                                  @RequestHeader("userName") String userName,
                                                  @RequestBody Map<String, Object> filter,
                                                  HttpServletRequest request) {
        try {
            int productId = (int) request.getAttribute("productId");
            return ResponseEntity.status(HttpStatus.CREATED).body((new InsertLogBilling()).reportGenerator(bankName, userName,filter, reportType, productId));
        } catch (Exception var5) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You are Failed to Access Generate Report");
        }
    }
}

