package com.test.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.model.Account;
import com.test.app.model.Message;
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

import java.util.List;

@RequestMapping("/message")
@RestController
public class MessageController {

    @Autowired
    private VerificationService verification;

    @Autowired
    private DataAccessService database;

    @Autowired
    private MainService service;

    @PostMapping("/send")
    public ResponseEntity<Object> sendMessage(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                Message newMsg = service.getInputMsg(inputModel);
                System.out.println(newMsg);
                if (verification.verifyMsgHandlers(newMsg)) {
                    return ResponseEntity.ok(database.insertNewMessage(newMsg));
                }
                return ResponseEntity.badRequest().body("Wrong Handlers Error!");
            }
            return ResponseEntity.badRequest().body("Login Credentials Error!");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Json Processing Error!");
        }
    }

    @PostMapping("/check")
    public ResponseEntity<Object> checkMessage(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                Message newMsg = service.getInputMsg(inputModel);
                if (verification.verifyMsgHandlers(newMsg)) {
                    List<Message> messageList = database.readMessagesByEmail(inputModel.getEmail(),inputModel.getMsgReceiver());
                    return ResponseEntity.ok(service.getMessengerView(inputModel, messageList));
                }
                return ResponseEntity.badRequest().body("Wrong Handlers Error!");
            }
            return ResponseEntity.badRequest().body("Login Credentials Error!");
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body("Json Processing Error!");
        }
    }
}
