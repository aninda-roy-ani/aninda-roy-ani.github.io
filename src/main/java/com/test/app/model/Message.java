package com.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long message_id;
    @NotNull
    @Digits(integer = 20,fraction = 0)
    private Long time;
    @NotBlank
    @Size(max = 50)
    @Email
    private String sender;
    @NotBlank
    @Size(max = 50)
    @Email
    private String receiver;
    @NotBlank
    @Size(max = 1000)
    private String text;
}
