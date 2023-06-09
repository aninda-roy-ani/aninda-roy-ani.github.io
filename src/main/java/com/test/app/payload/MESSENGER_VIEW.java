package com.test.app.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Component
public class MESSENGER_VIEW {
    private String user;
    private String sender;
    private List<String> messages = new ArrayList<>(Arrays.asList());
}
