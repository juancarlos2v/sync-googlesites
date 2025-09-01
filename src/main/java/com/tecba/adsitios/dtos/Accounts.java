package com.tecba.adsitios.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Accounts {

    @JsonProperty("accounts")
    private List<Account> accounts;
    @JsonProperty("nextPageToken")
    private String nextPageToken;

    @Data
    public static class Account {

        @JsonProperty("name")
        private String name;
        @JsonProperty("accountName")
        private String accountName;

    }
}
