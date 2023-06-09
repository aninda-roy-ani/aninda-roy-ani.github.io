package com.test.app.payload;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor
public class InputModel {
    private String email,password,note_no1,note_no2,noteOwner,noteTitle,noteDate,noteDescription,msgReceiver,msgText,thoughtText;
    private Integer ScorePresentation,ScoreStructure;
    private Long note_id1,note_id2;
    private Double score;
}
