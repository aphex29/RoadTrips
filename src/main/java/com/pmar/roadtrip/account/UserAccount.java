package com.pmar.roadtrip.account;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Document(collection = "UserAccount")
public class UserAccount {

    @Id
    private ObjectId id;
    private ObjectId userId;
    private String password;



    public UserAccount(ObjectId id, ObjectId userId, String password){
        this.id=id;
        this.userId = userId;
        this.password=password;
    }


}
