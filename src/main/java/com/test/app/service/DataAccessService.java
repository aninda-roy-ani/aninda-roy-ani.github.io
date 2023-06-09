package com.test.app.service;

import com.test.app.dao.AccountRepository;
import com.test.app.dao.MessageRepository;
import com.test.app.dao.NoteRepository;
import com.test.app.dao.ThoughtRepository;
import com.test.app.model.Account;
import com.test.app.model.Message;
import com.test.app.model.Note;
import com.test.app.model.Thought;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataAccessService {

    @Autowired
    private AccountRepository accRepo;

    @Autowired
    private NoteRepository noteRepo;

    @Autowired
    private MessageRepository msgRepo;

    @Autowired
    private ThoughtRepository thoughtRepo;

    public Account insertNewAccount(Account insertAccount){
        accRepo.save(insertAccount);
        return insertAccount;
    }

    public Note insertNewNote(Note insertNote){
        noteRepo.save(insertNote);
        return insertNote;
    }

    public List<Note> readNotesByAuthor(String email) {
        return noteRepo.findNotesByAuthor(email);
    }

    public Message insertNewMessage(Message newMsg) {
        System.out.println(newMsg);
        msgRepo.save(newMsg);
        return newMsg;
    }

    public List<Message> readMessagesByEmail(String email, String another) {
        List<Message> messageListBoth = new ArrayList<>(Arrays.asList());
        msgRepo.findMessagesBySenderAndReceiver(email,another).forEach(message -> messageListBoth.add(message));
        msgRepo.findMessagesBySenderAndReceiver(another,email).forEach(message -> messageListBoth.add(message));
        System.out.println(messageListBoth);
        return messageListBoth;
    }

    public List<Note> readNotesByAuthorAndTitle(String email, String noteTitle) {
        return noteRepo.findNotesByAuthorAndTitle(email,noteTitle);
    }

    private Note getNote(Long noteNo) {
        System.out.println(noteNo);
        List<Note> notes = noteRepo.findNotesByNoteid(noteNo);
        if (notes.size() > 0){
            return notes.get(0);
        }
        return new Note();
    }

    public List<Note> updateNote(String noteNo1, Double score, Thought thought) {
        Note note = getNote(Long.parseLong(noteNo1));
        Double finalScore = note.getScore();
        List<Thought> lt = note.getThoughts();
        if (lt.size()>0){
            finalScore = (finalScore*lt.size()+score)/(lt.size()+1);

            System.out.println("----------------------");
            System.out.println(finalScore);
            DecimalFormat decimalFormat = new DecimalFormat("#.##");
            score = Double.valueOf(decimalFormat.format(finalScore));
            System.out.println(score);
            System.out.println("----------------------");
        }
        note.setScore(score);
        thought.setOwner(note.getAuthor());
        note.getThoughts().add(thought);
        thoughtRepo.save(thought);
        noteRepo.save(note);
        return noteRepo.findNotesByAuthor(thought.getInterpreter());
    }

    public List<Account> getSuggestedBuddies(String email) {
        //temporary
        List<Account> all = accRepo.findAll();
        all.removeIf(a->a.getEmail().equals(email));
        return all;
        /*
        In future, checking common address and common buddies of buddies have to be returned
         */
    }
}
