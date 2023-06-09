package com.test.app.payload;

import com.test.app.model.Note;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class AUTHOR_NOTES_VIEW {
    private String author;
    private List<Note> notes = new ArrayList<>(Arrays.asList());
}
