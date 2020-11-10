package com.hungrybird.backend.oauth.model.payload;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.time.Instant;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResponce {

    private final String data;
    private final Boolean success;
    private final String timestamp;
    private final String cause;
    private final String path;

    public ApiResponce(String data, Boolean success, String timestamp, String cause, String path) {
        this.data = data;
        this.success = success;
        this.timestamp = timestamp;
        this.cause = cause;
        this.path = path;
    }

    public ApiResponce(Boolean success, String data) {
        this.data = data;
        this.success = success;
        this.timestamp = Instant.now().toString();
        this.cause = null;
        this.path = null;
    }

    public String getData() {
        return data;
    }

    public Boolean getSuccess() {
        return success;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public String getCause() {
        return cause;
    }

    public String getPath() {
        return path;
    }
}
