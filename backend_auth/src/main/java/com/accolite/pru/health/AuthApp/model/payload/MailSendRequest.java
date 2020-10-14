package com.accolite.pru.health.AuthApp.model.payload;

import com.accolite.pru.health.AuthApp.validation.annotation.NullOrNotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ApiModel(value = "invitation Request", description = "invitation request payload")
public class MailSendRequest {

    @NullOrNotBlank(message = "Registration channelId can be null but not blank")
    @ApiModelProperty(value = "A valid channel", allowableValues = "NonEmpty String")
    private String channelId;

    @ApiModelProperty(value = "A valid email", required = true, allowableValues = "NonEmpty String")
    private List<String> email;

}
