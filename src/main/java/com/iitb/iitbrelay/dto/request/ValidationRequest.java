package com.iitb.iitbrelay.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class ValidationRequest {

    @JsonProperty("input_APPID")
    private Long appId;
    @JsonProperty("input_RequestID")
    private String reqId;

    @JsonProperty("input_UserID")
    private Long userId;

    @JsonProperty("input_Amount")
    private Double amount;

}
