package com.pmar.roadtrip.account;

import jakarta.persistence.*;

@Entity
@Table(name="USER_ACC_TBL")
public class UserAccount {

    @Id
    @SequenceGenerator(
            name = "user_acc_sequence",
            sequenceName = "user_acc_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_acc_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    @Column(
            name = "password",
            nullable = false
    )
    private String password;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;

    public UserAccount(){}

    public UserAccount(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
