package com.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.bytebuddy.agent.builder.AgentBuilder;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;

import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "note_id")
    private Long noteid;
    @NotBlank
    @Size(max = 50)
    private String title;
    @NotBlank
    @Size(max = 50)
    @Email
    private String author;
    @NotBlank
    @Size(max = 50)
    private String date;
    @NotBlank
    @Size(max = 16000)
    private String description;
    @Digits(integer = 2,fraction = 2)
    private Double score;
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "note_thought", joinColumns = @JoinColumn(name = "note_id"),
            inverseJoinColumns = @JoinColumn(name = "thought_id"))
    private List<Thought> thoughts = new ArrayList<>();

    public Note(String title, String author, String date, String description, Double score) {
        this.title = title;
        this.author = author;
        this.date = date;
        this.description = description;
        this.score = score;
    }

    public Note(Long note_id, Double score) {
        this.noteid = note_id;
        this.score = score;
    }

    public Note(Long note_id) {
        this.noteid = note_id;
    }
}
