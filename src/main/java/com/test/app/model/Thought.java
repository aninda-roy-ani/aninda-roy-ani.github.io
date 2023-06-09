package com.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Thought {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long thought_id;
    @Email
    @NotBlank
    @Size(max = 50)
    private String owner;
    @Email
    @NotBlank
    @Size(max = 50)
    private String interpreter;
    @Size(max = 500)
    private String interpretation;

    public Thought(String interpreter, String interpretation) {
        this.interpreter = interpreter;
        this.interpretation = interpretation;
    }

    public Thought(String owner, String interpreter, String interpretation) {
        this.owner = owner;
        this.interpreter = interpreter;
        this.interpretation = interpretation;
    }
}
