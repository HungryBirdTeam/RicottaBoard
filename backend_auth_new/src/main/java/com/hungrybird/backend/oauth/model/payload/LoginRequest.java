package com.hungrybird.backend.oauth.model.payload;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@ApiModel(value = "Login Request", description = "The login request payload")
public class LoginRequest {

//    @ApiModelProperty(value = "Registered username", allowableValues = "NonEmpty String", allowEmptyValue = false)
//    private String username;

    @ApiModelProperty(value = "User registered email", required = true, allowableValues = "NonEmpty String")
    private String email;

    @NotNull(message = "Login password cannot be blank")
    @ApiModelProperty(value = "Valid user password", required = true, allowableValues = "NonEmpty String")
    private String password;

//    @Valid
//    @ApiModelProperty(value = "Device info", required = true, dataType = "object", allowableValues = "A valid " +
//            "deviceInfo object")
//    private DeviceInfo deviceInfo;


//    public LoginRequest(String username, String email, String password, DeviceInfo deviceInfo) {
//        this.username = username;
//        this.email = email;
//        this.password = password;
//        this.deviceInfo = deviceInfo;
//    }

    public LoginRequest(String email, String password) {
//        this.username = "username";
        this.email = email;
        this.password = password;
//        this.deviceInfo = new DeviceInfo();
//        deviceInfo.setDeviceId("123");
//        deviceInfo.setDeviceType(DeviceType.DEVICE_TYPE_DESKTOP);
//        deviceInfo.setNotificationToken("123");
    }

    public LoginRequest() {
    }

//    public String getUsername() {
//        return username;
//    }
//
//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public DeviceInfo getDeviceInfo() {
//        return deviceInfo;
//    }
//
//    public void setDeviceInfo(DeviceInfo deviceInfo) {
//        this.deviceInfo = deviceInfo;
//    }
}
