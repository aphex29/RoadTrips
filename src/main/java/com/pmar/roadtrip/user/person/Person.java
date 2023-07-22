package com.pmar.roadtrip.user.person;

import com.pmar.roadtrip.route.Route;
import com.pmar.roadtrip.user.User;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import java.util.ArrayList;

@Entity
@Table(name="PERSON_TBL")
public class Person implements User {

    @Id
    @SequenceGenerator(
            name = "person_sequence",
            sequenceName = "person_sequence",
            initialValue=200,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "person_sequence"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "account_id",
            nullable = false,
            updatable = false
    )
    private Long accountId;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @Column(
            name = "username",
            nullable = false
    )
    private String username;

    @Column(
            name = "email",
            nullable = false
    )
    private String email;


    public Person(){}
    public Person(Long accountId, String firstName, String lastName, String username,String email){
        this.accountId = accountId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.email = email;
    }



    public String getUsername() {return username;}

    public String getFirstName() {return firstName;}

    public String getLastName() {return lastName;}

    public String getEmail() {return email;}

    public Long getAccountId(){return accountId;}

    public void setUsername(String newUsername) {username = newUsername;}

    public void setFirstName(String newFirstName) {firstName = newFirstName;}

    public void setLastName(String newLastName) {lastName = newLastName;}


    @Override
    public String toString(){
        return String.format("Person[Name: %s %s, Username: %s, Email: %s]",firstName,lastName,username,email);
    }
}
