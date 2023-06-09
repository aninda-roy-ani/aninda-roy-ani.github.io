package com.test.app.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.test.app.model.Account;
import com.test.app.model.Note;
import com.test.app.model.Thought;
import com.test.app.payload.AUTHOR_NOTES_VIEW;
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

@RequestMapping("/note")
@RestController
public class NoteController {

    @Autowired
    private VerificationService verification;

    @Autowired
    private DataAccessService database;

    @Autowired
    private MainService service;

    @PostMapping("/create")
    public ResponseEntity<Object> createNote(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Note newNote = service.getInputNote(inputModel);
            if (verification.verifyEmail(newNote.getAuthor())) {
                Note note = database.insertNewNote(newNote);
                return ResponseEntity.ok(note);
            } else {
                return ResponseEntity.badRequest().body("Author Email Error!");
            }
        } catch (JsonProcessingException e){
            return ResponseEntity.badRequest().body("Json Processing Error!");
        }
    }
    
    @PostMapping("/readAll")
    public ResponseEntity<AUTHOR_NOTES_VIEW> readNotes(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                String email = account.getEmail();
                if (verification.verifyEmail(email)){
                    return ResponseEntity.ok(new AUTHOR_NOTES_VIEW(email,database.readNotesByAuthor(email)));
                }
                return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
            }
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        }
    }

    @PostMapping("/read")
    public ResponseEntity<AUTHOR_NOTES_VIEW> readNote(@RequestBody String body){
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                String email = account.getEmail();
                if (verification.verifyEmail(email)){
                    return ResponseEntity.ok(new AUTHOR_NOTES_VIEW(email,
                            database.readNotesByAuthorAndTitle(email,inputModel.getNoteTitle())));
                }
                return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
            }
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        }
    }


    @PostMapping("/update")
    public ResponseEntity<AUTHOR_NOTES_VIEW> updateScoreAndThoughtToNote(@RequestBody String body) {
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            InputModel inputModel = objectMapper.readValue(body, InputModel.class);
            Account account = verification.verifyLoginAccountCredentials(
                    new Account(inputModel.getEmail(),inputModel.getPassword()));
            if (!account.getName().equals("NULL")) {
                String email = account.getEmail();
                if (verification.verifyEmail(email)){
                    return ResponseEntity.ok(new AUTHOR_NOTES_VIEW(email,
                            database.updateNote(inputModel.getNote_no1(),inputModel.getScore(),new Thought("test@t.com",email,inputModel.getThoughtText()))));
                }
                return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
            }
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        } catch (JsonProcessingException e) {
            return ResponseEntity.badRequest().body(new AUTHOR_NOTES_VIEW());
        }
    }

}
