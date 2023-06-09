package com.test.app.service;

import com.test.app.model.Message;
import com.test.app.model.Note;
import com.test.app.payload.AUTHOR_NOTES_VIEW;
import com.test.app.payload.InputModel;
import com.test.app.payload.MESSENGER_VIEW;
import com.test.app.payload.NOTE_VIEW;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class MainService {


    private NOTE_VIEW getNoteView(Note note) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat view = new SimpleDateFormat("MMM dd, yyyy");
        String dated = "", score = "0.0";
        List<String> noteThoughts = new ArrayList<>(Arrays.asList());
        try {
            Date date = format.parse(note.getDate());
            dated = view.format(date);
        } catch (ParseException e) {
            dated = "Jan 01,1990";
        }
        if (note.getScore() > 0.0){
            score = note.getScore().toString();
        }
        if (!note.getThoughts().isEmpty() || note.getThoughts() != null){
            note.getThoughts().forEach(t -> noteThoughts.add(t.getInterpreter()+ " : "+t.getInterpretation()));
        }
        return new NOTE_VIEW(note.getNoteid(), note.getTitle(),dated,note.getDescription(),score,noteThoughts);
    }

    public Message getInputMsg(InputModel inputModel) {
        return new Message(null, Instant.now().toEpochMilli(),
                inputModel.getEmail(), inputModel.getMsgReceiver(), inputModel.getMsgText()
        );
    }

    public Note getInputNote(InputModel inputModel) {
        return new Note(inputModel.getNoteTitle(),
                inputModel.getEmail(), LocalDate.now().toString(),inputModel.getNoteDescription(),
                0.0);
    }

    public MESSENGER_VIEW getMessengerView(InputModel inputModel, List<Message> messageList) {
        MESSENGER_VIEW MESSENGER = new MESSENGER_VIEW();
        MESSENGER.setUser(inputModel.getEmail());
        MESSENGER.setSender(inputModel.getMsgReceiver());
        sortMessages(messageList).forEach(m -> MESSENGER.getMessages().add(m.getSender()+" : "+m.getText()+"  ["
                + LocalDateTime.ofInstant(Instant.ofEpochMilli(m.getTime()), ZoneId.systemDefault())+"]"
        ));
        return MESSENGER;
    }

    private List<Message> sortMessages(List<Message> messageList) {
        List<Long> msgTimes = new ArrayList<>(Arrays.asList());
        messageList.forEach(m -> msgTimes.add(m.getTime()));
        
        for (int j=0; j<msgTimes.size(); j++){
            boolean isSorted = true;
            for (int i=0; i<msgTimes.size()-j-1; i++){
                if (msgTimes.get(i+1).compareTo(msgTimes.get(i)) < 0){
                    Long tempTime = msgTimes.get(i);
                    msgTimes.set(i, msgTimes.get(i+1));
                    msgTimes.set(i+1,tempTime);

                    Message tempText = messageList.get(i);
                    messageList.set(i, messageList.get(i+1));
                    messageList.set(i+1, tempText);
                    isSorted = false;
                }
            }
            if (isSorted) break;
        }
        System.out.println(messageList);
        return messageList;
    }

    public String getBirthday(String bday) {
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        SimpleDateFormat view = new SimpleDateFormat("MMM dd, yyyy");
        try {
            Date date = format.parse(bday);
            return view.format(date);
        } catch (ParseException e) {
            return "Jan 01, 1990";
        }
    }


}
