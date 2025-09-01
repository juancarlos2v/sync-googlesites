package com.tecba.adsitios.entities;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

@Document(collection = "sites")
public class Site {

    @Id
    private String id;

    private String title;
    private String address;
    private String postalCode;
    private String province;
    private String country;
    private String state;
    private String category;

    private Map<String, Object> rawData; // resto del JSON
}
