package com.tecba.adsitios.entities;


import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "accounts")
@Data
@Builder
public class Group {

    @Id
    private String id;
    private String name;
    private String accountName;


}
