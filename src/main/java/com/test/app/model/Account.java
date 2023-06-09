package com.test.app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long account_id;
    @NotBlank
    @Size(max = 50)
    @Email
    private String email;
    @NotBlank
    @Size(max = 50)
    private String password;
    @NotNull
    @Size(max = 20)
    private Long phone;
    @NotBlank
    @Size(max = 100)
    private String address;
    @NotBlank
    @Size(max = 100)
    private String name;
    @NotBlank
    @Size(max = 50)
    private String birthday;
    @NotBlank
    @Size(max = 500)
    private String image;
    /*
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "account_account", joinColumns = @JoinColumn(name = "account_id"),
            inverseJoinColumns = @JoinColumn(name = "account_id"))
    List<Account> friendList = new ArrayList<>(Arrays.asList());

     */

    public Account(String email, String password, Long phone, String address, String name, String birthday, String image) {
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.address = address;
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }

    public Account(String email, String password, String name, String birthday, String image) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.image = image;
    }

    public Account(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Account(String name) {
        this.name = name;
    }
}
/*
    @OneToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "friends_mapper",joinColumns = @JoinColumn(name = "accountid"),
            inverseJoinColumns = @JoinColumn(name = "accountid"))
    private List<Account> friends = new ArrayList<>(Arrays.asList());
}

 */
