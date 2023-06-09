package com.test.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
public class NOTE_VIEW {
    private Long note_no;
    private String title;
    private String date;
    private String description;
    private String score;
    private List<String> thoughts = new ArrayList<>(Arrays.asList());
}
