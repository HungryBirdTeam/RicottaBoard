package com.hungrybird.backend.oauth.model.payload;


import com.fasterxml.jackson.annotation.JsonInclude;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponce {

    private final String data;
    private final String type;
    private final String timestamp;



    public TokenResponce(String data, String type) {
        this.data = data;
        this.type = type;
        this.timestamp = Instant.now().toString();
    }

    public String getData() {
        return data;
    }

    public String getType() {
        return type;
    }

    public String getTimestamp() {
        return timestamp;
    }
}
