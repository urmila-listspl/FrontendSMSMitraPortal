package com.list20.SMSMitraPortal.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.list20.filter.InsertLogBilling;
import com.list20.pojo.BankDetails;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/bank"})
@CrossOrigin(
        origins = {"http://localhost:8085"},
        allowedHeaders = {"*"},
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.OPTIONS}
)
public class BankController {
    private static final String UPLOAD_DIR = "images";

    public BankController() {
    }

//    @PostMapping(
//            value = {"/registerBank"},
//            consumes = {"application/json"}
//    )
//    public ResponseEntity<Object> onBoardBank(@RequestHeader("bankName") String bankName, @RequestBody BankDetails bankDetails) {
//        String base64Image = bankDetails.getBankLogo();
//
//        try {
//            (new InsertLogBilling()).onBoardBank(bankDetails);
//            String sanitizedBankName = bankName.replaceAll("[^a-zA-Z0-9-_\\.]", "_");
//            Map<String, String> responseMap = new HashMap();
//            responseMap.put("bankOnBoardResponse", "Bank onboarded successfully.....");
//            return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
//        } catch (Exception var6) {
//            var6.printStackTrace();
//            return ResponseEntity.badRequest().body("Something went wrong, please try again.....");
//        }
//    }
@PostMapping(value = "/registerBank", consumes = "application/json")
//            consumes = {"application/json", "application/json;charset=UTF-8"})
//            , consumes = "multipart/form-data")
public ResponseEntity<Object> onBoardBank(
//            @RequestHeader("bankName") String bankName,
        @RequestBody BankDetails bankDetails)
//                    ("bankDetails") String bankDetails)// Receive as String and parse later
//            @RequestPart("file") MultipartFile file)
{
    try {

//            System.out.println("Received file: " + file.getOriginalFilename());

        // Convert bankDetails from JSON string to Map
//            ObjectMapper objectMapper = new ObjectMapper();
//            Map<String, Object> bankDetailsMap = objectMapper.readValue(bankDetails, new TypeReference<Map<String, Object>>() {});

        new InsertLogBilling().onBoardBank(bankDetails);

//            File uploadDir = new File(UPLOAD_DIR);
//            if (!uploadDir.exists()) {
//                uploadDir.mkdirs();
//            }

        // Save the file to the upload directory
//            String filePath = UPLOAD_DIR + bankName + "_" + file.getOriginalFilename();
//            File dest = new File(filePath);
//            file.transferTo(dest);
        Map<String, String> responseMap= new HashMap<>();
        responseMap.put("bankOnBoardResponse","Bank onboarded successfully.....");
        return ResponseEntity.status(HttpStatus.CREATED).body(responseMap);
    } catch (Exception e) {
        e.printStackTrace();
        return ResponseEntity.badRequest().body("Something went wrong, please try again.....");
    }
}

    @PatchMapping({"/updateMaxHitLimit"})
    public ResponseEntity<String> updateMaxHitLimit(@RequestParam String bankName, @RequestParam String serviceName, @RequestParam int hits) throws SQLException, ClassNotFoundException {
        boolean maxLimitUpdated = (new InsertLogBilling()).updateMaxHitLimit(serviceName, bankName, hits);
        return maxLimitUpdated ? ResponseEntity.status(HttpStatus.OK).body("Bank max limit updated for service " + serviceName + ".....") : ResponseEntity.badRequest().body("Something went wrong, please try again.....");
    }

    @PatchMapping({"/updateComplementoryLimit"})
    public ResponseEntity<String> updateComplementoryLimit(@RequestParam String bankName, @RequestParam int hits) throws SQLException, ClassNotFoundException {
        boolean maxLimitUpdated = (new InsertLogBilling()).updateComplementoryLimit(bankName, hits);
        return maxLimitUpdated ? ResponseEntity.status(HttpStatus.OK).body("Bank Complementory plan limit updated.....") : ResponseEntity.badRequest().body("Something went wrong, please try again.....");
    }

    @PatchMapping({"/topup"})
    public ResponseEntity<String> topup(@RequestParam String bankName, @RequestParam double topup, @RequestParam int planId) throws SQLException, ClassNotFoundException {
        String topUpResponse = (new InsertLogBilling()).updateTopup(bankName, topup, planId);
        return ResponseEntity.status(HttpStatus.OK).body(topUpResponse);
    }
}
