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


    private String username;
    private String email;

    private String password;
}
