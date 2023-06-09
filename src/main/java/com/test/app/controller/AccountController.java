package com.test.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.model.Account;
import com.test.app.payload.InputModel;
import com.test.app.service.DataAccessService;
import com.test.app.service.MainService;
import com.test.app.service.VerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequestMapping("/account")
@RestController
public class AccountController {

    @Autowired
    private VerificationService verification;
    
    @Autowired
    private DataAccessService database;

    @Autowired
    private MainService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createAccount(@Valid @RequestBody Account newAccount){
        if (verification.verifyEmail(newAccount.getEmail())){
            return ResponseEntity.badRequest().body("Email Already Exists Error!");
        }
        database.insertNewAccount(newAccount);
        return ResponseEntity.ok(newAccount);
    }

    @PostMapping("/read")
    public ResponseEntity<Account> readAccount(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                //account.setBirthday(service.getBirthday(account.getBirthday()));
                return ResponseEntity.ok(account);
            }
            return ResponseEntity.badRequest().body(new Account());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new Account());
        }
    }
}
