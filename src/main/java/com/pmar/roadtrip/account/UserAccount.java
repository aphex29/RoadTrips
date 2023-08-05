package com.pmar.roadtrip.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "UserAccount")
public class UserAccount {

    @Id
    private ObjectId id;

    private String username;

    private String password;

    private String email;


    public UserAccount(ObjectId id, String username, String password, String email){
        this.id=id;
        this.username=username;
        this.password=password;
        this.email = email;
    }

}
