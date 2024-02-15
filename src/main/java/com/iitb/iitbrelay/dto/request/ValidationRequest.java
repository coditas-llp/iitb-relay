package com.iitb.iitbrelay.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
